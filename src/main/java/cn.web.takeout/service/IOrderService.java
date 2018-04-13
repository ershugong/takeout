package cn.web.takeout.service;

import cn.web.takeout.model.Menu;
import cn.web.takeout.model.Order;
import cn.web.takeout.vo.OrderForShopVO;
import cn.web.takeout.vo.OrderListVO;
import cn.web.takeout.vo.OrderVO;

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
    OrderVO getNotBuyMenus(Map<String,Object> map) throws Exception;

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
    List<OrderForShopVO> getOrderByShopId(String shopId,Integer page) throws Exception;

    /**
     * 商家派送/取消订单
     * @param orderId
     * @return
     * @throws Exception
     */
    long updateOrderStatusByShop(String orderId,String status) throws Exception;

    /**
     * 购物车
     * @param map
     * @return
     * @throws Exception
     */
    List<Menu> getCart(Map<String,Object> map) throws Exception;

    /**
     * 购物车添加
     * @param map
     * @return
     * @throws Exception
     */
    long addCart(Map<String,Object> map) throws Exception;

    /**
     * 购物车删除
     * @param map
     * @return
     * @throws Exception
     */
    long removeCart(Map<String,Object> map) throws Exception;

    /**
     * 通过用户id和菜色id获取订单
     * @param map
     * @return
     * @throws Exception
     */
    Order getOrderByUserIdAndMenuId(Map<String,Object> map) throws Exception;

    /***
     * 查询店铺未提醒的订单
     * @param map
     * @return
     * @throws Exception
     */
    List<Order> getNotRemindOrder(Map<String,Object> map) throws Exception;


    /**
     * 获取商店的月售额
     * @param map
     * @return
     * @throws Exception
     */
    int countOrderNumByShopForMonth(Map<String,Object> map) throws Exception;
}
