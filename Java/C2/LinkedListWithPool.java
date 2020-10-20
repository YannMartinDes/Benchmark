package C2;

import C1.EltList;
import C1.LinkedList;

public class LinkedListWithPool extends LinkedList {
    ObjectPool pool;

    LinkedListWithPool(int poolSize){
        super();
        pool = new ObjectPool(poolSize);
    }

    public void appendLastWithPool(int val){
        EltList elt = pool.allocate();
        elt.val = val;
        elt.prev = sentinel.prev;
        elt.next = sentinel;
        sentinel.prev.next = elt;
        sentinel.prev = elt;
    }

    public void removeLastWithPool(){
        EltList suppElt = sentinel.prev;
        suppElt.prev.next = suppElt.next;
        suppElt.next.prev = suppElt.prev;
        pool.deallocate(suppElt);
    }

    @Override
    public void push(int val) {
        appendLastWithPool(val);
    }

    @Override
    public void pop() {
        removeLastWithPool();
    }
}
