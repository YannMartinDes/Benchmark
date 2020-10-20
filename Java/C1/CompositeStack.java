package C1;

public class CompositeStack implements IBenchmark
{
    private Array arr;
    private int top = 0;

    public CompositeStack (int n)
    {
        this.arr = new Array(n);
    }

    public void push(int val){
        if (top >= arr.capacity()){
            arr.expand(2* arr.capacity());
        }
        arr.set(top,val);
        top++;
    }

    public int top(){
        return arr.get(top-1);
    }
    public void pop(){
        top--;
    }
}
