package cn.web.takeout.vo;

public class DetailSingleOrderVO {
    private String shopName;
    private String state;
    private Integer price;
    private String date;
    private String menuName;
    private String menuHeadPic;
    private String menuId;

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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
