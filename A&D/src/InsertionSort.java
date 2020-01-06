import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] A = {3 ,7 ,5 ,1 ,4};
        int[] B = {5,1,7,32,8,34,5,3,67,9,3};
        System.out.println(Arrays.toString(B));
        badinsertionsort(B);
    }
    public static void insertionSort(int[] A){
        int n = A.length;
        for (int k = 0; k < n-1; k++) {
            //find i;
            int i = binarysearch(A, k+1, A[k+1]);
            System.out.println(i);
            int b = A[k+1];
            for(int j = k; j>=i; j--){
                A[j+1] = A[j];
            }
            A[i] = b;
            System.out.println(Arrays.toString(A));
        }
    }
    public static int binarysearch(int[] A, int right, int search){
        int left = 0;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(A[mid] < search){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
    public static void badinsertionsort(int[] A){
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = i-1; j >= 0 && A[j] > A[j+1]; j--) {
                swap(A, j, j+1);
            }
        }
        System.out.println(Arrays.toString(A));
    }
    public static void swap(int[] A, int a, int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}
