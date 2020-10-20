package C2;

import C1.LinkedList;

public class Program2 {
    public static void main (String[] args) throws Exception
    {
        int nbOp = 100;
        int nbPushPop = 1_000_000;

        LinkedListWithPool linkedListWithPool = new LinkedListWithPool(nbPushPop);
        LinkedList linkedList = new LinkedList();

        System.out.println("Start calculate time");

        long start = System.nanoTime();
        for(int i =0;i<nbOp;i++){
            for(int j = 0;j<nbPushPop;j++){
                linkedList.push(j);
            }
            for(int j = 0;j<nbPushPop;j++){
                linkedList.pop();
            }
        }
        long stop = System.nanoTime();
        System.out.println(nbOp+ " operations of "+nbPushPop+" push/pop without pool, times : "+((stop-start)/1_000_000)+"ms");

        start = System.nanoTime();
        for(int i =0;i<nbOp;i++){
            for(int j = 0;j<nbPushPop;j++){
                linkedListWithPool.push(j);
            }
            for(int j = 0;j<nbPushPop;j++){
                linkedListWithPool.pop();
            }
        }
        stop = System.nanoTime();
        System.out.println(nbOp+ " operations of "+nbPushPop+" push/pop with pool, times : "+((stop-start)/1_000_000)+"ms");
    }
}
