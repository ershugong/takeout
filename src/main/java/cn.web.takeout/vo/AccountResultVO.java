package cn.web.takeout.vo;

import java.util.List;

public class AccountResultVO {
    private List<String> menuTypes;
    private List<Integer> menuSales;
    private List<TwoAccountVO> commentList;

    public List<String> getMenuTypes() {
        return menuTypes;
    }

    public void setMenuTypes(List<String> menuTypes) {
        this.menuTypes = menuTypes;
    }

    public List<Integer> getMenuSales() {
        return menuSales;
    }

    public void setMenuSales(List<Integer> menuSales) {
        this.menuSales = menuSales;
    }

    public List<TwoAccountVO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<TwoAccountVO> commentList) {
        this.commentList = commentList;
    }
}
