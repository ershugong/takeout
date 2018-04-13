package cn.web.takeout.service.impl;

import cn.web.takeout.dao.*;
import cn.web.takeout.model.*;
import cn.web.takeout.service.IActivityService;
import cn.web.takeout.service.IOrderService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.vo.*;
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
    @Resource
    private IAddressDao addressDao;
    @Resource
    private IActivityService activityService;


    @Override
    public List<Order> selectOrder(String userId) {
        return orderDao.selectOrder(userId);
    }

    @Override
    public OrderVO getNotBuyMenus(Map<String, Object> map) throws Exception{
        OrderVO orderVO = new OrderVO();
        int totalPrice = 0;
        int sendPrice = 0;
        List<Order> orderList = orderDao.getNotBuyOrder(map);
        for(Order order : orderList){
            totalPrice = totalPrice+order.getPrice()*order.getNumb();//计算总价
        }


        //计算活动
        List<ActivityVO> activityVOList = activityService.getShopActivity(orderList.get(0).getShopId());
        Shop shop = shopDao.selectShop(orderList.get(0).getShopId());
        if(activityVOList.size() > 0){
            ActivityVO activityVO = activityVOList.get(0);//只有一个
            String lowLine = activityVO.getType1()+","+activityVO.getType2()+","+activityVO.getType3();
            ShopVO shopVO = new ShopVO();
            activityService.setShopAcitityType(shopVO,lowLine,activityVO.getDiscount());

            //通过活动计算总价
            if(shopVO.getActivitys().contains(0)){//满减
                if(totalPrice > Integer.parseInt(activityVO.getType1())){
                    totalPrice -= Integer.parseInt(activityVO.getType2());
                }
            }

            if(!"0".equals(activityVO.getType3()) && totalPrice > Integer.parseInt(activityVO.getType3())){//有免邮活动

            }else{
                totalPrice += shop.getSendPrice();
                sendPrice = shop.getSendPrice();
            }

            if(shopVO.getActivitys().contains(2)){//折扣
                totalPrice = totalPrice * 9 / 10;
            }

            orderVO.setActivities(shopVO.getActivitys());
        }else{
            orderVO.setActivities(new ArrayList<>());
        }

        orderVO.setTotalPrice(totalPrice);
        orderVO.setOrders(orderList);
        orderVO.setSendPrice(sendPrice);
        return orderVO;
    }

    @Override
    public List<Order> getOnlyNotBuyMenus(Map<String, Object> map) throws Exception {
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
    public List<OrderForShopVO> getOrderByShopId(String shopId,Integer page) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("shopId",shopId);
        map.put("start",(page-1)*CommenUtil.COMMENT_PAGE5);
        map.put("num",5);
        List<Order> orderList = orderDao.getOrderByshopId(map);
        int pages = orderDao.getOrderPageNum(shopId);
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
                Address address = addressDao.selectAddress(order.getAddressId());
                vo.setUserName(address.getUserName());
                vo.setDetailPlace(address.getProvince()+address.getCity()+address.getArea()+address.getDetailPlace());
                result.add(vo);
            }
            //获取又未提醒的订单
            Map<String,Object> remindMap = new HashMap<>();
            remindMap.put("shopId",shopId);
            remindMap.put("remind",CommenUtil.NOT_REMIND);
            List<Order> list = this.getNotRemindOrder(remindMap);
            if(list != null && list.size() > 0){
                result.get(0).setIsRemind(1);
            }
            if(result.size()>0){
                result.get(0).setPages(pages);
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

    @Override
    public List<Menu> getCart(Map<String, Object> map) throws Exception {
        List<Order> orderList = orderDao.getCart(map);
        List<Menu> result = new ArrayList<>();
        if(orderList != null){
            Menu menu;
            for(Order order : orderList){
               menu  = new Menu();
               menu.setId(order.getMenuId());
               Menu temp = menuDao.selectMenu(order.getMenuId());
               menu.setHeadPic(temp.getHeadPic());
               menu.setName(order.getMenuName());
               menu.setNumb(order.getNumb());
               menu.setPrice(temp.getPrice());
               menu.setShopId(temp.getShopId());
               result.add(menu);
            }
        }
        return result;
    }

    @Override
    public long addCart(Map<String, Object> map) throws Exception {
        return orderDao.addCart(map);
    }

    @Override
    public long removeCart(Map<String, Object> map) throws Exception {
        Order order = orderDao.getOrderByUserIdAndMenuId(map);
        if(order.getNumb() == 1){//删除
            orderDao.delOrder(order.getId());
        }else{
            orderDao.removeCart(map);
        }
        return 1;
    }

    @Override
    public Order getOrderByUserIdAndMenuId(Map<String, Object> map) throws Exception {
        return orderDao.getOrderByUserIdAndMenuId(map);
    }

    @Override
    public List<Order> getNotRemindOrder(Map<String, Object> map) throws Exception {
        List<Order> orderList =  orderDao.getNotRemindOrder(map);//返回参数以后 就把状态改成已提醒
        if(orderList!=null){
            for (Order order : orderList){
                Map<String,Object> orderMap = new HashMap<>();
                orderMap.put("id",order.getId());
                orderMap.put("remind",CommenUtil.IS_REMIND);
                orderDao.updateOrderRemind(orderMap);
            }
        }
        return orderList;
    }

    @Override
    public int countOrderNumByShopForMonth(Map<String, Object> map) throws Exception {
        return orderDao.countOrderNumByShopForMonth(map);
    }


}
