import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data is null");
        }
        if (size + 1 == backingArray.length) {
            resize();
        }
        backingArray[size + 1] = data;
        size += 1;
        upHeap(size);
    }

    /**
     * upHeap the new add element
     * index / 2 will get parent index
     */
    private void upHeap(int index) {
        // if the current element already is the top element
        // it is the min element, return it
        if (index / 2 == 0) {
            return;
        }
        if (backingArray[index].compareTo(backingArray[index / 2]) >= 0) {
            return;
        }
        // swap
        T parent = backingArray[index / 2];
        backingArray[index / 2] = backingArray[index];
        backingArray[index] = parent;
        upHeap(index / 2);
    }

    /**
     *  resize the array when the size + 1 == backingArray.length
     */
    private void resize() {
        T[] copy = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i < backingArray.length; i += 1) {
            copy[i] = backingArray[i];
        }
        backingArray = copy;
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new java.util.NoSuchElementException("The heap is empty");
        }
        T item = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size -= 1;
        downHeap(1);
        return item;
    }
    private void downHeap(int index) {
        // left child is null
        if (index > size / 2) {
            return;
        } else if (backingArray[index * 2 + 1] == null) {
            if (backingArray[index].compareTo(backingArray[index * 2]) > 0) {
                swap(index, index * 2);
                downHeap(index * 2);
            }
        } else if (backingArray[index * 2] == null && backingArray[index * 2 + 1] == null) {
            return;
        } else {
            if (backingArray[index].compareTo(backingArray[index * 2]) <= 0 && backingArray[index].compareTo(backingArray[index * 2 + 1]) <= 0) {
                return;
            } else {
                if (backingArray[index * 2].compareTo(backingArray[index * 2 + 1]) <= 0) {
                    swap(index, index * 2);
                    downHeap(index * 2);
                } else {
                    swap(index, index * 2 + 1);
                    downHeap(index * 2 + 1);
                }
            }
        }
    }

    private void swap(int parent, int child) {
        T temp = backingArray[parent];
        backingArray[parent] = backingArray[child];
        backingArray[child] = temp;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    private void printArray() {
        for (int i = 0; i < backingArray.length; i += 1) {
            System.out.println(backingArray[i] + " ");
        }
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();
        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(3);
        System.out.println(minHeap.remove());
        minHeap.printArray();
        System.out.println(minHeap.size());
    }
}