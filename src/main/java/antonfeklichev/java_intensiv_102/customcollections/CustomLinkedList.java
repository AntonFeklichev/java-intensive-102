package antonfeklichev.java_intensiv_102.customcollections;

public class CustomLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private int size = 0;

    /**
     * Внутренний класс для узлов списка.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param data Элемент для добавления.
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    /**
     * Получает элемент по индексу.
     *
     * @param index Индекс элемента.
     * @return Значение элемента.
     * @throws IndexOutOfBoundsException Если индекс некорректен.
     */
    public T get(int index) {
        checkIndex(index);
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * Удаляет элемент по индексу.
     *
     * @param index Индекс элемента для удаления.
     * @throws IndexOutOfBoundsException Если индекс некорректен.
     */
    public void remove(int index) {
        checkIndex(index);
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        size--;
    }

    /**
     * Возвращает размер списка.
     *
     * @return Количество элементов в списке.
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет корректность индекса.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Выполняет быструю сортировку списка.
     */
    public void quickSort() {
        head = quickSortRecursive(head);
    }

    /**
     * Рекурсивная реализация quick-sort для связного списка.
     *
     * @param start Начальный узел подсписка.
     * @return Отсортированный подсписок.
     */
    private Node<T> quickSortRecursive(Node<T> start) {
        // Базовый случай, если список пуст или состоит из одного элемента он уже отсортирован
        if (start == null || start.next == null) {
            return start;
        }

        // Выбираем первый элемент списка в качестве pivot
        Node<T> pivot = start;

        // Создаем временные списки для элементов, меньших и больших pivot
        Node<T> lessHead = new Node<>(null), lessTail = lessHead;
        Node<T> greaterHead = new Node<>(null), greaterTail = greaterHead;
        Node<T> current = start.next;

        // Разделяем список на две части относительно pivot
        while (current != null) {
            if (current.data.compareTo(pivot.data) < 0) {
                lessTail.next = current;
                lessTail = current;
            } else {
                greaterTail.next = current;
                greaterTail = current;
            }
            current = current.next;
        }

        // Завершаем два списка, разрываем связи
        lessTail.next = null;
        greaterTail.next = null;

        // Рекурсивно сортируем левый и правый подсписки
        Node<T> sortedLess = quickSortRecursive(lessHead.next);
        Node<T> sortedGreater = quickSortRecursive(greaterHead.next);

        // Объединяем отсортированные части с pivot и возвращаем новый отсортированный список
        return concatenate(sortedLess, pivot, sortedGreater);
    }

    /**
     * Объединяет отсортированные части списка.
     *
     * @param less  Отсортированный список элементов, меньших pivot.
     * @param pivot Опорный элемент.
     * @param greater Отсортированный список элементов, больших pivot.
     * @return Голова объединенного списка.
     */
    private Node<T> concatenate(Node<T> less, Node<T> pivot, Node<T> greater) {
        // Если список less пуст, pivot становится началом списка
        if (less == null) {
            pivot.next = greater;
            return pivot;
        }

        // Ищем конец списка less
        Node<T> temp = less;
        while (temp.next != null) {
            temp = temp.next;
        }

        // Присоединяем pivot к концу less
        temp.next = pivot;

        // Присоединяем greater к pivot
        pivot.next = greater;

        // Возвращаем объединенный список
        return less;
    }

    /**
     * Выводит список.
     */
    public void printList() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
