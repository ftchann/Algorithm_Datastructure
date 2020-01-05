
import java.util.Arrays;
import java.util.List;

class WordBreakProblem
{
    // Function to segment given String into a space-separated
    // sequence of one or more dictionary words
    static int counter=0;
    private static void wordBreakUtilBottomUp(String s, List<String> wordDict){

        if(s == null || s.length() == 0) {
            System.out.println("false");
            return;
        }

        boolean[] table  = new boolean[s.length()+1];

        table[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (table[j] && wordDict.contains(s.substring(j, i))) {
                    table[i] = true;
                }
            }
        }
        System.out.println("0"+ s);
        for(boolean a: table){
            if(a)
                System.out.print("T");
            else
                System.out.print("F");
        }
        System.out.println();
        //Backtracking
        String output = "";
        if(table[s.length()]){
            int j= s.length();
            while(j!= 0){
                for(int i=j-1;i>=0;i--){
                    if(table[i]){
                        String maybeoutput= s.substring(i,j);
                        if(wordDict.contains(maybeoutput)){
                            output = maybeoutput+ " " + output;
                            j=i;
                            break;
                        }
                    }
                }
            }
            System.out.println(output);
        }
        else
            System.out.println("false");
    }

    // main function
    public static void main(String[] args)
    {
        // List of Strings to represent dictionary
        List<String> dict = Arrays.asList("kuz", "kuzdra", "bokr");

        // input String
        String str = "kuzdrabokr";
        System.out.println(str.substring(0,0));
        wordBreakUtilBottomUp(str, dict);
    }
}