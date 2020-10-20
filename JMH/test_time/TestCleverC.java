package test_time;

public class TestCleverC implements ITest {
    //TEST MODIFIER POUR TEMPS CORRECT

    @Override
    public void setup(int size) {

    }

    @Override
    public void test1() {
        testNormalWhile();
    }

    @Override
    public void test2() {
        testCleverWhile();
    }

    @Override
    public void test3() {}

    public double testNormalWhile(){
        int year = 0;
        double sum = 200.0;
        double[] balance = new double[20000];

        sum *= 1.001;
        balance[year] = sum;

        while(balance[year] < Math.pow(2,32)){
            sum *= 1.001;
            year++;
            balance[year] = sum;
        }

        return sum;
    }

    public double testCleverWhile(){
        int year = 0;
        double sum = 200.0;
        double[] balance = new double[20000];
        while ((balance[year++] = sum *= 1.001) < Math.pow(2,32));

        return sum;
    }
}
