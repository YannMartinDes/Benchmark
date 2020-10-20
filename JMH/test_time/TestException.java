package test_time;

public class TestException implements ITest {

    class MyException extends Exception {
        @Override
        public Throwable fillInStackTrace() {
            return this;
        }
    }

    int size;
    int sum;

    @Override
    public void setup(int size) {
        this.size = size;
    }

    @Override
    public void test1() {
        sum =0;
        for(int i =0;i<size;i++){
            try {
                Exception exception = new Exception();
                throw exception;
            }
            catch (Exception e){
                sum++;
            }
        }
    }

    @Override
    public void test2() {
        sum = 0;
        for(int i =0;i<size;i++){
            try {
                MyException exception = new MyException();
                throw exception;
            }
            catch (Exception e){
                sum++;
            }
        }
    }

    @Override
    public void test3() {
        sum = 0;
        MyException exception = new MyException();
        for(int i =0;i<size;i++){
            try {
                throw exception;
            }
            catch (Exception e){
                sum++;
            }
        }
    }
}
