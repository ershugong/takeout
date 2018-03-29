package cn.web.takeout.model;

public class Activity {
    private String id;
    private String name;
    private Integer lowLine;
    private Integer discount;
    private String shopId;
    private Integer type;
    private String typePhoto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLowLine() {
        return lowLine;
    }

    public void setLowLine(Integer lowLine) {
        this.lowLine = lowLine;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypePhoto() {
        return typePhoto;
    }

    public void setTypePhoto(String typePhoto) {
        this.typePhoto = typePhoto;
    }
}
