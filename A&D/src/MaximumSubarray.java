public class MaximumSubarray {
    public static void main(String[] args) {
        int A[] = {-2, -5, 6, -2, -3, 1, 5, -6};
        int maxsum=0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = Math.max(sum+A[i], 0);
            maxsum= Math.max(maxsum, sum);
        }
        System.out.println(maxsum);
    }
}
