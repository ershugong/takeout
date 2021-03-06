package cn.web.takeout.model;

import java.util.Date;

public class Shop {
    private String id;//商店id
    private String shopName;//商店名称
    private String shoperId;//店主id
    private String logo;//商店logo
    private String address;//是否为美食（0 : 否，1：是）
    private int isFood;//是否为超市（0 : 否，1：是）
    private int isMarket;//是否为超市（0 : 否，1：是）
    private int isFruit;//是否为果蔬（0 : 否，1：是）
    private int isDessert;//是否为甜品（0 : 否，1：是）
    private int isMajorSend;//是否为平台专送（0 : 否，1：是）
    private int isSupper;//是否为正餐（0 : 否，1：是）
    private int isSnack;//是否为小吃（0 : 否，1：是）
    private Date createTime;//创建时间
    private Double latitude;//纬度
    private Double longitude;//经度
    private int lowSend;//多少元起配送
    private int sendPrice;//运送费
    private String remark;//自我介绍
    private String activityType;//包含的商店

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShoperId() {
        return shoperId;
    }

    public void setShoperId(String shoperId) {
        this.shoperId = shoperId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsFood() {
        return isFood;
    }

    public void setIsFood(int isFood) {
        this.isFood = isFood;
    }

    public int getIsMarket() {
        return isMarket;
    }

    public void setIsMarket(int isMarket) {
        this.isMarket = isMarket;
    }

    public int getIsFruit() {
        return isFruit;
    }

    public void setIsFruit(int isFruit) {
        this.isFruit = isFruit;
    }

    public int getIsDessert() {
        return isDessert;
    }

    public void setIsDessert(int isDessert) {
        this.isDessert = isDessert;
    }

    public int getIsMajorSend() {
        return isMajorSend;
    }

    public void setIsMajorSend(int isMajorSend) {
        this.isMajorSend = isMajorSend;
    }

    public int getIsSupper() {
        return isSupper;
    }

    public void setIsSupper(int isSupper) {
        this.isSupper = isSupper;
    }

    public int getIsSnack() {
        return isSnack;
    }

    public void setIsSnack(int isSnack) {
        this.isSnack = isSnack;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getLowSend() {
        return lowSend;
    }

    public void setLowSend(int lowSend) {
        this.lowSend = lowSend;
    }

    public int getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(int sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
