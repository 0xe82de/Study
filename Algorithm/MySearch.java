public class MySearch {

    public static void main(String[] args) {
        int[] arr = null;

        arr = createArr();
        int index = binarySearch(arr, 5, 0, arr.length - 1);
        System.out.println(index == -1 ? "데이터가 없습니다." : "[" + index + "] 위치에 데이터가 존재합니다.");
        System.out.println();
    }

    static int[] createArr() {
        return new int[] {5, 6, 3, 9, 7, 1, 8, 0, 2, 4};
    }

    static int binarySearch(int[] arr, int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (target == arr[mid]) {
            return mid;
        } else if (target < arr[mid]) {
            return binarySearch(arr, target, start, mid - 1);
        } else {
            return binarySearch(arr, target, mid + 1, end);
        }
    }
}