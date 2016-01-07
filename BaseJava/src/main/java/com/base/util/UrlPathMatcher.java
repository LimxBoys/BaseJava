package com.base.util;
/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public class UrlPathMatcher {

	public boolean pathMatchesUrl(String url,String resURL){
		
		if(url.equals(resURL)){
			return true;
		}		
		return false;
	}
	
}
