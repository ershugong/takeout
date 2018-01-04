package cn.web.takeout.controller;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping("/checkUser")
    public String selectUser(String userId,String password,RedirectAttributes attributes) throws IOException {
        User user = userService.checkUser(userId,password);
        String url;
        if(user != null){//验证成功，跳转页面
            attributes.addAttribute("user",user);
            url = "redirect:/stage/index.jsp";
        }else{//验证失败，重新登录
            url = "redirect:/login.jsp";
        }
        return url;
    }

    @RequestMapping("/registerUser")
    public String registerUser(@ModelAttribute("form") User user) throws IOException{
        long result = userService.registerUser(user);
        String url;
        if(result == 1){//注册成功
            url = "redirect:/login.jsp";
        }else{//注册失败
            url = "redirect:/register.jsp";
        }
        return url;
    }
}
