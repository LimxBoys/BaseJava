package com.base.test;

import com.base.test.single.Single1;
import com.base.test.single.Single2;
import com.base.test.single.Single3;
import com.base.test.single.Single4;
import com.base.test.single.Single5;

public class SingleThread implements Runnable {
	private Single5 single;
	private String name;

	public SingleThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		single = Single5.getInstance();
		System.out.println("single="+name + "=" + single.hashCode());
	}

}
