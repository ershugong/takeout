package cn.web.takeout.service.impl;


import cn.web.takeout.dao.IMenuDao;
import cn.web.takeout.model.Menu;
import cn.web.takeout.service.IMenuService;
import cn.web.takeout.util.CommenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("menuService")
public class MenuServiceImpl implements IMenuService{

    @Resource
    private IMenuDao menuDao;
    @Override
    public Menu selecMenu(String id) {
        return menuDao.selectMenu(id);
    }

    @Override
    public long insertMenu(Menu menu) {
        if(menu.getHeadPic() == null){
            menu.setHeadPic("upload/x.png");//初始化头像
        }
        menu.setCreateTime(new Date());
        menu.setNumb(CommenUtil.MENU_NUM);
        return menuDao.insertMenu(menu);
    }

    @Override
    public long updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    public List<Menu> getAllMenu(String shopId) {
        return menuDao.getAllMenu(shopId);
    }

    @Override
    public long delMenu(String id) {
        return menuDao.delMenu(id);
    }
}
