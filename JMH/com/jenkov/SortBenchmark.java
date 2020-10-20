package com.jenkov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 5, time = 2)
public class SortBenchmark {

    int[] shortArray;
    int[] longArray;
    Random r;

    void selectionSort(int arr[])
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public void heapSort(int arr[])
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void mergSort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergSort(arr, l, m);
            mergSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void quickSort(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    //-------------------------------------------------------------------------------
    @Setup
    public void setup(){
        r = new Random();
        shortArray = new int[200];
        longArray = new int[4096];


    }

    @Benchmark
    public void shortSelectionSort(){
        for(int i =0;i<200;i++){
            shortArray[i] = r.nextInt(200);
        }

        selectionSort(shortArray);
    }

    @Benchmark
    public void shortBubbleSort(){
        for(int i =0;i<200;i++){
            shortArray[i] = r.nextInt(200);
        }

        bubbleSort(shortArray);
    }

    @Benchmark
    public void shortInsertionSort(){
        for(int i =0;i<200;i++){
            shortArray[i] = r.nextInt(200);
        }

        insertionSort(shortArray);
    }

    @Benchmark
    public void shortHeapSort(){
        for(int i =0;i<200;i++){
            shortArray[i] = r.nextInt(200);
        }

        heapSort(shortArray);
    }

    @Benchmark
    public void shortMergeSort(){
        for(int i =0;i<200;i++){
            shortArray[i] = r.nextInt(200);
        }

        mergSort(shortArray,0,shortArray.length-1);
    }

    @Benchmark
    public void shortQuickSort(){
        for(int i =0;i<200;i++){
            shortArray[i] = r.nextInt(200);
        }

        quickSort(shortArray,0,shortArray.length-1);
    }

    @Benchmark
    public void shortJavaSort(){
        for(int i =0;i<200;i++){
            shortArray[i] = r.nextInt(200);
        }

        java.util.Arrays.sort(shortArray);
    }

    //------------------------------------------------------
    @Benchmark
    public void longSelectionSort(){
        for(int j=0;j<4096;j++){
            longArray[j] = r.nextInt(500);
        }

        selectionSort(longArray);
    }

    @Benchmark
    public void longBubbleSort(){
        for(int j=0;j<4096;j++){
            longArray[j] = r.nextInt(500);
        }

        bubbleSort(longArray);
    }

    @Benchmark
    public void longInsertionSort(){
        for(int j=0;j<4096;j++){
            longArray[j] = r.nextInt(500);
        }

        insertionSort(longArray);
    }

    @Benchmark
    public void longHeapSort(){
        for(int j=0;j<4096;j++){
            longArray[j] = r.nextInt(500);
        }

        heapSort(longArray);
    }

    @Benchmark
    public void longMergeSort(){
        for(int j=0;j<4096;j++){
            longArray[j] = r.nextInt(500);
        }

        mergSort(longArray,0,longArray.length-1);
    }

    @Benchmark
    public void longQuickSort(){
        for(int j=0;j<4096;j++){
            longArray[j] = r.nextInt(500);
        }

        quickSort(longArray,0,longArray.length-1);
    }

    @Benchmark
    public void longJavaSort(){
        for(int j=0;j<4096;j++){
            longArray[j] = r.nextInt(500);
        }

        java.util.Arrays.sort(longArray);
    }

    public static void main(String[]args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(SortBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
