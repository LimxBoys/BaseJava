package com.base.util;


import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public class ResponseUtil {

  public static <T> Map<String, Object> success() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", true);
    return map;
  }
  
  public static <T> Map<String, Object> success(String message) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", true);
    map.put("msg", message);
    return map;
  }
  
  
  public static <T> Map<String, Object> fail(String message) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", false);
    map.put("err", message);
    return map;
  }
}
