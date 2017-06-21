import java.util.*

/**
 * Created by feresr on 20/6/17.
 */
fun main(args: Array<String>) {
    val unsorted = arrayOf(5, 3, -1, 1, 2, 0, 4, -2)

    sort("Bubble Sort       Θ(n^2)", { bubbleSort(unsorted.clone()) })
    sort("Selection Sort    Θ(n^2)", { selectionSort(unsorted.clone()) })
    sort("Insertion Sort    Θ(n^2)", { insertionSort(unsorted.clone()) })
    sort("Quick Sort        Θ(n^2)", { quickSort(unsorted.clone()) })
    sort("Merge Sort        Θ(n log(n))", { mergeSort(unsorted.clone()) })
}


fun sort(name: String, sortingAlgorithm: () -> Array<Int>) {
    println(name)
    sortingAlgorithm().forEach { print(" $it ") }
    println()
    println()
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
fun bubbleSort(array: Array<Int>): Array<Int> {
    var change: Boolean
    for (i in 0 until array.size - 1) {
        change = false
        for (j in 0 until array.size - 1 - i) {
            if (array[j] > array[j + 1]) {
                val aux = array[j]
                array[j] = array[j + 1]
                array[j + 1] = aux

                change = true
            }
        }
        if (!change) {
            break
        }
    }

    return array
}

/**
 * Θ(n^2)
 * Consider the first one on the list to be the minimum value.
 * Go through the list looking for a lower value than the 'considered minimum'
 * If found, swap the two.
 * Consider the second one on the list to be the minimum value... repeat
 */
fun selectionSort(array: Array<Int>): Array<Int> {
    for (i in 0 until array.size - 1) {
        var min = i
        for (j in i + 1 until array.size) {
            if (array[j] < array[min]) {
                min = j
            }
        }

        if (min != i) {
            val aux = array[min]
            array[min] = array[i]
            array[i] = aux
        }
    }

    return array
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
fun insertionSort(array: Array<Int>): Array<Int> {
    for (i in 1 until array.size) {
        var hole = i
        val value = array[hole]
        while (hole > 0 && array[hole - 1] > value) {
            array[hole] = array[hole - 1]
            hole--
        }
        array[hole] = value
    }

    return array
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
fun quickSort(array: Array<Int>, start: Int = 0, end: Int = array.size - 1): Array<Int> {
    if (!array.isEmpty() && start < end) {

        var wall = start
        val pivot = array[end]
        for (j in start until end) {
            if (array[j] < pivot) {
                val aux = array[j]
                array[j] = array[wall]
                array[wall] = aux
                wall++
            }
        }

        array[end] = array[wall]
        array[wall] = pivot

        quickSort(array, start, wall - 1)
        quickSort(array, wall, end)
    }

    return array
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
fun mergeSort(array: Array<Int>): Array<Int> {
    if (array.size > 1) {

        val lowerHalf = mergeSort(Arrays.copyOfRange(array, 0, array.size / 2))
        val upperHalf = mergeSort(Arrays.copyOfRange(array, array.size / 2, array.size))

        var lindex = 0
        var uindex = 0
        for (i in 0 until array.size) {
            if (lindex < lowerHalf.size && uindex < upperHalf.size) {
                if (lowerHalf[lindex] < upperHalf[uindex]) {
                    array[i] = lowerHalf[lindex]
                    lindex++
                } else {
                    array[i] = upperHalf[uindex]
                    uindex++
                }
            } else {
                if (lindex < lowerHalf.size) {
                    array[i] = lowerHalf[lindex]
                    lindex++
                } else {
                    array[i] = upperHalf[uindex]
                    uindex++
                }
            }
        }
    }

    return array
}