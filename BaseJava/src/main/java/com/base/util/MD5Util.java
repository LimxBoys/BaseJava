package com.base.util;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public class MD5Util {
	public static String encode(String value){
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			byte [] bytes = messageDigest.digest(value.getBytes());
			return new String(Hex.encode(bytes));
		}catch(Exception e){
			e.getStackTrace();
		}
		return null;
	}
	public static String encrypt(String username, String password) {
		return DigestUtils.md5Hex(username + "{" + password + "}");
	}
}
