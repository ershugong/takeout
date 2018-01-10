package cn.web.takeout.controller;

import cn.web.takeout.model.Shop;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IShopService;
import cn.web.takeout.service.IUserService;
import cn.web.takeout.util.CommenUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Resource
    private IShopService shopService;
    @Resource
    private IUserService userService;

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

    @ResponseBody
    @RequestMapping("/updateShopNotFile")
    public Shop updateShopNotFile(Shop shop,HttpSession session) throws Exception{
        //Shop history = shopService.selectShop(shop.getId());
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
}
