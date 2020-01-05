public class QuickSort {
    // 1 Pivot Quicksort, with most right one as Pivot.
    public static void main(String[] args) {
        int A[] = {1,5,65,8,3,23,9,6,4,32,28};
        for (int a: A) {
            System.out.print(a+ " ");
        }
        System.out.println();
        quicksort(A,0,A.length-1);
        for (int a: A) {
            System.out.print(a+ " ");
        }
    }

    private static void quicksort(int[] A, int l, int r) {
        if(l < r){
            int k=split(A,l,r);
            quicksort(A,l,k-1);
            quicksort(A,k+1,r);
        }
    }

    private static int split(int[] A, int l, int r) {
        int i=l;
        int j=r-1;
        int p=A[r];//Pivot auswählen
        do{
            while(i<r && A[i]<p){
                i++;}
            while(j>l && A[j] > p){
                j--;}
            if(i<j){
                Swap(A, i, j);}
        }while((i<j));
        Swap(A,i,r);
        return i;
    }
    private static void Swap(int[] A, int i, int largest) {
        int temp=A[i];
        A[i]=A[largest];
        A[largest]=temp;
    }
}