package C1;

import java.util.Scanner;

public class Program
{
    public final static Benchmark bench = new Benchmark();

    public static void main (String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        sc.nextInt();


        System.out.println("P1");
        bench.ComputeBench1(new Stack(1),"C1.Stack",1,100_000);
        bench.ComputeBench1(new CompositeStack(1),"C1.CompositeStack",1,100_000);
        bench.ComputeBench1(new InheritedStack(1),"C1.InheritedStack",1,100_000);
        bench.ComputeBench1(new StackListInt(),"C1.Stack list",1,100_000);

        System.out.println("P2");
        bench.ComputeBench2(new Stack(1),"C1.Stack",1,10_000);
        bench.ComputeBench2(new CompositeStack(1),"C1.CompositeStack",1,10_000);
        bench.ComputeBench2(new InheritedStack(1),"C1.InheritedStack",1,10_000);
        bench.ComputeBench2(new StackListInt(),"C1.Stack list",1,10_000);
    }
}
