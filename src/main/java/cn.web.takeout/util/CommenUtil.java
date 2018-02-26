package cn.web.takeout.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommenUtil {
    /**
     * 商家
     */
    public final static Integer SHOPER = 1;
    /**
     * 用户
     */
    public final static Integer USER = 0;
    /**
     * 商品归类（初始化为0）
     */
    public final static Integer TYPE0_INT = 0;
    /**
     * 菜单上架
     */
    public final static Integer MENU_UP_STATUS = 1;
    /**
     * 初始化当前菜单的数目
     */
    public final static Integer MENU_NUM = 0;
    /**
     * 商店评分（赞数目）
     */
    public final static String SHOP_LAUD = "laud";
    /**
     * 商店最低价字段
     */
    public final static String SHOP_LOW_SEND = "low_send";
    /**
     * 商店配送价字段
     */
    public final static String SHOP_SEND_PRICE = "send_price";
    /**
     * 待结算
     */
    public final static String NOT_BUY = "待结算";
    /**
     * 已付款
     */
    public final static String ALREADLY_BUY = "已付款";
    /**
     * 已取消
     */
    public final static String ORDER_CANCEL = "已取消";
    /**
     * 已派送
     */
    public final static String ORDER_SEND = "已派送";
    /**
     * 初始化订单数量1
     */
    public final static Integer MENU_NUMB_INIT1 = 1;
    /**
     * 好评
     */
    public final static String GOOD_TYPE = "GOOD";
    /**
     * 中评
     */
    public final static String NOTBAD_TYPE = "NOTBAD";
    /**
     * 差评
     */
    public final static String BAD_TYPE = "BAD";


    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString();//转化为String对象
        uuid = uuid.replace("-", "");
        return uuid;
    }

    public static String FormatDate(Date date,String format) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(date);
        return result;
    }
}
