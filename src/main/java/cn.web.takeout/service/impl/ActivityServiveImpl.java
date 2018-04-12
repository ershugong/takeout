package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IActivityDao;
import cn.web.takeout.dao.ICommentDao;
import cn.web.takeout.dao.IMenuDao;
import cn.web.takeout.dao.IShopDao;
import cn.web.takeout.model.Activity;
import cn.web.takeout.model.Comment;
import cn.web.takeout.model.Menu;
import cn.web.takeout.model.Shop;
import cn.web.takeout.service.IActivityService;
import cn.web.takeout.service.ICommentService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.vo.ActivityVO;
import cn.web.takeout.vo.CommentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("activityService")
public class ActivityServiveImpl implements IActivityService{
    @Resource
    private IActivityDao activityDao;

    @Resource
    private IShopDao shopDao;

    @Override
    public Activity selectActivity(String id) throws Exception {
        return activityDao.selectActivity(id);
    }

    @Override
    public long insertActivity(String name, String lowLine, Integer discount, String shopId) throws Exception {
        Activity activityTemp = activityDao.getShopActivity(shopId);
        Activity activity = new Activity();
        activity.setId(CommenUtil.getUUID32());
        activity.setLowLine(lowLine);
        activity.setName(name);
        activity.setDiscount(discount);
        activity.setShopId(shopId);
        activity.setCreateTime(new Date());
        activity.setType(0);
        activity.setTypePhoto("");

        Shop shop = new Shop();
        shop.setId(shopId);


        //处理商店的类型总结
        String [] type = lowLine.split(",");
        if(type[0].equals("0") && type[1].equals("0") && type[2].equals("0")){
            shop.setActivityType("0");
        } else if(type[2].equals("0")){//没有免邮活动
            shop.setActivityType("1");
        }else if(type[0].equals("0") || type[1].equals("0")){//没有满减活动
            shop.setActivityType("2");
        }else{
            shop.setActivityType("1,2");
        }
        shopDao.updateShopActivityType(shop);

        if(activityTemp != null){
            return activityDao.updateShopActivity(activity);
        }else{
            return activityDao.insertActivity(activity);
        }
    }

    @Override
    public List<ActivityVO> getShopActivity(String shopId) throws Exception {
        List<ActivityVO> result = new ArrayList<>();
        Activity activity = activityDao.getShopActivity(shopId);
        if(activity != null){
            ActivityVO activityVO = new ActivityVO();
            String [] temp = activity.getLowLine().split(",");
            activityVO.setDiscount(activity.getDiscount());
            activityVO.setType1(temp[0]);
            activityVO.setType2(temp[1]);
            activityVO.setType3(temp[2]);
            result.add(activityVO);
        }
        return result;
    }
}
