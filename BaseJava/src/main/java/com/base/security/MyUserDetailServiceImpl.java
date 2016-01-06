package com.base.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.base.dao.RoleDAO;
import com.base.dao.UserDao;
import com.base.model.Role;
import com.base.model.User;


/**
 *通过MyUserDetailServiceImpl拿到用户信息后，authenticationManager对比用户的密码（即验证用户）
 *
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 * 
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 权限验证类
 * @author lanyuan
 * 2013-11-19
 * @author limingxing
 * @2015-12-2
 * version 1.0v
 */
public class MyUserDetailServiceImpl implements UserDetailsService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDAO roleDAO;
	
	/**
	 *  登陆验证时，通过username获取用户的所有权限信息，
	 *   并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	 */
	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {
		User user=null;
		List<User> list=userDao.findUserByLoginName(loginName);
		if(list!=null&&list.size()!=0)
			user=list.get(0);
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		org.springframework.security.core.userdetails.User userdetail = new org.springframework.security.core.userdetails.User(
				user.getLoginName(), user.getPassword(), true, true, true, true,
				grantedAuths);
		return userdetail;
	}
	/**
	 *取得用户的权限
	 * @param user
	 * @return
	 */
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		List<Role> roles=roleDAO.findRolesByLoginName(user.getLoginName());
			Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
			for (Role res : roles) {
				// 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
				authSet.add(new SimpleGrantedAuthority(res.getRoleName()));
			}
			return authSet;
		}
}
