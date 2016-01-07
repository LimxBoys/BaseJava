package com.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.model.Role;


/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public interface RoleDAO {

  /**
   * 
   * @Title: insertRole
   * @Description: 新增角色
   * @param: role
   * @return
   * @throws
   */
  public void insertRole(Role role);


  /**
   * 
   * @Title: updateRole
   * @Description: 修改角色
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
   * @Description: 删除角色
   * @param: roleId 角色id
   * @return: void 返回类型
   * @throws
   */
  public void deleteRole(Integer roleId);

  /**
   * 
   * @Title: deleteFunctionRole
   * @Description: 删除角色功能关联表
   * @param: roleId 角色id
   * @return: void 返回类型
   * @throws
   */
  public void deleteFunctionRole(Integer roleId);

  /**
   * 
   * @Title: findAllEffectiveRole
   * @Description: 查询所有有效的角色
   * @param: 设定文件
   * @return: List<Role> 返回类型
   * @throws
   */
  public List<Role> findAllEffectiveRoles();

  /**
   * 
   * @Title: findRolesByLoginName
   * @Description: 根据登陆名查询角色
   * @param: loginName
   * @return: List<Role> 返回类型
   * @throws
   */
  public List<Role> findRolesByLoginName(String loginName);


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
   * @param: tempMap [roleName:角色名称]
   * @return: List<Role> 返回类型
   * @throws
   */
  public List<Role> findRoleList(Map<String, Object> tempMap);


  /**
   * 
   * @Title: findRoleById
   * @Description: 根据roleId查询角色信息
   * @param: roleId 角色id
   * @return: Role 返回类型
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
  public void deleteRoleFunction(@Param("roleId")int roleId);
  /**
   * 插入角色功能表
   * @param roleId
   * @param functionId
   */
  public void insertRoleFunction(@Param("roleId")int roleId,@Param("functionId")int functionId);
}
