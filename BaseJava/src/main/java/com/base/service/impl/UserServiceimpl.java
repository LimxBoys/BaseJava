package com.base.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.base.dao.RoleDAO;
import com.base.dao.UserDao;
import com.base.model.Role;
import com.base.model.User;
import com.base.service.UserService;
import com.base.util.ConstantUtil;
import com.base.vo.UserRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("userService")
public class UserServiceimpl implements UserService {
	private static final Logger logger = Logger
			.getLogger(UserServiceimpl.class);
	@Autowired
	private UserDao userDao;

	public List<User> findall() {
		// TODO Auto-generated method stub
		return userDao.findall();
	}

	public PageInfo<User> findbypage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userDao.findall();
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	public int insertUser1(User user) {
		return userDao.insertUser1(user);
	}

	@Autowired
	private RoleDAO roleDAO;

	public void insertUser(User user) {
		User initUser = findUserByLoginName(user.getLoginName());
		if (initUser == null){
			userDao.insertUser(user);
			System.out.println(1222);
			}
	}

	public void deleteUser(Integer userId) {
		userDao.deleteUserRole(userId);
		userDao.deleteUser(userId);

	}

	public void deleteUser(Integer userId, String loginName) {
		deleteUser(userId);

	}

	public void updateUser(User user) {

		userDao.updateUser(user);

	}

	public void updateRoleschecked(int userId, String roleIds) {

		logger.info("roleIds:" + roleIds);
		// 删除旧关系
		userDao.deleteUserRole(userId);

		if (StringUtils.isEmpty(roleIds))
			return;
		// 建立新关系
		String[] ids = roleIds.split(",");
		for (String roleId : ids) {
			userDao.insertUserRole(userId, Integer.valueOf(roleId));
		}

	}

	public void updatePassword(String loginName, String oldPassword,
			String password) {
		User user = findUserByLoginName(loginName);
		userDao.updatePassword(user);
	}

	public void updateState(int id, int state) {
		state = state == ConstantUtil.OK ? ConstantUtil.FAIL : ConstantUtil.OK;
		userDao.updateState(id, state);

	}

	public User findUserByLoginName(String loginName) {
		logger.info("loginName:" + loginName);
		List<User> list = userDao.findUserByLoginName(loginName);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	public User findUserByUserPhone(String userPhone) {
		logger.info("userPhone:" + userPhone);
		List<User> list = userDao.findUserByUserPhone(userPhone);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	public PageInfo<User> findUserList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userDao.finduserList();
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	public PageInfo<User> findUserList(User user, int pageNum, int pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userDao.findUserListByQuery(user);
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	public List<UserRole> findRoleList(int userId) {

		List<UserRole> userRoles = new ArrayList<UserRole>();
		List<Role> roles = roleDAO.findAllEffectiveRoles();
		List<Integer> myRoles = userDao.findRolesByUserId(userId);

		for (Role role : roles) {
			UserRole userRole = new UserRole();
			userRole.setId(role.getId());
			userRole.setName(role.getRoleName());
			for (Integer myid : myRoles) {
				if (role.getId().equals(myid))
					userRole.setChecked(true);
			}
			userRoles.add(userRole);
		}

		return userRoles;
	}

	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	public void insertUserRole(int userId, int roleId) {
		userDao.insertUserRole(userId, roleId);
	}

	public void insertUserForCasServer(User user) {
		User initUser = findUserByLoginName(user.getLoginName());
		if (initUser != null)
			user.setState(ConstantUtil.OK);
		user.setInsertTime(new Timestamp(System.currentTimeMillis()));
		userDao.insertUser(user);
	}

	public void deleteUserRole(Integer userId) {
		// TODO Auto-generated method stub
		userDao.deleteUserRole(userId);
	}

}
