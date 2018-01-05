package cn.web.takeout.service;

import cn.web.takeout.model.User;

public interface IUserService {
    User selectUser(String id);
    User checkUser(String userId,String password);
    long registerUser(User user);
    long updateUser(User user);
}
