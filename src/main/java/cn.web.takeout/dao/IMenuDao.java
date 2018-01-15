package cn.web.takeout.dao;

import cn.web.takeout.model.Menu;

import java.util.List;

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

    /**
     *通过商店id查询所有的菜色
     * @param shopId
     * @return
     */
    List<Menu> getAllMenu(String shopId);
}