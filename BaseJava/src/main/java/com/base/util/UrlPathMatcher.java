package com.base.util;

public class UrlPathMatcher {

	public boolean pathMatchesUrl(String url,String resURL){
		
		if(url.equals(resURL)){
			return true;
		}		
		return false;
	}
	
}
