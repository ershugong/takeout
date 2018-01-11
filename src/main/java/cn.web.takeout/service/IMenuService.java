package cn.web.takeout.service;

import cn.web.takeout.model.Menu;

public interface IMenuService {
    Menu selecMenu(String id);
    long insertMenu(Menu menu);
    long updateMenu(Menu menu);
}
