package com.serializationdemo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Employee implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int empId;
	private String empName;
	
	
	
	public Employee(int empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
}

class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int custId;
	private int custAge;
	
	public Customer(int custId, int custName) {
		super();
		this.custId = custId;
		this.custAge = custName;
	}
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getCustName() {
		return custAge;
	}
	public void setCustName(int custName) {
		this.custAge = custName;
	}
}


public class SerializationDemo {

	public static void main(String[] args) {
		
		Employee e1=new Employee(100,"Rahul");
		Employee e2=new Employee(101,"John");
		
		Customer c1= new Customer(111, 50);
		
		try {
			FileOutputStream fout=new FileOutputStream("C:\\Users\\Milton\\developer_zone\\abc.txt");
            ObjectOutputStream out=new ObjectOutputStream(fout);
            
            out.writeObject(e1);
            out.writeObject(e2);
            out.writeObject(c1);
            
            //out.flush();
            out.close();
            
            System.out.println("Serialization is been successfully executed");
            
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println("Deserialization process: ");
//		
//		try {
//			FileInputStream fis = new FileInputStream(
//					"C:\\Users\\Milton\\developer_zone\\abc.txt");
//			ObjectInputStream ois=new ObjectInputStream(fis);
//			
//			Employee e4 = (Employee)ois.readObject();
//			Employee e5 = (Employee)ois.readObject();
//			
//			System.out.println(e4.getEmpId() +"  "+e4.getEmpName());
//			System.out.println(e5.getEmpId() +"  "+e5.getEmpName());
//			 System.out.println("Deserialization is been successfully executed");
//			fis.close();
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
	}
}
