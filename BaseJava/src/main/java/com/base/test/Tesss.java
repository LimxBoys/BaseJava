package com.base.test;

import groovy.swing.factory.BeanFactory;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.base.model.User;

public class Tesss {
	public static void main(String[] args) throws Exception {
		ClassPathResource res=new ClassPathResource("config/spring.xml");
		DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
		System.out.println(res.contentLength());
		reader.loadBeanDefinitions(res);
		System.out.println(reader.getBeanFactory().getBeanDefinitionCount());
		String[] beanNames=reader.getBeanFactory().getBeanDefinitionNames();
		for(String beanName:beanNames){
			System.out.println(beanName);
		}
		BeanDefinition beanDef=reader.getBeanFactory().getBeanDefinition("sqlSessionFactory");
		System.out.println(beanDef.getFactoryBeanName());
		System.out.println(beanDef.getAttribute("class"));
		System.out.println(beanDef.getParentName());
		MutablePropertyValues values=beanDef.getPropertyValues();
		Connection connection=GetConnection.GetConnection();
		System.out.println(connection);
	}
}
