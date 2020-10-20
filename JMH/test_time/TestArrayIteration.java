package test_time;

public class TestArrayIteration implements ITest{
    /*
    Meme temps environ pour les deux car :
        On charge par bloc de 64o et int = 4o et en avancant de 16 par 16,
        on fait 16*4 = 64o; et donc on charge un nouveau bloc à chaque fois. On ne gagne rien.
     */
    int[] arr;

    public void setup(int size) {
        arr = new int[size];
    }

    public void test1(){
        testArrayIteration();
    }

    public void test2(){
        testArrayIterationWithStep();
    }

    public void test3(){ }


    public void testArrayIteration() {
        for (int i = 0; i < arr.length; i++) arr[i] *= 3;
    }

    public void testArrayIterationWithStep() {
        for (int i = 0; i < arr.length; i += 16) arr[i] *= 3;
    }
}
