package com.base.tree;


import java.util.List;

public class BaseModules
{
  private String moduleId;
  private BaseModules parent;
  private List<BaseModules> children;
  private String moduleName;
  private String moduleUrl;
  private String parentId;
  private Short leaf;
  private Short expanded;
  private Short displayIndex;
  private Short isDisplay;
  private String enModuleName;
  private String iconCss;
  private String information;
  private Integer modulePerm;
  private Integer moduleType;
  private Integer moduleBelong;

  public String getModuleId()
  {
    return this.moduleId;
  }

  public void setModuleId(String moduleId) {
    this.moduleId = moduleId;
  }
  public void setParent(BaseModules parent) {
    this.parent = parent;
  }

  public BaseModules getParent() {
    return this.parent;
  }
  public void setChildren(List<BaseModules> children) {
    this.children = children;
  }
  public List<BaseModules> getChildren() {
    return this.children;
  }

  public String getModuleName() {
    return this.moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public String getModuleUrl() {
    return this.moduleUrl;
  }

  public void setModuleUrl(String moduleUrl) {
    this.moduleUrl = moduleUrl;
  }

  public String getParentId() {
    return this.parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public Short getExpanded() {
    return this.expanded;
  }

  public void setExpanded(Short expanded) {
    this.expanded = expanded;
  }

  public Short getDisplayIndex() {
    return this.displayIndex;
  }

  public void setDisplayIndex(Short displayIndex) {
    this.displayIndex = displayIndex;
  }

  public Short getIsDisplay() {
    return this.isDisplay;
  }

  public void setIsDisplay(Short isDisplay) {
    this.isDisplay = isDisplay;
  }

  public String getEnModuleName() {
    return this.enModuleName;
  }

  public void setEnModuleName(String enModuleName) {
    this.enModuleName = enModuleName;
  }

  public String getIconCss() {
    return this.iconCss;
  }

  public void setIconCss(String iconCss) {
    this.iconCss = iconCss;
  }

  public String getInformation() {
    return this.information;
  }

  public void setInformation(String information) {
    this.information = information;
  }

  public Short getLeaf() {
    return this.leaf;
  }

  public void setLeaf(Short leaf) {
    this.leaf = leaf;
  }

  public Integer getModulePerm() {
    return this.modulePerm;
  }

  public void setModulePerm(Integer modulePerm) {
    this.modulePerm = modulePerm;
  }

  public Integer getModuleType() {
    return this.moduleType;
  }

  public void setModuleType(Integer moduleType) {
    this.moduleType = moduleType;
  }

  public Integer getModuleBelong()
  {
    return this.moduleBelong;
  }

  public void setModuleBelong(Integer moduleBelong)
  {
    this.moduleBelong = moduleBelong;
  }



 
}