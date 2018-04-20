package cn.web.takeout.service.impl;

import cn.web.takeout.dao.ICommentDao;
import cn.web.takeout.dao.IMenuDao;
import cn.web.takeout.model.Comment;
import cn.web.takeout.model.Menu;
import cn.web.takeout.service.ICommentService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.vo.CommentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("commentService")
public class CommentServiveImpl implements ICommentService{
    @Resource
    private ICommentDao commentDao;
    @Resource
    private IMenuDao menuDao;

    @Override
    public Comment selectComment(String id) throws Exception {
        return commentDao.selectComment(id);
    }

    @Override
    public long insertComment(String userId,String menuId,String comment,String userName,String commentType) throws Exception {
        Comment commentPO = new Comment();
        commentPO.setId(CommenUtil.getUUID32());
        commentPO.setContent(comment);
        commentPO.setUserId(userId);
        Menu menu = menuDao.selectMenu(menuId);
        commentPO.setShopId(menu.getShopId());
        commentPO.setMenuId(menu.getId());
        commentPO.setUserName(userName);
        commentPO.setCreateTime(new Date());
        commentPO.setCommentType(commentType);
        return commentDao.insertComment(commentPO);
    }

    @Override
    public List<Comment> searchCommentByShopId(String shopId) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("shopId",shopId);
        return commentDao.searchCommentByShopId(map);
    }

    @Override
    public List getCommentListForPC(String shopId,Integer page) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("shopId",shopId);
        map.put("start",(page-1)*CommenUtil.COMMENT_PAGE5);
        map.put("num",5);
        List<Comment> commentList = commentDao.searchCommentByShopId(map);
        List<CommentVO> result = new ArrayList<>();
        if(null != commentList){
            CommentVO vo;
            int index = 0;
            for (Comment comment : commentList){
                index +=1;
                vo = new CommentVO();
                vo.setCommentType(comment.getCommentType());
                vo.setCreateTime(CommenUtil.FormatDate(comment.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                Menu menu = menuDao.selectMenu(comment.getMenuId());
                vo.setMenuHeadPic(menu.getHeadPic());
                vo.setMenuName(menu.getName());
                vo.setUserName(comment.getUserName());
                vo.setContent(comment.getContent());
                result.add(vo);
            }
            if(result.size()>0){
                result.get(0).setNum(commentDao.getCommentPageNum(shopId));//总共的数目
            }
        }
        return result;
    }

    @Override
    public List getShopAllComments(String shopId) throws Exception {
        List<Comment> comments = searchCommentByShopId(shopId);
        List<CommentVO> result = new ArrayList<>();
        if(comments != null){
            for(Comment comment : comments){
                CommentVO commentVO = new CommentVO();
                commentVO.setUserName(comment.getUserName());
                commentVO.setContent(comment.getContent());
                commentVO.setUserHeadPic(comment.getUserHeadPic());
                long hoursNum = 1000*60*60;//一小时的毫秒数
                long hour = (new Date().getTime() - comment.getCreateTime().getTime())/hoursNum;
                if(hour == 0){
                    hour = 1;
                }else if(hour > 24){
                    hour = 24;
                }else{
                    hour += 1;
                }
                commentVO.setHours(hour);
                result.add(commentVO);
            }
        }
        return result;
    }
}
