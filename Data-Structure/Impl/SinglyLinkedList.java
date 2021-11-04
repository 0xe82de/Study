import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {

    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<String>();
        list.add("3");
        list.add("2");
        list.add("1");
        list.remove("2");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    // head 노드
    private Node<E> head;
    // tail 노드
    private Node<E> tail;
    // 노드의 개수
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 특정 위치의 노드를 반환한다.
     *
     * @param index : 위치
     * @return 노드
     */
    private Node<E> search(int index) {
        // 범위를 벗어나는 위치일 경우 예외를 던진다.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = head;
        for (int i = 0; i < index; ++i) {
            node = node.next;
        }
        return node;
    }

    /**
     *  head 노드 앞에 노드를 추가한다.
     *
     * @param value : 데이터
     */
    public void addFirst(E value) {
        // 새 노드 생성
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;
        head = newNode;
        ++size;

        // 생성된 노드가 새로운 노드 뿐이라면
        if (head.next == null) {
            tail = head;
        }
    }

    /**
     * tail 노드 뒤에 노드를 추가한다.
     *
     * @param value : 데이터
     * @return 노드를 추가하면 {@Code true}를 반환한다.
     */
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    /**
     * tail 노드 뒤에 노드를 추가한다.
     * 
     * @param value : 데이터
     */
    public void addLast(E value) {
        // 첫 노드일 경우 addFirst() 메서드 호출
        if (size == 0) {
            addFirst(value);
            return;
        }

        // 새 노드 생성
        Node<E> newNode = new Node<E>(value);
        tail.next = newNode;
        tail = newNode;
        ++size;
    }

    /**
     * 특정 위치에 노드를 추가한다.
     * 
     * @param index : 위치
     * @param value : 데이터
     */
    public void add(int index, E value) {
        // 범위를 벗어나면 예외 발생
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 위치가 가장 앞이라면 addFirst() 메서드 호출
        if (index == 0) {
            addFirst(value);
            return;
        }

        // 위치가 가장 뒤라면 addLast() 메서드 호출
        if (index == size) {
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prevNode = search(index - 1);

        // 추가하려는 위치의 노드
        Node<E> nextNode = prevNode.next;

        // 추가하려는 노드
        Node<E> newNode = new Node<E>(value);

        /**
         * 이전 노드가 추가하려는 노드를 가리키도록 하고
         * 추가하려는 노드는 다음 노드를 가리키도록 한다.
         */
        prevNode.next = newNode;
        newNode.next = nextNode;
        ++size;
    }

    /**
     * head 노드를 삭제한다.
     * 
     * @return 삭제된 노드의 데이터
     */
    public E remove() {
        // 노드가 없다면
        if (head == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드의 데이터를 반환하기 위해 데이터를 저장한다.
        E element = head.data;

        Node<E> nextNode = head.next;

        // 데이터와 링크를 삭제한다.
        head.data = null;
        head.next = null;

        // 새로운 head 노드로 기존의 head 노드의 다음 노드를 저장한다.
        head = nextNode;
        --size;

        // 남아있는 노드가 없다면면
        if (size == 0) {
            tail = null;
        }

        return element;
    }

    /**
     * 특정 위치의 노드를 삭제한다.
     *
     * @param index : 위치
     * @return 삭제된 노드의 데이터
     */
    public E remove(int index) {
        // 범위를 벗어나면
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 삭제하려는 노드의 위치가 첫 번째라면
        if (index == 0) {
            return remove();
        }

        // 삭제할 노드의 이전 노드
        Node<E> prevNode = search(index - 1);
        // 삭제할 노드
        Node<E> removedNode = prevNode.next;
        // 삭제할 노드의 다음 노드
        Node<E> nextNode = removedNode.next;
        
        // 삭제된 노드의 데이터를 반환하기 위해 데이터를 저장한다.
        E element = removedNode.data;
        
        // 이전 노드가 다음 노드를 가리키도록 한다.
        prevNode.next = nextNode;

        // 데이터와 링크를 삭제한다.
        removedNode.data = null;
        removedNode.next = null;
        --size;
        
        return element;
    }

    /**
     * 특정 데이터를 가지는 노드를 삭제한다.
     *
     * @param value : 데이터
     * @return 삭제하면 {@Code true}를, 특정 데이터를 가지는 노드가 없으면 {@Code false}를 반환한다.
     */
    public boolean remove(Object value) {
        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> node = head;

        // value와 일치하는 노드를 찾는다.
        for (; node != null; node = node.next) {
            if (value.equals(node.data)) {
                hasValue = true;
                break;
            }
            prevNode = node;
        }
        
        // 일치하는 요소가 없으면
        if (node == null) {
            return false;
        }
        
        // 삭제하려는 노드가 head라면 remove() 메서드 호출
        if (node.equals(head)) {
            remove();
            return true;
        }
        else {
            // 이전 노드의 링크에 삭제하려는 노드의 다음 노드를 저장한다.
            prevNode.next = node.next;

            node.data = null;
            node.next = null;
            --size;
            return true;
        }
    }

    /**
     * 특정 위치의 데이터를 반환한다.
     * 
     * @param index : 위치
     * @return 데이터
     */
    public E get(int index) {
        return search(index).data;
    }

    /**
     * 특정 위치의 노드에 데이터를 저장한다.
     *
     * @param index : 위치
     * @param value : 데이터
     */
    public void set(int index, E value) {
        Node<E> node = search(index);
        node.data = value;
    }

    /**
     * 특정 데이터를 가지는 노드의 위치를 반환한다.
     * 같은 데이터를 가지는 노드가 두 개 이상이라면 첫 번째 노드의 위치를 반환한다.
     * 데이터를 가지는 노드가 없으면
     * 
     * @param value : 데이터
     * @return 노드가 존재하면 위치를 반환하고, 없으면 {@Code -1}을 반환한다.
     */
    public int indexOf(Object value) {
        int index = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (value.equals(node.data)) {
                return index;
            }
            ++index;
        }
        // 데이터가 없으면
        return -1;
    }

    /**
     * 특정 데이터를 가지는 노드의 존재 여부를 반환한다.
     * 
     * @param value : 데이터
     * @return 존재하면 {@Code true}를, 존재하지 않으면 {@Code false}를 반환한다.
     */
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
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
     * 데이터 존재 여부를 반환한다.
     *
     * @return 데이터가 존재하지 않으면 {@Code true}를, 존재하면 {@Code false}를 반환한다.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 데이터를 모두 삭제한다.
     */
    public void clear() {
        for (Node<E> node = head; node != null;) {
            Node<E> nextNode = node.next;
            node.data = null;
            node.next = null;
            node = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    /**
     * 배열을 복사(깊은 복사)하여 반환한다.
     *
     * @return 복사된 배열
     * @throws CloneNotSupportedException : clone() 메서드가 던지는 예외
     */
    public Object clone() throws CloneNotSupportedException {
        SinglyLinkedList<? super E> clone = (SinglyLinkedList<? super E>)super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> node = head; node != null; node = node.next) {
            clone.add(node.data);
        }

        return clone;
    }

    /**
     * LinkedList를 객체 배열로 반환한다.
     *
     * @return 배열
     */
    public Object[] toArray() {
        Object[] array = new Object[size];

        int idx = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            array[idx++] = (E)node.data;
        }

        return array;
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
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        int idx = 0;
        Object[] result = a;
        for (Node<E> node = head; node != null; node = node.next) {
            result[idx++] = node.data;
        }

        return a;
    }

    /**
     * Comparator를 전달받지 못하면 해당 객체의 Comparable에 구현된 정렬 방식을 사용한다.
     * 만약 Comparable이 구현되어 있지 않으면 에러가 발생한다.
     * 구현되어 있을 때 {@Code null}을 파라미터로 넘기면
     * Arrays.sort() 메서드가 객체의 compareTo 메서드에 정의된 방식대로 정렬한다.
     */
    public void sort() {
        sort(null);
    }

    /**
     * LinkedList를 Object 배열로 만들고 정렬한다.
     * 정렬된 배열의 데이터를 LinkedList에 저장한다.
     *
     * @param c
     */
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int idx = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            node.data = (E)a[idx++];
        }
    }




}
