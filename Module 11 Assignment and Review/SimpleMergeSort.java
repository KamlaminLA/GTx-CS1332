import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class SimpleMergeSort {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Queue<T[]> queue = seperateArr(arr);
        T[] res = mergeSortHelper(queue, comparator);
        for (int i = 0; i < arr.length; i += 1) {
            arr[i] = res[i];
        }

    }

    private static <T> Queue<T[]> seperateArr(T[] arr) {
        Queue<T[]> res = new LinkedList<>();
        for (int i = 0; i < arr.length; i += 1) {
            res.add((T[]) new Object[]{arr[i]});
        }
        return res;

    }

    private static <T> T[] mergeSortHelper(Queue<T[]> arrList, Comparator<T> comparator) {
        while (arrList.size() > 1) {
            T[] arr1 = arrList.poll();
            T[] arr2 = arrList.poll();
            arrList.add(merge(arr1, arr2, comparator));
        }
        return arrList.poll();
    }

    private static <T> T[] merge(T[] arr1, T[] arr2, Comparator<T> comparator) {
        T[] newArr = (T[]) new Object[arr1.length + arr2.length];
        int i = 0, j = 0, index = 0;
        while (i < arr1.length && j < arr2.length) {
            if (comparator.compare(arr1[i], arr2[j]) <= 0) {
                newArr[index] = arr1[i];
                i += 1;
            } else {
                newArr[index] = arr2[j];
                j += 1;
            }
            index += 1;
        }
        while (i < arr1.length) {
            newArr[index] = arr1[i];
            i += 1;
            index += 1;
        }
        while (j < arr2.length) {
            newArr[index] = arr2[j];
            j += 1;
            index += 1;
        }
        return newArr;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }
}
