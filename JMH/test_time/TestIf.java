package test_time;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestIf implements ITest{
    class Bird{
        int type = -1;

        public Bird(int type){
            this.type = type;
        }

        public boolean isGrower(){
            return type == 1;
        }

        public boolean isPullet(){
            return type == 0;
        }
    }

    List<Bird> birds;
    int sum = 0;

    public void setup(int size){
        sum = 0;
        //birds = new ArrayList<>();
        birds = new LinkedList<>();
        for(int i =0; i< size; i++){
            birds.add(new Bird(i%2));//On alterne.
        }
    }

    public void test1(){
        testClassicIf();
    }

    public void test2(){
        testOptimizedIf();
    }

    public void test3(){}

    public int testClassicIf(){
        sum = 0;
        for(int i =0; i< birds.size(); i++){
            if(birds.get(i).isGrower()) sum++;
            if(birds.get(i).isPullet()) sum--;
        }
        return sum;
    }

    public int testOptimizedIf(){
        sum = 0;
        for(int i =0; i< birds.size(); i++){
            Bird bird = birds.get(i);
            if(bird.isGrower()) sum++;
            if(bird.isPullet()) sum--;
        }
        return sum;
    }
}
