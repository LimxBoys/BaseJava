package com.base.util;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
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
