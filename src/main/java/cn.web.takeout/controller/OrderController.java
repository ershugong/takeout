package cn.web.takeout.controller;

import cn.web.takeout.model.Menu;
import cn.web.takeout.model.Order;
import cn.web.takeout.model.Shop;
import cn.web.takeout.service.IAddressService;
import cn.web.takeout.service.IMenuService;
import cn.web.takeout.service.IOrderService;
import cn.web.takeout.service.IShopService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.vo.AddressAndMenu;
import cn.web.takeout.vo.AddressVO;
import cn.web.takeout.vo.DetailSingleOrderVO;
import cn.web.takeout.vo.OrderListVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private IOrderService orderService;
    @Resource
    private IMenuService menuService;
    @Resource
    private IShopService shopService;
    @Resource
    private IAddressService addressService;

    /**
     * 结算
     * @param
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/settleAccouts")
    public List settleAccouts(String userId,String menuId) throws Exception{
        /**
         * 购物车功能
         * 1.先获取当前没有结算的订单，如果菜色相同，则删除
         * 2.添加新的订单
         * 3.不用返回参数
         */
        String OrderId = CommenUtil.getUUID32();

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("status", CommenUtil.NOT_BUY);

        List<Order> previousOrders = orderService.getNotBuyMenus(map);
        if(previousOrders != null){
            for (Order order : previousOrders){
                OrderId = order.getOrderId();
                if(order.getMenuId().equals(menuId)){//只需要添加数量，同样的菜色
                    Map<String,Object> orderMap = new HashMap<>();
                    orderMap.put("id",order.getId());
                    orderMap.put("numb",order.getNumb() + 1);
                    orderMap.put("price",order.getPrice());
                    orderService.updateOrderNumb(orderMap);
                    return new ArrayList();
                }
            }
        }

        Order order = new Order();
        order.setId(CommenUtil.getUUID32());
        order.setUserId(userId);
        order.setMenuId(menuId);
        Menu menu = menuService.selecMenu(menuId);
        order.setStatus(CommenUtil.NOT_BUY);
        order.setCreateTime(CommenUtil.FormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        Shop shop = shopService.selectShop(menu.getShopId());
        order.setShopId(menu.getShopId());
        order.setShopName(shop.getShopName());
        order.setPrice(menu.getPrice());
        order.setOrderId(OrderId);
        order.setMenuName(menu.getName());
        order.setNumb(CommenUtil.MENU_NUMB_INIT1);
        orderService.insertOrder(order);
        return new ArrayList();
    }

    /**
     * 结算
     * @param userId
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/toBuy")
    public List toBuy(String userId) throws Exception{
        //获取未结算的订单，修改金额
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("status",CommenUtil.NOT_BUY);
        map.put("targetStatus",CommenUtil.ALREADLY_BUY);
        orderService.updateNotBuyOrder(map);

        //发送通知到商家派送

        return new ArrayList();
    }

    /**
     * 获取该用户的所有订单
     * @param userId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getAllOrders")
    public List getAllOrders(String userId) throws Exception{
        List<OrderListVO> result = orderService.getAllOrders(userId);
        return result;
    }

    /**
     * 获取该订单的所有菜单
     * @param orderId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getDetailOrders")
    public List getDetailOrders(String orderId) throws Exception{
        List<DetailSingleOrderVO> result = orderService.getDetailOrders(orderId);
        return result;
    }

    /**
     * 购物车移除商品
     * @param userId
     * @param menuId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delOrderAccount")
    public List delOrderAccount(String userId,String menuId,String shopId) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("status",CommenUtil.NOT_BUY);
        map.put("userId",userId);
        map.put("shopId",shopId);
        map.put("menuId",menuId);
        orderService.delOrderAccount(map);
        return new ArrayList();
    }

    /**
     * 获取该商店的所有订单
     * @param shopId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getOrderByShopId")
    public List getOrderByShopId(String shopId) throws Exception{
        return orderService.getOrderByShopId(shopId);
    }

    /**
     * 商家派送/取消订单
     */
    @ResponseBody
    @RequestMapping("/updateOrderStatusByShop")
    public List updateOrderStatusByShop(String orderId,String type) throws Exception{
        String status;
        if("1".equals(type)){
            status = CommenUtil.ORDER_SEND;
        }else{
            status = CommenUtil.ORDER_CANCEL;
        }
        orderService.updateOrderStatusByShop(orderId,status);
        return new ArrayList();
    }

    @ResponseBody
    @RequestMapping("/getDefaultAddressAndOrders")
    public AddressAndMenu getDefaultAddressAndOrders(String userId) throws Exception{
        AddressAndMenu result = new AddressAndMenu();
        AddressVO defaultAddress = addressService.getAddressByUserId(userId,CommenUtil.DEFAULT_ADDRESS).get(0);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("status", CommenUtil.NOT_BUY);
        List<Order> orderList = orderService.getNotBuyMenus(map);
        result.setAddressVO(defaultAddress);
        result.setOrderList(orderList);
        return result;
    }

}
