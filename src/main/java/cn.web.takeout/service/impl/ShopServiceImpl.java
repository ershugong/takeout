package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IShopDao;
import cn.web.takeout.model.Shop;
import cn.web.takeout.service.IShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("shopService")
public class ShopServiceImpl implements IShopService{
    @Resource
    private IShopDao shopDao;
    @Override
    public Shop selectShop(String id) {
        return shopDao.selectShop(id);
    }

    @Override
    public long insertShop(Shop shop) {
        return shopDao.insertShop(shop);
    }

    @Override
    public long updateShop(Shop shop) {
        return shopDao.updateShop(shop);
    }
}
