package com.java8.consumerinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

class ConsImpl implements Consumer<String> {

	@Override
	public void accept(String t) {
		System.out.println(t);

	}
}

public class ConsumerDemo {
	public boolean isEvenNumber(int i) {
		if (i % 2 == 0)
			return true;
		return false;
	}

	public static void main(String[] args) {
		List<String> list = Arrays.asList("4", "5", "6", "7", "8");

		Consumer<String> c = new ConsImpl();
		list.forEach(c);
		String str=null;
		System.out.println(str=="A");
	}
}
