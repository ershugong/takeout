package cn.web.takeout.vo;

public class CommentVO {
    private String menuHeadPic;
    private String menuName;
    private String userName;
    private String createTime;
    private String commentType;
    private String content;

    public String getMenuHeadPic() {
        return menuHeadPic;
    }

    public void setMenuHeadPic(String menuHeadPic) {
        this.menuHeadPic = menuHeadPic;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
