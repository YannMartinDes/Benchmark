package test_time;

import java.util.ArrayList;
public class TestArraylistRemove implements ITest{
    ArrayList<Integer> arrayList;

    @Override
    public void setup(int size) {
        arrayList = new ArrayList<>();

        for(int i =0; i<size; i++){
            arrayList.add(i);
        }
    }

    @Override
    public void test1() {

        for(int i = 0; i<arrayList.size();i++){
            arrayList.remove(0);
            arrayList.add(0,i);
        }
    }

    @Override
    public void test2() {
        for(int i = 0; i<arrayList.size();i++){
            arrayList.remove(arrayList.size()-1);
            arrayList.add(arrayList.size()-1,i);
        }
    }

    @Override
    public void test3() {

    }
}
