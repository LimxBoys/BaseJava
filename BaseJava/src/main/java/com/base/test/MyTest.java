/*package com.base.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.base.model.User;
import com.base.service.UserService;
import com.base.util.MD5Util;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

*//**
 * spring的单元测试
 * 
 * @author limingxing
 * 
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring.xml")
public class MyTest {
	@Autowired
	private UserService userService;
	@Autowired
	private MemCachedClient memCachedClient;
	@Test
	public void test() {
		List<User> list = userService.findall();
		for (User user : list) {
			System.out.println("username=" + user.getUsername());
		}
	}

	@Test
	public void test1() {
		for (int i = 0; i < 100; i++) {

			User user = new User();
			user.setUsername("limingxing" + i);
			user.setPassword("limx" + i);

			System.out.println(userService.insertUser1(user));
			System.out.println(user.getId());
		}
	}

	@Test
	public void test001() {
		MemCachedClient client = new MemCachedClient();
		String[] addr = { "127.0.0.1:11211" };
		Integer[] weights = { 3 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();

		// String [] s =pool.getServers();
		client.setCompressEnable(true);
		client.setCompressThreshold(1000 * 1024);

		// 将数据放入缓存
		client.set("test2", "test2");

		// 将数据放入缓存,并设置失效时间
		Date date = new Date(2000000);
		client.set("test1", "test1", date);

		// 删除缓存数据
		// client.delete("test1");

		// 获取缓存数据
		String str = (String) client.get("test1");
		System.out.println(str);
	}
	@Test
	public void test123(){
		memCachedClient.set("test", "limingxing");
		String str = (String) memCachedClient.get("test");
		System.out.println(str);
	}
	@Test
	public void test12(){
		System.out.println(MD5Util.encode("123"));
	}
}
*/