package cn.web.takeout.dao;

import cn.web.takeout.model.Comment;

import java.util.List;
import java.util.Map;

public interface ICommentDao {
    /**
     * 通过主键id获取评价
     * @param id
     * @return
     * @throws Exception
     */
    Comment selectComment(String id) throws Exception;

    /**
     * 插入单条评价
     * @param comment
     * @return
     * @throws Exception
     */
    long insertComment(Comment comment) throws Exception;

    /**
     * 通过商店获取其所有的评价
     * @param map
     * @return
     * @throws Exception
     */
    List<Comment> searchCommentByShopId (Map<String,Object> map) throws Exception;

    /**
     * 获取商店的评论数目
     * @param shopId
     * @return
     * @throws Exception
     */
    Integer getCommentPageNum(String shopId) throws Exception;
}
