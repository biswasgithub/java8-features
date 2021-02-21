package com.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Task1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		
		System.out.println(Thread.currentThread().getName() +" : for Task1");
		
		return "Task1";
	}
}

class Task2 implements Callable<String>{

	@Override
	public String call() throws Exception {
		
		System.out.println(Thread.currentThread().getName() +" : for Task2");
		
		return "Task2";
	}
}

public class ExecutorDemo {
	
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor=Executors.newFixedThreadPool(4);
		System.out.println("Start : ");
		
		Future taskl1a=executor.submit(new Task1());
		Future taskl1b=executor.submit(new Task1());
		Future taskl1c=executor.submit(new Task1());
		Future taskl1d=executor.submit(new Task1());
		Future taskl2a=executor.submit(new Task2());
		//executor.shutdown(); // will get RejectedExecutionException
		Future taskl2b=executor.submit(new Task2());
		Future taskl2c=executor.submit(new Task2());
		
		System.out.println(taskl1a.get() +"  "+taskl1a.isDone());
		
		executor.shutdown();
	}
}
