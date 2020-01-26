public class parenthesis {
    public static void main(String[] args) {
        int[] v = {6 , -3, -2, 5};
        int N = v.length;
        int max = 0;
        int min = 0;
        int pos = 0;
        for (int i = N-1; i >=0 ; i--) {
            if(v[i] > 0){
                max += v[i];
                min += v[i];
                pos += v[i];
            }else{
                max = Math.max(v[i] - min, v[i] + max);
                min += v[i] - 2 * pos;
                pos = 0;
            }
            System.out.println(max + " " + min + " "+ pos);
        }
    }
}
