import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int stopIndex = arr.length - 1;
        while (stopIndex != 0) {
            // every iteration start at index 0
            int i = 0;
            // index of last swap occurred
            // if no swap occurred, stopIndex will equal 0
            // the outer loop terminate and we know the array is sorted
            int lastSwapIndex = 0;
            while (i < stopIndex) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    // swap method to swap arr[i] and arr[i + 1]
                    swap(arr, i, i + 1);
                    lastSwapIndex = i;
                }
                i += 1;
            }
            stopIndex = lastSwapIndex;
        }
        // for loop version
        /** int stopIndex;
        for (stopIndex = arr.length - 1; stopIndex > 0;) {
            int lastSwapIndex = 0;
            for (int i = 0; i < stopIndex; i += 1) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    lastSwapIndex = i;
                }
            }
            stopIndex = lastSwapIndex;
        } */
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // find the min item in the unsorted array, put them in order
        // 0, 1, 2, 3... until the end
        // if there is only one item left unsorted, that sub-array
        // is actually sorted
        for (int i = 0; i < arr.length - 1; i += 1) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j += 1) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // we always start at index 1 because index 0 is always relative sorted
        // insertionSort is to find the correct position for current item
        // if the current item is no greater than previous item, we know this
        // two items in reality sorted order.
        for (int i = 1; i < arr.length; i += 1) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j], arr[j - 1]) < 0) {
                swap(arr, j, j - 1);
                j -= 1;
            }
        }
    }
}
