package cn.web.takeout.dao;

import cn.web.takeout.model.Shop;

import java.util.List;

public interface IShopDao {
    /**
     * 通过主键选择商店
     * @param id
     */
    Shop selectShop(String id);

    /**
     * 新增店铺
     * @param shop
     * @return
     */
    long insertShop(Shop shop);

    /**
     * 修改店铺
     * @param shop
     * @return
     */
    long updateShop(Shop shop);

    /**
     * 获取所有的商店
     * @return
     */
    public List<Shop> getAllShop();

}
