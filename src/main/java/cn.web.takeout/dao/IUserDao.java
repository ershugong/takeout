package cn.web.takeout.dao;

import cn.web.takeout.model.User;

public interface IUserDao {
    User selectUser(long id);
}
