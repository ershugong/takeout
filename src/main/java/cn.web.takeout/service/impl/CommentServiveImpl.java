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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        return commentDao.searchCommentByShopId(shopId);
    }

    @Override
    public List getCommentListForPC(String shopId) throws Exception {
        List<Comment> commentList = commentDao.searchCommentByShopId(shopId);
        List<CommentVO> result = new ArrayList<>();
        if(null != commentList){
            CommentVO vo;
            for (Comment comment : commentList){
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
        }
        return result;
    }
}
