package com.concurrent.demo03;

public class Buffer {
	private Object lock;

	public Buffer() {
		lock = this;
	}

	public void write() {

		synchronized (lock) {
			long startDate = System.currentTimeMillis();
			for (;;) {
				if (System.currentTimeMillis() - startDate > Integer.MAX_VALUE) {
					break;
				}
			}
			System.out.println("往缓冲区写入数据结束");
		}
	}

	public void read() {

		synchronized (lock) {
			System.out.println("开始从缓冲区读取数据");
		}
	}

}
