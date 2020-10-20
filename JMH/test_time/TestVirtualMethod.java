package test_time;

public class TestVirtualMethod implements ITest {

    public interface MyInterface{
        public int getMyDoubleEntry(int entry);
    }

    public class Class1 implements MyInterface{
        int size;

        public Class1(int size){
            this.size = size;
        }

        @Override
        public int getMyDoubleEntry(int entry) {
            return entry*2;
        }

        public int getMyDoubleEntryNoVirtual(int entry){
            return entry*2;
        }
    }

    MyInterface myInterface;
    int size;
    int sum;

    @Override
    public void setup(int size) {
        myInterface = new Class1(size);
        this.size = size;
        sum = 0;
    }

    @Override
    public void test1() {
        sum = 0;
        for(int i =0;i<size;i++){
            if(myInterface instanceof Class1) {
                sum += ((Class1) myInterface).getMyDoubleEntryNoVirtual(i);
            }
        }
    }

    @Override
    public void test2() {
        sum = 0;
        for(int i =0;i<size;i++){
            sum += myInterface.getMyDoubleEntry(i);
        }
    }

    @Override
    public void test3() {

    }
}
