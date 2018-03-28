package cn.web.takeout.controller;

import cn.web.takeout.model.Comment;
import cn.web.takeout.service.ICommentService;
import cn.web.takeout.util.EmojiFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private ICommentService commentService;

    @ResponseBody
    @RequestMapping("/selectComment")
    public Comment selectComment(String id) throws Exception{
        Comment comment = commentService.selectComment(id);
        return comment;
    }

    @ResponseBody
    @RequestMapping("/searchCommentByShopId")
    public List<Comment> searchCommentByShopId(String shopId) throws Exception{
        return commentService.searchCommentByShopId(shopId);
    }

    @ResponseBody
    @RequestMapping("/insertComment")
    public List insertComment(String userId, String menuId, String content, String userName,String commentType) throws Exception{
        content = new String(content.getBytes("iso8859-1"),"UTF-8");//GET请求参数包含中文需要转码
        userName = new String(userName.getBytes("iso8859-1"),"UTF-8");//GET请求参数包含中文需要转码
        if(EmojiFilter.containsEmoji(userName)){//过滤特殊的表情
            userName = EmojiFilter.filterEmoji(userName);
        }
        commentService.insertComment(userId,menuId,content,userName,commentType);
        return new ArrayList();
    }

    @ResponseBody
    @RequestMapping("/getCommentListForPC")
    public List getCommentListForPC(String shopId,Integer page) throws Exception{
       List list = commentService.getCommentListForPC(shopId,page);
       return list;
    }

}
