package com.concurrent.demo02;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author krisjin
 * @date 2015-1-29
 */
public class SingletonOne {

	private static SingletonOne instance = new SingletonOne();
	private AtomicLong counts = new AtomicLong(0);

	private SingletonOne() {
	}

	public synchronized static SingletonOne newInstace() {

		if (instance == null) {
			instance = new SingletonOne();
			return instance;
		}

		return instance;
	}

	protected void increment(long max) {
		long val = 1;
		long start = System.currentTimeMillis();
		for (long i = val; i <= max; i++) {
			counts.incrementAndGet();
		}
		long end = System.currentTimeMillis();
		System.out.println(max + " 求和结果:" + counts.get() + "  耗时：" + (end - start) / 1000 + " s");
	}
}
