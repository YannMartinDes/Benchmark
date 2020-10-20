package test_time;

import java.lang.reflect.Method;

public class TestMethod implements ITest {


    public class ClassMethod{
        int size;


        public ClassMethod(int size){
            this.size = size;
        }

        public int getSize1(){
            return this.size;
        }

        public final int getSize2(){
            return this.size;
        }
    }

    int size;
    int sum;
    ClassMethod classMethod;

    @Override
    public void setup(int size) {
        this.size = size;
        classMethod = new ClassMethod(size);
        sum = 0;
    }

    @Override
    public void test1() {
        sum = 0;
        for(int i =0; i <size;i++){
            sum += classMethod.getSize1();
        }
    }

    @Override
    public void test2() {
        sum = 0;
        for(int i =0; i <size;i++){
            sum += classMethod.getSize2();
        }
    }

    @Override
    public void test3() {
        sum = 0;
        for(int i =0; i <size;i++){
            sum += classMethod.size;
        }
    }
}
