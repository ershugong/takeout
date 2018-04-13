package cn.web.takeout.vo;

import cn.web.takeout.model.Order;

import java.util.List;

public class AddressAndMenu {
    private AddressVO addressVO;//默认地址
    private OrderVO orderList;//所有订单

    public AddressVO getAddressVO() {
        return addressVO;
    }

    public void setAddressVO(AddressVO addressVO) {
        this.addressVO = addressVO;
    }

    public OrderVO getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderVO orderList) {
        this.orderList = orderList;
    }
}
