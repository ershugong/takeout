package cn.web.takeout.controller;

import cn.web.takeout.model.Menu;
import cn.web.takeout.model.Shop;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IMenuService;
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
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;

    @RequestMapping("/selectMenu")
    public Menu selectMenu(String id, HttpSession session) throws Exception{
        Menu menu = menuService.selecMenu(id);
        session.setAttribute("menu",menu);
        return menu;
    }

    @ResponseBody
    @RequestMapping("/getAllMenu")
    public List<Menu> getAllMenu(String shopId) throws Exception{
        List<Menu> menus = menuService.getAllMenu(shopId);//获取菜单
        return menus;
    }

    @ResponseBody
    @RequestMapping("/updateMenu")
    public Menu updateMenu(@RequestParam("file") MultipartFile file, Menu menu, HttpSession session) throws Exception{
        String message;
        String fileName = file.getOriginalFilename();
        if(!file.isEmpty()) {
            //获取文件后缀名
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            message = "menu-"+ CommenUtil.getUUID32() + "." + prefix;//现在的文件名是时间戳加原文件名，出现图片相同时，读取不出来的bug
            String realPath = session.getServletContext().getRealPath("/upload/");//将文件保存在当前工程下的一个upload文件
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, message));//将文件的输入流输出到一个新的文件
            message="upload/"+message;
            menu.setHeadPic(message);
        }
        Menu history = menuService.selecMenu(menu.getId());
        if(history == null){
            menu.setId(CommenUtil.getUUID32());
            menuService.insertMenu(menu);
        }else{
            menu.setId(history.getId());
            menuService.updateMenu(menu);
        }
        return menu;
    }

    @ResponseBody
    @RequestMapping("/updateMenuNotFile")
    public Menu updateMenuNotFile(Menu menu,HttpSession session) throws Exception{
        Menu history = menuService.selecMenu(menu.getId());
        if(history != null){
            menu.setId(history.getId());
            menu.setHeadPic(history.getHeadPic());
            menu.setCreateTime(history.getCreateTime());
            menu.setStatus(history.getStatus());
            menu.setShopId(history.getShopId());
            menuService.updateMenu(menu);
        }else{
            User user = (User)session.getAttribute("user");
            menu.setShopId(user.getShopId());
            menu.setId(CommenUtil.getUUID32());
            menuService.insertMenu(menu);
        }
        return menu;
    }


}
