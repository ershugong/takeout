package cn.web.takeout.controller;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IUserService;
import cn.web.takeout.util.AesCbcUtil;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.util.HttpRequest;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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

    @ResponseBody
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)
    public Map decodeUserInfo(String encryptedData, String iv, String code) {

        Map map = new HashMap();

        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx7bd6b7c90344d3d1";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "0653eb97df3fe67795c99704cd4fe5f3";
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.fromObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }
}
