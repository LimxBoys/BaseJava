package com.base.test.single;

/**
 * 第一种（懒汉，线程不安全）：
 * 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。
 * @author Administrator
 * 
 */
public class Single1 {
	private static Single1 instance;

	private Single1() {
	}

	public static Single1 getInstance() {
		if (instance == null) {
			instance = new Single1();
		}
		return instance;
	}
}