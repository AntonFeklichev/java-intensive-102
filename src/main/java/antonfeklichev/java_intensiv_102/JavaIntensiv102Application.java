package antonfeklichev.java_intensiv_102;

import antonfeklichev.java_intensiv_102.customcollections.CustomArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaIntensiv102Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaIntensiv102Application.class, args);

		CustomArrayList<Integer> list = new CustomArrayList<>();
		list.add(5);
		list.add(3);
		list.add(8);
		list.add(1);
		list.add(7);

		System.out.println("Перед сортировкой:");
		list.printList();

		list.mergeSort();

		System.out.println("После сортировки:");
		list.printList();

		list.remove(2);
		System.out.println("После удаления элемента по индексу 2:");
		list.printList();

		System.out.println("Элемент под индексом 1: " + list.get(1));

	}

}
