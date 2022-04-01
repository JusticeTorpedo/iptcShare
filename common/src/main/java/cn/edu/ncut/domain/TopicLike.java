package cn.edu.ncut.domain;

import java.util.Date;

public class TopicLike {
    private Integer id;
    private Integer topicId;
    private String userName;
    private Date createTime;

    public TopicLike() {
    }

    public TopicLike(Integer topicId, String userName, Date createTime) {
        this.topicId = topicId;
        this.userName = userName;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
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
