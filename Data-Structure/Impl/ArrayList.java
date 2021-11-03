package com.ad.datastructure.list;

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

    // 최소(기본) 용적 크기
    private static final int DEFAULT_CAPACITY = 10;
    // 빈 배열
    private static final Object[] EMPTY_ARRAY = {};

    // 요소 개수
    private int size;

    // 요소를 담을 배열
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

    private void resize() {
        int array_capacity = array.length;

        // 배열의 용적이 0이면
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

        // 용적의 절반 미만으로 요소가 차지하고 있을 때
        if (size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            // 복사
            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
        }
    }
    
    public boolean add(E value) {
        addLast(value);
        return true;
    }
    
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
            
            // index 기준 후자에 있는 모든 요소들 한 칸씩 뒤로 밀기
            for (int i = size; i > index; --i) {
                array[i] = array[i - 1];
            }
            
            // index 위치에 요소 할당
            array[index] = value;
            ++size;
        }
    }

    public void addFirst(E value) {
        add(0, value);
    }
    
    public void addLast(E value) {
        // 배열에 공간이 없으면
        if (size == array.length) {
            resize();
        }
        // 마지막 위치에 요소 추가
        array[size] = value;
        // 사이즈 1 증가
        ++size;
    }
    
    @SuppressWarnings("unchecked")
    public E get(int index) {
        // 범위를 벗어나면 예외 발생
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // Object 타입에서 E 타입으로 형변환 후 반환
        return (E) array[index];
    }

    public void set(int index, E value) {
        // 범위를 벗어나면 예외 발생
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = value;
    }

    public int indexOf(Object value) {
        // value와 같은 객체(요소 값)일 경우 i(위치) 반환
        for (int i = 0; i < size; ++i) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        // 일치하는 것이 없으면 -1을 반환
        return -1;
    }

    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; --i) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object value) {
        // 0 이상이면 요소가 존재한다.
        if (indexOf(value) >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 삭제할 요소를 반환하기 위해 임시 변수에 초기화
        E element = (E) array[index];
        array[index] = null;

        // 삭제한 요소의 뒤에 있는 모든 요소들을 한 칸씩 당긴다.
        for (int i = index; i < size; ++i) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }
        --size;
        resize();
        return element;
    }

    public boolean remove(Object value) {
        // 삭제하고자 하는 요소의 인덱스 탐색
        int index = indexOf(value);

        // -1이라면 array에 요소가 없다는 의미이므로 false 반환
        if (index == -1) {
            return false;
        }

        // index 위치에 있는 요소를 삭제
        remove(index);
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        // 모든 공간을 null 처리한다.
        for (int i = 0; i < size; ++i) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    public Object clone() throws CloneNotSupportedException {
        // 새로운 객체 생성
        ArrayList<?> cloneList = (ArrayList<?>)super.clone();

        // 새로운 객체의 배열도 생성
        cloneList.array = new Object[size];

        // 배열의 값을 복사
        System.arraycopy(array, 0, cloneList.array, 0, size);

        return cloneList;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }
        // 원본 배열, 원본배열 시작위치, 복사할 배열, 복사할 배열 시작위치, 복사할 요소 수
        System.arraycopy(array, 0, a, 0, size);
        return a;
    }
}
