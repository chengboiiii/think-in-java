package com.java.thread;

import java.util.concurrent.TimeUnit;

public class Teller implements Runnable, Comparable<Teller> {
	private static int counter = 0;
	private final int id = counter++;
	private int customerServed = 0;
	private CustomerLine customers;
	private boolean serving = true;
	
	
	public Teller(CustomerLine customers) {
		super();
		this.customers = customers;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				Customer customer = customers.take();
				TimeUnit.MICROSECONDS.sleep(customer.getServiceTime());
				synchronized (this) {
					customerServed++;
					while(!serving){
						wait();
					}
				}
			}
		}catch(InterruptedException e){
			System.out.println("线程中断");
		}
		System.out.println(this+"结束");
	}
	
	@Override
	public synchronized int compareTo(Teller o) {
		return customerServed>o.customerServed?-1:(customerServed == o.customerServed ? 0:1);
	}
	
	public synchronized void doSomethingElse(){
		customerServed = 0;
		serving = false;
	}
	
	public synchronized void serveCustomerLine(){
		assert !serving : "already serving"+this;
		serving = true;
		notifyAll();
	}

	@Override
	public String toString() {
		return "Teller [id=" + id + "]";
	}
	
	
	
}
