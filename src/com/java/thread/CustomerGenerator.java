package com.java.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustomerGenerator implements Runnable{

	private CustomerLine customers;
	private static Random rand = new Random(47);
	
	public CustomerGenerator(CustomerLine customers) {
		super();
		this.customers = customers;
	}



	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
				customers.put(new Customer(rand.nextInt(1000)));
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("CustomerGenerator 结束");
	}
	
}
