package test_time;

import java.io.File;

public class TestLocalVariable implements ITest {
    static int varStatic = 2048*2048*2;
    int var = 2048*2048*2;
    int size;

    @Override
    public void setup(int size) {
        this.size = size;
    }

    @Override
    public void test1() {
        StaticField();
    }

    @Override
    public void test2() {
        Field();
    }

    @Override
    public void test3() {
        LocalField();
    }

    private int StaticField(){
        int sum = 0;
        for(int i =0;i<size;i++){
            sum += TestLocalVariable.varStatic;
        }
        return sum;
    }

    private int Field(){
        int sum = 0;
        for(int i =0;i<size;i++){
            sum += var;
        }
        return sum;
    }

    private int LocalField(){
        int sum = 0;
        int local = var;
        for(int i =0;i<size;i++){
            sum += local;
        }
        return sum;
    }
}
