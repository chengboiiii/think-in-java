package com.java.thread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerSimulation {
	static final int MAX_LINE_SIZE = 50;
	static final int ADJUST_PROID = 1000;
	
	public static void main(String[] args) throws IOException, NumberFormatException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
		exec.execute(new CustomerGenerator(customers));
		exec.execute(new TellerManager(exec, customers, ADJUST_PROID));
		
		if(args.length == 0){
			TimeUnit.SECONDS.sleep(new Integer(100));
		}else{
			System.out.println("press enter to quit");
			System.in.read();
		}
		
		exec.shutdown();
	}
}
