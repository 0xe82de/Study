import java.util.Arrays;

public class MySort {

    public static void main(String[] args) {
        int[] arr = null;

        arr = createArr();
        System.out.println("[선택 정렬] 정렬 전 => " + Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("[선택 정렬] 정렬 후 => " + Arrays.toString(arr));
        System.out.println();

        arr = createArr();
        System.out.println("[삽입 정렬] 정렬 전 => " + Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("[삽입 정렬] 정렬 후 => " + Arrays.toString(arr));
    }

    static int[] createArr() {
        return new int[] {5, 6, 3, 9, 7, 1, 8, 0, 2, 4};
    }

    static void selectionSort(int[] arr) {
        final int SIZE = arr.length;

        for (int i = 0; i < SIZE; i++) {
            int minIndex = i;
            for (int j = i + 1; j < SIZE; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    static void insertionSort(int[] arr) {
        final int SIZE = arr.length;

        for (int i = 1; i < SIZE; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
        }
    }
}