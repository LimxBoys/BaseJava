package com.base.test.single;

/**
 * 第七种（双重校验锁）：
 * 
 * @author Administrator
 * 
 */
public class Single6 {
	private volatile static Single6 single6;

	private Single6() {
	}

	public static Single6 getSingle5() {
		if (single6 == null) {
			synchronized (Single5.class) {
				if (single6 == null) {
					single6 = new Single6();
				}
			}
		}
		return single6;
	}
}