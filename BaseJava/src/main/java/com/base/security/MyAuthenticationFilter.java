package com.base.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.base.dao.UserDao;
import com.base.model.User;
import com.base.util.MD5Util;

public class MyAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	@Autowired
	private UserDao userDao;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);
		password=MD5Util.encode(password);
		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = null;

		List<User> list = userDao.findUserByLoginName(username);
		

		try {
			if (list == null || !list.get(0).getPassword().equals(password)) {
				BadCredentialsException exception = new BadCredentialsException(
						"用户名或密码不匹配");
				throw exception;
			}
		} catch (Exception e) {
			if(list==null||list.size()==0){
				UsernameNotFoundException exception=new UsernameNotFoundException("用戶名不存在");
				throw exception;
			}else{
			BadCredentialsException exception = new BadCredentialsException(
					"用户名或密码不匹配");
			throw exception;}
		}

		// 实现验证
		authRequest = new UsernamePasswordAuthenticationToken(username,
				password);
		// 允许设置用户详细属性
		setDetails(request, authRequest);
		// 运行
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		super.setDetails(request, authRequest);
	}
}
