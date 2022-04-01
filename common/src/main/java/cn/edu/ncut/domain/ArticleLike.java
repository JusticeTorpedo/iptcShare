package cn.edu.ncut.domain;

import java.util.Date;

public class ArticleLike {
    private Integer id;
    private Integer articleId;
    private Integer userId;
    private Date createTime;

    public ArticleLike() {
    }

    public ArticleLike(Integer articleId, Integer userId, Date createTime) {
        this.articleId = articleId;
        this.userId = userId;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
