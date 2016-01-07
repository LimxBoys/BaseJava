package com.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.model.Function;
 
 
/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public interface FunctionDAO {
 
  
    /*###############-------insert区域-------##################*/
    /**
     * 
    * @Title: insertFunction
    * @Description: 新增功能
    * @param @param function
    * @return 
    * @throws
     */
    public void insertFunction(Function function);
     
    /**
     * 
    * @Title: insertFunctionRole
    * @Description: 插入用户、角色关联
    * @param @param functionId 功能ID
    * @param @param roleId 角色ID
    * @return void    返回类型
    * @throws
     */
    public void insertFunctionRole(@Param("functionId") int functionId,@Param("roleId") int roleId);
    
    /**
     * 
    * @Title: deleteFunction
    * @Description: 删除功能表
    * @param @param id    id
    * @return void    返回类型
    * @throws
     */
    /*###############-------insert区域-------##################*/
    
    
    
    
    
    
  /*###############-------delete区域-------##################*/
    public void deleteFunction(int id);
    
    /**
     * 
    * @Title: deleteFunctionRole
    * @Description: 删除关联表
    * @param @param functionId   功能ID
    * @return void    返回类型
    * @throws
     */
    public void deleteFunctionRole(int functionId);
    
    /**
     * 
    * @Title: deleteFunctionRole
    * @Description: 删除关联表
    * @param: roleId    角色id
    * @return void    返回类型
    * @throws
     */
    public void deleteRoleFunction(int roleId);
    
  /*###############-------delete区域-------##################*/
    
    
    
   /*###############-------update区域-------##################*/
    /**
     * 修改function状态
    * @Title: updateState
    * @Description: 修改function状态
    * @param @param id functionId
    * @param @param state 状态
    * @return void    返回类型
    * @throws
     */
    public void updateState(@Param("id")int id, @Param("state")int state);
    
    
    /**
     * 修改function
    * @Title: updateFunction
    * @Description: 修改
    * @param @param function
    * @return void    返回类型
    * @throws
     */
    public void updateFunction(Function function);
    
   /*###############-------update区域-------##################*/
    
    
    
    
    
  /*###############-------find区域-------##################*/
    
    /**
     * 加载function
    * @Title: findFunction
    * @Description: 加载function
    * @param @param id
    * @param @return
    * @return Function    返回类型
    * @throws
     */
    public Function findFunction(int id);
    
    /**
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
     * @Title: findEffectiveFunctinList
     * @Description: 查询所有有效的功能权限列表
     * @return List<Function> 有效的功能权限列表功能列表
     * @throws
     */
    public List<Function> findEffectiveFunctinList();

    
    /**
     * 
    * @Title: findAllEffectiveFunctionsbyLoginName
    * @Description: 查询当前用户的权限
    * @param @param loginName 登陆名
    * @param @return    设定文件
    * @return List<Function>    返回类型
    * @throws
     */
    public List<Function> findAllEffectiveFunctionsbyLoginName(String loginName);
    
    /**
     * 
    * @Title: findFunctionList
    * @Description: 查询所有功能模块
    * @param @return    设定文件
    * @return List<Function>    功能列表
    * @throws
     */
    public List<Function> findFunctionList();
    
    
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
    * @Title: findFunctionListByfNameAndAction
    * @Description: 条件查询function list
    * @param @param function
    * @param @return    设定文件
    * @return List<Function>    返回类型
    * @throws
     */
    public List<Function> findFunctionListByQuery(Function function);

    /**
     * 
    * @Title: findFunctionTee
    * @Description: 查询角色下对应的功能
    * @param：roleId
    * @return List<FunctionTree>     返回类型
    * @throws
     */  
    public List<Integer> findFunctionTree(int roleId);
    

    /**
     * 
    * @Title: findFunctionbyParentId
    * @Description: 查询孩子节点
    * @param @param id
    * @param @return    设定文件
    * @return List<Function>    返回类型
    * @throws
     */
    public List<Function> findFunctionbyParentId(int id);
    

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
    
    /*###############-------find区域-------##################*/

}