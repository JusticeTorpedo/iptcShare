package cn.edu.ncut.domain;

import java.util.Date;

public class Topic {
    private Integer id;
    private String userName;
    private String title;
    private String content;
    private Integer isAvailable;
    private Integer visitCount;
    private Integer likeCount;
    private Integer answerCount;
    private Date createTime;

    public Topic() {
    }

    public Topic(String userName, String title, String content, Integer isAvailable, Integer visitCount, Integer likeCount, Integer answerCount, Date createTime) {
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.isAvailable = isAvailable;
        this.visitCount = visitCount;
        this.likeCount = likeCount;
        this.answerCount = answerCount;
        this.createTime = createTime;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) { this.content = content; }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
