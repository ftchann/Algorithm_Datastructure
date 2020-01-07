import java.util.Arrays;
// Really Bad Implementation of Median of Median. it still runs in O(n), but uses a lot of extra space;
public class QuickSelect {
    public static void main(String[] args) {
        int[] A = {0,2,1,7,9,3,8,4,5,6};
        int k = 6; // suche nach dem k. kleinsten element;
        int ret = quickselect(A,0,A.length-1,k);
        System.out.println(ret);
    }
    //without median of median using most right as pivot;
    public static int quickselect(int[] A, int l,int r, int k){
        int partition = split(A,l,r);

        if(partition == k)
            return A[partition];
        else if(partition < k )
            return quickselect(A, partition + 1, r, k );
        else
            return quickselect(A, l, partition-1, k );
    }
    private static int split(int[] A, int l, int r) {
        int i=l;
        int j=r-1;
        int pivot = pivot(A,l,r);
        Swap(A,pivot,r);
        int p=A[r];//Pivot auswählen
        do{
            while(i<r && A[i]<p){
                i++;}
            while(j>l && A[j] > p){
                j--;}
            if(i<j){
                Swap(A, i, j);}
        }while((i<j));
        Swap(A,i,r);
        return i;
    }
    // Median of Medians fuck inplace, i won't rewrite whole code
    public static int pivot(int[] A, int l, int r){
        int[] B = new int[r-l+1];
        for (int i = 0; i < B.length; i++) {
            B[i] = A[l+i];
        }
        return median(B);
    }
    public static int median(int[] B){
        int plus = 0;
        if(B.length % 5 != 0){
            plus++;
        }
        int cpointer = 0;
        int C[] = new int[B.length/5+plus];
        for (int i = 0; i < B.length; i+=5) {
            int toadd;
            if(B.length-i >=5){
                int[] D = new int[5];
                for (int j = 0; j < 5; j++) {
                    D[j]=B[i+j];
                }
                toadd = partition5(D);
            }else{
                int[] D = new int[B.length-i];
                for (int j = 0; j < B.length-i; j++) {
                    D[j] = B[j+i];
                }
                toadd = partition5(D);
            }
            C[cpointer] = toadd;
            cpointer++;
        }
        if(C.length == 1){
            return C[0];
        }
        return median(C);
    }
    public static int partition5(int[] A){
        Arrays.sort(A);
        int n=A.length;
        return A[n/2];
    }
    private static void Swap(int[] A, int i, int largest) {
        int temp=A[i];
        A[i]=A[largest];
        A[largest]=temp;
    }
}
