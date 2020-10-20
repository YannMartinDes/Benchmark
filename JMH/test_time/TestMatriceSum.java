package test_time;

public class TestMatriceSum implements ITest{
    double [][] matrice;
    int size;

    public void setup(int size) {
        matrice = new double[size][size];
        this.size = size;
    }


    public void test1(){
        testClassicMatriceSum();
    }

    public void test2(){
        testOptimizedMatriceSum();
    }

    public void test3(){}

    public double[] testClassicMatriceSum(){
        double[] rowsum = new double[size];
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                rowsum[i] += matrice[i][j];
            }
        }
        return rowsum;
    }

    public double[] testOptimizedMatriceSum() {
        double[] rowsum = new double[size];

        for (int i = 0; i < size; i++) {
            double[] arri = matrice[i];
            double sum = 0.0;
            for (int j = 0; j < size; j++) {
                sum += arri[j];
            }
            rowsum[i] = sum;
        }
        return rowsum;
    }
}
