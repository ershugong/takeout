package cn.web.takeout.dao;

import cn.web.takeout.model.Activity;
import cn.web.takeout.model.Comment;

import java.util.List;
import java.util.Map;

public interface IActivityDao {
    /**
     * 通过主键id获取评价
     * @param id
     * @return
     * @throws Exception
     */
    Activity selectActivity(String id) throws Exception;

    /**
     * 插入单条评价
     * @param activity
     * @return
     * @throws Exception
     */
    long insertActivity(Activity activity) throws Exception;

    /**
     * 获取商店的活动
     * @param shopId
     * @return
     * @throws Exception
     */
    Activity getShopActivity(String shopId) throws Exception;

    /**
     * 更新商店活动
     * @param activity
     * @return
     * @throws Exception
     */
    long updateShopActivity(Activity activity) throws Exception;

}
