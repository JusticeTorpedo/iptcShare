package cn.edu.ncut.domain;

import java.util.Date;

public class TopicAnswer {
    private Integer id;
    private Integer topicId;
    private String userName;
    private String content;
    private Integer isAvailable;
    private Integer likeCount;
    private Integer commentCount;
    private Date createTime;

    public TopicAnswer() {
    }

    public TopicAnswer(Integer topicId, String userName, String content, Integer isAvailable, Integer likeCount, Integer commentCount, Date createTime) {
        this.topicId = topicId;
        this.userName = userName;
        this.content = content;
        this.isAvailable = isAvailable;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
