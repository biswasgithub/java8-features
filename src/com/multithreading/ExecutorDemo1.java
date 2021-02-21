package com.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Worker implements Callable<String> {

	@Override
	public String call() throws Exception {

		String str=Thread.currentThread().getName();
		//System.out.println(Thread.currentThread().getName());

//		int sum = 0;
//		for (int i = 0; i < 5; i++) {
//			sum += i;
//		}

		return str;
	}

}

public class ExecutorDemo1 {

	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(4);
		
		Future<String>[] futures = new Future[5];
		Callable<String> w = new Worker();
		try {
			for (int i = 0; i < 5; i++) {
				Future<String> future = executors.submit(w);
				futures[i] = future;

			}

			for (int i = 0; i < futures.length; i++) {

				try {
					System.out.println("Result from Future " + i + ":" + futures[i].get());
				}

				catch (InterruptedException | ExecutionException e) {

				}
			}
		} finally {
			executors.shutdown();
		}
	}

}
