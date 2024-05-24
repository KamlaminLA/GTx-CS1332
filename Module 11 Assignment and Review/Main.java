import java.util.*;
public class Main {
    private static class sortComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
    public static void main(String[] args) {
        int[] unsorted = new int[]{6,8,5,-20,-11,100,-11,10,12,-10000};
        Sorting.lsdRadixSort(unsorted);
        for (int i = 0; i < unsorted.length; i += 1) {
            System.out.print(unsorted[i] + " ");
        }
        System.out.println();
        System.out.println(-5 / 10);
    }
}
