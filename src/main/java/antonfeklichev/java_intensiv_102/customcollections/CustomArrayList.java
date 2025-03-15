package antonfeklichev.java_intensiv_102.customcollections;

import java.util.Arrays;

public class CustomArrayList<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    /**
     * Конструктор, создающий список с начальной емкостью по умолчанию.
     */
    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавляет элемент в список.
     *
     * @param element Элемент, который нужно добавить.
     */
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Получает элемент по индексу.
     *
     * @param index Индекс элемента.
     * @return Элемент по указанному индексу.
     * @throws IndexOutOfBoundsException Если индекс выходит за пределы списка.
     */
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по индексу.
     *
     * @param index Индекс элемента, который нужно удалить.
     * @throws IndexOutOfBoundsException Если индекс выходит за пределы списка.
     */
    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return Размер списка.
     */
    public int size() {
        return size;
    }

    /**
     * Увеличивает емкость массива, если он заполнен.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    /**
     * Проверяет, что индекс находится в допустимых границах.
     *
     * @param index Индекс для проверки.
     * @throws IndexOutOfBoundsException Если индекс недопустим.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Выполняет сортировку списка методом Merge Sort.
     */
    public void mergeSort() {
        if (size > 1) {
            elements = mergeSortRecursive(Arrays.copyOf(elements, size));
        }
    }

    /**
     * Выполняет рекурсивную сортировку массива методом Merge Sort.
     *
     * @param array Исходный массив, который необходимо отсортировать.
     * @return Новый отсортированный массив.
     */
    private Object[] mergeSortRecursive(Object[] array) {
        // Если массив содержит один или ноль элементов, он уже отсортирован
        if (array.length < 2) {
            return array;
        }

        // Определяем середину массива
        int middleIndex = array.length / 2;

        // Разделяем массив на две части
        Object[] leftSubArray = Arrays.copyOfRange(array, 0, middleIndex);
        Object[] rightSubArray = Arrays.copyOfRange(array, middleIndex, array.length);

        // Рекурсивно сортируем левую и правую части, затем объединяем их
        Object[] sortedLeft = mergeSortRecursive(leftSubArray);
        Object[] sortedRight = mergeSortRecursive(rightSubArray);

        return merge(sortedLeft, sortedRight);
    }

    /**
     * Объединяет два отсортированных массива в один.
     *
     * @param leftArray  Отсортированный левый подмассив.
     * @param rightArray Отсортированный правый подмассив.
     * @return Новый массив, содержащий все элементы из двух подмассивов в отсортированном порядке.
     */
    private Object[] merge(Object[] leftArray, Object[] rightArray) {
        // Создаем новый массив для объединения элементов
        Object[] mergedArray = new Object[leftArray.length + rightArray.length];

        // Индексы для прохода по левому, правому и объединенному массивам
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;

        // Сравниваем элементы и добавляем их в новый массив в правильном порядке
        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (((T) leftArray[leftIndex]).compareTo((T) rightArray[rightIndex]) <= 0) {
                mergedArray[mergedIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                mergedArray[mergedIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }

        // Если в левом подмассиве остались элементы, добавляем их
        while (leftIndex < leftArray.length) {
            mergedArray[mergedIndex] = leftArray[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        // Если в правом подмассиве остались элементы, добавляем их
        while (rightIndex < rightArray.length) {
            mergedArray[mergedIndex] = rightArray[rightIndex];
            rightIndex++;
            mergedIndex++;
        }

        // Возвращаем объединенный и отсортированный массив
        return mergedArray;
    }

    /**
     * Выводит список на экран.
     */
    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
