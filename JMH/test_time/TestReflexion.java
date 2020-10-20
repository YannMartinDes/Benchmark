package test_time;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflexion implements ITest {

    int size;
    int sum;
    Method method;

    public int getDoubleEntry(int entry){
        return entry*2;
    }

    @Override
    public void setup(int size) {
        this.size = size;
        try{
            method = TestReflexion.class.getMethod("getDoubleEntry",int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test1() {
        sum =0;
        for(int i =0; i< size; i++){
            sum += getDoubleEntry(i);
        }
    }

    @Override
    public void test2() {
        sum =0;
        try {
            for(int i =0; i< size; i++){
                sum += (int) method.invoke(this,i);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test3() {
        sum =0;
        try {
            for(int i =0; i< size; i++){
                if(method.canAccess(this))
                   sum += (int) method.invoke(this,i);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
