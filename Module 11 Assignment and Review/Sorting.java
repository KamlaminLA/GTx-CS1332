import java.util.Comparator;
import java.util.LinkedList;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

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
        T[] sortArr = sort(arr, comparator);
        for (int i = 0; i < arr.length; i += 1) {
            arr[i] = sortArr[i];
        }
    }

    private static <T> T[] sort(T[] arr, Comparator<T> comparator) {
        if (arr.length <= 1) {
            return arr;
        }
        int len = arr.length;
        int mid = len / 2;
        T[] left = seperateArr(arr, 0, mid);
        T[] right = seperateArr(arr, mid, len);
        T[] sortLeft = sort(left, comparator);
        T[] sortRight = sort(right, comparator);
        T[] mergeArr = merge(sortLeft, sortRight, comparator);
        return mergeArr;

    }
    // [3,4,5,2]
    // newLeft = [null, null] newRight = [null, null]
    // newLeft[i] = [left + i]

    private static <T> T[] seperateArr(T[] arr, int left, int right) {
        T[] newArr = (T[]) new Object[right - left];
        for (int i = 0; i < newArr.length; i += 1) {
            newArr[i] = arr[i + left];
        }
        return newArr;

    }

    private static <T> T[] merge(T[] arr1, T[] arr2, Comparator<T> comparator) {
        T[] newArr = (T[]) new Object[arr1.length + arr2.length];
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (comparator.compare(arr1[i], arr2[j]) <= 0) {
                newArr[i + j] = arr1[i];
                i += 1;
            } else {
                newArr[i + j] = arr2[j];
                j += 1;
            }
        }
        while (i < arr1.length) {
            newArr[i + j] = arr1[i];
            i += 1;
        }
        while (j < arr2.length) {
            newArr[i + j] = arr2[j];
            j += 1;
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
        // Step 1: Get the largest num and calc the k
        // [-1000, 9, 10, 23, -5]
        // MAX_VAL is public static variable from Integer class
        // Integer class is build-in class
        int smallestNum = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i += 1) {
            int curr = arr[i];
            if (curr > 0) {
                curr *= -1;
            }
            smallestNum = Math.min(curr, smallestNum);
        }
        int k = getK(smallestNum);
        LinkedList<Integer>[] lst = new LinkedList[19];
        // we have to initiate all the LinkedList in LinkedList arr
        // otherwise lst[0-19] will be null, access the index give us
        // nullPointerException
        for (int i = 0; i < lst.length; i += 1) {
            lst[i] = new LinkedList<>();
        }
        for (int i = 0; i < k; i += 1) {
            for (int j = 0; j < arr.length; j += 1) {
                int currNum = arr[j];
                // negative digit up to -9 corresponding to
                // index 0 in the LinkedList arr
                int currDigit = getDigit(currNum, i) + 9;
                lst[currDigit].addLast(currNum);
            }
            reorder(arr, lst);
        }

    }

    private static void reorder(int[] arr, LinkedList<Integer>[] lst) {
        int index = 0;
        for (int i = 0; i < lst.length; i += 1) {
            LinkedList<Integer> ll = lst[i];
            while (ll.size() > 0) {
                arr[index] = ll.removeFirst();
                index += 1;
            }
        }
    }

    private static int getDigit(int num, int i) {
        int res = 0;
        while (i >= 0) {
            res = num % 10;
            num = num / 10;
            i -= 1;
        }
        return res;

    }
    private static int getK(int num) {
        int ans = 0;
        while (num < 0) {
            num = num / 10;
            ans += 1;
        }
        return ans;
    }
}
