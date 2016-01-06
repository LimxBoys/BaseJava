package com.base.util;

import java.security.MessageDigest;
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
}
