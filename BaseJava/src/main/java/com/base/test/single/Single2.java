package com.base.test.single;

/**
 * 第二种（懒汉，线程安全）： 这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading，
 * 但是，遗憾的是，效率很低，99%情况下不需要同步。
 * 
 * @author Administrator
 * 
 */
public class Single2 {
	private static Single2 instance;

	private Single2() {
	}

	public static synchronized Single2 getInstance() {
		if (instance == null) {
			instance = new Single2();
		}
		return instance;
	}
}
