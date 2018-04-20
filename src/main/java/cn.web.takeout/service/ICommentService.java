package cn.web.takeout.service;

import cn.web.takeout.model.Comment;

import java.util.List;

public interface ICommentService {
    /**
     * 通过主键id获取评价
     * @param id
     * @return
     * @throws Exception
     */
    Comment selectComment(String id) throws Exception;

    /**
     * 插入单条评价
     * @return
     * @throws Exception
     */
    long insertComment(String userId,String menuId,String comment,String userName,String commentType) throws Exception;

    /**
     * 通过商店获取其所有的评价
     * @param shopId
     * @return
     * @throws Exception
     */
    List<Comment> searchCommentByShopId (String shopId) throws Exception;

    /**
     * 通过店铺获取其所有评价
     * @param shopId
     * @return
     * @throws Exception
     */
    public List getCommentListForPC(String shopId,Integer page) throws Exception;

    /**
     * 用户端获取商店所有的用户评价
     * @param shopId
     * @return
     * @throws Exception
     */
    List getShopAllComments(String shopId) throws Exception;
}
