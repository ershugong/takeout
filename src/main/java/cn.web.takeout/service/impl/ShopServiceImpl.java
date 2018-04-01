package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IShopDao;
import cn.web.takeout.model.Shop;
import cn.web.takeout.service.IShopService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.util.GetLatAndLngByBaidu;
import cn.web.takeout.vo.ShopVO;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
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
    public List<ShopVO> orderShop(String orderKey, List<ShopVO> shopVOList) {
        if("3".equals(orderKey)){//起送价最低
            for(int i=0;i<shopVOList.size()-1;i++){
                for(int j=0;j<shopVOList.size()-1-i;j++){
                    if(shopVOList.get(j).getLowSend()>shopVOList.get(j+1).getLowSend()){
                        ShopVO temp = shopVOList.get(j);
                        shopVOList.set(j,shopVOList.get(j+1));
                        shopVOList.set(j+1,temp);
                    }
                }
            }
        }else if("4".equals(orderKey)){//配送费最低
            for(int i=0;i<shopVOList.size()-1;i++){
                for(int j=0;j<shopVOList.size()-1-i;j++){
                    if(shopVOList.get(j).getSendPrice()>shopVOList.get(j+1).getSendPrice()){
                        ShopVO temp = shopVOList.get(j);
                        shopVOList.set(j,shopVOList.get(j+1));
                        shopVOList.set(j+1,temp);
                    }
                }
            }
        }else if("2".equals(orderKey)){//评分最高

        }else if("1".equals(orderKey)){//速度最快
            for(int i=0;i<shopVOList.size()-1;i++){
                for(int j=0;j<shopVOList.size()-1-i;j++){
                    if(shopVOList.get(j).getTime()>shopVOList.get(j+1).getTime()){
                        ShopVO temp = shopVOList.get(j);
                        shopVOList.set(j,shopVOList.get(j+1));
                        shopVOList.set(j+1,temp);
                    }
                }
            }
        }else if("5".equals(orderKey)){//销量最高
            for(int i=0;i<shopVOList.size()-1;i++){
                for(int j=0;j<shopVOList.size()-1-i;j++){
                    if(shopVOList.get(j).getSales()<shopVOList.get(j+1).getSales()){
                        ShopVO temp = shopVOList.get(j);
                        shopVOList.set(j,shopVOList.get(j+1));
                        shopVOList.set(j+1,temp);
                    }
                }
            }
        }else if("6".equals(orderKey)){//距离
            for(int i=0;i<shopVOList.size()-1;i++){
                for(int j=0;j<shopVOList.size()-1-i;j++){
                    if(shopVOList.get(j).getDistance()>shopVOList.get(j+1).getDistance()){
                        ShopVO temp = shopVOList.get(j);
                        shopVOList.set(j,shopVOList.get(j+1));
                        shopVOList.set(j+1,temp);
                    }
                }
            }
        }
        else{//综合排序

        }
        return shopVOList;
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
