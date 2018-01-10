package cn.web.takeout.controller;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IUserService;
import cn.web.takeout.util.CommenUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    /**
     * 登录验证
     * @param userId
     * @param password
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/checkUser")
    public String selectUser(String userId, String password, HttpSession session) throws IOException {
        User user = userService.checkUser(userId,password);
        String url;
        if(user != null){//验证成功，跳转页面
            session.setAttribute("user",user);
            url = "redirect:/stage/index.jsp";
        }else{//验证失败，重新登录
            url = "redirect:/login.jsp";
        }
        return url;
    }

    /**
     * 注册
     * @param user
     * @return
     * @throws IOException
     */
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

    /**
     * 修改用户资料(带头像)
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public User updateUser(@RequestParam("file") MultipartFile file,User user,HttpSession session) throws Exception{
        String message;
        String userName = user.getUserName();
        String fileName = file.getOriginalFilename();
        if(!file.isEmpty()) {
            //获取文件后缀名
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            message = userName +"-"+ CommenUtil.getUUID32() + "." + prefix;//现在的文件名是时间戳加原文件名，出现图片相同时，读取不出来的bug
            String realPath = session.getServletContext().getRealPath("/upload/");//将文件保存在当前工程下的一个upload文件
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, message));//将文件的输入流输出到一个新的文件
            message="upload/"+message;
            user.setHeadPic(message);
        }
        userService.updateUser(user);
        session.setAttribute("user",user);
        return user;
    }

    @RequestMapping("/updateUserNotFile")
    @ResponseBody
    public User updateUserNotFile(User user,HttpSession session) throws Exception{
        User history = userService.selectUser(user.getId());
        userService.updateUser(user);
        user.setCreateTime(history.getCreateTime());
        session.setAttribute("user",user);
        return user;
    }
}
