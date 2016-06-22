package com.base.test.single;

/**
 * 第四种（饿汉，变种）：
 * 
 * @author Administrator
 * 
 */
public class Single4 {
	private static Single4 instance = null;
	static {
		instance = new Single4();
	}

	private Single4() {
	}

	public static Single4 getInstance() {
		return instance;
	}
}
