import java.util.Arrays;

/**
 * Created by feresr on 30/12/16.
 */
public class Main {

    public static void main(String[] args) {
        int[] result = selectionSort(new int[]{5, 3, 2, 4, 6, 1, 8, 7});
        System.out.println(Arrays.toString(result));
    }

    /**
     * Θ(n^2)
     * Compare pairs of values and swap them if necessary.
     * Do this N - 1 times to guarantee the list will be sorted.
     * It's called bubble sort because big values tend to bubble up
     * to the right of the array pretty quickly. In fact, after n iterations
     * through the entire array the rightmost n elements are guaranteed to be
     * in their right position.
     */
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

    /**
     * Θ(n^2)
     * Consider the first value on the list to be 'sorted'
     * Pick up the second value and store it on a variable, define
     * a variable 'hole' in the index where this second value used to be
     * Start moving left from where this hole is, and ask if the number on the left
     * is greater than the value we pick up. If it is, shift the number onto the hole
     * and move the hole to the left as well. If the reach a value that is lower than
     * the picked up value, just insert the picked up value into the hole.
     * Then move on to the next value on the list and repeat the process
     */
    private static int[] insertionSort(int[] list) {
        int hole;
        int value;
        for (int i = 1; i < list.length; i++) {
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

    /**
     * worst case: Θ(N^2) average: Θ(n log n)
     * Pick the last value on the list and call it 'pivot'
     * Reorder the list so all values lower than the pivot are on the left side
     * and all value greater than the pivot are on the right side
     * To accomplish this, just select the first value on the list and call pIndex,
     * then go through the list asking if any value is lower than pivot, if they are
     * swap this lower value with the index on pIndex and increment pIndex.
     * Lastly, swap the end (pivot) with the value on pIndex.
     * Then, recursively call quickSort on both halves of the list and repeat the
     * process until the list has just one element.
     */
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

            list[end] = list[pIndex];
            list[pIndex] = pivot;

            quickSort(list, start, pIndex - 1);
            quickSort(list, pIndex, end);

        }

        return list;
    }

    /**
     * Θ(n log(n))
     * Split the list in two and recursively call mergeSort on
     * them until the list consists of a single element.
     * Then, merge the two lists together into a new list, taking always the
     * lowest between the first elements of each list.
     * Example: [1, 4, 9] and [2, 3, 10]
     * Between the first elements of each list (1) and (2), take (1).
     * Now, between (4) and (2), take (2). Between (4) and (3), take (3)...
     */
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

    /**
     * Θ(n^2)
     * Consider the first one on the list to be the minimum value.
     * Go through the list looking for a lower value than the 'considered minimum'
     * If found, swap the two.
     * Consider the second one on the list to be the minimum value... repeat
     */
    private static int[] selectionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < list[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int aux = list[i];
                list[i] = list[min];
                list[min] = aux;
            }
        }
        return list;
    }
}
