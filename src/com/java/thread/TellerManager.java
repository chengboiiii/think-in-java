package com.java.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TellerManager implements Runnable {
	
	private ExecutorService exec;
	private CustomerLine customers;
	private PriorityBlockingQueue<Teller> workingTellers = new PriorityBlockingQueue<Teller>();
	private Queue<Teller> tellerDoingOrther = new LinkedList<Teller>();
	private int adjustmentPeriod;
	private static Random  rand= new Random(47);
	
	public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
		super();
		this.exec = exec;
		this.customers = customers;
		this.adjustmentPeriod = adjustmentPeriod;
		
		Teller teller = new Teller(customers);
		exec.execute(teller);
		workingTellers.add(teller);
	}
	
	public void adjustTellerNumber(){
		if(customers.size()/workingTellers.size() >2){
			if(tellerDoingOrther.size()>0){
				Teller teller = tellerDoingOrther.remove();
				teller.serveCustomerLine();
				workingTellers.offer(teller);
				return;
			}else{
				Teller teller = new Teller(customers);
				exec.execute(teller);
				workingTellers.add(teller);
				return;
			}
		}
		if(workingTellers.size()>1 && customers.size()/workingTellers.size()<2){
			reassinOneTeller();
			if(customers.size() == 0){
				while(workingTellers.size()>1){
					reassinOneTeller();
				}
			}
		}
		
	}


	private void reassinOneTeller() {
		Teller teller = workingTellers.poll();
		teller.doSomethingElse();
		tellerDoingOrther.offer(teller);
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.print(customers+"{");
				for(Teller teller :workingTellers){
					System.out.println(teller.toString());
				}
				System.out.println("}");
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println(this+"结束");
	}

	@Override
	public String toString() {
		return "TellerManager []";
	}
	
	

}
