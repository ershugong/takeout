package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IUserDao;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService{
    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }

    @Override
    public User checkUser(String userId, String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("password",password);
        return userDao.checkUser(map);
    }
}
