package cn.edu.ncut.domain;

import java.util.Date;

public class AnswerCommentLike {
    private Integer id;
    private Integer commentId;
    private String userName;
    private Date createTime;

    public AnswerCommentLike() {
    }

    public AnswerCommentLike(Integer commentId, String userName, Date createTime) {
        this.commentId = commentId;
        this.userName = userName;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

