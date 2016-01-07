package com.base.controller;
/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:56:27
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.service.AreaService;
import com.base.vo.BaseArea;
import com.base.vo.Criteria;

@Controller
@RequestMapping(value = "/area")
public class AreaController {
	@Autowired
	private AreaService areaService;

	@RequestMapping("/area")
	public String area() {
		return "/views/ly/area";
	}

	/***
	 * 省份查询
	 * */
	@RequestMapping("/provinceQuery")
	@ResponseBody
	public Object provinceQuery(Criteria paramCriteria) {
		List<BaseArea> listDept = areaService.findProvince();
		System.out.println(listDept);
		return listDept;
	}

	/**
	 * 城市查询
	 * **/
	@RequestMapping("/provinceCity")
	@ResponseBody
	public Object provinceCity(HttpSession session, Criteria paramCity,
			String province) {
		Criteria criteria = new Criteria();
		criteria.put("province", province);
		List<BaseArea> listCity = areaService.findCity(criteria);
		return listCity;
	}

	/**
	 * 县城查询
	 * */
	@RequestMapping("/provinceCounty")
	@ResponseBody
	public Object provinceCounty(HttpSession session, Criteria paramCity,
			String city) {
		Criteria criteria = new Criteria();
		criteria.put("city", city);
		List<BaseArea> listCount = areaService.findCounty(criteria);
		return listCount;
	}
}
