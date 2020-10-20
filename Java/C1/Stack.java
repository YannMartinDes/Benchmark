package C1;

public class Stack implements IBenchmark
{
    private int[] arr;
    private int top = 0;

    public Stack(int n){
        arr = new int[n];
    }

    public void push(int val){
        if (top >= arr.length){
            int[] temp = new int[2*arr.length];
            for (int i = 0 ; i< arr.length; i++){
                temp[i] = arr[i];
            }
            arr = temp;
        }
        arr[top++] = val;
    }

    public int top(){
        return arr[top - 1];
    }

    public void pop(){
        top--;
    }
}