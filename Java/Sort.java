import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		
		int[] arr = new int[10]; 
		
		arr = new int[] {1, 10, 3, 8, 5, 6, 7, 4, 9, 2};
		System.out.println("버블 정렬 전: " + Arrays.toString(arr));
		bubbleSort(arr);
		System.out.println("버블 정렬 후: " + Arrays.toString(arr));
		System.out.println();
		
		arr = new int[] {1, 10, 3, 8, 5, 6, 7, 4, 9, 2};
		System.out.println("삽입 정렬 전: " + Arrays.toString(arr));
		InsertionSort(arr);
		System.out.println("삽입 정렬 후: " + Arrays.toString(arr));
		System.out.println();
		
		arr = new int[] {1, 10, 3, 8, 5, 6, 7, 4, 9, 2};
		System.out.println("선택 정렬 전: " + Arrays.toString(arr));
		selectionSort(arr);
		System.out.println("선택 정렬 후: " + Arrays.toString(arr));
		System.out.println();
		
	}
	
	private static void bubbleSort(int[] arr) {
		
		int temp;
		
		final int LEN = arr.length;
		for (int i = 0; i < LEN; ++i) {
			for (int j = 0; j < LEN - 1 - i; ++j) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
	}
	
	private static void InsertionSort(int[] arr) {
		
		int temp;
		
		final int LEN = arr.length;
		for (int i = 1; i < LEN; ++i) {
			temp = arr[i];
			int index = i;
			
			while (index > 0 && temp < arr[index - 1]) {
				arr[index] = arr[index - 1];
				--index;
			}
			
			arr[index] = temp;
		}
		
	}
	
	private static void selectionSort(int[] arr) {
		
		int temp;
		int minIndex;
		int min;
		
		final int LEN = arr.length;
		for (int i = 0; i < LEN; ++i) {
			minIndex = i;
			min = arr[minIndex];
			for (int j = i + 1; j < LEN; ++j) {
				
				if (min > arr[j]) {
					minIndex = j;
					min = arr[j];
				}
			}
			
			temp = arr[i];
			arr[i] = min;
			arr[minIndex] = temp;
		}
		
	}
	
}
