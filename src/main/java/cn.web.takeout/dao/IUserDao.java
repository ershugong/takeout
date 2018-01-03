package cn.web.takeout.dao;

import cn.web.takeout.model.User;

import java.util.Map;

public interface IUserDao {
    User selectUser(long id);

    /**
     * 登录验证
     * @param map
     * @return
     */
    User checkUser(Map<String,Object> map);
}
