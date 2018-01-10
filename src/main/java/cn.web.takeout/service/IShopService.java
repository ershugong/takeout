package cn.web.takeout.service;

import cn.web.takeout.model.Shop;

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
}
