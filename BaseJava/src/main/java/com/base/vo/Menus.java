package com.base.vo;

/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午11:01:36
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
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
