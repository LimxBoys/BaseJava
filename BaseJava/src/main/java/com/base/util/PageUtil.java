package com.base.util;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
 * 
 * 项目名称：base_spring_framework 类名称：PageUtil 类描述： 封装list展示 创建人：limingxing
 * 创建时间：2015年2月6日 下午1:43:58 修改人：limingxing 修改时间：2015年2月6日 下午1:43:58 修改备注：
 * 
 * @version
 * 
 */
public class PageUtil {

	private static String TOTAL = "total";
	private static String DATA = "rows";

	public static <T> Map<String, Object> convertGrid(PageInfo<T> page) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (page != null) {
			map.put(TOTAL, page.getTotal());
			map.put(DATA, page.getList());
			return map;
		} else
			return null;
	}
}
