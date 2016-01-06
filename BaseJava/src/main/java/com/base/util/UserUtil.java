package com.base.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtil {
	
	public static User findSessionUser(){

		//获取上下文
		SecurityContext securityContext=SecurityContextHolder.getContext();
		//获取认证对象
		Authentication auth=securityContext.getAuthentication();
		//在认证对象中获取主题对象
		Object principal=auth.getPrincipal();
		String username="";
		if(principal instanceof UserDetails){
			username=((UserDetails) principal).getUsername();
		}else{
			username=principal.toString();
		}
		return (User) principal;
	}
}
