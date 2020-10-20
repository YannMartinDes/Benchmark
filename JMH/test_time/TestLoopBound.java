package test_time;

public class TestLoopBound implements ITest{
    int[] arr;
    int sum;

    public void setup(int size){
        arr = new int[size];
    }

    public int size(){
        return this.arr.length;
    }

    public void test1(){testClassicLoop();}

    public void test2(){
        testOptimizedLoop();
    }

    public void test3(){}

    public int testClassicLoop(){
        sum = 1;
        for(int i = 0; i<size()*2; i++){
            sum +=i;
        }
        return sum;
    }

    public int testOptimizedLoop(){
        sum = 1;
        for(int i = 0, stop = size()*2; i<stop; i++){
            sum +=i;
        }
        return sum;
    }
}
