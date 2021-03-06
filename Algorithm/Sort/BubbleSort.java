import java.io.*;
import java.util.Arrays;

/**
 * 거품 정렬
 */

public class BubbleSort {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = {5, 4, 3, 2, 1};

        bw.write("정렬 전 : " + Arrays.toString(arr) + "\n");
        bubbleSort(arr);
        bw.write("정렬 후 : " + Arrays.toString(arr));

        bw.close();
    }

    /**
     * 거품 정렬
     * @param arr : 정렬할 배열
     */
    private static void bubbleSort(int[] arr) {
        final int SIZE = arr.length - 1;
        int temp;
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE - i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
