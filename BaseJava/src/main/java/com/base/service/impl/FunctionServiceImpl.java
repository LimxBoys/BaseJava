package com.base.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.base.dao.FunctionDAO;
import com.base.model.Function;
import com.base.service.FunctionService;
import com.base.util.ConstantUtil;
import com.base.vo.FunctionTree;

/**
 * function serviceImpl
 * 
 * 项目名称：base_spring_framework 类名称：FunctionServiceImpl 类描述： 创建人：limingxing
 * 创建时间：2015年1月27日 下午7:17:30 修改人：limingxing 修改时间：2015年1月27日 下午7:17:30 修改备注：
 * 
 * @version
 * 
 */
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionDAO functionDAO;

	public void insertFunction(Function function) {
		functionDAO.insertFunction(function);

	}

	public void deleteFunction(int id) {

		// 存在孩子，不能删除
		List<Function> list = functionDAO.findFunctionbyParentId(id);
		// 删除关联表
		functionDAO.deleteFunctionRole(id);
		// 删除功能表
		functionDAO.deleteFunction(id);

	}

	public void updateFunction(Function function) {

		functionDAO.updateFunction(function);

	}

	public void updateFunctionschecked(int roleId, String functionIds) {
		// 删除旧关系
		functionDAO.deleteRoleFunction(roleId);
		// 建立新关系
		if (!"".equals(functionIds)) {
			String[] ids = functionIds.split(",");
			for (String functionId : ids) {
				if (Integer.valueOf(functionId).equals(0))
					continue;
				functionDAO.insertFunctionRole(Integer.valueOf(functionId),
						roleId);
			}

		}
	}

	public List<Function> findAllEffectiveFunctionsbyLoginName(String loginName) {
		return functionDAO.findAllEffectiveFunctionsbyLoginName(loginName);
	}

	public List<Function> findAllEffectiveFunctionsbyRoleId(Integer roleId) {
		return functionDAO.findAllEffectiveFunctionsbyRoleId(roleId);
	}

	public PageInfo<Function> findFunctionList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Function> list = functionDAO.findFunctionList();
		PageInfo<Function> page = new PageInfo<Function>(list);
		return page;
	}

	public PageInfo<Function> findFunctionList(Function function, int pageNum,
			int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Function> list = functionDAO.findFunctionListByQuery(function);
		PageInfo<Function> page = new PageInfo<Function>(list);
		return page;
	}

	public List<Integer> findEffectiveFunctionsbyAction(String action) {
		return functionDAO.findEffectiveFunctionsbyAction(action);
	}

	public List<FunctionTree> findFunctionTree(int roleId) {

		List<FunctionTree> functionTreeList = new ArrayList<FunctionTree>();
		List<Function> effectiveFunctinList = functionDAO
				.findEffectiveFunctinList();
		List<Integer> checkedFunctionlist = functionDAO
				.findFunctionTree(roleId);

		for (Function func : effectiveFunctinList) {
			FunctionTree funcTree = new FunctionTree();
			funcTree.setId(func.getId());
			funcTree.setpId(func.getParentId());
			funcTree.setName(func.getFunctionName());
			funcTree.setIcon(func.getIcon());
			for (Integer checkedFunctionId : checkedFunctionlist) {
				if (Integer.valueOf(func.getId()).equals(checkedFunctionId))
					funcTree.setChecked(true);
			}
			functionTreeList.add(funcTree);
		}

		return functionTreeList;
	}

	public List<FunctionTree> findRootList(String id) {

		List<FunctionTree> functionTreeList = new ArrayList<FunctionTree>();
		List<Function> allFunctionlist = functionDAO.findFunctionList();

		for (Function func : allFunctionlist) {
			FunctionTree funcTree = new FunctionTree();
			funcTree.setId(func.getId());
			funcTree.setpId(func.getParentId());
			funcTree.setName(func.getFunctionName());
			funcTree.setIcon(func.getIcon());
			if (!StringUtils.isEmpty(id)) {
				// 查询id的父ID
				Function function = functionDAO.findFunction(Integer
						.valueOf(id));
				if (func.getId() == function.getParentId())
					funcTree.setChecked(true);
				if (func.getId() == function.getId())
					continue;
			}
			functionTreeList.add(funcTree);
		}

		return functionTreeList;
	}

	public List<Function> findFunctionbyParentIdAndFunctionName(
			Function function) {

		return functionDAO.findFunctionbyParentIdAndFunctionName(function);
	}

	public void updateState(int id, int state) {
		state = state == ConstantUtil.OK ? ConstantUtil.FAIL : ConstantUtil.OK;

		functionDAO.updateState(id, state);
	}

	public List<Function> findEffectiveFunctinList() {
		// TODO Auto-generated method stub
		return functionDAO.findEffectiveFunctinList();
	}

}
