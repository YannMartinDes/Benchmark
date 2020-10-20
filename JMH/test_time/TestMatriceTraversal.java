package test_time;



public class TestMatriceTraversal implements ITest {
    int [][] matrice;
    int mSize;


    public void setup(int mSize) {
        this.mSize = mSize;
        matrice = new int[mSize][mSize];
    }

    public void test1(){
        testRowTraversal();
    }

    public void test2(){
        testColumnTraversal();
    }

    public void test3(){}

    public long testRowTraversal() {
        long sum = 0;
        for(int i = 0; i<mSize; i++){
            for(int j = 0; j<mSize; j++){
                sum += matrice[i][j];
            }
        }
        return sum;
    }

    public long testColumnTraversal() {
        long sum = 0;
        for(int i = 0; i<mSize; i++){
            for(int j = 0; j<mSize; j++){
                sum += matrice[j][i];
            }
        }
        return sum;
    }
}
