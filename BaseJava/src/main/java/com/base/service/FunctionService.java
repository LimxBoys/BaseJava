package com.base.service;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.base.model.Function;
import com.base.vo.FunctionTree;


/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public interface FunctionService {


  
  /**
   * 
   * @Title: 增加Function
   * @Description: 新增角色
   * @param @param function Function对象
   * @return void 返回类型
   * @throws
   */
  public void insertFunction(Function function);


  /**
   * 
  * @Title: deleteFunction
  * @Description: 删除功能
  * @param @param id    id
  * @return void    返回类型
  * @throws
   */
  public void deleteFunction(int id);

  /**
   * 
   * @Title: updateFunction
   * @Description: 更新用户
   * @param @param function
   * @param @return
   * @throws
   */
  public void updateFunction(Function function);

  

  /**
   * 开启、关闭function
  * @Title: updateState
  * @Description: 开启、关闭function
  * @param @param id
  * @param @param state
  * @return void    返回类型
  * @throws
   */
  public void updateState(int id, int state);
  
  /**
   * 
  * @Title: updateFunctionschecked
  * @Description: 选择角色功能
  * @param @param roleId roleId 
  * @param @param functionIds    功能Ids
  * @return void    返回类型
  * @throws
   */
  public void updateFunctionschecked(int roleId, String functionIds);
  /**
   * 
   * @Title: findAllEffectiveFunctionsbyLoginName
   * @Description: 查询function
   * @param @param loginName 登录名
   * @return Function Function对象
   * @throws
   */
  public List<Function> findAllEffectiveFunctionsbyLoginName(String loginName);


  /**
   * 
   * @Title: findAllEffectiveFunctionsbyRoleId
   * @Description: 根据roleId查询有效的functions
   * @param @param roleId
   * @param @return 设定文件
   * @return List<Function> 返回类型
   * @throws
   */
  public List<Function> findAllEffectiveFunctionsbyRoleId(Integer roleId);
  
  
  /**
   * 
  * @Title: findFunctionList
  * @Description: 分页查询所有功能列表
  * @param @param pageNum 当前页
  * @param @param pageSize 每页数量
  * @param @return    设定文件
  * @return PageInfo<Function>    返回类型
  * @throws
   */
  public PageInfo<Function> findFunctionList(int pageNum,int pageSize);
  

  /**
   * 
  * @Title: findFunctionList
  * @Description: 条件分页查询所有功能列表
  * @param @param function 
  * @param @param pageNum 当前页
  * @param @param pageSize 每页数量
  * @param @return    设定文件
  * @return PageInfo<Function>    返回类型
  * @throws
   */
  public PageInfo<Function> findFunctionList(Function function, int pageNum, int pageSize);
  /**
   * 
  * @Title: findEffectiveFunctionsbyAction
  * @Description: 查询functionId
  * @param @param action action请求
  * @param @return    设定文件
  * @return List<Integer>    返回类型
  * @throws
   */
  public List<Integer> findEffectiveFunctionsbyAction(String action);

  
  /**
   * 
  * @Title: findFunctionTee
  * @Description: 查询角色下对应的功能
  * @param：roleId
  * @return List<FunctionTree>     返回类型
  * @throws
   */  
  public List<FunctionTree> findFunctionTree(int roleId);


  /**
   * 
  * @Title: findRootList
  * @Description: 含选择的function树
  * @param @param id 当前functionId
  * @param @return
  * @return List<FunctionTree>    返回类型
  * @throws
   */
  public List<FunctionTree> findRootList(String id);

  /**
   * 
  * @Title: findFunctionbyParentIdAndFunctionName
  * @Description: 根据functionName和parentId查询列表
  * @param @param function
  * @param @return
  * @return List<Function>    返回类型
  * @throws
   */
  public List<Function> findFunctionbyParentIdAndFunctionName(Function function);
  /**
   * 
   * @Title: findEffectiveFunctinList
   * @Description: 查询所有有效的功能权限列表
   * @return List<Function> 有效的功能权限列表功能列表
   * @throws
   */
  public List<Function> findEffectiveFunctinList();

}
