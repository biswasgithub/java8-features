package com.deserializationdemo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

class Employee implements Serializable {

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

class Customer implements Serializable {

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

public class DeserializationDemo {

	public static void main(String[] args) {

		System.out.println("Deserialization process: ");

		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\Milton\\developer_zone\\abc.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Employee e4 = (Employee) ois.readObject();
			Employee e5 = (Employee) ois.readObject();
			Customer c = (Customer) ois.readObject();

			System.out.println("Customer: " + c.getCustId() + " " + c.getCustName());
			System.out.println("Employee: " + e4.getEmpId() + "  " + e4.getEmpName());
			System.out.println("Employee: " + e5.getEmpId() + "  " + e5.getEmpName());
			System.out.println("Deserialization is been successfully executed");
			fis.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

