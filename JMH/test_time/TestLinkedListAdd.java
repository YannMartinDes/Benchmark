package test_time;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestLinkedListAdd implements ITest{

    LinkedList<Integer> linkedList;
    int startMiddle;

    @Override
    public void setup(int size) {

        linkedList = new LinkedList<>();

        for(int i =0; i<size; i++){
            linkedList.add(i);
        }

        startMiddle = linkedList.size()/2;
    }

    @Override
    public void test1() {
        for(int i = 0; i<linkedList.size();i++){
            linkedList.add(0,i);
            linkedList.remove(0);
        }
    }

    @Override
    public void test2() {
        for(int i = 0; i<linkedList.size();i++){
            linkedList.add(startMiddle,i);
            linkedList.remove(startMiddle);
        }
    }

    @Override
    public void test3() {
    }
}
