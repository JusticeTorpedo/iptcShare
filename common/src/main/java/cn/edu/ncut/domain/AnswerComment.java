package cn.edu.ncut.domain;

import java.util.Date;

public class AnswerComment {
    private Integer id;
    private Integer answerId;
    private String userName;
    private String content;
    private Integer likeCount;
    private Date createTime;

    public AnswerComment() {
    }

    public AnswerComment(Integer answerId, String userName, String content, Integer likeCount, Date createTime) {
        this.answerId = answerId;
        this.userName = userName;
        this.content = content;
        this.likeCount = likeCount;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
