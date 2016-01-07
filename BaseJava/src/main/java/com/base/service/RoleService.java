package com.base.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.base.model.Role;


/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public interface RoleService {
  /**
   * 
   * @Title: 增加Role
   * @Description: 新增角色
   * @param: role Role对象
   * @return: void 返回类型
   * @throws
   */
  public void insertRole(Role role);

  /**
   * 
   * @Title: updateRole
   * @Description: 更新角色
   * @param: role
   * @return
   * @throws
   */
  public void updateRole(Role role);

  /**
   * 
   * @Title: updateRoleState
   * @Description: 更新角色状态
   * @param: tempMap [id:roleId,state:角色状态]
   * @return
   * @throws
   */
  public void updateRoleState(Map<String, Object> tempMap);

  /**
   * 
   * @Title: deleteRole
   * @Description: 刪除角色
   * @param: roleId 角色ID
   * @return: void 返回类型
   * @throws
   */
  public void deleteRole(Integer roleId);

  /**
   * 
   * @Title: deleteRoles
   * @Description: 刪除角色
   * @param: roleIds 角色IDs
   * @return: void 返回类型
   * @throws
   */
  public void deleteRoles(String ids);

  /**
   * 
   * @Title: findRolesByLoginName
   * @Description: 查询role
   * @param: loginName 登录名
   * @return: Role Role对象
   * @throws
   */
  public List<Role> findRolesByLoginName(String loginName);


  /**
   * 查询所有有效角色
   * 
   * @Title: findAllRole
   * @Description: 查询所有有效角色
   * @return List<Role> 返回类型
   * @throws
   */
  public List<Role> findAllEffectiveRoles();



  /**
   * 
   * @Title: findRolesByFunctIonId
   * @Description: 根据functionId查询roleId
   * @param: functionId 功能ID
   * @return: List<Integer> 返回类型
   * @throws
   */
  public List<Integer> findRolesByFunctIonId(Integer functionId);


  /**
   * 
   * @Title: findRoleList
   * @Description: 查询所有角色
   * @param tempMap 查询条件
   * @param: pageNum 当前页
   * @param: pageSize 每页显示数量
   * @return: PageInfo<Role> 返回类型
   * @throws
   */
  public PageInfo<Role> findRoleList(Map<String, Object> tempMap, int pageNum, int pageSize);


  /**
   * 
   * @Title: findRoleById
   * @Description: 根据roleId查询角色信息
   * @param: roleId 角色ID
   * @return: List<Integer> 返回类型
   * @throws
   */
  public Role findRoleById(Integer roleId);

  

  /**
   * 
  * @Title: findRoleByRoleName
  * @Description: 根据roleName查询角色信息
  * @param @param roleName
  * @param @return
  * @return List<Role>    返回类型
  * @throws
   */
  public List<Role> findRoleByRoleName(String roleName);
  /**
   * 根据角色删除功能
   * @param roleId
   */
  public void deleteRoleFunction(int roleId);
  /**
   * 插入角色功能表
   * @param roleId
   * @param functionId
   */
  public void insertRoleFunction(int roleId,int functionId);

}
