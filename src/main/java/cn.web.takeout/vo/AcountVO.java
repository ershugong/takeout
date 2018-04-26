package cn.web.takeout.vo;

import java.util.List;

public class AcountVO {
    private String id;
    /**柱状图*/
    private String menuType;
    private Integer typeSale;

    /**饼图*/
    private Integer commentTypeNum;
    private String commentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Integer getTypeSale() {
        return typeSale;
    }

    public void setTypeSale(Integer typeSale) {
        this.typeSale = typeSale;
    }

    public Integer getCommentTypeNum() {
        return commentTypeNum;
    }

    public void setCommentTypeNum(Integer commentTypeNum) {
        this.commentTypeNum = commentTypeNum;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }
}
