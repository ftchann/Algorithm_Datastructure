import java.util.ArrayList;

public class PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue myheap = new PriorityQueue();
        myheap.add(new dEdge(1,20));
        myheap.add(new dEdge(2,20));
        myheap.add(new dEdge(3,50));
        myheap.decreseKey(0,2);
        myheap.printkey();
    }
    ArrayList<dEdge> heap;
    public PriorityQueue(){
        heap = new ArrayList<>();
    }
    public void printkey(){
        for(dEdge a: heap){
            System.out.print(a.weight+ " ");
        }
        System.out.println();
    }
    public void add(dEdge k){
        heap.add(k);
        siftUp(heap.size()-1);
    }
    public dEdge remove(){
        int n = heap.size();
        swap(0, n-1);
        dEdge temp = heap.remove(n-1);
        siftDown(0);
        return temp;
    }
    public void decreseKey(int index, int value){
        heap.get(index).weight = value;
        siftUp(index);
    }
    public void increaseKey(int index, int value){
        heap.get(index).weight = value;
        siftDown(index);
    }
    public void siftDown(int k){
        int current = k;
        int n = heap.size();
        while(current <= n/2){
            int index = current;
            if(getLeft(current).weight < heap.get(current).weight){
                index = leftIndex(current);
            }
            if(rightIndex(current) < n){
                if(getRight(current).weight < getLeft(current).weight){
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
            if(getParent(current).weight > heap.get(current).weight){
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
    private dEdge getParent(int index) {
        return heap.get(parentIndex(index));
    }

    private dEdge getLeft(int index) {
        return heap.get(leftIndex(index));
    }

    private dEdge getRight(int index) {
        return heap.get(rightIndex(index));
    }
    public void swap(int l, int r){
        dEdge temp = heap.get(l);
        heap.set(l, heap.get(r));
        heap.set(r, temp);
    }
}
class dEdge{
    int vertex;
    int weight;
    dEdge(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}
