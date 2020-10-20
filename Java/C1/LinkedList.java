package C1;

import java.util.List;

public class LinkedList{//LIST CHEZ LE PROF

    protected EltList sentinel;

    public LinkedList(){
        sentinel = new EltList();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void appendLast(int val){
        EltList elt = new EltList();
        elt.val = val;
        elt.prev = sentinel.prev;
        elt.next = sentinel;
        sentinel.prev.next = elt;
        sentinel.prev = elt;
    }

    public void removeLast(){
        if(sentinel.next != sentinel.prev){
            EltList suppElt = sentinel.prev;
            suppElt.prev.next = suppElt.next;
            suppElt.next.prev = suppElt.prev;
        }
    }

    public void push(int val){
        appendLast(val);
    }

    public void pop(){
        removeLast();
    }

    public int top(){
        return last();
    }

    public int last(){
        return sentinel.prev.val;
    }
}
