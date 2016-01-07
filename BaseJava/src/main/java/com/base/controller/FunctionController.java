package com.base.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.base.model.Function;
import com.base.service.FunctionService;
import com.base.tree.BaseModules;
import com.base.tree.Tree;
import com.base.tree.TreeMenu;
import com.base.tree.TreeUtil;
import com.base.util.PageUtil;
import com.base.util.ResponseUtil;
import com.base.util.UserUtil;

/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:56:27
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
@Controller
@RequestMapping("/function")
public class FunctionController {

	@Autowired
	FunctionService functionService;

	/**
	 * 新增功能
	 * 
	 * @Title: add
	 * @Description: 新增功能
	 * @param @param function 对象
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/add")
	public @ResponseBody
	Object add(BaseModules baseModules) {
		Function function = new Function();
		function.setAction(baseModules.getModuleUrl());
		function.setDesc(baseModules.getDisplayIndex());
		function.setDisplay(baseModules.getIsDisplay());
		function.setFunctionName(baseModules.getModuleName());
		function.setIcon(baseModules.getIconCss());
		function.setInsertTime(new Timestamp(System.currentTimeMillis()));
		function.setParentId(Integer.valueOf(baseModules.getParentId()
				.substring(11, baseModules.getParentId().length())));
		function.setState(1);
		functionService.insertFunction(function);
		return ResponseUtil.success();
	}

	/**
	 * 删除function
	 * 
	 * @Title: delete
	 * @Description: 删除function
	 * @param @param id functionId
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody
	Object delete(@RequestParam(value = "id") String id) {
		id = id.substring(11, id.length());
		functionService.deleteFunction(Integer.valueOf(id));
		return ResponseUtil.success();
	}

	@RequestMapping(value = "/frozen", method = RequestMethod.POST)
	public @ResponseBody
	Object frozen(@RequestParam("id") int id, @RequestParam("state") int state) {
		functionService.updateState(id, state);
		return ResponseUtil.success();

	}

	/**
	 * 修改function
	 * 
	 * @Title: update
	 * @Description: 修改function
	 * @param @param function
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody
	Object update(BaseModules baseModules) {

		Function function = new Function();
		function.setAction(baseModules.getModuleUrl());
		function.setDesc(baseModules.getDisplayIndex());
		function.setDisplay(baseModules.getIsDisplay());
		function.setFunctionName(baseModules.getModuleName());
		function.setIcon(baseModules.getIconCss());
		function.setInsertTime(new Timestamp(System.currentTimeMillis()));
		function.setParentId(Integer.valueOf(baseModules.getParentId()
				.substring(11, baseModules.getParentId().length())));
		function.setState(1);
		String moduleId=baseModules.getModuleId();
		function.setId(Integer.valueOf(moduleId.substring(11,moduleId.length())));
		functionService.updateFunction(function);
		return ResponseUtil.success();
	}

	/**
	 * 
	 * @Title: checkFunctions
	 * @Description: 更新function选择树
	 * @param @param roleId 角色ID
	 * @param @param functionIds 功能id集合
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/checkFunctions")
	public @ResponseBody
	Object checkFunctions(@RequestParam(value = "roleId") int roleId,
			@RequestParam(value = "functionIds") String functionIds) {
		functionService.updateFunctionschecked(roleId, functionIds);
		return ResponseUtil.success();
	}

	/**
	 * 
	 * @Title: mylist
	 * @Description: 获取当前用户的功能列表
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/mylist")
	public @ResponseBody
	Object mylist() {

		List<Function> functionList = functionService
				.findAllEffectiveFunctionsbyLoginName(UserUtil
						.findSessionUser().getUsername().toString());
		List<Tree> trees = TreeUtil.getTreeFunction(functionList);
		return trees;

	}

	/**
	 * 
	 * @Title: mylist
	 * @Description: 模块添加时，父模块显示
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/listtreecombobox")
	public @ResponseBody
	Object listtreecombobox() {
		List<Function> functionList = functionService
				.findEffectiveFunctinList();
		List<Tree> list = TreeUtil.getTreeFunction(functionList);
		List<Tree> trees = new ArrayList<Tree>();
		Tree tree = new Tree();
		tree = new Tree();
		tree.setText("根模块");
		tree.setId("_authority_0");
		tree.setLeaf(true);
		tree.setExpanded(true);
		tree.setChildren(list);
		trees.add(tree);
		return trees;

	}

	/**
	 * 
	 * @Title: listtree
	 * @Description: 获取所有模块的树形列表
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/listtree")
	public @ResponseBody
	Object listtree() {
		List<Function> functionList = functionService
				.findEffectiveFunctinList();
		List<Tree> trees = TreeUtil.getTreeFunction(functionList);
		return trees;

	}

	/**
	 * 
	 * @Title: list
	 * @Description: 所有功能列表
	 * @param @param pageNum 当前页
	 * @param @param pageSize 每页数量
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/list")
	public @ResponseBody
	Object list(Function function,
			@RequestParam(defaultValue = "1", value = "pageNum") int pageNum,
			@RequestParam(defaultValue = "15", value = "pageSize") int pageSize) {

		PageInfo<Function> page = functionService.findFunctionList(function,
				pageNum, pageSize);
		return PageUtil.convertGrid(page);
	}

	/**
	 * 
	 * @Title:findAllEffectiveFunctionsbyRoleId
	 * @Description: 根据角色id查询功能
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/findAllEffectiveFunctionsbyRoleId")
	@ResponseBody
	Object findAllEffectiveFunctionsbyRoleId(
			@RequestParam(value = "roleId", required = false) int roleId) {
		List<Function> functionList = functionService
				.findAllEffectiveFunctionsbyRoleId(roleId);
		return functionList;

	}

	@RequestMapping("/module")
	public String function() {
		return "/views/limx/modulelist";
	}
}
