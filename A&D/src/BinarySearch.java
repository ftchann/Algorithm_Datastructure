
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
