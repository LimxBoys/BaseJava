package com.base.model;

import java.sql.Timestamp;

/**
 * 角色表
 * 
 * 项目名称：base_spring_framework 类名称：Role 类描述： 创建人：limingxing 创建时间：2015年1月27日 下午6:42:22
 * 修改人：limingxing 修改时间：2015年1月27日 下午6:42:22 修改备注：
 * 
 * @version 1.0
 * 
 */
public class Role {

	private Integer id;
	private String roleName;// 角色名称
	private Integer state;// 状态，0=冻结，1=正常
	private Timestamp insertTime;// 插入时间
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

}
