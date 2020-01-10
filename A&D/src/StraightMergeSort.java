public class StraightMergeSort {
    public static void mergesort(int[] A){

        int n = A.length;

        for (int curr_size = 1; curr_size <= n-1; curr_size = 2*curr_size)
        {
            for (int left_start = 0; left_start < n-1; left_start += 2*curr_size)
            {

                int mid = Math.min(left_start + curr_size - 1, n-1);

                int right_end = Math.min(left_start + 2*curr_size - 1, n-1);

                merge(A, left_start, mid, right_end);
            }
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
        mergesort(A);
        System.out.println();
        for (int a: A) {
            System.out.print(a+ " ");
        }
    }
}
