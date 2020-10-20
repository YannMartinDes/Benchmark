package test_time;

import gnu.trove.TIntArrayList;

import java.util.ArrayList;

public class TestTrove implements ITest{

    TIntArrayList troveArraylist;
    ArrayList<Integer> arrayList;
    int size;
    int sum;

    @Override
    public void setup(int size) {
         this.size = size;
    }

    @Override
    public void test1() {
        troveArraylist = new TIntArrayList();
        for(int i =0; i <size; i++){
            troveArraylist.add(i);
        }
    }

    @Override
    public void test2() {
        arrayList = new ArrayList<>();
        for(int i =0; i <size;i++){
            arrayList.add(i);
        }
    }

    @Override
    public void test3() {

    }
}
