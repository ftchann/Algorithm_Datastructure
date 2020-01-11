import java.io.*;
import java.util.Scanner;

class Main2 {
    //
    // The solution
    //
    public static void read_and_solve(InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        int v = scanner.nextInt();
        switch(v) {
            case 1: out.println("Hello World!"); break;
            case 2: out.println("Hello ETH!"); break;
            default:
                out.println("Hello Judge!"); break;
        }
        scanner.close();
    }

    //
    // Do not modify the main file, and keep the method read_and_solve
    //
    public static void main(String[] args) throws Exception {
        read_and_solve(System.in, System.out);
        throw new Exception("hallo");
    }
}