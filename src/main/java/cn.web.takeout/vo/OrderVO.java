package cn.web.takeout.vo;

import cn.web.takeout.model.Order;

import java.util.List;

public class OrderVO {
    private List<Order> orders;
    private Integer totalPrice;
    private List<Integer> activities;
    private Integer sendPrice;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Integer> getActivities() {
        return activities;
    }

    public void setActivities(List<Integer> activities) {
        this.activities = activities;
    }

    public Integer getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(Integer sendPrice) {
        this.sendPrice = sendPrice;
    }
}
