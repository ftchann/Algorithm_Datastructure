
public class BinarySearch {

	int binarySearch(int array[], int x) {
		int left = 0, right = array.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;

			// Check if x is present at mid
			if (array[mid] == x)
				return mid;

			// If x greater, ignore left half
			if (array[mid] < x)
				left = mid + 1;

			// If x is smaller, ignore right half
			else
				right = mid - 1;
		}

		// if we reach here, then element was
		// not present
		return -1;
	}
	private static int lowerBound(int[] A, int element){
		//System.out.println(Arrays.toString(A) + " " + element);
		int low = 0;
		int high = A.length-1;
		while(low < high){
			int middle = low + (high - low +1)/2;
			if(A[middle] > element)
				high = middle-1;
			else
				low = middle;
		}
		return low;
	}
	public static int upperbound(int A[], int element){
		int low = 0;
		int high = A.length-1;
		while(low < high){
			int middle = low + (high - low)/2;
			if(A[middle] >= element)
				high = middle;
			else
				low = middle + 1;
		}
		return low;
	}

	public static void main(String args[]) {
		BinarySearch ob = new BinarySearch();
		int arr[] = { 2, 3, 4, 10, 40 };
		int n = arr.length;
		int x = 10;
		int result = ob.binarySearch(arr, x);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at " + "index " + result);
	}

}
