import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] A = {1,5,7,32,8,34,5,3,67,9,3};
        System.out.println(Arrays.toString(A));
        selectionsort(A);
        System.out.println(Arrays.toString(A));
    }
    public static void selectionsort(int[] A){
        int n=A.length;
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minindex=0;
            for (int j = i; j < n; j++) {
                if(A[j] < min){
                    min = A[j];
                    minindex = j;
                }
            }
            swap(A, i, minindex);
        }
    }
    public static void swap(int[] A, int a, int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}
