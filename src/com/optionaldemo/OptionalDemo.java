package com.optionaldemo;

import java.util.Optional;

class Person{
	
	int id;
	String name;
	
	public Person() {
		super();
	}
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

public class OptionalDemo {

	public static void main(String[] args) {
		
		Person p1 = new Person();
		Person p2=new Person(0, "John");
		
		String ans1= "hello";
		String ans2=null;
		
		Optional<String> obj = Optional.ofNullable(ans2);
		if(obj.isPresent()) {
			System.out.println("hello");
		}
		
	}

	
}
