import java.io.*;
import java.util.Arrays;

/**
 * 삽입 정렬
 */

public class InsertionSort {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = {5, 4, 3, 2, 1};

        bw.write("정렬 전 : " + Arrays.toString(arr) + "\n");
        insertionSort(arr);
        bw.write("정렬 후 : " + Arrays.toString(arr));

        bw.close();
    }

    /**
     * 삽입 정렬
     * @param arr : 정렬할 배열
     */
    private static void insertionSort(int[] arr) {
        final int SIZE = arr.length;
        int temp, j;
        for (int i = 1; i < SIZE; ++i) {
            temp = arr[i];
            j = i - 1;
            while (j >= 0 && temp < arr[j]) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = temp;
        }
    }
}