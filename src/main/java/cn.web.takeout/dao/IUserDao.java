package cn.web.takeout.dao;

import cn.web.takeout.model.User;

import java.util.Map;

public interface IUserDao {
    User selectUser(String id);

    /**
     * 登录验证
     * @param map
     * @return
     */
    User checkUser(Map<String,Object> map);

    /**
     * 注册用户
     * @param user
     * @return
     */
    long registerUser(User user);

    /**
     * 修改用户个人资料
     * @param user
     * @return
     */
    long updateUser(User user);
}
