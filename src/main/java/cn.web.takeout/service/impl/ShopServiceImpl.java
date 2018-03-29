package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IShopDao;
import cn.web.takeout.model.Shop;
import cn.web.takeout.service.IShopService;
import cn.web.takeout.util.GetLatAndLngByBaidu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String address = shop.getAddress();
        Map<String,Double> place = GetLatAndLngByBaidu.getLngAndLat(address);
        shop.setLatitude(place.get("lat"));//插入纬度
        shop.setLongitude(place.get("lng"));//插入经度
        return shopDao.updateShop(shop);
    }

    @Override
    public List<Shop> getAllShop() {
       return shopDao.getAllShop();
    }

    @Override
    public List<Shop> orderShop(String orderKey) {
        return shopDao.orderShop(orderKey);
    }

    @Override
    public List<Shop> termShop(String shopType) {
        Map<String,Object> map = new HashMap<>();
        if(shopType != null){
            map.put(shopType,1);
        }
        return shopDao.termShop(map);
    }


}
