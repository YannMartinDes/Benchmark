package test_time;

public class TestStringBuilder implements ITest {
    int size;

    @Override
    public void setup(int size) {
        this.size = size;
    }

    @Override
    public void test1() {
        ClassicStringConcat();
    }

    @Override
    public void test2() {
        StringBuilderConcat();
    }

    @Override
    public void test3() {

    }

    private String ClassicStringConcat(){
        String s = "";
        for (int i=0; i<size; i++) {
            s += "#" + i;
        }
        return s;
    }

    private String StringBuilderConcat(){
        StringBuilder sbuf = new StringBuilder();
        for (int i=0; i<size; i++) {
            sbuf.append("#").append(i);
        }
        String s = sbuf.toString();
        return s;
    }
}
