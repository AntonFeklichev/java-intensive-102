package antonfeklichev.java_intensiv_102;

import antonfeklichev.java_intensiv_102.customcollections.CustomArrayList;
import antonfeklichev.java_intensiv_102.customcollections.CustomLinkedList;
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

        System.out.println("list перед сортировкой:");
        list.printList();

        list.mergeSort();

        System.out.println("list после сортировки:");
        list.printList();

        list.remove(2);
        System.out.println("list после удаления элемента по индексу 2:");
        list.printList();

        System.out.println("Элемент list под индексом 1: " + list.get(1));

        CustomLinkedList<Integer> linkedList = new CustomLinkedList<>();
        linkedList.add(5);
        linkedList.add(3);
        linkedList.add(8);
        linkedList.add(1);
        linkedList.add(7);

        System.out.println("linkedList перед сортировкой:");
        linkedList.printList();

        linkedList.quickSort();

        System.out.println("linkedList после сортировки::");
        linkedList.printList();

		linkedList.remove(3);
		System.out.println("list после удаления элемента по индексу 3:");
		linkedList.printList();

		System.out.println("Элемент linkedList под индексом 2: " + linkedList.get(2));
    }

}


