package com.base.vo;
/**
 * 
 *    
 * 项目名称：base_spring_framework   
 * 类名称：FunctionTree   
 * 类描述：   角色、功能树
 * 创建人：limy
 * 创建时间：2015年2月6日 下午1:28:47   
 * 修改人：Administrator   
 * 修改时间：2015年2月6日 下午1:28:47   
 * 修改备注：   
 * @version    
 *
 */
public class FunctionTree {

  private Integer id;
  private Integer pId;//父ID
  private String name;//功能名称
  private String url;//地址
  private String icon;//图标
  private boolean checked = false;//是否选中


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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public boolean isChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }

}
