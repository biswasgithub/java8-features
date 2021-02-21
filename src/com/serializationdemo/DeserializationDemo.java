package com.serializationdemo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

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
