import java.util.Arrays;

public class Natural2WayMergeSort {
    public static void main(String[] args) {
        int[] A = {1,5,7,32,8,34,5,3,67,9,3};
        System.out.println(Arrays.toString(A));
        mergesort(A);
        System.out.println(Arrays.toString(A));
    }
    public static void mergesort(int[] A){
        int left = 100;//random initialize
        int n = A.length;
        do{
            int right = -1;
            while(right < n){
                left = right + 1;
                int middle = left;
                while(middle < n-1 && A[middle + 1] >= A[middle]){
                    middle++;
                }
                if(middle < n-1){
                    right = middle + 1;
                    while(right < n-1 && A[right+1] >= A[right]){
                        right++;
                    }
                    merge(A, left, middle, right);
                }
                else{
                    right = n;
                }
            }
        }while(left != 0);
    }
    public static void merge(int[] A, int left, int mid, int right) {
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
}
