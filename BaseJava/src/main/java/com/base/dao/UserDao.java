package com.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.model.User;

public interface UserDao {
	List<User> findall();
	int insertUser1(User user);

    /**
     * 
    * @Title: insertUser
    * @Description: 新增用户
    * @param @param user
    * @return 
    * @throws
     */
    public void insertUser(User user);
     
    /**
     * 
    * @Title: insertUserRole
    * @Description: 插入用户、角色关联
    * @param @param userId 用户ID
    * @param @param roleId 角色ID
    * @return void    返回类型
    * @throws
     */
    public void insertUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 
    * @Title: deleteUser
    * @Description: 删除用户
    * @param @param userId    用户id
    * @return void    返回类型
    * @throws
     */
    public void deleteUser(Integer userId);
    
    
    /**
     * 
    * @Title: updateUser
    * @Description: 修改用户
    * @param @param user    用户
    * @return void    返回类型
    * @throws
     */
    public void updateUser(User user);
    /**
     * 
    * @Title: deleteUserRole
    * @Description: 删除关联表
    * @param @param userId    用户id
    * @return void    返回类型
    * @throws
     */
    public void deleteUserRole(Integer userId);
    
    /**
     * 
    * @Title: updatePassword
    * @Description: 修改密码
    * @param @param user   user
    * @return void    返回类型
    * @throws
     */
    public void updatePassword(User user);
    
    
    
    /**
     * 
    * @Title: updateState
    * @Description: 冻结、解冻账号
    * @param @param id 主键
    * @param @param state 修改后的状态   
    * @return void    返回类型
    * @throws
     */
    public void updateState(@Param("id")int id,@Param("state")int state);
    
    
    /**
     * 
    * @Title: finduserList
    * @Description: 查询使用用户
    * @param @return    用户列表
    * @return List<User>    返回类型
    * @throws
     */
    public List<User> finduserList();
    
    /**
     * 
    * @Title: findUserListByQuery
    * @Description: 条件查询user
    * @param @param user
    * @param @return    设定文件
    * @return List<User>    返回类型
    * @throws
     */
    public List<User> findUserListByQuery(User user);
    
    /**
     * 
    * @Title: findRolesByUserId
    * @Description: 查询当前用户关联的角色ID
    * @param @param userId 用户ID
    * @param @return    设定文件
    * @return List<Integer>    返回类型
    * @throws
     */
    public List<Integer> findRolesByUserId(int userId);
    
    
    /**
     * 
    * @Title: findUserByLoginName
    * @Description: 查询user
    * @param @param loginName
    * @param @return    设定文件
    * @return List<User>    返回类型
    * @throws
     */
    public List<User> findUserByLoginName(String loginName);
    
    /**
     * 
    * @Title: findUserByEmail
    * @Description: 查询user
    * @param @param email
    * @param @return
    * @return User    返回类型
    * @throws
     */
    public User findUserByEmail(String email);
    
    /**
     * 
    * @Title: findUserById
    * @Description: 根据ID加载对象
    * @param @return
    * @return User    返回类型
    * @throws
     */
    public User findUserById(int id);
    
    public List<User> findUserByUserPhone(String userPhone);
    
}
