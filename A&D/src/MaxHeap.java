import java.util.ArrayList;

public class MaxHeap {
    //MaxHeap
    ArrayList<Integer> heap;
    public MaxHeap(){
        heap = new ArrayList<>();
    }
    public void print(){
        for(int a: heap){
            System.out.print(a+ " ");
        }
        System.out.println();
    }
    public void add(int k){
        heap.add(k);
        siftUp(heap.size()-1);
    }
    public int max(){
        // Assume heap is not empty, don't want to throw Error cause i don't want to catch them.
        return heap.get(0);
    }
    public int remove(){
        int n = heap.size();
        swap(0, n-1);
        int temp = heap.remove(n-1);
        siftDown(0);
        return temp;
    }
    public void decreseKey(int index, int value){
        heap.set(index , value);
        siftDown(index);
    }
    public void increaseKey(int index, int value){
        heap.set(index, value);
        siftUp(index);
    }
    public void siftDown(int k){
        int current = k;
        int n = heap.size();
        while(current <= n/2){
            int index = current;
            if(getLeft(current) > heap.get(current)){
                index = leftIndex(current);
            }
            if(rightIndex(current) < n){
                if(getRight(current) > getLeft(current)){
                    index = rightIndex(current);
                }
            }
            if(index != current){
                swap(current,index);
                current = index;
            }
            else{
                return;
            }
        }
    }
    public void siftUp(int k){
        int current = k;
        while(parentIndex(current) >= 0){
            if(getParent(current) < heap.get(current)){
                swap(current, parentIndex(current));
            }
            current = parentIndex(current);
        }
    }
    public int parentIndex(int index){
        return ((index + 1)/2) - 1;
    }

    public int leftIndex(int index) {
        return ((index + 1)*2) - 1;
    }

    public int rightIndex(int index) {
        return (index + 1)*2;
    }
    private int getParent(int index) {
        return heap.get(parentIndex(index));
    }

    private int getLeft(int index) {
        return heap.get(leftIndex(index));
    }

    private int getRight(int index) {
        return heap.get(rightIndex(index));
    }
    public void swap(int l, int r){
        int temp = heap.get(l);
        heap.set(l, heap.get(r));
        heap.set(r, temp);
    }

    public static void main(String[] args) {
        MaxHeap myheap = new MaxHeap();
        myheap.add(10);
        myheap.add(5);
        myheap.add(50);
        myheap.decreseKey(0,2);
        myheap.print();
    }
}
