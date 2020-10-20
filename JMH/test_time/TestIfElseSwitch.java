package test_time;

public class TestIfElseSwitch implements ITest{
    int size;
    int sum;


    public void setup(int size){
        this.size = size;
    }

    public void test1(){
        testIfElse();
    }

    public void test2(){
        testSwitch();
    }

    public void test3(){}

    public int testIfElse(){
        sum = 0;
        for(int i = 0 ; i<size; i++){
            int test = i%15;

            if(test == 0) sum +=1;
            else if(test==1) sum -=1;
            else if(test==2) sum +=2;
            else if(test==3) sum -=2;
            else if(test==4) sum +=3;
            else if(test==5) sum -=3;
            else if(test==6) sum +=4;
            else if(test==7) sum -=4;
            else if(test==8) sum +=5;
            else if(test==9) sum -=5;
            else if(test==10) sum +=6;
            else if(test==11) sum -=6;
            else if(test==12) sum +=7;
            else if(test==13) sum -=7;
            else if(test==14) sum +=8;
            else sum -=8;
        }
        return sum;
    }

    public int testSwitch(){
        sum = 0;
        for(int i = 0; i<size;i++){
            int test = i%5;

            switch (test){
                case 0:
                    sum +=1; break;
                case 1 :
                    sum -=1; break;
                case 2:
                    sum +=2; break;
                case 3:
                    sum -=2; break;
                case 4 :
                    sum +=3; break;
                case 5 :
                    sum -=3; break;
                case 6 :
                    sum +=4; break;
                case 7 :
                    sum -=4; break;
                case 8 :
                    sum +=5; break;
                case 9 :
                    sum -=5; break;
                case 10 :
                    sum +=6; break;
                case 11 :
                    sum -=6; break;
                case 12 :
                    sum +=7; break;
                case 13 :
                    sum -=7; break;
                case 14 :
                    sum +=8; break;
                default:
                    sum -=8; break;
            }
        }
        return sum;
    }
}
