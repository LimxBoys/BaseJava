package com.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.base.model.User;
import com.base.vo.UserRole;
import com.github.pagehelper.PageInfo;

public interface UserService {

	List<User> findall();
	PageInfo<User> findbypage(int pageNum, int pageSize);
	int insertUser1(User user);

	/**
	 * 
	 * @Title: 增加USER
	 * @Description: 新增用户
	 * @param @param user user对象
	 * @return void 返回类型
	 * @throws
	 */
	public void insertUser(User user);

	/**
	 * 刪除用戶
	 * 
	 * @Title: deleteUser
	 * @Description: 刪除用戶
	 * @param @param userId 用户ID
	 * @return void 返回类型
	 * @throws
	 */
	public void deleteUser(Integer userId);

	/**
	 * 
	 * @Title: deleteUser
	 * @Description: 删除用户
	 * @param @param userId
	 * @param @param loginName 用户
	 * @return void 返回类型
	 * @throws
	 */
	public void deleteUser(Integer userId, String loginName);

	/**
	 * 
	 * @Title: updateUser
	 * @Description: 更新用户
	 * @param @param user
	 * @param @return
	 * @throws
	 */
	public void updateUser(User user);

	/**
	 * 
	 * @Title: updatePassword
	 * @Description: 修改密码
	 * @param @param loginName 登录名
	 * @param @param oldPassword 旧密码
	 * @param @param password 新密码
	 * @throws
	 */
	public void updatePassword(String loginName, String oldPassword,
			String password);

	/**
	 * 
	 * @Title: updateState
	 * @Description: 冻结或者解冻用户账号
	 * @param @param id
	 * @param @param state 当前状态
	 * @return void 返回类型
	 * @throws
	 */
	public void updateState(int id, int state);

	/**
	 * 
	 * @Title: updateRoleschecked
	 * @Description: 选择用户角色
	 * @param @param userId userid
	 * @param @param roleIds 角色集合
	 * @return void 返回类型
	 * @throws
	 */
	public void updateRoleschecked(int userId, String roleIds);

	/**
	 * 
	 * @Title: findUserList
	 * @Description: 查询所有用户
	 * @param @param pageNum 当前页
	 * @param @param pageSize 每页显示数量
	 * @param @return
	 * @return PageInfo<User> 返回类型
	 * @throws
	 */
	public PageInfo<User> findUserList(int pageNum, int pageSize);

	/**
	 * 
	 * @Title: findUserList
	 * @Description: 条件查询user
	 * @param @param user
	 * @param @param pageNum 当前页
	 * @param @param pageSize 每页显示数量
	 * @param @return 设定文件
	 * @return PageInfo<User> 返回类型
	 * @throws
	 */
	public PageInfo<User> findUserList(User user, int pageNum, int pageSize);

	/**
	 * 
	 * @Title: findRoleList
	 * @Description: 查询当前用户的角色列表
	 * @param @param userId 用户ID
	 * @param @return
	 * @return List<UserRole> 返回类型
	 * @throws
	 */
	public List<UserRole> findRoleList(int userId);

	/**
	 * 
	 * @Title: findUserByLoginName
	 * @Description: 查询user
	 * @param @param loginName 登录名
	 * @return User User对象
	 * @throws
	 */
	public User findUserByLoginName(String loginName);
	/**
	 * 添加用户角色关联
	 * 
	 * @Title: insertUserRole
	 * @Description: TODO
	 * @param @param userId
	 * @param @param roleId
	 * @return void
	 * @throws
	 */
	public void insertUserRole(int userId, int roleId);

	public User findUserByUserPhone(String userPhone);

	public void insertUserForCasServer(User user);
	  /**
     * 
    * @Title: deleteUserRole
    * @Description: 删除关联表
    * @param @param userId    用户id
    * @return void    返回类型
    * @throws
     */
    public void deleteUserRole(Integer userId);
}
