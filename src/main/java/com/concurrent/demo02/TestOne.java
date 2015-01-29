package com.concurrent.demo02;

/**
 * @author krisjin
 * @date 2015-1-29
 */
public class TestOne {

	public static void main(String[] args) {
		SingletonOne instance = SingletonOne.newInstace();
		instance.increment(100000000);
	}
	
	
	
	
	

}
