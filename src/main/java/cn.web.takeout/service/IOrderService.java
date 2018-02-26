package cn.web.takeout.service;

import cn.web.takeout.model.Order;
import cn.web.takeout.vo.OrderForShopVO;
import cn.web.takeout.vo.OrderListVO;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    /**
     * 通过主键id获取订单信息（单个）
     * @param userId
     * @return
     */
    List<Order> selectOrder(String userId);

    /**
     * 获取未结算的订单
     * @param map
     * @return
     */
    List<Order> getNotBuyMenus(Map<String,Object> map);

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
     * @param userId
     * @return
     */
    List<OrderListVO> getAllOrders(String userId) throws Exception;

    /**
     * 通过订单号获取该订单的详情
     * @param orderId
     * @return
     * @throws Exception
     */
    public List getDetailOrders(String orderId) throws Exception;

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
    List<OrderForShopVO> getOrderByShopId(String shopId) throws Exception;

    /**
     * 商家派送/取消订单
     * @param orderId
     * @return
     * @throws Exception
     */
    long updateOrderStatusByShop(String orderId,String status) throws Exception;
}
