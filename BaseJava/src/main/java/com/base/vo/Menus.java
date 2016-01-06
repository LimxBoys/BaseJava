package com.base.vo;

/**
 * 
 *    
 * 项目名称：base_spring_framework   
 * 类名称：Menus   
 * 类描述：   function用户菜单Vo
 * 创建人：Administrator   
 * 创建时间：2015年2月6日 下午1:26:02   
 * 修改人：Administrator   
 * 修改时间：2015年2月6日 下午1:26:02   
 * 修改备注：   
 * @version    
 *
 */
public class Menus {

  private Integer id;//列表树ID
  private Integer pId;//父ID
  private String name;//功能名称
  private String url;//请求路径
  private String icon;//图标

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

  
  
}
