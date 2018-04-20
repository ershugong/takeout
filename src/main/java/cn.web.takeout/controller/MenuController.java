package cn.web.takeout.controller;

import cn.web.takeout.model.*;
import cn.web.takeout.service.IAddressService;
import cn.web.takeout.service.ICommentService;
import cn.web.takeout.service.IMenuService;
import cn.web.takeout.service.IOrderService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.vo.AddressVO;
import cn.web.takeout.vo.MenuListVO;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;
    @Resource
    private IOrderService orderService;
    @Resource
    private ICommentService commentService;

    @RequestMapping("/selectMenu")
    public Menu selectMenu(String id, HttpSession session) throws Exception{
        Menu menu = menuService.selecMenu(id);
        session.setAttribute("menu",menu);
        return menu;
    }

    @ResponseBody
    @RequestMapping("/getAllMenusByShop")
    public List<Menu> getAllMenusByShop(String shopId) throws Exception{
        List<Menu> menus = menuService.getAllMenu(shopId);//获取菜单
        return menus;
    }


    @ResponseBody
    @RequestMapping("/getAllMenu")
    public List<MenuListVO> getAllMenu(@RequestParam("shopId") String shopId,String userId) throws Exception{
        List<MenuListVO> result = new ArrayList<MenuListVO>();
        List<Menu> menus = menuService.getAllMenu(shopId);//获取菜单
        Map<String,List<Menu>> menuMap = new HashMap<>();
        List<Menu> singleMenu;
        for(Menu menu : menus){
            if(menuMap.containsKey(menu.getType())){
                menuMap.get(menu.getType()).add(menu);
            }else{
                singleMenu = new ArrayList<>();
                singleMenu.add(menu);
                menuMap.put(menu.getType(),singleMenu);
            }
        }

        for(String key : menuMap.keySet()){
            MenuListVO menuListVO = new MenuListVO();
            menuListVO.setTypeName(key);
            menuListVO.setMenuContent(menuMap.get(key));
            result.add(menuListVO);
        }

        if(result.size() > 0){
            //获取当前用户待结算的订单
            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("status",CommenUtil.NOT_BUY);
            map.put("shopId",shopId);
            List<Order> NotPayOrders = orderService.getOnlyNotBuyMenus(map);
            if(NotPayOrders != null){
                Integer cost = 0;
                for (Order order : NotPayOrders){
                    cost += order.getPrice()*order.getNumb();
                }
                result.get(0).setCost(cost);
            }else{
                result.get(0).setCost(0);
            }

            //插入评论信息
            result.get(0).setCommentList(commentService.getShopAllComments(shopId));
        }

        return result;
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
            User user = (User)session.getAttribute("user");
            menu.setShopId(user.getShopId());
            menu.setId(CommenUtil.getUUID32());
            menuService.insertMenu(menu);
        }else{
            menu.setCreateTime(history.getCreateTime());
            menu.setStatus(history.getStatus());
            menu.setShopId(history.getShopId());
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

    @RequestMapping("/delMenu")
    public void delMenu(String id, HttpServletResponse response)throws Exception{
        long result = menuService.delMenu(id);
        PrintWriter pw = response.getWriter();
        if(result == 1){//删除成功
            pw.write(1);
        }else{
            pw.write(0);
        }
    }


}
