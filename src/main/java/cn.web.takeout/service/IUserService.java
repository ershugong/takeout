package cn.web.takeout.service;

import cn.web.takeout.model.User;

public interface IUserService {
    User selectUser(String id);
    User checkUser(String userId,String password);
    long registerUser(User user);
    long updateUser(User user);
    /**
     * 修改用户密码
     * @param user
     * @return
     */
    long updateUserPassword(User user) throws Exception;
}
