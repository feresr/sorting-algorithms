import java.util.Arrays;

/**
 * Created by feresr on 30/12/16.
 */
public class Main {

    public static void main(String[] args) {
        int[] result = mergeSort(new int[]{5, 3, 2, 4, 6, 1, 8, 7});
        System.out.println(Arrays.toString(result));
    }

    //O(n^2)
    private static int[] bubbleSort(int[] list) {
        int help;
        boolean change;
        for (int j = 0; j < list.length - 1; j++) {
            change = false;
            for (int i = 0; i < list.length - j - 1; i++) {
                if (list[i] > list[i + 1]) {
                    //swap
                    help = list[i + 1];
                    list[i + 1] = list[i];
                    list[i] = help;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }

        return list;
    }

    //O(n^2)
    private static int[] insertionSort(int[] list) {
        int hole;
        int value;
        for (int i = 1; i < list.length - 1; i++) {
            hole = i;
            value = list[i];
            while (hole > 0 && list[hole - 1] > value) {
                list[hole] = list[hole - 1];
                hole--;
            }
            list[hole] = value;
        }
        return list;
    }

    //worst case: O(N^2) average: O(n log n)
    private static int[] quickSort(int[] list, int start, int end) {
        if (start < end) {
            int pivot = list[end];
            int pIndex = start;

            for (int i = start; i < end; i++) {
                if (list[i] <= pivot) {
                    int aux = list[i];
                    list[i] = list[pIndex];
                    list[pIndex] = aux;
                    pIndex++;
                }
            }

            int aux = list[end];
            list[end] = list[pIndex];
            list[pIndex] = aux;

            quickSort(list, start, pIndex - 1);
            quickSort(list, pIndex, end);

        }

        return list;
    }

    //Θ(n log(n))
    private static int[] mergeSort(int[] list) {
        if (list.length <= 1) {
            return list;
        }

        int half = list.length / 2;

        int[] half1 = mergeSort(Arrays.copyOfRange(list, 0, half));
        int[] half2 = mergeSort(Arrays.copyOfRange(list, half, list.length));

        int i1 = 0;
        int i2 = 0;
        int[] sorted = new int[half1.length + half2.length];
        int si = 0;

        while (i1 < half1.length && i2 < half2.length) {
            if (half1[i1] > half2[i2]) {
                sorted[si] = half2[i2];
                i2++;
            } else {
                sorted[si] = half1[i1];
                i1++;
            }
            si++;
        }

        if (i1 < i2) {
            for (int i = i1; i < half1.length; i++) {
                sorted[si] = half1[i];
                si++;
            }
        } else {
            for (int i = i2; i < half2.length; i++) {
                sorted[si] = half2[i];
                si++;
            }
        }

        return sorted;
    }
}
