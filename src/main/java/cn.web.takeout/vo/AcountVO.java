package cn.web.takeout.vo;

import java.util.List;

public class AcountVO {
    private List<String> menuType;
    private List<Integer> typeSale;

    public List<String> getMenuType() {
        return menuType;
    }

    public void setMenuType(List<String> menuType) {
        this.menuType = menuType;
    }

    public List<Integer> getTypeSale() {
        return typeSale;
    }

    public void setTypeSale(List<Integer> typeSale) {
        this.typeSale = typeSale;
    }
}
