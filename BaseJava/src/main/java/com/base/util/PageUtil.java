package com.base.util;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
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
