package com.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.base.dao.FunctionDAO;
import com.base.dao.RoleDAO;
import com.base.model.Function;
import com.base.model.Role;
import com.base.util.UrlPathMatcher;

/**
 *这个用来加载资源与权限的全部对应关系的，并提供一个通过资源获取所有权限的方法。 
 *首先，这里也是模拟了从数据库中获取信息。
 *其中loadResourceDefine方法不是必须的
 * ，这个只是加载所有的资源与权限的对应关系并缓存起来，避免每次获取权限都访问数据库（提高性能），然后getAttributes根据参数
 * （被拦截url）返回权限集合。
 * 这种缓存的实现其实有一个缺点，因为loadResourceDefine方法是放在构造器上调用的，而这个类的实例化只在web服务器启动时调用一次
 * ，那就是说loadResourceDefine方法只会调用一次
 * ，如果资源和权限的对应关系在启动后发生了改变，那么缓存起来的就是脏数据，现在这里使用的就是缓存数据
 * ，那就会授权错误了。但如果资源和权限对应关系是不会改变的，这种方法性能会好很多。
 *	在getAttributes方法里面调用dao（这个是加载完，后来才会调用的，所以可以使用dao）
 *  ，通过被拦截url获取数据库中的所有权限，封装成Collection<ConfigAttribute>返回就行了。（灵活、简单）
 * @author limingxing
 * @2015-12-3
 * version 1.0v
 */
public class MySecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	@Autowired
	private FunctionDAO functionDAO;
	@Autowired
	private RoleDAO roleDAO;
	private UrlPathMatcher urlMatcher = new UrlPathMatcher();

	/**
	 * @PostConstruct是Java EE 5引入的注解， Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 * 
	 *加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Role> roles = roleDAO.findAllEffectiveRoles();
			for (Role role : roles) {
				List<Function> functions = this.functionDAO
						.findAllEffectiveFunctionsbyRoleId(role.getId());
				for (Function function : functions) {
					Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
					ConfigAttribute configAttribute = new SecurityConfig(
							role.getRoleName());
					configAttributes.add(configAttribute);
					String url=function.getAction();
					 if (resourceMap.containsKey(url)) {

		                    Collection<ConfigAttribute> value = resourceMap.get(url); //取出这个url的权限集合
		                    value.add(configAttribute);
		                    resourceMap.put(url, value);
		                } else {
		                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		                    atts.add(configAttribute);
		                    resourceMap.put(url, atts);
		                }
				}
			}
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 参数是要访问的url，返回这个url对应的所有权限（或角色）
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		loadResourceDefine();
		// object getRequestUrl 是获取用户请求的url地址
		String url = ((FilterInvocation) object).getRequestUrl();

		// resourceMap保存了loadResourceDefine方法加载进来的数据
		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext()) {

			// 取出resourceMap中读取数据库的url地址
			String resURL = ite.next();

			// 如果两个
			// url地址相同，那么将返回resourceMap中对应的权限集合，然后跳转到MyAccessDecisionManager类里的decide方法，再判断权限
			if (urlMatcher.pathMatchesUrl(url, resURL)) {
				return resourceMap.get(resURL); // 返回对应的url地址的权限
				// ，resourceMap是一个主键为地址，值为权限的集合对象
			}
		}

		// 如果上面的两个url地址没有匹配，返回return
		// null，不再调用MyAccessDecisionManager类里的decide方法进行权限验证，代表允许访问页面
		return null;
	}

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
