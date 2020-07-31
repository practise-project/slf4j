package com.example.test.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 组织数据模型
 * @author ClowLAY
 * create date 2020/4/8
 */

@Document("organize")
public class Organize {

    @Id
    private ObjectId id;

    /**
     * 上一级组织ID(一级组织为0)
     */
    @Field("pid")
    private String pId;

    /**
     *组织类型 （1为公司，2为部门）
     */
    @Field("org_type")
    @Indexed(background = true)
    private int orgType;

    /**
     *组织名称
     */
    @Field("org_name")
    @Indexed(background = true)
    private String orgName;

    /**
     *组织编码
     */
    @Field("org_code")
    @Indexed(background = true)
    private String orgCode;


    /**
     *组织级别
     */
    @Field("org_level")
    @Indexed(background = true)
    private int orgLevel;



    /**
     *创建时间
     */
    @Field("create_time")
    @Indexed(background = true)
    private Long createTime;

    /**
     *更新时间
     */
    @Field("update_time")
    @Indexed(background = true)
    private Long updateTime;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public int getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(int orgLevel) {
        this.orgLevel = orgLevel;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Organize{" +
                "id=" + id +
                ", pid=" + pId +
                ", orgName='" + orgName + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", orgLevel='" + orgLevel + '\'' +
                ", orgType=" + orgType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
