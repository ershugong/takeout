package cn.web.takeout.service.impl;


import cn.web.takeout.dao.IMenuDao;
import cn.web.takeout.model.Menu;
import cn.web.takeout.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        return menuDao.insertMenu(menu);
    }

    @Override
    public long updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }
}
