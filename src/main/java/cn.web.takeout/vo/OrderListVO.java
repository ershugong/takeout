package cn.web.takeout.vo;

import cn.web.takeout.model.Order;

import java.util.List;

public class OrderListVO {
    private String shopName;
    private String state;
    private Integer price;
    private String date;
    private String menuName;
    private String menuHeadPic;
    private List<Order> order;
    private String orderId;
    private String phone;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuHeadPic() {
        return menuHeadPic;
    }

    public void setMenuHeadPic(String menuHeadPic) {
        this.menuHeadPic = menuHeadPic;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
