package C1;

public class Benchmark {

    public static final String TIMESTR = " ms";
    public static final int NANOTOMS = 1_000_000;


    private long getTime ()
    { return System.nanoTime(); }

    public double ComputeBench1(IBenchmark bench,String title,int nbExec,int nbop){
        double timeExec = NanoToMs(averageBench(bench,new Execute(){
            @Override
            public long exec(IBenchmark bench) {
                return benchMark1(bench,nbop);
            }
        },nbExec));
        displayBench(title,timeExec);
        return timeExec;

    }

    public double ComputeBench2(IBenchmark bench,String title,int nbExec,int nbop){
        double timeExec = NanoToMs(averageBench(bench,new Execute(){
            @Override
            public long exec(IBenchmark bench) {
                return benchMark2(bench,nbop);
            }
        },nbExec));
        displayBench(title,timeExec);
        return timeExec;

    }

    public long benchMark1(IBenchmark bench,int nbop){
        long start = getTime();

        int sum = 0;
        for(int i =0; i<nbop; i++){
            bench.push(i);
            sum+= bench.top();
            bench.pop();
        }
        return getTime()-start;
    }

    public long benchMark2(IBenchmark bench,int nbop){
        long start = getTime();
        int sum = 0;
        for(int i =0; i<nbop; i++) {
            bench.push(i);
            sum+= bench.top();
        }

        for(int i =0; i<nbop; i++) {
            bench.pop();
        }
        return getTime()-start;
    }

    public long averageBench(IBenchmark bench, Execute fonction, int number){
        long total = 0;
        for(int i = 0; i<number; i++){
            total += fonction.exec(bench);
        }
        return total/number;
    }

    public double NanoToMs(long nano){
        return  (double)nano/ NANOTOMS;
    }


    public void displayBench(String title,Double timeExec){

        System.out.println("name:" + title+" | value : "+Double.toString(timeExec)+ TIMESTR);
    }

}
