
//HeapSort with BinaryHeap. Fibonacci Heap will come.
public class HeapSort {
    public static void sort(char[] A){
        int n= A.length;
        int mid=n/2;
        //Create Max Heap;
        for (int i = mid; i >=0 ; i--) {
            RestoreHeapCondition(i, A,n);
        }
        System.out.println();
        //SelectionSort;

        for (int i = 1; i <= n-1; i++) {
            Swap(A,n-i,0);
            for (char a: A) {
                System.out.print(a);
                System.out.print(" ");
            }
            System.out.println();
            RestoreHeapCondition(0,A,n-i);
        }
    }

    private static void RestoreHeapCondition(int i, char[] A, int n ){
        int left=2*i+1;
        int right=2*i+2;

        int largest=i;
        if(left < n && A[left] > A[largest])
            largest = left;
        if(right <n && A[right] > A[largest])
            largest = right;
        if(largest != i){
            //swap
            Swap(A,i,largest);

            //recursive call
            RestoreHeapCondition(largest, A,n);
        }
    }

    private static void Swap(char[] A, int i, int largest) {
        char temp=A[i];
        A[i]=A[largest];
        A[largest]=temp;
    }

    public static void main(String[] args) {
        char[] A= {'A', 'L', 'G', 'O', 'R', 'I', 'T', 'H', 'M'};
        for (char a: A) {
            System.out.print(a);
            System.out.print(" ");
        }
        sort(A);
    }
}