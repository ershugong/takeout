package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IUserDao;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IUserService;
import cn.web.takeout.util.CommenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService{
    @Resource
    private IUserDao userDao;

    public User selectUser(String userId) {
        return this.userDao.selectUser(userId);
    }

    @Override
    public User checkUser(String userId, String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("password",password);
        return userDao.checkUser(map);
    }

    @Override
    public long registerUser(User user) {
        //需要补充的参数有，id，type，shopId，createTime
        user.setId(CommenUtil.getUUID32());
        user.setType(CommenUtil.SHOPER);
        user.setHeadPic("images/platform/0.png");//在管理后台具体设置
        user.setCreateTime(new Date());
        user.setShopId(CommenUtil.getUUID32());
        return userDao.registerUser(user);
    }

    @Override
    public long updateUser(User user) {
        return userDao.updateUser(user);
    }
}
