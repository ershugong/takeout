package cn.web.takeout.vo;

public class OrderForShopVO {
    private String id;
    private String menuName;
    private String menuHeadPic;
    private String ext;//备注
    private String detailPlace;//地址
    private Integer numb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getNumb() {
        return numb;
    }

    public void setNumb(Integer numb) {
        this.numb = numb;
    }

    public String getDetailPlace() {
        return detailPlace;
    }

    public void setDetailPlace(String detailPlace) {
        this.detailPlace = detailPlace;
    }
}
