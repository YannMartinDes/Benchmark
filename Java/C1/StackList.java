package C1;

public class StackList{
    private LinkedList list;

    public StackList(){
        list = new LinkedList();
    }

    public void push(int val){
        list.appendLast(val);
    }

    public void pop(){
        list.removeLast();
    }

    public int top(){
        return list.last();
    }
}
