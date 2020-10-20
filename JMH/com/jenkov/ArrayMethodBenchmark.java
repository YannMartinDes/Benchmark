package com.jenkov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 5, time = 2)
public class ArrayMethodBenchmark {

    int[] arr;
    int size = 2048*2048*2;

    @Setup
    public void setup(){
        arr = new int[size];

        for(int i =0; i<size;i++){
            arr[i] = i;
        }
    }

    @Benchmark
    public void regularCopy(){
        int[] copy = new int[arr.length];

        for(int i =0; i<arr.length;i++){
            copy[i] = arr[i];
        }
    }

    @Benchmark
    public void ArrayCopy(){
        int[] copy = new int[arr.length];

        java.lang.System.arraycopy(arr,0,copy,0,arr.length);
    }

    @Benchmark
    public boolean regularEqual(){
        int[] copy = new int[arr.length];
        java.lang.System.arraycopy(arr,0,copy,0,arr.length);

        for(int i =0; i<arr.length;i++){
            if(copy[i] != arr[i])
                return false;
        }
        return true;
    }

    @Benchmark
    public boolean ArrayEqual(){
        int[] copy = new int[arr.length];
        java.lang.System.arraycopy(arr,0,copy,0,arr.length);

        return java.util.Arrays.equals(arr,copy);
    }

    @Benchmark
    public void regularFill(){
        int[] copy = new int[arr.length];

        for(int i =0;i<arr.length;i++){
            copy[i] = 8;
        }
    }

    @Benchmark
    public void ArrayFill(){
        int[] copy = new int[arr.length];

        java.util.Arrays.fill(copy,8);
    }

    public static void main(String[]args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(ArrayMethodBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
