package cn.edu.ncut.domain;

import java.util.Date;

public class MaterialLike {
    private Integer id;
    private Integer materialId;
    private Integer userId;
    private Date createTime;

    public MaterialLike() {
    }

    public MaterialLike(Integer materialId, Integer userId, Date createTime) {
        this.materialId = materialId;
        this.userId = userId;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
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
