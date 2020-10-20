package C2;

import C1.EltList;

public class ObjectPool {
    EltList arr[];
    int top;

    ObjectPool(int poolSize){
        System.out.println("Preparation of pool");
        arr = new EltList[poolSize];
        for(int i=0;i<poolSize;i++){
            arr[i] = new EltList();
        }
        top = poolSize;
        System.out.println("Finish preparation of pool");
    }

    public EltList allocate(){
        top--;
        return arr[top];
    }

    public void deallocate(EltList eltList){
        arr[top] = eltList;
        top++;
    }
}
