package com.base.model;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public class Function implements Serializable{

  private int id;
  private int parentId;// 父Id
  private String functionName;// 功能名称
  private int state;// 状态，0=冻结，1=正常
  private int display;// 是否显示，0=不显示，1=显示
  private String action;// 请求路径
  private String icon;//图标
  private int desc;// 排序
  private Timestamp insertTime;// 插入时间

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public String getFunctionName() {
    return functionName;
  }

  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public int getDesc() {
    return desc;
  }

  public void setDesc(int desc) {
    this.desc = desc;
  }

  public Timestamp getInsertTime() {
    return insertTime;
  }

  public void setInsertTime(Timestamp insertTime) {
    this.insertTime = insertTime;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public int getDisplay() {
    return display;
  }

  public void setDisplay(int display) {
    this.display = display;
  }
  

}
