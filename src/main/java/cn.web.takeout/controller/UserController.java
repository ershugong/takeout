package cn.web.takeout.controller;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping("/checkUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("id");
        String password = request.getParameter("password");
        //User user = userService.selectUser(Long.parseLong(userId));
        User user = userService.checkUser(userId,password);
        if(user != null){//验证成功，跳转页面
            response.getWriter().write(1);
        }else{//验证失败，重新登录
            response.getWriter().write(0);
        }
        response.getWriter().close();
    }
}
