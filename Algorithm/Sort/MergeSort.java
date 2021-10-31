import java.io.*;
import java.util.Arrays;

/**
 * 병합 정렬
 */

public class MergeSort {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = { 5, -5, 4, -4, 3, -3, 2, -2, 1, -1 };
        final int N = arr.length;
        int[] sorted = new int[N];

        bw.write("정렬 전 : " + Arrays.toString(arr) + "\n");
        mergeSort(arr, 0, N - 1, sorted);
        bw.write("정렬 후 : " + Arrays.toString(arr));

        // io close
        bw.close();
    }

    /**
     * 병합 정렬
     * @param arr : 정렬할 배열
     */
    private static void mergeSort(int[] arr, int left, int right, int[] sorted) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        // 왼쪽 부분 배열을 분할한다.
        mergeSort(arr, left, mid, sorted);
        // 오른쪽 부분 배열을 분할한다.
        mergeSort(arr, mid + 1, right, sorted);
        // 부분 배열을 병합한다.
        merge(arr, left, mid, right, sorted);
    }

    /**
     * 병합 메서드
     * @param arr : 부분 배열
     * @param left : 왼쪽 배열의 시작 인덱스
     * @param mid : 오른쪽 배열의 시작 인덱스
     * @param right : 부분 배열의 최대 인덱스
     * @param sorted : 정렬된 배열
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] sorted) {
        int i = left;
        int j = mid + 1;
        int k = left;

        for (; k <= right; ++k) {
            // 오른쪽 부분 배열의 모든 요소가 삽입되면 왼쪽 부분 배열를 sorted에 넣는다.
            if (j > right) sorted[k] = arr[i++];
                // 왼쪽 부분 배열의 모든 요소가 모두 삽입되면 오른쪽 부분 배열를 soted에 넣는다.
            else if (i > mid) sorted[k] = arr[j++];
                // 왼쪽 배열 요소가 오른쪽 배열 요소보다 작으면 sorted에 왼쪽 배열 요소를 넣는다.
            else if (arr[i] <= arr[j]) sorted[k] = arr[i++];
                // 오른쪽 배열 요소가 왼족 배열 요소보다 작으면 sorted에 오른쪽 배열 요소를 넣는다.
            else sorted[k] = arr[j++];
        }

        // 병합
        for (i = left; i <= right; ++i)
            arr[i] = sorted[i];
    }
}
