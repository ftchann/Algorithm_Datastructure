import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        int[] A = {3,2,4,6,5,7,1};
        int n=A.length;
        int[] T = new int[n];
        for (int i = 0; i < n; i++) {
            T[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n; i++) {
            int index = binarysearch(T, i, A[i]);
            T[index] = A[i];
            //System.out.println(Arrays.toString(T));
        }
        int count =0;
        for (int i = 0; i < n; i++) {
            if(T[i] != Integer.MAX_VALUE){
                count++;
            }
        }
        System.out.println(count);
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
}
