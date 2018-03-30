package cn.web.takeout.vo;

import cn.web.takeout.model.Menu;

import java.util.List;

public class MenuListVO {
    private String typeName;
    private List<Menu> menuContent;
    private Integer cost;
    private ShopVO shopVO;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Menu> getMenuContent() {
        return menuContent;
    }

    public void setMenuContent(List<Menu> menuContent) {
        this.menuContent = menuContent;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public ShopVO getShopVO() {
        return shopVO;
    }

    public void setShopVO(ShopVO shopVO) {
        this.shopVO = shopVO;
    }
}
