package cn.edu.ncut.domain;

import java.util.Date;

public class Material {
    private Integer id;
    private String userName;
    private String categoryFirst;
    private String categorySecond;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Integer isAvailable;
    private Integer visitCount;
    private Integer likeCount;

    public Material() {
    }

    public Material(String userName, String categoryFirst, String categorySecond, String title, String content, Date createTime, Date updateTime, Integer isAvailable, Integer visitCount, Integer likeCount) {
        this.userName = userName;
        this.categoryFirst = categoryFirst;
        this.categorySecond = categorySecond;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isAvailable = isAvailable;
        this.visitCount = visitCount;
        this.likeCount = likeCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCategoryFirst() {
        return categoryFirst;
    }

    public void setCategoryFirst(String categoryFirst) {
        this.categoryFirst = categoryFirst;
    }

    public String getCategorySecond() {
        return categorySecond;
    }

    public void setCategorySecond(String categorySecond) {
        this.categorySecond = categorySecond;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
