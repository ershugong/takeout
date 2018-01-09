package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IShopDao;
import cn.web.takeout.dao.IUserDao;
import cn.web.takeout.model.Shop;
import cn.web.takeout.model.User;
import cn.web.takeout.service.IUserService;
import cn.web.takeout.util.CommenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService{
    @Resource
    private IUserDao userDao;
    @Resource
    private IShopDao shopDao;

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
        user.setHeadPic("upload/y.jpg");//在管理后台具体设置
        user.setCreateTime(new Date());
        user.setShopId(CommenUtil.getUUID32());

        //同时创建此商户的商店
        Shop shop = new Shop();
        shop.setId(user.getShopId());
        shop.setShoperId(user.getId());
        shop.setCreateTime(new Date());
        shop.setLogo("../upload/shop.jpg");
        shop.setIsDessert(CommenUtil.TYPE0_INT);
        shop.setIsFood(CommenUtil.TYPE0_INT);
        shop.setIsFruit(CommenUtil.TYPE0_INT);
        shop.setIsMajorSend(CommenUtil.TYPE0_INT);
        shop.setIsMarket(CommenUtil.TYPE0_INT);
        shop.setIsSupper(CommenUtil.TYPE0_INT);
        shop.setIsSnack(CommenUtil.TYPE0_INT);
        shopDao.insertShop(shop);

        return userDao.registerUser(user);
    }

    @Override
    public long updateUser(User user) {
        return userDao.updateUser(user);
    }
}
