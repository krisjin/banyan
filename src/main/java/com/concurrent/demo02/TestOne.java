package com.concurrent.demo02;

/**
 * @author krisjin
 * @date 2015-1-29
 */
public class TestOne {

	public static void main(String[] args) {

		for (int i = 0; i < 5; i++) {
			Thread t1 = new Thread(new Threads());
			t1.start();
		}

	}

	static class Threads implements Runnable {
		public void run() {
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < 100000000; i++)
				SingletonOne.newInstace();
			
			
			System.out.println(Thread.currentThread().getName()+" cost: " + (System.currentTimeMillis() - startTime) / 1000 + "s");
		}
	}

}
