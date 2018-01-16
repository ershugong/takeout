package cn.web.takeout.util;

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

    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString();//转化为String对象
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
