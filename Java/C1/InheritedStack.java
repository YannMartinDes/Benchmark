package C1;

public class InheritedStack extends Array implements IBenchmark
{
    private int top =0;

    public InheritedStack (int n)
    {
        super(n);
    }

    public void push(int val){
        if( top >= capacity()){
            expand(2* capacity());
        }
        set(top, val);
        top++;
    }

    public int top(){
        return get(top-1);
    }

    public void pop(){
        top--;
    }
}
