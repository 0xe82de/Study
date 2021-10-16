# Sort

## Bubble Sort

```Java
// Java
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
```

## Insertion Sort

```Java
// Java
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
```

## Selection Sort

```Java
// Java
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
```
