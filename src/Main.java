import java.util.Arrays;

/**
 * Created by feresr on 30/12/16.
 */
public class Main {

    public static void main(String[] args) {
        //int[] result = bubbleSort(new int[]{1, 5, 2, 4, 6, 3, 7, 8});
        int[] result = quickSort(new int[]{1, 5, 2, 8, 6, 8, 7, 4}, 0 , 7);
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
}
