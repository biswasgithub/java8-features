package com.java8.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamAPI {

	private static double sumIterator(List<Employee> list) {
		Iterator<Employee> it = list.iterator();
		double sum = 0;
		while (it.hasNext()) {
			Employee e = it.next();
			if (e.getSalary() > 50000) {
				sum += e.getSalary();
			}
		}
		return sum;
	}

	private static double sumStream(List<Employee> list) {
		return list.stream().filter(i -> i.getSalary() > 50000).mapToDouble(i -> i.getSalary()).sum();
	}

	public static void main(String[] args) {

		List<Employee> employeeMembers = new ArrayList<>();
		Employee e1 = new Employee(100, "Amitabh", 50000.00);
		Employee e2 = new Employee(101, "Shekhar", 30000.00);
		Employee e3 = new Employee(102, "Aman", 10000.00);
		Employee e4 = new Employee(103, "Rahul", 20000.00);
		Employee e5 = new Employee(104, "Shahrukh", 60000.00);
		Employee e6 = new Employee(105, "Lokesh", 70000.00);
		Employee e7 = new Employee(106, "Lokesh", 90000.00);

		employeeMembers.add(e1);
		employeeMembers.add(e2);
		employeeMembers.add(e3);
		employeeMembers.add(e4);
		employeeMembers.add(e5);
		employeeMembers.add(e6);
		employeeMembers.add(e7);

		// before java 8

		Double sumOfSalary = sumIterator(employeeMembers);
		System.out.println("Sum of Salary using java 7: " + sumOfSalary);

		// with java 8
		Double sumOfSalary1 = sumStream(employeeMembers);
		System.out.println("Sum of Salary using java 8: " + sumOfSalary1);

		/*
		 * filter return boolean. Predicates:In mathematical logic, a predicate is a
		 * function that receives a value and returns a boolean value. The Predicate
		 * functional interface is a specialization of a Function that receives a
		 * generified value and returns a boolean. A typical use case of the Predicate
		 * lambda is to filter a collection of values. The filter() method is also lazy,
		 * meaning it will not be evaluated until you call a reduction method, like
		 * collect
		 */

		System.out.println("Example of filter : ");

		// fetch emp name whose salary > to given salary
		employeeMembers.stream().filter(p -> p.getSalary() > 50000) // filtering salary
				.filter(s -> s.getName().startsWith("L")) // filtering based on name whose name start with "A"
				.map(s -> s.getName().toUpperCase()) // fetching name
				.forEach(System.out::println);  //iterating name

		// filtering with multiple criteria based based on
		Set<String> empNameSet = employeeMembers.stream()
				.filter(p -> p.getSalary() > 50000 && p.id > 105)											
				.map(s -> s.getName().toUpperCase()) // fetching name
				.collect(Collectors.toSet());

		System.out.println("Set view of employee name:" + empNameSet);

		/*
		 * the map() is used to transform one object into other by applying a function.
		 * The map(Function mapper) method takes a Function, technically speaking, an
		 * object of java.util.function.Function interface. This function is then
		 * applied to each element of Stream to convert it into the type you want. map
		 * don't return boolean
		 */

		System.out.println("\n Example of map : ");

		// return list of employee name
		List<String> employees = employeeMembers.stream().map(s -> s.getName()).collect(Collectors.toList());
		System.out.println("\nName of employee list utg6gsing map: " + employees);

		List<String> memberNameList = employeeMembers.stream().map(s -> s.getName().toUpperCase())
				.collect(Collectors.toList());
		System.out.println("\nSet view of employeeMembers: " + memberNameList);

		List<Double> empSalary = employeeMembers.stream().map(s -> s.salary * .5).collect(Collectors.toList());
		System.out.println("\nIncremented Salary: " + empSalary);

		List<Employee> emps = employeeMembers.stream().map(temp -> {
			Employee e = new Employee();
			e.setId(temp.getId());
			e.setName(temp.getName());
			e.setSalary(temp.getSalary() * .5);
			return e;
		}).collect(Collectors.toList());

		List<Double> salary = emps.stream().filter(s -> s.id > 105).map(Employee::getSalary)
				.collect(Collectors.toList());

		System.out.println("\nIncremented Salary: " + salary);

		// sorted

		List<Employee> sortedList = employeeMembers.stream()
				// .sorted((o1, o2) -> o1.getName().compareTo(o2.getName())) // sorted by name
				// .sorted(Comparator.comparing(Employee::getName)) // sorted by name
				.sorted(Comparator.comparingDouble(Employee::getSalary)) // sorted by salary
				// .sorted((o1, o2) -> o1.getId() - o2.getId()) // sorted by id
				//.sorted(Comparator.comparingInt(Employee::getId).reversed()) // sorted by id
				.collect(Collectors.toList());

		System.out.println("\nSorted by name/salary/Id: ");
		sortedList.forEach(e -> System.out
				.println("Id: " + e.getId() + " Name: " + e.getName() + "     Salary: " + e.getSalary()));

		List<Double> sortedList1 = employeeMembers.stream()
				// .sorted((o1, o2) -> o1.getId() - o2.getId())
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed()) // sorted by salary
				.map(s -> s.salary).collect(Collectors.toList());

		System.out.println("\nSorted by salary: " + sortedList1);

		/*
		 * A stream can hold complex data structures like Stream<List<String>>. In cases
		 * like this, flatMap() helps us to flatten the data structure to simplify
		 * further operations: convert the Stream<List<String>> to a simpler
		 * Stream<String>
		 */

		List<List<String>> namesNested = Arrays.asList(Arrays.asList("Jeff", "Bezos"), Arrays.asList("Bill", "Gates"),
				Arrays.asList("Mark", "Zuckerberg"));

		List<String> namesFlatStream = namesNested.stream().flatMap(Collection::stream).collect(Collectors.toList());

		System.out.println("\nFlat map: " + namesFlatStream);

		// **************************************** terminal operations

		/*
		 * min() and max() return the minimum and maximum element in the stream
		 * respectively, based on a comparator. They return an Optional since a result
		 * may or may not exist (due to, say, filtering):
		 */

		Employee emp1 = employeeMembers.stream().min(Comparator.comparingDouble(Employee::getSalary))
				.orElseThrow(NoSuchElementException::new);

		System.out.println("\nemployee based on min Id:  " + emp1);

		Employee emp2 = employeeMembers.stream().max(Comparator.comparingDouble(Employee::getSalary))
				.orElseThrow(NoSuchElementException::new);

		System.out.println("employee based on max. salary: " + emp2);

		 //System.out.println("\nAverage Salary: ");
		 //employeeMembers.stream().mapToDouble(s->s.getSalary()).average().ifPresent(System.out::print);

		/*
		 * These operations all take a predicate and return a boolean. Short-circuiting
		 * is applied and processing is stopped as soon as the answer is determined:
		 * allMatch() checks if the predicate is true for all the elements in the
		 * stream. anyMatch() checks if the predicate is true for any one element in the
		 * stream noneMatch() checks if there are no elements matching the predicate.
		 */

		boolean matchedResult = employeeMembers.stream().anyMatch((s) -> s.getName().startsWith("A"));

		System.out.println("\nAny match: " + matchedResult);

		matchedResult = employeeMembers.stream().allMatch((s) -> s.getName().startsWith("A"));

		System.out.println("\nAll match: " + matchedResult);

		matchedResult = employeeMembers.stream().noneMatch((s) -> s.getName().startsWith("A"));

		System.out.println("\nNone Match: " + matchedResult);

		// count
		long totalMatched = employeeMembers.stream().filter((s) -> s.getName().startsWith("A")).count();

		System.out.println("\nCount: " + totalMatched);

		/*
		 * short-circuit operations findFirst(): It will return first element from
		 * stream and then will not process any more element. It returns Optional
		 * object.
		 */

		Optional<Double> salaryOfFirstNameWithL = employeeMembers.stream().filter((s) -> s.getName().startsWith("L"))
				.map(s -> s.getSalary()).findFirst();

		System.out.println("\nFind first: " + salaryOfFirstNameWithL.get());

		employeeMembers.stream().filter(s -> s.getName().startsWith("L")).findFirst().ifPresent(System.out::println);

		// mapToInt()
		System.out.println("\nMap to Int: ");
		Arrays.stream(new String[] { "3", "6", "8", "14", "15" }).mapToInt(num -> Integer.parseInt(num))
				.filter(num -> num % 3 == 0).forEach(System.out::println);

		Arrays.stream(new int[] { 1, 2, 3 }).map(n -> 2 * n + 1).average().ifPresent(System.out::println);


		// why order matters

		Stream.of("d2", "a2", "b1", "b3", "c")
	    .map(s -> {
	        System.out.println("map: " + s);
	        return s.toUpperCase();
	    })
	    .filter(s -> {
	        System.out.println("filter: " + s);
	        return s.startsWith("A");
	    })
	    .forEach(s -> System.out.println("forEach: " + s));
		

		System.out.println();

		Stream.of("d2", "a2", "b1", "b3", "c")
	    .filter(s -> {
	        System.out.println("filter: " + s);
	        return s.startsWith("a");
	    })
	    .map(s -> {
	        System.out.println("map: " + s);
	        return s.toUpperCase();
	    })
	    .forEach(s -> System.out.println("forEach: " + s));


		
	}
}
