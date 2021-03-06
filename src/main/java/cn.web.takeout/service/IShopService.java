package cn.web.takeout.service;

import cn.web.takeout.model.Shop;
import cn.web.takeout.vo.AccountResultVO;
import cn.web.takeout.vo.ShopVO;

import java.util.List;
import java.util.Map;

public interface IShopService {
    /**
     * 通过主键选择
     * @param id
     * @return
     */
    Shop selectShop(String id);

    /**
     * 插入商店信息
     * @param shop
     * @return
     */
    long insertShop(Shop shop);

    /**
     * 更新商店的信息
     * @param shop
     * @return
     */
    long updateShop(Shop shop);

    /**
     * 获取所有的商店
     * @return
     */
    List<Shop> getAllShop();

    /**
     * 店铺排序
     * @return
     */
    List<ShopVO> orderShop(String orderKey, List<ShopVO> shopVOList);

    /**
     * 通过类型筛选商店
     * @param shopType
     * @return
     */
    List<Shop> termShop(String shopType);

    /**
     * 获取商家和商店信息
     * @param shopId
     * @return
     * @throws Exception
     */
    List getShopAndShoper(String shopId) throws Exception;

    /**
     * 筛选活动的商店
     * @param type
     * @return
     * @throws Exception
     */
    List<Shop> getActivityShop(String type) throws Exception;

    /**
     * 获取店铺的类型 销售情况
     */
    AccountResultVO getShopAccount(String shopId) throws Exception;
}
