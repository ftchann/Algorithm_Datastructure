import java.util.Arrays;
import java.util.Scanner;

public class QuickSortfFromScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		// int n = s.nextInt();
		// int[] array = new int[n];
		int[] test = { 5, 10, 12, 12, 13, 3, 21, 13, 4 };
		// for (int i = 0; i < n; i++) {
		// array[i] = s.nextInt();
		// }
		quicksort(test, 0, test.length - 1);

		System.out.println(Arrays.toString(test));

	}

	  private static void quicksort(int[] array, int low, int high) {
		    if (low < high) {
		      int pivot = partition(array, low, high);
		      quicksort(array, low, pivot);
		      quicksort(array, pivot + 1, high);
		    }
		  }

		  // Performs Hoare partition algorithm for quicksort
		  private static int partition(int[] array, int low, int high) {
		    int pivot = array[low];
		    int i = low - 1;
		    int j = high + 1;
		    
		    while (true) {
		      do {
		        i++;
		      } while (array[i] < pivot);
		      do {
		        j--;
		      } while (array[j] > pivot);
		      
		      if (i < j) {
		    	  swap(array, i, j);
		      }
		      else return j;
		    }
		  }

		  // Swap two elements
		  private static void swap(int[] ar, int i, int j) {
		    int temp = ar[i];
		    ar[i] = ar[j];
		    ar[j] = temp;
		  }


}
