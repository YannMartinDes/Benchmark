package test_time;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestLinkedListGet implements ITest {

    LinkedList<Integer> linkedList;
    ArrayList<Integer> arrayList;

    @Override
    public void setup(int size) {

        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();

        for(int i =0; i<size; i++){
            linkedList.add(i);
            arrayList.add(i);
        }
    }

    @Override
    public void test1() {
        int sum = 0;
        for(int i = 0; i<linkedList.size();i++){
            sum += linkedList.get(i);
        }
    }

    @Override
    public void test2() {
        int sum = 0;
        for(Integer x : linkedList){
            sum += x;
        }
    }

    @Override
    public void test3() {

    }
}
