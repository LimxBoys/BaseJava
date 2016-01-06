package com.base.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ext树菜单
 * 
 * @author limingxing
 * @date 2015-12-11
 * 
 */
public class TreeMenu {

	private Map<String, Integer> treePermMap;
	private List<BaseModules> list;
	private BaseModules root;

	public TreeMenu() {

	}

	public TreeMenu(List<BaseModules> list) {
		this.list = list;
		this.root = new BaseModules();
		this.treePermMap = new HashMap<String, Integer>();
	}

	/**
	 * 组合json
	 * 
	 * @param list
	 * @param node
	 * @return
	 */
	private Tree getNodeJson(List<BaseModules> list, BaseModules node,
			boolean isPrivsTree) {
		Tree tree = new Tree();
		tree.setDisplayIndex(node.getDisplayIndex());
		tree.setParentId(node.getParentId());
		tree.setId("_authority_" + node.getModuleId());
		// tree.setText("<span onmouseover=\"this.className='active'\" onmouseout=\"this.className=''\">"+node.getModuleName()+"</span>");
		tree.setIconCls(node.getIconCss());
		tree.setExpanded(false);
		tree.setChildren(new ArrayList<Tree>());
		tree.setIsdisplay(node.getIsDisplay());
		if (list == null) {
			// 防止没有权限菜单
			return tree;
		}

		if (hasChild(list, node)) {
			tree.setText(node.getModuleName());
			tree.setUrl("");
			// tree.setLeaf(node.getLeaf() == 1 ? true : false);
			tree.setLeaf(false);
			tree.setExpanded(node.getExpanded() == 1 ? true : false);

			List<Tree> treeList = new ArrayList<Tree>();
			// 获得当前非叶子节点的子模块列表
			List<BaseModules> childList = getChildList(list, node);
			Iterator<BaseModules> it = childList.iterator();

			// 将子模块转换为tree节点
			BaseModules modules = null;
			List<Tree> privsTreeList = null;
			Tree privsTree = null;
			Tree tmp = null;
			while (it.hasNext()) {
				modules = (BaseModules) it.next();
				// 递归
				tmp = getNodeJson(list, modules, isPrivsTree);
				treeList.add(tmp);
			}
			// 添加当前节点的子节点
			tree.setChildren(treeList);
			// } else if ((node.getParentId() == root.getModuleId()) ||
			// node.getModuleUrl() == null) {
			// // 防止是主菜单,或者主菜单里面的url为空，但是下面没有子菜单的时候
			// tree.setUrl("");
			// tree.setLeaf(node.getLeaf() == 1 ? true : false);
			// tree.setExpanded(node.getExpanded() == 1 ? true : false);
		} else {
			tree.setText(node.getModuleName());
			tree.setUrl(node.getModuleUrl());
			tree.setLeaf(true);
			tree.setExpanded(node.getExpanded() == 1 ? true : false);
		}
		return tree;
	}

	/**
	 * 判断是否有子节点
	 * 
	 * @param list
	 * @param node
	 * @return
	 */
	private boolean hasChild(List<BaseModules> list, BaseModules node) {
		return getChildList(list, node).size() > 0 ? true : false;
	}

	/**
	 * 得到子节点列表
	 * 
	 * @param list
	 * @param modules
	 * @return
	 */
	public List<BaseModules> getChildList(List<BaseModules> list,
			BaseModules modules) {
		List<BaseModules> moduleList = new ArrayList<BaseModules>();
		Iterator<BaseModules> it = list.iterator();
		while (it.hasNext()) {
			BaseModules baseModule = (BaseModules) it.next();
			if (baseModule.getParentId().equals(modules.getModuleId())) {
				moduleList.add(baseModule);
			}
		}
		return moduleList;
	}

	public Tree getTreeJson(boolean isPrivsTree) {
		// 父菜单的id为0
		this.root.setModuleId("0");
		this.root.setLeaf((short) 0);
		this.root.setExpanded((short) 0);
		this.root.setDisplayIndex(Short.valueOf("1"));
		this.root.setIsDisplay(Short.valueOf("1"));
		return this.getNodeJson(this.list, this.root, isPrivsTree);
	}

	public Map<String, Integer> getTreePermMap() {
		return treePermMap;
	}

	public void setTreePermMap(Map<String, Integer> treePermMap) {
		this.treePermMap = treePermMap;
	}

	public List<BaseModules> getList() {
		return list;
	}

	public void setList(List<BaseModules> list) {
		this.list = list;
	}

	public BaseModules getRoot() {
		return root;
	}

	public void setRoot(BaseModules root) {
		this.root = root;
	}
}
