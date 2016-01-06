package com.base.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.base.model.Function;

public class TreeUtil {
	public static List<Tree> getTreeFunction(List<Function> functionList) {
		List<BaseModules> modules = new ArrayList<BaseModules>();
		for (int i = 0; i < functionList.size(); i++) {
			Function function = functionList.get(i);
			BaseModules m = new BaseModules();
			m.setModuleId(String.valueOf(function.getId()));
			m.setModuleName(function.getFunctionName());
			m.setExpanded(Short.valueOf("0"));
			m.setParentId(String.valueOf(function.getParentId()));
			m.setModuleUrl(function.getAction());
			m.setIconCss(function.getIcon());
			m.setDisplayIndex(Short.parseShort(String.valueOf(function
					.getDesc())));
			m.setLeaf(Short.valueOf("1"));// 叶子节点
			m.setExpanded(Short.valueOf("1"));// 是否展开
			m.setIsDisplay(Short.valueOf(String.valueOf(function.getDisplay())));
			modules.add(m);
		}
		TreeMenu menu = new TreeMenu(modules);
		return menu.getTreeJson(false).getChildren();
	}
	/*
	 * private List<BaseModules> getSubModules(BaseModules d, List<BaseModules>
	 * ModulesList) { List subDeptList = new ArrayList(); BaseModules tmp =
	 * null; for (Iterator it = ModulesList.iterator(); it.hasNext(); ) { tmp =
	 * (BaseModules)it.next(); if ((tmp.getParent() == null) ||
	 * (tmp.getParent().getModuleId().equals(d.getModuleId()))) {
	 * tmp.setChildren(getSubModules(tmp, ModulesList)); if
	 * (tmp.getChildren().size() == 0) tmp.setLeaf(Short.valueOf("1")); else {
	 * tmp.setLeaf(Short.valueOf("0")); } subDeptList.add(tmp); } } return
	 * subDeptList; }
	 */
}
