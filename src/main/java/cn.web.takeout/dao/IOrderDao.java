package cn.web.takeout.dao;

import cn.web.takeout.model.Menu;
import cn.web.takeout.model.Order;
import cn.web.takeout.vo.DetailSingleOrderVO;

import java.util.List;
import java.util.Map;

public interface IOrderDao {
    /**
     * 通过用户id获取订单信息
     * @param userId
     * @return
     */
    List<Order> selectOrder(String userId);

    /**
     * 获取未结算的订单
     * @param map
     * @return
     */
    List<Order> getNotBuyOrder(Map<String,Object> map);

    /**
     * 更新订单数量
     * @param map
     * @return
     */
    long updateOrderNumb(Map<String,Object> map);

    /**
     * 插入订单
     * @param order
     * @return
     */
    long insertOrder(Order order);

    /**
     * 修改订单为已付款
     * @param map
     * @return
     */
    long updateNotBuyOrder(Map<String,Object> map);

    /**
     * 获取该用户的所有订单
     * @param map
     * @return
     */
    List<Order> getAllOrders(Map<String,Object> map);

    /**
     * 获取详细订单菜色
     * @param map
     * @return
     * @throws Exception
     */
    List<Order> getDetailOrders(Map<String,Object> map) throws Exception;

    /**
     * 购物车减少某件商品
     * @param map
     * @return
     * @throws Exception
     */
    long delOrderAccount(Map<String,Object> map) throws Exception;

    /**
     * 获取该商店的所有订单
     * @param shopId
     * @return
     * @throws Exception
     */
    List<Order> getOrderByshopId(String shopId) throws Exception;

    /**
     * 商家派送/取消订单
     * @param map
     * @return
     * @throws Exception
     */
    long updateOrderStatusByShop(Map<String,Object> map) throws Exception;
}
