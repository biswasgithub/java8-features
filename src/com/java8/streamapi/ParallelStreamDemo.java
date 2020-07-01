package com.java8.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParallelStreamDemo {
	
	public static void main(String[] args) {
		
		Stack<Integer> stack=new Stack<Integer>();
		 
        long t1, t2;
        List<Employee> eList = new ArrayList<Employee>();
        for(int i=0; i<100; i++) {
            eList.add(new Employee("A", 20000.00));
            eList.add(new Employee("B", 3000.0));
            eList.add(new Employee("C", 15002.0));
            eList.add(new Employee("D", 7856.0)); 
            eList.add(new Employee("E", 200.0)); 
            eList.add(new Employee("F", 50000.0));
        }
 
        /***** Here We Are Creating A 'Sequential Stream' & Displaying The Result *****/
        t1 = System.currentTimeMillis();   
        System.out.println("Sequential Stream Count?= " + eList.stream().filter(e -> e.getSalary() > 15000).count());
 
        t2 = System.currentTimeMillis();
        System.out.println("Sequential Stream Time Taken?= " + (t2-t1) + "\n");
 
        /***** Here We Are Creating A 'Parallel Stream' & Displaying The Result *****/
        t1 = System.currentTimeMillis();        
        System.out.println("Parallel Stream Count?= " + eList.parallelStream().filter(e -> e.getSalary() > 15000).count());
 
        t2 = System.currentTimeMillis();
        System.out.println("Parallel Stream Time Taken?= " + (t2-t1));
    }

}
