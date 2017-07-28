package com.java.thread;

public class Customer {
	private final int serviceTime;

	public Customer(int serviceTime) {
		super();
		this.serviceTime = serviceTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	@Override
	public String toString() {
		return "Customer [serviceTime=" + serviceTime + "]";
	}
	
	
}
