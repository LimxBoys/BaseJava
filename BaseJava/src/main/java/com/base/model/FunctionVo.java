package com.base.model;

import com.base.tree.TreeNodeExtAttribute;
import com.base.tree.TreeNodeId;
import com.base.tree.TreeNodeParentId;
import com.base.tree.TreeNodeQueryByParent;
import com.base.tree.TreeNodeRoot;
import com.base.tree.TreeNodeText;
import com.sun.org.glassfish.gmbal.Description;

@Description("菜单")
@TreeNodeRoot(id = "ROOT", name = "菜单")
public class FunctionVo {
	private String id;
	@TreeNodeId
	private String code;
	@TreeNodeParentId
	@TreeNodeQueryByParent
	@TreeNodeExtAttribute
	private String pid;
	@TreeNodeText
	@TreeNodeExtAttribute
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FunctionVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
