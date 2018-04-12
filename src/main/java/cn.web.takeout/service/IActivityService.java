package cn.web.takeout.service;

import cn.web.takeout.model.Activity;
import cn.web.takeout.model.Comment;
import cn.web.takeout.model.Shop;
import cn.web.takeout.vo.ActivityVO;
import cn.web.takeout.vo.ShopVO;

import java.util.List;

public interface IActivityService {
    /**
     * 通过主键id获取评价
     * @param id
     * @return
     * @throws Exception
     */
    Activity selectActivity(String id) throws Exception;

    /**
     * 插入单条评价
     * @return
     * @throws Exception
     */
    long insertActivity(String name, String lowLine, Integer discount, String shopId) throws Exception;

    /**
     * 获取商店的活动
     * @param shopId
     * @return
     * @throws Exception
     */
    List<ActivityVO> getShopActivity(String shopId) throws Exception;

    /**
     * 设置商店的活动类型
     * @param shop
     * @param type
     * @return
     * @throws Exception
     */
    Shop setShopAcitityType(Shop shop,String type) throws Exception;
    /**
     * 设置商店的活动类型
     * @param shop
     * @param type
     * @return
     * @throws Exception
     */
    ShopVO setShopAcitityType(ShopVO shop, String type) throws Exception;

}
