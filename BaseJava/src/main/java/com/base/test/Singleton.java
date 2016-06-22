package com.base.test;

public class Singleton {
	public static final ThreadLocal<Singleton> threadInstance = new ThreadLocal<Singleton>();
	public static Singleton singleton = null;
	
	public static Singleton getSingleton() {
		if (threadInstance.get() == null) {
			singleton = createSingleton();
		}
		return singleton;
	}

	public static Singleton createSingleton() {
		synchronized (Singleton.class) {
			if (singleton == null) {
				singleton = new Singleton();
				threadInstance.set(singleton);
			}
		}
		return singleton;
	}

}
