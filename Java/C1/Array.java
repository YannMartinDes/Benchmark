package C1;

public class Array
{
    private int[] arr;
    public Array(int n) {
        arr = new int[n];
    }

    public void set(int i, int val) {
        arr[i] = val;
    }
    public int capacity(){return arr.length;}

    public int get(int i){
        return arr[i];
    }

    public void expand(int n){
        if (n >arr.length){
            int [] temp = new int [n];
            for (int i = 0; i < arr.length; i++){
                temp[i] = arr[i];
            }
            arr = temp;
        }
    }
}
