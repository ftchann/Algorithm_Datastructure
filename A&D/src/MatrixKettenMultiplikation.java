public class MatrixKettenMultiplikation {
    // From geek for geeks
    public static void main(String[] args) {
        int p[] = {40, 20, 30, 10, 30};
        /*
        There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
        Let the input 4 matrices be A, B, C and D.  The minimum number of
        multiplications are obtained by putting parenthesis in following way
        (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
         */
        int arr[] = new int[] {1, 2, 3, 4, 3};
        int n = arr.length;

        System.out.println("Minimum number of multiplications is "+
                MatrixChainOrder(arr, 1, n-1));
        int size = arr.length;

        System.out.println("Minimum number of multiplications is "+
                MatrixChainOrderDP(arr, size));
    }
    //Rekursive MatrixChainMultiplikation
    public static int MatrixChainOrder(int p[], int i, int j)
    {
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;

        for (int k=i; k<j; k++)
        {
            int count = MatrixChainOrder(p, i, k) +
                    MatrixChainOrder(p, k+1, j) +
                    p[i-1]*p[k]*p[j];
            if (count < min)
                min = count;
        }
        return min;
    }
    public static int MatrixChainOrderDP(int p[], int n)
    {
        int m[][] = new int[n][n];

        int i, j, k, L, q;

        /* m[i,j] = Minimum number of scalar multiplications needed
        to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where
        dimension of A[i] is p[i-1] x p[i] */

        // cost is zero when multiplying one matrix.
        for (i = 1; i < n; i++)
            m[i][i] = 0;

        // L is chain length.
        for (L=2; L<n; L++)
        {
            for (i=1; i<n-L+1; i++)
            {
                j = i+L-1;
                if(j == n) continue;
                m[i][j] = Integer.MAX_VALUE;
                for (k=i; k<=j-1; k++)
                {
                    // q = cost/scalar multiplications
                    q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }
        return m[1][n-1];
    }
}
