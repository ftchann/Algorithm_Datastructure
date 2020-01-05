public class MergeSort {
    // Normal MergeSort, Recursive
    public static void mergesort(int[] A, int left, int right){
        int mid=(left+right)/2;
        if(right - left >= 1) {
            mergesort(A, left, mid);
            mergesort(A, mid + 1, right);
            merge(A,left,mid,right);
        }
    }

    private static void merge(int[] A, int left, int mid, int right) {
        int i=left;
        int j= mid+1;
        int k = 0;
        int[] B = new int[right-left+1];
        while(i<=mid && j <= right){
            if(A[i] <= A[j]){
                B[k]=A[i];
                k++;
                i++;
            }
            else{
                B[k]=A[j];
                k++;
                j++;
            }
        }
        for( ; i<=mid;i++,k++){
            B[k]=A[i];
        }
        for( ; j<=right;j++,k++){
            B[k]=A[j];
        }
        //Copy B to A
        int t=left;
        k=0;
        while(t<=right){
            A[t]=B[k];
            t++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] A = {1,5,7,32,8,34,5,3,67,9,3};
        for (int a: A) {
            System.out.print(a+ " ");
        }
        mergesort(A,0,A.length-1);
        System.out.println();
        for (int a: A) {
            System.out.print(a+ " ");
        }
    }
}