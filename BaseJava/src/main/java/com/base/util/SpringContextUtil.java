package com.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 获取spring上下文工具类
 * @author limingxing
 * @Date:2016-1-19上午9:36:04
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

private static ApplicationContext applicationContext;
	
	private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);
	/**
	 * 实现ApplicationContextAware接口的context注入函数，将其存入静态变量
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
		logger.debug("SpringContextUtil注入ApplicationContext");
	}

	/**
	 * 获取存储在静态变量中的ApplicationContext
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中获取Bean，自动转型为所赋值对象的类型
	 * @param <T>
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz){
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}
	/**
	 * 清除ApplicationContext静态变量.
	 */
	public static void cleanApplicationContext(){
		applicationContext=null;
	}
	/**
	 * 检查是否获取到了ApplicationContext
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException(
					"spring未注入,请在spring*.xml中定义SpringContextUtil");
		}
	}
	}