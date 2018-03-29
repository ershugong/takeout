package cn.web.takeout.controller;

import cn.web.takeout.model.Shop;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IOrderService;
import cn.web.takeout.service.IShopService;
import cn.web.takeout.service.IUserService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.util.GetLatAndLngByBaidu;
import cn.web.takeout.vo.ShopVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Resource
    private IShopService shopService;
    @Resource
    private IUserService userService;
    @Resource
    private IOrderService orderService;

    @ResponseBody
    @RequestMapping("/updateShop")
    public Shop updateShop(@RequestParam("file") MultipartFile file,Shop shop, HttpSession session) throws Exception{
        Shop history = shopService.selectShop(shop.getId());
        User user = userService.selectUser(history.getShoperId());
        String message;
        String fileName = file.getOriginalFilename();
        if(!file.isEmpty()) {
            //获取文件后缀名
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            message = user.getUserName() +"-shop-"+ CommenUtil.getUUID32() + "." + prefix;//现在的文件名是时间戳加原文件名，出现图片相同时，读取不出来的bug
            String realPath = session.getServletContext().getRealPath("/upload/");//将文件保存在当前工程下的一个upload文件
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, message));//将文件的输入流输出到一个新的文件
            message="upload/"+message;
            shop.setLogo(message);
        }
        shopService.updateShop(shop);
        session.setAttribute("shop",shop);
        return shop;
    }


    @RequestMapping("/updateShopNotFile")
    @ResponseBody
    public Shop updateShopNotFile(Shop shop,HttpSession session) throws Exception{
        shopService.updateShop(shop);
        session.setAttribute("shop",shop);
        return shop;
    }

    @ResponseBody
    @RequestMapping("/selectShop")
    public Shop selectShop(String id,HttpSession session) throws Exception{
        Shop shop = shopService.selectShop(id);
        session.setAttribute("shop",shop);
        return shop;
    }

    @ResponseBody
    @RequestMapping("/getAllShop")
    public List<ShopVO> getAllShop(@RequestParam("latitude")Double latitude,@RequestParam("longitude")Double longitude) throws Exception{
        List<ShopVO> result = new ArrayList<ShopVO>();
        List<Shop> shops = shopService.getAllShop();
        result = addDistance(shops,latitude,longitude);
       return result;
    }

    @ResponseBody
    @RequestMapping("/orderShop")
    public List<ShopVO> orderShop(@RequestParam("orderKey") String orderKey,
                                  @RequestParam("latitude")Double latitude,@RequestParam("longitude")Double longitude) throws Exception{
        if("3".equals(orderKey)){//起送价最低
            orderKey = CommenUtil.SHOP_LOW_SEND;
        }else if("4".equals(orderKey)){//配送费最低
            orderKey = CommenUtil.SHOP_SEND_PRICE;
        }else if("2".equals(orderKey)){//评分最高

        }else if("1".equals(orderKey)){//速度最快

        }else{//综合排序

        }

        List<Shop> shopList = shopService.orderShop(orderKey);
        List<ShopVO> shopVOS = addDistance(shopList,latitude,longitude);//添加距离的参数
        return shopVOS;
    }

    @ResponseBody
    @RequestMapping("/termShop")
    public List<ShopVO> termShop(@RequestParam("shopType")String shopType,
                                 @RequestParam("latitude")Double latitude,@RequestParam("longitude")Double longitude) throws Exception{
        List<Shop> shopList = shopService.termShop(shopType);
        List<ShopVO> result = addDistance(shopList,latitude,longitude);
        return result;
    }


    /**
     * 给商店添加距离的信息
     * @param shops
     * @param latitude
     * @param longitude
     * @return
     * @throws Exception
     */
    private List<ShopVO> addDistance(List<Shop> shops,Double latitude,Double longitude) throws Exception{
        List<ShopVO> result = new ArrayList<ShopVO>();
        ShopVO shopVO;
        for(Shop shop : shops){
            shopVO = new ShopVO();
            BeanUtils.copyProperties(shopVO,shop);//转换
            //计算距离
            double distance = GetLatAndLngByBaidu.getDistance(shop.getLongitude(),shop.getLatitude(),longitude,latitude);
            BigDecimal b   =   new   BigDecimal(distance/1000);
            distance  =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();
            shopVO.setDistance(distance);
            //计算菜餐到达时间是按 配餐30分钟固定，餐送员按40km/h的骑行速度
            Double tempTime = 30 + distance/40000*60;
            int time = tempTime.intValue();
            shopVO.setTime(time);

            //添加月售数目
            Date date  = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH,1);//本月开始第一天
            Date startTime = calendar.getTime();
            calendar.setTime(startTime);
            calendar.add(Calendar.MONTH,1);
            Date endTime = calendar.getTime();
            Map<String,Object> map = new HashMap<>();
            map.put("startTime",startTime);
            map.put("endTime",endTime);
            map.put("shopId",shop.getId());
            int sales = orderService.countOrderNumByShopForMonth(map);
            shopVO.setSales(sales);
            result.add(shopVO);
        }
        return  result;
    }

}
