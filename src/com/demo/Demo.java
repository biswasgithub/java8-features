package com.demo;

class A {

	synchronized void add(int i) {

		System.out.println(Thread.currentThread().getName()  +": Wellcome");
	}

	synchronized void addNew(int i) {

		System.out.println(Thread.currentThread().getName()  +":  Hello");
	}
}

class MyThraed extends Thread {
	
	A a1=new A();
	public void run() {
		a1.addNew(100);
		a1.add(5);
	}
}

public class Demo {

	public static void main(String[] args) {
		
		MyThraed b=new MyThraed();
		
		Thread t1=new Thread(b);
		Thread t2=new Thread(b);
		t1.setName("Thread 1 ");
		t2.setName("Thread 2 ");
		
		t1.start();
		t2.start();
		
	}
}
