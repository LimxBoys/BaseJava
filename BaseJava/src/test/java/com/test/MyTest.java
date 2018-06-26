package com.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.base.model.FunctionVo;
import com.base.service.UserService;
import com.base.util.tree.TreeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring.xml")
public class MyTest {
	@Autowired
	private UserService userService;
	@Test
	public void test() {
		
		List<FunctionVo> list=new ArrayList<FunctionVo>();
		FunctionVo functionVo=new FunctionVo();
		functionVo.setCode("000");
		functionVo.setPid("");
		functionVo.setName("一级菜单");
		list.add(functionVo);
		FunctionVo functionVo1=new FunctionVo();
		functionVo1.setCode("001");
		functionVo1.setPid("000");
		functionVo1.setName("二级菜单");
		list.add(functionVo1);
		FunctionVo functionVo2=new FunctionVo();
		functionVo2.setCode("002");
		functionVo2.setPid("000");
		functionVo2.setName("二级菜单");
		list.add(functionVo2);
		String result = TreeUtil.buildTree(list, FunctionVo.class);
		System.out.println("result="+result);
	}
	public static void main(String[] args) {
		List<FunctionVo> list=new ArrayList<FunctionVo>();
		FunctionVo functionVo=new FunctionVo();
		functionVo.setCode("000");
		functionVo.setPid("");
		functionVo.setName("一级菜单");
		list.add(functionVo);
		FunctionVo functionVo1=new FunctionVo();
		functionVo1.setCode("001");
		functionVo1.setPid("000");
		functionVo1.setName("二级菜单");
		list.add(functionVo1);
		FunctionVo functionVo2=new FunctionVo();
		functionVo2.setCode("002");
		functionVo2.setPid("000");
		functionVo2.setName("二级菜单");
		list.add(functionVo2);
		String result = TreeUtil.buildTree(list, FunctionVo.class);
		System.out.println("result="+result);
	}

}
