package com.jenkov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 5, time = 2)
public class SearchBenchmark {

    int[] array;
    Random r;
    int num;
    int size = 2048*1024;
    HashMap<Integer,Integer> hashMap;
    TreeMap<Integer,Integer> treeMap;

    @Setup
    public void setup(){
        array = new int[size];
        r = new Random();
        for(int i =0; i<size;i++){//Déjà triée du coup.
            array[i] = i;
        }

        hashMap = new HashMap<>();
        for(int i =0;i<size;i++){
            hashMap.put(i,i);
        }

        treeMap = new TreeMap<>();
        for(int i =0;i<size;i++){
            treeMap.put(i,i);
        }

    }


    @Benchmark
    public int regularSearchArray(){
        int index = -1;
        for(int j =0;j<2048;j++){
            num = r.nextInt(size);

            for(int i =0;i<array.length;i++){
                if(num == array[i])
                    index = i;
            }
        }
        return index;
    }

    @Benchmark
    public int BinarySearchArray(){
        int index = -1;
        for(int j =0;j<2048;j++) {
            num = r.nextInt(size);
            index = java.util.Arrays.binarySearch(array, num);
        }
        return index;
    }


    @Benchmark
    public int HashMapSearch(){
        int index = -1;
        for(int j =0;j<2048;j++) {
            num = r.nextInt(size);
            index = hashMap.get(j);
        }
        return index;
    }

    @Benchmark
    public int TreeMapSearch(){
        int index = -1;
        for(int j =0;j<2048;j++) {
            num = r.nextInt(size);
            index = treeMap.get(j);
        }
        return index;
    }

    public static void main(String[]args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(SearchBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
