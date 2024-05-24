import java.util.*;
public class Main {
    private static class sortComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{6,7,5,4,3,2,1};
        Sorting.insertionSort(arr, new sortComparator());
        for (int i = 0; i < arr.length; i += 1) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
