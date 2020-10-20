package test_time;

public class TestConstantArray implements ITest {

    int size;

    public static int monthdays(int y, int m) {
        int[] monthlengths =
                { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        return m == 2 && leapyear(y) ? 29 : monthlengths[m-1];
    }

    private final static int[] monthlengths2 =
            { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };


    public static int monthdays2(int y, int m) {
        return m == 2 && leapyear(y) ? 29 : monthlengths2[m-1];
    }


    public static boolean leapyear(int year){
        return (year%400 == 0) || (year%4 == 0 && year%100 != 0);
    }

    @Override
    public void setup(int size) {
        this.size = size;
    }

    @Override
    public void test1() {
        InternalConstantArray();
    }

    @Override
    public void test2() {
        StaticConstantArray();
    }

    @Override
    public void test3() {

    }

    private int InternalConstantArray(){
        int sum =0;
        for(int y =1;y <size;y++){
            for(int m = 1;m<=12;m++){
                sum +=monthdays(y,m);
            }
        }
        return sum;
    }

    private int StaticConstantArray(){
        int sum =0;
        for(int y =1;y <size;y++){
            for(int m = 1;m<=12;m++){
                sum +=monthdays2(y,m);
            }
        }
        return sum;
    }
}
