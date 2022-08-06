package com.java8.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

class Customer {

	int id;
	String name;
	double salary;
	List<String> bookList;

	public Customer(int id, String name, double salary, List<String> bookList) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.bookList = bookList;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<String> getBookList() {
		return bookList;
	}

	public void setBookList(List<String> bookList) {
		this.bookList = bookList;
	}

}

public class StreamAPIDemo {

	public static void main(String[] args) {

		List<String> list1 = new ArrayList<>();
		list1.add("Java 8 in Action");
		list1.add("Spring Boot in Action");
		list1.add("Effective Java (3nd Edition)");
		Customer c1 = new Customer(10, "John", 30000, list1);

		List<String> list2 = new ArrayList<>();
		list2.add("Java 8 in Action");
		list2.add("Spring Boot in Action");
		list2.add("Effective Java (3nd Edition)");
		Customer c2 = new Customer(50, "Roky", 35000, list2);

		Customer c3 = new Customer(70, "Demi", 70000, Arrays.asList("C", "Java"));

		Customer c4 = new Customer(30, "Rimi", 30000, Arrays.asList("C", "Java"));
		Customer c5 = new Customer(60, "Jimi", 60000, Arrays.asList("C", "C++"));
		Customer c6 = new Customer(50, "Kiki", 50000, Arrays.asList("Python", "Java"));
		Customer c7 = new Customer(40, "Angle", 40000, Arrays.asList("DB", "Java"));
		Customer c8 = new Customer(80, "Rishik", 80000, Arrays.asList("SQL", "Java"));
		Customer c9 = new Customer(20, "Angle", 20000, Arrays.asList("C++", "Java"));

		List<Customer> custList = new ArrayList<Customer>();

		custList.add(c1);
		custList.add(c2);
		custList.add(c3);
		custList.add(c4);
		custList.add(c5);
		custList.add(c6);
		custList.add(c7);
		custList.add(c8);
		custList.add(c9);

		// ******* filter()

		List<Customer> result = custList.stream().filter(s -> s.getSalary() > 40000)
				.filter(s -> s.getBookList().contains("Java")).collect(Collectors.toList());
		// result.forEach(s->System.out.println(s.getId()));

		List<String> result1 = custList.stream().filter(s -> s.getSalary() > 40000)
				.filter(s -> s.getBookList().contains("Java")).map(x -> x.getName()).collect(Collectors.toList());
		// System.out.println(result1);

		// ************ map() ***********

		List<String> result11 = custList.stream().filter(s -> s.getSalary() > 40000)
				.filter(s -> s.getBookList().contains("Java")).map(x -> x.getName().toUpperCase())
				.collect(Collectors.toList());
		// System.out.println(result11);

		// ***************sort collection using Comparator.comparaing()***********

		List<Customer> result3 = custList.stream().filter(s -> s.getSalary() > 40000)
				.sorted(Comparator.comparing(Customer::getId).reversed()).collect(Collectors.toList());

		// result3.forEach(s->System.out.println(s.getId() +" "+s.getName()));

		List<Customer> result4 = custList.stream().filter(s -> s.getSalary() > 40000)
				.sorted(Comparator.comparing(Customer::getName).thenComparing(Customer::getId))
				.collect(Collectors.toList());
		// result4.forEach(s->System.out.println(s.getId() +" "+s.getName()));

		// ***************sort collection using sort() without stream()***********

		Comparator<Customer> idComparator = (Customer z1, Customer z2) -> z1.getId() - z2.getId();
		Comparator<Customer> nameComparator = (Customer z1, Customer z2) -> z1.getName().compareTo(z2.getName());
		custList.sort(idComparator);
		custList.sort(idComparator.reversed());

		// custList.forEach(s-> System.out.println(s.getId()));

		// ************** terminal operation**********

		Optional<Integer> resultName = custList.stream().filter(x -> x.getName().startsWith("A")).map(x -> x.getId())
				.findFirst();
		// System.out.println(resultName.get());
		long count = custList.stream().filter(x -> x.getName().startsWith("A")).count();
		// System.out.println(count);

		boolean result22 = custList.stream().anyMatch(x -> x.getName().startsWith("A"));
		boolean result23 = custList.stream().noneMatch(x -> x.getName().startsWith("A"));

		// System.out.println(result22 +" "+ result23);

		// Stream().max()/min()-> returns max/min element from the stream according to
		// provided Comparator. It is special case of reduction

		int max = custList.stream().max(Comparator.comparing(Customer::getId)).get().id;
		int min = custList.stream().min(Comparator.comparing(Customer::getId)).get().id;
		// System.out.println(max +" "+min);

		// ******** stream() in map collection ************

		HashMap<Integer, String> hashMap = new HashMap<>();
		hashMap.put(3, "Rahul");
		hashMap.put(1, "Ram");
		hashMap.put(4, "John");
		hashMap.put(2, "Shyam");

		// looping map
		hashMap.forEach((k, v) -> {
			if (k != null) {
				System.out.println(k + "  " + v);
			}
		});

		// MAP -> Stream-> Filter -> String
		String result111 = hashMap.entrySet().stream().filter(k -> k.getKey() == 3).map(x -> x.getValue().toUpperCase())
				.collect(Collectors.joining());
		// System.out.println(result111);

		// MAP -> Stream-> Filter -> String

		Map<Integer, String> result114 = hashMap.entrySet().stream().filter(k -> k.getKey() == 3)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		// or like

		Map<Integer, String> result115 = hashMap.entrySet().stream().filter(k -> k.getKey() == 3)
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

		// System.out.println(result115);

		// ************ Example 2: Map from stream having duplicate key

		// Create a Map from List based on property of Pojo class and avoiding duplicate
		// key problem.

		// If we use customer-id as key which is not unique and customer-name as value
		// then it throws ILLegalStateEception
		// because key must be unique. So mergeFunction to use to avoid this type
		// problem.
		// Java 8 Streams provide Collectors.toMap(keyMapper,valueMapper,mergeFunction)
		// overloaded method where we can mention which
		// value will be used as key when duplicate key issue occur. mergeFunction is a
		// function that operates on two values associated
		// to the same key. id1 corresponds to the first customer that was encountered
		// when collecting elements and id2
		// corresponds to the second customer encountered: and lambda tells to keep the
		// first id1 and ignores the second id2.

		Map<Integer, String> custMap = custList.stream().filter(x -> x.getId() > 20)
				.collect(Collectors.toMap(Customer::getId, Customer::getName, (id1, id2) -> id1));
		// System.out.println(custMap);

		// ************* sort map based on key and value and customized logic
		Map<Integer, String> sortMapBasedOnKey = custMap.entrySet().stream().filter(x -> x.getKey() > 20)
				.sorted(Map.Entry.comparingByKey())
				// .sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
		// System.out.println(sortMapBasedOnKey);

		Map<Integer, String> sortMapBasedCustomLogic = custMap.entrySet().stream()
				.sorted(Entry.comparingByValue((o1, o2) -> o1.length() - o2.length())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		// System.out.println(sortMapBasedCustomLogic);

		// To reverse the hashMap, use Comparator.reversedOrder() or
		// Collections.reversedOrder()
		Map<Integer, String> reverseMap = custMap.entrySet().stream()
				// .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
				.sorted(Entry.comparingByKey(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey,
						Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		// System.out.println(reverseMap);

		// ********** flatMap() ******
		List<List<String>> books1 = custList.stream().map(x -> x.getBookList()) // List<String>
				// .filter(x -> !x.toLowerCase().contains("123")) // filter
				.collect(Collectors.toList());
		// System.out.println(books1);

		List<String> books2 = custList.stream().flatMap(x -> x.getBookList().stream()) // Stream<String>
				.filter(x -> !x.equals("Java")) // filter
				.collect(Collectors.toList());
		System.out.println(books2);

	}

}
