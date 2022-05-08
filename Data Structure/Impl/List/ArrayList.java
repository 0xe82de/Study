import java.util.Arrays;

public class ArrayList<E> {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        // add
        System.out.println("## add ##");
        list.add("apple");
        list.add("bear");

        // get
        System.out.println("## get ##");
        for (int i = 0, listSize = list.size(); i < listSize; ++i) {
            System.out.println(list.get(i));
        }
        System.out.println();

        // add index
        System.out.println("## add index ##");
        list.add(1, "circle");
        for (int i = 0, listSize = list.size(); i < listSize; ++i) {
            System.out.println(list.get(i));
        }
        System.out.println();

        // remove index
        System.out.println("## remove index ##");
        list.remove(1);
        for (int i = 0, listSize = list.size(); i < listSize; ++i) {
            System.out.println(list.get(i));
        }
        System.out.println();

        // remove value
        System.out.println("## remove value ##");
        list.add("circle");
        list.add("circle");
        list.remove("circle");
        for (int i = 0, listSize = list.size(); i < listSize; ++i) {
            System.out.println(list.get(i));
        }
        System.out.println();

        // indexOf
        System.out.println("## indexOf ##");
        System.out.println(list.indexOf("bear"));
        System.out.println();

        // clear
        System.out.println("## clear & isEmpty ##");
        list.clear();
        System.out.println(list.isEmpty());
    }

    // 최소(기본) 용량 크기
    private static final int DEFAULT_CAPACITY = 10;
    // 빈 배열
    private static final Object[] EMPTY_ARRAY = {};

    // 데이터 개수
    private int size;

    // 데이터를 담을 배열
    Object[] array;

    // 생성자 1 (초기 공간 할당 x)
    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 생성자 2 (초기 공간 할당 O)
    public ArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    /**
     * 리스트의 사이즈를 늘리거나 줄인다.
     */
    private void resize() {
        int array_capacity = array.length;

        // 배열의 용량이 0이면
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 배열에 공간이 없으면
        if (size == array_capacity) {
            int new_capacity = array_capacity * 2;

            // 복사
            array = Arrays.copyOf(array, new_capacity);
            return;
        }

        // 용량의 절반 미만으로 데이터가 차지하고 있을 때
        if (size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            // 복사
            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
        }
    }

    /**
     * 마지막 위치에 데이터를 추가한다.
     *
     * @param value : 데이터
     * @return 성공하면 {@Code true}를 반환한다.
     */
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    /**
     * 특정 위치에 데이터를 추가한다.
     * 
     * @param index : 위치
     * @param value : 데이터
     */
    public void add(int index, E value) {
        // 영역을 벗어나면 예외 발생
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // index가 마지막 위치라면
        if (index == size) {
            addLast(value);
        }
        else {
            // 배열에 공간이 없으면
            if (size == array.length) {
                resize();
            }
            
            // index 기준 후자에 있는 모든 데이터들 한 칸씩 뒤로 밀기
            for (int i = size; i > index; --i) {
                array[i] = array[i - 1];
            }
            
            // index 위치에 데이터 할당
            array[index] = value;
            ++size;
        }
    }

    /**
     * 가장 앞에 데이터를 추가한다.
     * 
     * @param value : 데이터
     */
    public void addFirst(E value) {
        add(0, value);
    }

    /**
     * 가장 뒤에 데이터를 추가한다.
     * 
     * @param value : 데이터
     */
    public void addLast(E value) {
        // 배열에 공간이 없으면
        if (size == array.length) {
            resize();
        }
        // 마지막 위치에 데이터 추가
        array[size] = value;
        // 사이즈 1 증가
        ++size;
    }

    /**
     * 특정 위치의 데이터를 반환한다.
     * 
     * @param index : 위치
     * @return 데이터
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        // 범위를 벗어나면 예외 발생
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // Object 타입에서 E 타입으로 형변환 후 반환
        return (E) array[index];
    }

    /**
     * 특정 위치에 데이터를 저장한다.
     * 
     * @param index : 위치
     * @param value : 데이터
     */
    public void set(int index, E value) {
        // 범위를 벗어나면 예외 발생
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = value;
    }

    /**
     * 특정 데이터의 첫 번째 위치를 반환한다.
     *
     * @param value : 데이터
     * @return 데이터가 존재하면 위치를 반환하고, 존재하지 않으면 -1을 반환한다.
     */
    public int indexOf(Object value) {
        // value와 같은 객체(데이터 값)일 경우 i(위치) 반환
        for (int i = 0; i < size; ++i) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        // 일치하는 것이 없으면 -1을 반환
        return -1;
    }

    /**
     * 특정 데이터의 마지막 위치를 반환한다.
     *
     * @param value : 데이터
     * @return 데이터가 존재하면 위치를 반환하고, 존재하지 않으면 -1을 반환한다.
     */
    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; --i) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 특정 데이터의 존재 여부를 반환한다.
     *
     * @param value : 데이터
     * @return 존재하면 {@Code true}를, 존재하지 않으면 {@Code false}를 반환한다.
     */
    public boolean contains(Object value) {
        // 0 이상이면 데이터가 존재한다.
        if (indexOf(value) >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 특정 위치의 데이터를 삭제한다.
     * 
     * @param index : 위치
     * @return 삭제된 데이터
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 삭제할 데이터를 반환하기 위해 임시 변수에 초기화
        E element = (E) array[index];
        array[index] = null;

        // 삭제한 데이터의 뒤에 있는 모든 데이터들을 한 칸씩 당긴다.
        for (int i = index; i < size; ++i) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }
        --size;
        resize();
        return element;
    }

    /**
     * 특정 데이터를 삭제한다. 여러 개라면 첫 번째 위치의 데이터를 삭제한다.
     * 
     * @param value : 데이터
     * @return 삭제하면 {@Code true}를 반환한다.
     */
    public boolean remove(Object value) {
        // 삭제하고자 하는 데이터의 인덱스 탐색
        int index = indexOf(value);

        // -1이라면 array에 데이터가 없다는 의미이므로 false 반환
        if (index == -1) {
            return false;
        }

        // index 위치에 있는 데이터를 삭제
        remove(index);
        return true;
    }

    /**
     * 데이터 개수를 반환한다.
     * 
     * @return 데이터 개수
     */
    public int size() {
        return size;
    }

    /**
     * 데이터 개수가 0인지 확인하여 결과를 반환한다.
     *
     * @return 데이터가 없으면 {@Code true}를, 있으면 {@Code false}를 반환한다.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 배열의 모든 데이터를 삭제한다.
     */
    public void clear() {
        // 모든 공간을 null 처리한다.
        for (int i = 0; i < size; ++i) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    /**
     * 배열을 복사(깊은 복사)하여 반환한다.
     *
     * @return 복사된 배열
     * @throws CloneNotSupportedException : clone() 메서드가 던지는 예외
     */
    public Object clone() throws CloneNotSupportedException {
        // 새로운 객체 생성
        ArrayList<?> cloneList = (ArrayList<?>)super.clone();

        // 새로운 객체의 배열도 생성
        cloneList.array = new Object[size];

        // 배열의 값을 복사
        System.arraycopy(array, 0, cloneList.array, 0, size);

        return cloneList;
    }

    /**
     * ArrayList를 객체 배열로 반환한다.
     *
     * @return 배열
     */
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    /**
     * 다른 배열에 데이터를 복사한다.
     *
     * @param a : 복사받을 배열
     * @param <T> : 상위 타입으로 데이터를 받기 위해 사용한다.
     * @return 복사된 배열
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }
        // 원본 배열, 원본배열 시작위치, 복사할 배열, 복사할 배열 시작위치, 복사할 데이터 수
        System.arraycopy(array, 0, a, 0, size);
        return a;
    }
}
