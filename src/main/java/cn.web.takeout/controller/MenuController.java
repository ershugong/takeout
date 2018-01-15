package cn.web.takeout.controller;

import cn.web.takeout.model.Menu;
import cn.web.takeout.service.IMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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


}
