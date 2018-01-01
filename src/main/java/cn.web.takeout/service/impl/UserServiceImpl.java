package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IUserDao;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService{
    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }
}
