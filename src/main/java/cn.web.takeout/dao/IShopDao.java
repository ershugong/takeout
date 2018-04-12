package cn.web.takeout.dao;

import cn.web.takeout.model.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     List<Shop> getAllShop();

    /**
     * 店铺排序
     * @return
     */
     List<Shop> orderShop(@Param("orderKey") String orderKey);

    /**
     * 通过类型筛选商店
     * @param map
     * @return
     */
     List<Shop> termShop(Map<String,Object> map);

    /**
     * 更新商店的活动类型
     * @param shop
     * @return
     * @throws Exception
     */
    long updateShopActivityType(Shop shop) throws Exception;

    /**
     * 筛选活动的商店
     * @param map
     * @return
     * @throws Exception
     */
    List<Shop> getActivityShop(Map<String,Object> map) throws Exception;

}
