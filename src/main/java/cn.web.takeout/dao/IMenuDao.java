package cn.web.takeout.dao;

import cn.web.takeout.model.Menu;

public interface IMenuDao {
    /**
     * 插入菜单
     * @param id
     * @return
     */
    Menu selectMenu(String id);

    /**
     * 修改菜色
     * @param menu
     * @return
     */
    long insertMenu(Menu menu);

    /**
     * 更新菜色
     * @param menu
     * @return
     */
    long updateMenu(Menu menu);
}
