package C1;

public class StackListInt implements IBenchmark{
    private LinkedList list;

    public StackListInt(){
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
