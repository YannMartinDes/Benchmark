package test_time;

public class TestFinalAndConst implements ITest {
    private static final int const1 = 4194304;//2048*2048
    private final int const2 = 4194304;//2048*2048
    private int const3 = 4194304;//2048*2048

    int[] arr;

    public void setup(int size){
        arr = new int[size];//Remplie de 0 donc mutliplication bidon.
    }

    public void test1(){
        testNormal();
    }

    public void test2(){
        testFinal();
    }

    public void test3(){
        testStaticFinal();
    }

    public void testStaticFinal(){
        for(int i=0; i< arr.length; i++){
            arr[i] *= TestFinalAndConst.const1;
        }
    }

    public void testFinal(){
        for(int i=0; i< arr.length; i++){
            arr[i] *= this.const2;
        }
    }

    public void testNormal(){
        for(int i=0; i< arr.length; i++){
            arr[i] *= this.const3;
        }
    }
}
