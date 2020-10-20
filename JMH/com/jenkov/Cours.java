package com.jenkov;

public class Cours {

    static class EltList {
        EltList next;
        EltList prev;
        int val;
    }

    final static int POOLSIZE = 1000000;

    static class EltListPool {
        EltList[] arr;
        int top;
        EltListPool(){
            arr=new EltList[POOLSIZE];
            for(int i=0;i<POOLSIZE;i++){
                arr[i]=new EltList();
            }
            top=POOLSIZE;
        }
        EltList allocate() {
            top--;
            return arr[top];
        }
        void deallocate(EltList elt) {
            arr[top] = elt;
            top++;
        }
    }



    static class List {
        EltList sentinel;
        List() {
            sentinel=new EltList();
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
        }
        int last() {
            return sentinel.prev.val;
        }
        void appendLast(int val) {
            EltList elt = new EltList();
            elt.val = val;
            elt.prev = sentinel.prev;
            elt.next = sentinel;
            sentinel.prev.next = elt;
            sentinel.prev = elt;
        }
        void removeLast() {
            EltList suppElt = sentinel.prev;
            suppElt.prev.next = suppElt.next;
            suppElt.next.prev = suppElt.prev;
        }
        void appendLastWithPool(EltListPool pool,int val) {
            EltList elt = pool.allocate();
            elt.val = val;
            elt.prev = sentinel.prev;
            elt.next = sentinel;
            sentinel.prev.next = elt;
            sentinel.prev = elt;
        }
        void removeLastWithPool(EltListPool pool) {
            EltList suppElt = sentinel.prev;
            suppElt.prev.next = suppElt.next;
            suppElt.next.prev = suppElt.prev;
            pool.deallocate(suppElt);
        }
    }

    //
    // StackList
    //

    public static class StackList {
        List list;

        StackList(){
            list=new List();
        }
        void push(int val) {
            list.appendLast(val);
        }
        void pop(){
            list.removeLast();
        }
        int top() {
            return list.last();
        }
    }

    public static class StackListWithPool {
        List list;
        EltListPool pool;

        StackListWithPool(){
            list=new List();
            pool=new EltListPool();
        }
        void push(int val) {
            list.appendLastWithPool(pool,val);
        }
        void pop(){
            list.removeLastWithPool(pool);
        }
        int top() {
            return list.last();
        }
    }

    public static void main(String[]args) {
        StackList stack = new StackList();
        long diff;
        long start = System.nanoTime();
        for (int k = 0; k < 100; k++) {
            for (int i = 0; i < POOLSIZE; i++) {
                stack.push(i);
            }
            for (int i = 0; i < POOLSIZE; i++) {
                //		printf("top=%d", top(&stack));
                stack.pop();
            }
        }
        long stop = System.nanoTime();
        diff = (stop - start)/1000000;

        System.out.println(POOLSIZE + " push/pop without pool time=" + diff + " ms");

        StackListWithPool stackWithPool = new StackListWithPool();
        start = System.nanoTime();
        for (int k = 0; k < 100; k++) {
            for (int i = 0; i < POOLSIZE; i++) {
                stackWithPool.push(i);
            }
            for (int i = 0; i < POOLSIZE; i++) {
                //		printf("top=%d", top(&stack));
                stackWithPool.pop();
            }
        }
        stop = System.nanoTime();
        diff = (stop - start)/1000000;

        System.out.println(POOLSIZE + " push/pop with pool time=" + diff + " ms");
    }
}
