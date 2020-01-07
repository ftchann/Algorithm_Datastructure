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
    public void siftDown(int index) {
        while(true) {
            int maxIdx = index;
            if (leftIndex(index) < heap.size() && getLeft(index) > heap.get(maxIdx)) {
                maxIdx = leftIndex(index);
            }
            if (rightIndex(index) < heap.size() && getRight(index) > heap.get(maxIdx)) {
                maxIdx = rightIndex(index);
            }
            if (maxIdx != index) {
                swap(index, maxIdx);
                index = maxIdx;
            }
            else {
                break;
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
        int[] toadd = {19, 18, 16, 15, 17, 4, 12, 14, 11, 13, 6, 3, 0, 8, 2, 5, 9, 10, 1, 7};
        MaxHeap myheap = new MaxHeap();
        for (int i = 0; i < toadd.length; i++) {
            myheap.add(toadd[i]);
        }
        System.out.println(myheap.heap.size());
        myheap.print();
        myheap.remove();
        myheap.print();
    }
}
