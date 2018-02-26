package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IMenuDao;
import cn.web.takeout.dao.IOrderDao;
import cn.web.takeout.dao.IShopDao;
import cn.web.takeout.dao.IUserDao;
import cn.web.takeout.model.Menu;
import cn.web.takeout.model.Order;
import cn.web.takeout.model.Shop;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IOrderService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.vo.DetailSingleOrderVO;
import cn.web.takeout.vo.OrderForShopVO;
import cn.web.takeout.vo.OrderListVO;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("orderService")
public class IOrderServiceImpl implements IOrderService {
    @Resource
    private IOrderDao orderDao;
    @Resource
    private IMenuDao menuDao;
    @Resource
    private IShopDao shopDao;
    @Resource
    private IUserDao userDao;


    @Override
    public List<Order> selectOrder(String userId) {
        return orderDao.selectOrder(userId);
    }

    @Override
    public List<Order> getNotBuyMenus(Map<String, Object> map) {
        return orderDao.getNotBuyOrder(map);
    }

    @Override
    public long updateOrderNumb(Map<String, Object> map) {
        return orderDao.updateOrderNumb(map);
    }

    @Override
    public long insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    @Override
    public long updateNotBuyOrder(Map<String, Object> map) {
        return orderDao.updateNotBuyOrder(map);
    }

    @Override
    public List<OrderListVO> getAllOrders(String userId) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("status",CommenUtil.NOT_BUY);
        List<Order> orderList = orderDao.getAllOrders(map);

        Map<String,OrderListVO> tempMap = new LinkedHashMap<>();

        List<OrderListVO> result = new ArrayList<>();
        OrderListVO orderListVO;
        if(null != orderList){
            for (Order order : orderList){
                if(tempMap.containsKey(order.getOrderId())){
                    tempMap.get(order.getOrderId()).getOrder().add(order);
                    //添加当前菜色订单的金额（单价*数量）
                    Integer price = tempMap.get(order.getOrderId()).getPrice() + (order.getPrice() * order.getNumb());
                    tempMap.get(order.getOrderId()).setPrice(price);
                }else{
                    orderListVO = new OrderListVO();
                    List<Order> tempOrderList = new ArrayList<>();
                    tempOrderList.add(order);
                    orderListVO.setShopName(order.getShopName());
                    orderListVO.setDate(order.getCreateTime());
                    orderListVO.setMenuName(order.getMenuName());
                    orderListVO.setPrice(order.getPrice() * order.getNumb());
                    orderListVO.setState(order.getStatus());
                    orderListVO.setOrderId(order.getOrderId());
                    orderListVO.setOrder(tempOrderList);
                    Shop shop = shopDao.selectShop(order.getShopId());
                    User user = userDao.selectUser(shop.getShoperId());
                    orderListVO.setPhone(user.getPhone());
                    orderListVO.setMenuHeadPic(shop.getLogo());
                    tempMap.put(order.getOrderId(),orderListVO);
                }
            }

            for(String key : tempMap.keySet()){
                result.add(tempMap.get(key));
            }
        }
        return result;
    }

    @Override
    public List getDetailOrders(String orderId) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("status",CommenUtil.ALREADLY_BUY);
        map.put("orderId",orderId);
        List<Order> orderList = orderDao.getDetailOrders(map);
        List<DetailSingleOrderVO> result = new ArrayList<>();

        if(null != orderList){
            DetailSingleOrderVO orderVO;
            for(Order order : orderList){
                orderVO = new DetailSingleOrderVO();
                orderVO.setDate(order.getCreateTime().substring(0,10));
                Menu menu = menuDao.selectMenu(order.getMenuId());
                orderVO.setMenuHeadPic(menu.getHeadPic());
                orderVO.setMenuName(menu.getName());
                orderVO.setPrice(order.getPrice());
                orderVO.setState(CommenUtil.ALREADLY_BUY);
                orderVO.setMenuId(order.getMenuId());
                Shop shop = shopDao.selectShop(menu.getShopId());
                orderVO.setShopName(shop.getShopName());
                result.add(orderVO);
            }
        }


        return result;
    }

    @Override
    public long delOrderAccount(Map<String, Object> map) throws Exception {
        return orderDao.delOrderAccount(map);
    }

    @Override
    public List<OrderForShopVO> getOrderByShopId(String shopId) throws Exception {
        List<Order> orderList = orderDao.getOrderByshopId(shopId);
        List<OrderForShopVO> result = new ArrayList<>();
        if(null != orderList){
            OrderForShopVO vo;
            for(Order order : orderList){
                vo = new OrderForShopVO();
                vo.setId(order.getId());
                vo.setExt(order.getExt());
                Menu menu = menuDao.selectMenu(order.getMenuId());
                vo.setMenuHeadPic(menu.getHeadPic());
                vo.setMenuName(menu.getName());
                vo.setNumb(order.getNumb());
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public long updateOrderStatusByShop(String orderId,String status) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);
        map.put("orderId",orderId);
        return orderDao.updateOrderStatusByShop(map);
    }


}
