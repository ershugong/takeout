package cn.web.takeout.controller;

import cn.web.takeout.model.Menu;
import cn.web.takeout.service.IMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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


}
