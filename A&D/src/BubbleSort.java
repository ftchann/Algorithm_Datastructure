import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] A = {1,5,7,32,8,34,5,3,67,9,3};
        System.out.println(Arrays.toString(A));
        bubblesort(A);
        System.out.println(Arrays.toString(A));
    }
    public static void bubblesort(int A[]){
        int n= A.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i-1;j++){
                if(A[j] > A[j+1]){
                    swap(A, j, j+1);
                }
            }
        }
    }
    public static void swap(int[] A, int a, int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}
