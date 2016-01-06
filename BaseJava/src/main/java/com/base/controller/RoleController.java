package com.base.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.base.model.Role;
import com.base.service.RoleService;
import com.base.util.PageUtil;
import com.base.util.ResponseUtil;
import com.base.util.SearchConditionUtil;

/**
 * 
 * 
 * 项目名称：base-spring-framework 类名称：RoleController 类描述： 角色管理工具 创建人：LiMingYi
 * 创建时间：2015年2月6日 下午4:16:24 修改人：LiMingYi 修改时间：2015年2月6日 下午4:16:24 修改备注：
 * 
 * @version
 * 
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@RequestMapping("/role")
	public String role() {
		return "/views/limx/role";
	}

	@RequestMapping(value = "/view")
	public @ResponseBody
	Role view(@RequestParam(value = "id") int id) {
		Role role = roleService.findRoleById(id);
		return role;
	}

	@RequestMapping(value = "/add")
	public @ResponseBody
	Object add(Role role, HttpServletRequest request,
			HttpServletResponse response) {
		role.setInsertTime(new Timestamp(System.currentTimeMillis()));
		role.setState(1);
		roleService.insertRole(role);
		return ResponseUtil.success();
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	Object update(@RequestParam(value = "id") int id, Role role,
			HttpServletRequest request, HttpServletResponse response) {
		role.setId(id);
		roleService.updateRole(role);
		return ResponseUtil.success();
	}

	/**
	 * 新增用户
	 * 
	 * @Title: updateState
	 * @Description: 修改角色状态
	 * @param id
	 *            roleId
	 * @param state
	 *            角色状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateState")
	public @ResponseBody
	Object updateState(@RequestParam(value = "id") int id,
			@RequestParam(value = "state") int state,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("id", id);
		tempMap.put("state", state == 0 ? 1 : 0);
		roleService.updateRoleState(tempMap);
		return ResponseUtil.success();
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody
	Object delete(@RequestParam(value = "id") int id) {
		roleService.deleteRole(id);
		return ResponseUtil.success();
	}

	@RequestMapping(value = "/deleteBatch")
	public @ResponseBody
	Object deleteBatch(@RequestParam(value = "ids") String ids) {

		roleService.deleteRoles(ids);
		return ResponseUtil.success();
	}

	@RequestMapping("/list")
	public @ResponseBody
	Object list(HttpServletRequest request,
			@RequestParam(defaultValue = "1", value = "pageNum") int pageNum,
			@RequestParam(defaultValue = "15", value = "pageSize") int pageSize) {
		Map<String, Object> searchCondionMap = SearchConditionUtil
				.packageSearchCondion(request);
		PageInfo<Role> page = roleService.findRoleList(searchCondionMap,
				pageNum, pageSize);
		return PageUtil.convertGrid(page);
	}
	@RequestMapping("/listall")
	public @ResponseBody
	Object listall(HttpServletRequest request) {
		Map<String, Object> searchCondionMap = SearchConditionUtil
				.packageSearchCondion(request);
		List<Role> listrole= roleService.findAllEffectiveRoles();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(listrole!=null&&listrole.size()!=0){
			for(Role role:listrole){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("id", role.getId());
				map.put("text", role.getDescription());
				list.add(map);
			}
			
		}
		return list;
	}

	@RequestMapping(value="/saveRoleFunction",method=RequestMethod.GET)
	@ResponseBody
	public Object saveRoleFunction(int roleId,@RequestParam(value = "ids[]", required = false) String[] ids) {
		roleService.deleteRoleFunction(roleId);
		if (ids != null && ids.length != 0) {
			for (String functionId : ids) {
				roleService.insertRoleFunction(
						roleId,
						Integer.valueOf(functionId.substring(11,
								functionId.length())));
			}
		}
		return ResponseUtil.success();
	}
}
