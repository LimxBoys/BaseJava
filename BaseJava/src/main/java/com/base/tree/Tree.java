package com.base.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * ext树菜单
 * 
 * @author limingxing
 * @date 2015-12-11
 * 
 */
public class Tree {

	private String id;
	private String name;
	private String text;
	private String iconCls;
	private boolean expanded;
	private boolean leaf;
	private String url;
	private String parentId;
	private List<Tree> children;
	private int displayIndex;
	private String tableName;
	private int isdisplay;
	public Tree(){
	    
	}
	
	public int getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(int isdisplay) {
		this.isdisplay = isdisplay;
	}

	public Tree(String id,String text){
	    this.id = id;
	    this.text = text;
	}
	
	public Tree(String id,String text,boolean leaf){
        this.id = id;
        this.text = text;
        this.leaf = leaf;
        if(!leaf){
            children = new ArrayList<Tree>();
        }
    }
	
	public int getDisplayIndex() {
		return displayIndex;
	}

	public void setDisplayIndex(int displayIndex) {
		this.displayIndex = displayIndex;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
