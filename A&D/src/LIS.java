import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        // dummy element to get right index;
        int[] A = {Integer.MAX_VALUE,3,2,4,6,5,7,1};
        int n=A.length;
        int[] T = new int[n];
        int[] V = new int[n];
        for (int i = 0; i < n; i++) {
            T[i] = Integer.MAX_VALUE;
        }
        T[0] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int index = binarysearch(T, i, A[i]);
            T[index] = A[i];
            V[i] = T[index-1];
            System.out.println(Arrays.toString(T));
            System.out.println(Arrays.toString(V));
        }
        int count =0;
        for (int i = 1; i < n; i++) {
            if(T[i] != Integer.MAX_VALUE){
                count++;
            }
        }
        System.out.print(T[count] + " ");

        //find index
        int index = n-1;
        int tosearch = T[count];
        for(int i = 0; i<count-1; i++){
            for (; index >= 0; index--) {
                if(tosearch == A[index]){
                    break;
                }
            }
            System.out.print(V[index] + " ");
            tosearch = V[index];
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
