package com.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.service.UserService;
import com.base.util.EmailUtil;
import com.base.util.ExcelUtil;
import com.base.util.ExcelUtil5;

@Controller
@RequestMapping("/test")
public class TestController {
	private Log log=LogFactory.getLog(TestController.class);
	@Autowired
	private UserService userService;
	@RequestMapping("/excel")
	@ResponseBody
	public Object excel(HttpServletResponse response) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String title = "test";
		try {
			// 第一个sheet
			Map<String, Object> listMap = new HashMap<String, Object>();
			List<Map<String, Object>> listdata = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("t1", "t1");
			map.put("t2", "t2");
			map.put("t3", "t3");
			map.put("t4", "t4");
			Map<String, Object> map1 = new LinkedHashMap<String, Object>();
			map1.put("t1", "t1");
			map1.put("t2", "t2");
			map1.put("t3", "t3");
			map1.put("t4", "t4");
			listdata.add(map);
			listdata.add(map1);
			listMap.put("title", "sheet1");
			listMap.put("head", new String[] { "列一", "列二", "列三", "列四" });
			listMap.put("list", listdata);
			list.add(listMap);
			// 第二个sheet
			Map<String, Object> listMap1 = new HashMap<String, Object>();
			List<Map<String, Object>> listdata1 = new ArrayList<Map<String, Object>>();
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("t1", "t1");
			map2.put("t2", "t2");
			map2.put("t3", "t3");
			map2.put("t4", "t4");
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("t1", "t1");
			map3.put("t2", "t2");
			map3.put("t3", "t3");
			map3.put("t4", "t4");
			listdata1.add(map2);
			listdata1.add(map3);
			listMap1.put("title", "sheet2");
			listMap1.put("head", new String[] { "列一", "列二", "列三", "列四" });
			listMap1.put("list", listdata1);
			list.add(listMap1);
			ExcelUtil5.exportExcel(response, list, title);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@RequestMapping("/testmap")
	@ResponseBody
	public Object testMap() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "value1");
		map.put("2", "value2");
		map.put("3", "value3");
		Map<String,Object> map1=null;
		try {
			System.out.println(map1.get("a"));
		} catch (Exception e) {
			log.info("日志记录：**************com.base.controller.TestController.testMap.......出错");
			return false;
		}
		// 第一种：普遍使用，二次取值
		System.out.println("通过Map.keySet遍历key和value：");
		for (String key : map.keySet()) {
			System.out.println("key= " + key + " and value= " + map.get(key));
		}

		// 第二种
		System.out.println("通过Map.entrySet使用iterator遍历key和value：");
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}

		// 第四种
		System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
		for (String v : map.values()) {
			System.out.println("value= " + v);
		}
		return true;
	}
	@RequestMapping("/error")
	@ResponseBody
	public Object error(){
		
		return userService.findall();
	}
	@RequestMapping("/email")
	@ResponseBody
	public Object email(){
		EmailUtil.sendhtmlemail("657713230@qq.com", "您好", "你好");
		return "";
	}
}
