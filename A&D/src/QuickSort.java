public class QuickSort {
    // 1 Pivot Quicksort, with most right one as Pivot. Does not work with duplicate value.
    // update with better partition works now;
    public static void main(String[] args) {
        int A[] = {1,5,65,8,3,23,9,6,4,32,28,1,1};
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
            int k=partition(A,l,r);
            quicksort(A,l,k-1);
            quicksort(A,k+1,r);
        }
    }
    private static int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
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