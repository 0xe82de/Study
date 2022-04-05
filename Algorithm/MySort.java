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
        System.out.println();

        arr = createArr();
        System.out.println("[퀵 정렬] 정렬 전 => " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("[퀵 정렬] 정렬 후 => " + Arrays.toString(arr));
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

    static void quickSort(int[] arr, int src, int dst) {
        if (src >= dst) {
            return;
        }

        int pivot = src;
        int left = pivot + 1;
        int right = dst;

        while (left <= right) {
            /**
             * 피벗보다 큰 값이 나올때까지 left 증가
             */
            while (left <= right && arr[pivot] >= arr[left]) {
                ++left;
            }

            /**
             * 피벗보다 작은 값이 나올 때까지 right 감소
             */
            while (right >= left && arr[pivot] <= arr[right]) {
                --right;
            }

            int temp = 0;
            if (left > right) {
                /**
                 * 엇갈리면 피벗과 작은 데이터가 된 right를 교환
                 */
                temp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = temp;
            } else {
                /**
                 * left right 교환
                 */
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        quickSort(arr, src, right - 1);
        quickSort(arr, right + 1, dst);
    }
}