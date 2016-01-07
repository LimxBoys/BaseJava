package com.base.vo;


/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午11:01:44
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public class UserRole {

  private Integer id;//树ID
  private Integer pId=0;//父ID
  private String name;//树节点名称
  private boolean checked=false;//是否选中
  private String icon="fa fa-th-large";//默认图标
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getpId() {
    return pId;
  }
  public void setpId(Integer pId) {
    this.pId = pId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public boolean isChecked() {
    return checked;
  }
  public void setChecked(boolean checked) {
    this.checked = checked;
  }
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }

}
