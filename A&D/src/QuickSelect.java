import java.util.Arrays;

public class QuickSelect {
    public static void main(String[] args) {
        int[] A = {0,2,1,7,9,3,8,4,5,6};
        int k = 4; // suche nach dem k. kleinsten element;
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
    private static void Swap(int[] A, int i, int largest) {
        int temp=A[i];
        A[i]=A[largest];
        A[largest]=temp;
    }
}
