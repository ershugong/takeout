package cn.web.takeout.service;

import cn.web.takeout.model.Shop;

import java.util.List;

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
    List<Shop> orderShop(String orderKey);

    /**
     * 通过类型筛选商店
     * @param shopType
     * @return
     */
    List<Shop> termShop(String shopType);
}
