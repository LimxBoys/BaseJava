package com.base.test;

public class Single {
	private static ThreadLocal<Single> threadLocal = new ThreadLocal<Single>();
	public static Single single;

	private Single() {
	}

	public static synchronized Single getSingle() {
		 if (threadLocal.get() == null) {
		if (single == null)
			single = new Single();
			threadLocal.set(single);
		}
		return single;
	}
}
