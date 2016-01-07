package com.base.vo;
/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public class BaseArea {
	private String base_areaid;
	private String name;
	private String parentid;
	private String vieworder;
	public String getBase_areaid() {
		return base_areaid;
	}
	public void setBase_areaid(String base_areaid) {
		this.base_areaid = base_areaid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getVieworder() {
		return vieworder;
	}
	public void setVieworder(String vieworder) {
		this.vieworder = vieworder;
	}
	public BaseArea() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
