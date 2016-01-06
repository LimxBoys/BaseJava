package com.base.vo;


/**
 * 
 * 用户角色数Vo   
 * 项目名称：base_spring_framework   
 * 类名称：UserRole   
 * 类描述：   用户角色数Vo
 * 创建人：Administrator   
 * 创建时间：2015年2月6日 下午1:24:44   
 * 修改人：Administrator   
 * 修改时间：2015年2月6日 下午1:24:44   
 * 修改备注：   
 * @version    
 *
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
