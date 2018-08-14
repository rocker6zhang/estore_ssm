package com.estore.been;

import java.util.Date;

import javax.validation.constraints.Pattern;



public class Send {
    private Integer id;


    private Date sendDate;

    @Pattern(regexp="\\S{2,255}|[\\u2E80-\\u9FFF\\S]{1,255}"
    		,message="公司名必须是1-255位中文或者2到255位英文和数字的组合")
    private String target;

    private String role;

    private Date createDate;

    private Date updateDate;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	@Override
	public String toString() {
		return "Send [id=" + id + ", sendDate=" + sendDate + ", target=" + target + ", role=" + role + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", description=" + description + "]";
	}
    
    
}