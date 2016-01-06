package com.base.util;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * 项目名称：base-spring-framework 类名称：SearchConditionUtil 类描述： 查询条件封装 创建人：LiMingYi 创建时间：2015年2月6日
 * 下午2:38:58 修改人：LiMingYi 修改时间：2015年2月6日 下午2:38:58 修改备注：
 * 
 * @version
 * 
 */
public class SearchConditionUtil {

  public static <T> Map<String, Object> packageSearchCondion(HttpServletRequest request) {
    Map<String, Object> searchCondionMap = new HashMap<String, Object>();
    Enumeration<String> paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String paramName = (String) paramNames.nextElement();

      String[] paramValues = request.getParameterValues(paramName);
      if (paramValues.length == 1) {
        String paramValue = paramValues[0];
        if (paramValue.length() != 0) {
          searchCondionMap.put(paramName, paramValue);
        }
      }
    }
    return searchCondionMap;
  }
}
