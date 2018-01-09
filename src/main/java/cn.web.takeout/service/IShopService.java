package cn.web.takeout.service;

import cn.web.takeout.model.Shop;

public interface IShopService {
    Shop selectShop(String id);
    long insertShop(Shop shop);
    long updateShop(Shop shop);
}
