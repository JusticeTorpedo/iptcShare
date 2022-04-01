package cn.edu.ncut.domain;

import java.util.Date;

public class AnswerLike {
    private Integer id;
    private Integer answerId;
    private String userName;
    private Date createTime;

    public AnswerLike() {
    }

    public AnswerLike(Integer answerId, String userName, Date createTime) {
        this.answerId = answerId;
        this.userName = userName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
