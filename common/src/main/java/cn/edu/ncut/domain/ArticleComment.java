package cn.edu.ncut.domain;

import java.util.Date;

public class ArticleComment {
    private Integer id;
    private Integer articleId;
    private Integer userId;
    private String userName;
    private String content;
    private Date createTime;

    public ArticleComment() {
    }

    public ArticleComment(Integer articleId, Integer userId, String userName, String content, Date createTime) {
        this.articleId = articleId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
