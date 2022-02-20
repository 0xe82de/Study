# 자바 컬렉션

컬렉션 프레임워크는 '`데이터 군`을 저장하는 클래스들을 표준화한 설계'를 의미한다.

- 컬렉션 : 다수의 데이터
- 프레임워크 : 표준화된 프로그래밍 방식

> 컬렉션 프레임워크의 핵심 인터페이스 간의 상속 계층도

```text
- Collection - List
             - Set

- Map
```

| 인터페이스 | 특징                                                                                                                                                                                 |
| ---------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| List       | - 순서가 있는 데이터의 집합이며 데이터의 중복을 허용한다. <br> - 구현 클래스: ArrayList, LinkedList, Stack, Vector 등                                                                |
| Set        | - 순서를 유지하지 않는 데이터의 집합이며 데이터의 중복을 허용하지 않는다. <br> - 구현 클래스: HashSet, TreeSet 등                                                                    |
| Map        | - 키와 값의 쌍으로 이루어진 데이터의 집합이며 순서는 유지되지 않는다. 키는 중복을 허용하지 않고 값은 중복을 허용한다. <br> - 구현 클래스: HashMap, TreeMap, Hashtable, properties 등 |

## ArrayList

| 메서드                                  | 설명                                                          |
| --------------------------------------- | ------------------------------------------------------------- |
| ArrayList()                             | 크기가 10인 ArrayList를 생성한다.                             |
| ArrayList(int initialCapacity)          | 지정된 초기용량을 갖는 ArrayList를 생성한다.                  |
| boolean addAll(Collection c)            | 주어진 컬렉션의 모든 객체를 저장한다.                         |
| boolean addAll(int index, Collection c) | 지정된 위치부터 주어진 컬렉션의 모든 객체를 저장한다.         |
| void ensureCapacity(int minCapacity)    | ArrayList의 용량이 최소한 minCapacity가 되도록 한다.          |
| Iterator iterator()                     | ArrayList의 iterator 객체를 반환한다.                         |
| ListIterator listIterator()             | ArrayList의 ListIterator를 반환한다.                          |
| ListIterator listIterator(int index)    | ArrayList의 지정된 위치부터 시작하는 ListIterator를 반환한다. |
| boolean retainAll(Collection c)         | 주어진 컬렉션과 공통된 객체만을 남기고 나머지는 삭제한다.     |
| void sort(Comparator c)                 | 지정된 정렬 기준으로 ArrayList를 정렬한다.                    |
| void trimToSize()                       | 용량을 크기에 맞게 줄인다. (빈 공간을 없앤다.)                |

## LinkedList

> LinkedList는 Queue 인터페이스(JDK 1.5)와 Deque 인터페이스(JDK 1.6)를 구현하도록 변경되었다.

| 메서드                                  | 설명                                                                                                                     |
| --------------------------------------- | ------------------------------------------------------------------------------------------------------------------------ |
| LinkedList()                            | LinkedList 객체를 생성한다.                                                                                              |
| LinkedList(Collection c)                | 주어진 컬렉션을 포함하는 LinkedList 객체를 생성한다.                                                                     |
| boolean addAll(Collection c)            | 주어진 컬렉션에 포함된 모든 요소를 LinkedList의 끝에 추가한다. 성공하면 true를, 실패하면 false를 반환한다.               |
| boolean addAll(int index, Collection c) | 지정된 위치에 주어진 컬렉션에 포함된 모든 요소를 LinkedList의 끝에 추가한다. 성공하면 true를, 실패하면 false를 반환한다. |
| Iterator iterator()                     | Iterator를 반환한다.                                                                                                     |
| ListIterator listIterator()             | ListIterator를 반환한다.                                                                                                 |
| ListIterator listIterator(int index)    | 지정된 위치에서부터 시작하는 ListIterator를 반환한다.                                                                    |
| boolean removeAll(Collection c)         | 지정된 컬렉션의 요소와 일치하는 요소를 모두 삭제한다.                                                                    |
| boolean retainAll(Collection c)         | 지정된 컬렉션의 모든 요소가 포함되어 있는지 확인한다.                                                                    |
| Object element()                        | LinkedList의 첫 번째 요소를 반환한다.                                                                                    |
| boolean offer(Object o)                 | 지정된 객체를 LinkedList의 끝에 추가한다. 성공하면 true를, 실패하면 false를 반환한다.                                    |
| Object peek()                           | LinkedList의 첫 번째 요소를 반환한다.                                                                                    |
| Object poll()                           | LinkedList의 첫 번째 요소를 반환하고 LinkedList에서는 삭제된다.                                                          |
| Object remove()                         | LinkedList의 첫 번째 요소를 삭제한다.                                                                                    |
| void addFirst(Object o)                 | LinkedList의 맨 앞에 객체를 추가한다.                                                                                    |
| Iterator descendingIterator()           | 역순으로 조회하기 위한 DescendingIterator를 반환한다.                                                                    |
| Object getFirst()                       | LinkedList의 첫 번째 요소를 반환한다.                                                                                    |
| boolean offerFirst(Object o)            | LinkedList의 맨 앞에 객체를 추가한다. 성공하면 true를, 실패하면 false를 반환한다.                                        |

### ArrayList vs LinkedList 성능 비교

- 데이터의 추가/삭제
  - 순차적으로 데이터를 추가/삭제하는 경우 `ArrayList`가 더 빠르다.
  - 중간에 데이터를 추가/삭제하는 경우 `LinkedList`가 더 빠르다.
  - 데이터의 양이 적을 경우 두 컬렉션의 성능이 비슷할 수 있다.
- 접근
  - 특정 인덱스에 접근할 때는 `ArrayList`가 더 빠르다.

## Stack, Queue

- `스택`은 순차적으로 데이터를 추가하고 삭제하므로 `ArrayList`와 같은 배열 기반의 컬렉션 클래스로 구현하는 것이 적합하다.
- `큐`는 데이터를 꺼낼 때 항상 첫 번째 데이터를 삭제하므로, `LinkedList`로 구현하는 것이 적합하다.

### 스택의 메서드

| 메서드               | 설명                                                                                       |
| -------------------- | ------------------------------------------------------------------------------------------ |
| int search(Object o) | 스택에서 주어진 객체를 찾아서 그 위치를 반환한다. 없으면 -1을 반환한다. 시작 위치는 1이다. |

### 큐의 메서드

| 메서드           | 설명                                                                                            |
| ---------------- | ----------------------------------------------------------------------------------------------- |
| Object element() | 삭제없이 요소를 읽어온다. peek와 달리 Queue가 비었을 때 NoSuchElementException 예외가 발생한다. |

## Iterator, ListIterator, Enumeration

`Iterator`, `ListIterator`, `Enumeration`은 모두 컬렉션에 저장된 요소에 접근하는데 사용되는 인터페이스다. `Enumeration`은 `Iterator`의 구버전이며, `ListIterator`는 `Iterator`의 기능을 향상시킨 것이다.

### Iterator 인터페이스의 메서드

| 메서드            | 설명                                                                               |
| ----------------- | ---------------------------------------------------------------------------------- |
| boolean hasNext() | 읽어 올 요소가 남아있는지 확인한다. 있으면 true를, 없으면 false를 반환한다.        |
| Object next()     | 다음 요소를 읽어 온다.                                                             |
| void remove()     | next()로 읽어 온 요소를 삭제한다. next()를 호출한 다음에 remove()를 호출해야 한다. |

> 컬렉션 Iterator 예제

```java
Collection c = new ArrayList(); // 다른 컬렉션으로 변경 시 이 부분만 고치면 된다.
Iterator it = c.iterator();

while (it.hasNext()) {
    System.out.println(it.next());
}
```

> 맵 Iterator 예제

```java
Map map = new HashMap();
Iterator it = map.entrySet().iterator();
```

### ListIterator, Enumeration

`ListIterator`는 `Iterator`를 상속받아서 기능을 추가한 것으로, 양방향으로의 이동이 가능하다. 다만, `ArrayList`나 `LinkedList`와 같이 `List` 인터페이스를 구현한 컬렉션에서만 사용할 수 있다.

### ListIterator의 메서드

| 메서드                | 설명                                                                                                                                                     |
| --------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------- |
| boolean hasPrevious() | 읽어 올 이전 요소가 남아있는지 확인한다. 있으면 true를, 없으면 false를 반환한다.                                                                         |
| Object previous()     | 이전 요소를 읽어 온다.                                                                                                                                   |
| void remove()         | next() 또는 previous()로 읽어 온 요소를 삭제한다. 반드시 next()나 previous()를 먼저 호출한 다음에 이 메서드를 호출해야 한다. (선택적 기능)               |
| void set(Object o)    | next() 또는 previous()로 읽어 온 요소를 지정된 객체로 변경한다. 반드시 next()나 previous()를 먼저 호출한 다음에 이 메서드를 호출해야 한다. (선택적 기능) |

위 표에서 `선택적 기능`이라고 표시된 것들은 반드시 구현하지 않아도 된다. 하지만 인터페이스로부터 상속받은 메서드는 추상메서드라 메서드의 몸통(body)를 반드시 만들어 주어야 하므로 다음과 같이 처리한다.

```java
public void remove() {
    throw new UnsupportedOperationException();
}
```

단순히 `public void remove() {};`와 같이 구현하는 것보다 이처럼 예외를 던져서 구현되지 않은 기능이라는 것을 메서드를 호출하는 쪽에 알리는 것이 좋다. 그렇지 않으면 호출하는 쪽에서 소스를 구해보기 전까지는 이 기능이 바르게 동작하지 않는 이유를 알 방법이 없다.

## Arrays

### 배열의 복사 - copyOf(), copyOfRange()

```java
int[] arr = {0, 1, 2, 3, 4};
int[] arr2 = Arrays.copyOf(arr, arr.length); // arr2 = {0, 1, 2, 3, 4};
int[] arr3 = Arrays.copyOf(arr, 3); // arr3 = {0, 1, 2};
int[] arr4 = Arrays.copyOf(arr, 7); // arr4 = {0, 1, 2, 3, 4, 0, 0};
int[] arr5 = Arrays.copyOfRange(arr, 2, 4); // arr5 = {2, 3};
int[] arr6 = Arrays.copyOfRange(arr, 0, 7); // arr6 = {0, 1, 2, 3, 4, 0, 0};
```

### 배열 채우기 - fill(), setAll()

```java
int[] arr = new int[5];
Arrays.fill(arr, 9); // arr = {9, 9, 9, 9, 9};
Arrays.setAll(arr, () -> (int) (Math.random() * 5) + 1); // arr = {1, 2, 1, 5, 1}; 랜덤
```

### 배열의 정렬과 검색 - sort(), binarySearch()

```java
int[] arr = {3, 2, 0, 1, 4};
int idx = Arrays.binarySearch(arr, 2); // idx = -5 -> 잘못된 결과

Arrays.sort(arr); // 정렬, {0, 1, 2, 3, 4};
int idx = Arrays.binarySearch(arr, 2); // idx = 2 -> 올바른 결과
```

배열의 첫 번째 요소부터 순서대로 하나씩 검색하는 것을 `순차 검색(linear search)`라고 하는데, 이 방법은 배열이 정렬되어 있을 필요는 없지만 배열의 요소를 하나씩 비교하므로 시간이 많이 소요된다.

반면에 `이진 검색(binary search)`는 배열의 검색할 범위를 반복적으로 절반으로 줄여가면서 검색하므로 검색 속도가 빠르다. 단, 배열이 정렬되어 있어야 한다.

### 배열의 비교와 출력 - equals(), toString()

`toString()` 배열의 모든 요소를 문자열로 출력할 수 있다. 다차원 배열에는 `deepToString()` 메서드를 사용해야 한다. `deepToString()`은 배열의 모든 요소를 재귀적으로 접근해서 문자열을 구성하므로 2차원 뿐만 아니라 3차원 이상의 배열에도 동작한다.

```java
int[] arr = {0, 1, 2, 3, 4};
int[][] arr2D = {{11, 12,}, {21, 22}};

Arrays.toString(arr); // [0, 1, 2, 3, 4];
Arrays.deepToString(arr2D); // [[11, 12], [21, 22]]
```

`equals()`는 두 배열에 저장된 모든 요소를 비교해서 같으면 true를, 다르면 false를 반환한다. 2차원 이상의 배열에는 `deepEquals()`를 사용해야 한다.

```java
String[][] str2D = new String[][] {{"aaa", "bbb"}, {"AAA", "BBB"}};
String[][] str2D2 = new String[][] {{"aaa", "bbb"}, {"AAA", "BBB"}};

Arrays.equals(str2D, str2D2); // false
Arrays.deepEquals(str2D, str2D2); // true
```

### 배열을 List로 변환 - asList(Object.. a)

`asList()`는 배열을 `List`에 담아서 반환한다. 매개변수의 타입이 가변인수이므로 배열 생성 없이 저장할 요소들만 나열하는 것도 가능하다.

```java
List list = Arrays.asList(new Integer[] {1, 2, 3, 4, 5}); // list = [1, 2, 3, 4, 5];
List list = Arrays.asList(1, 2, 3, 4, 5); // list = [1, 2, 3, 4, 5];
list.add(6); // UnsupportedOperationException 예외 발생
```

한 가지 주의할 점은 `asList()`가 반환한 `List`의 크기를 변경할 수 없다는 것이다. 추가 또는 삭제가 불가능하며 저장된 내용은 변경할 수 있다. 만약 크기를 변경할 수 있는 `List`가 필요하다면 다음과 같이 하면 된다.

```java
List list = new ArrayList(Arrays.asList(1, 2, 3, 4, 5));
```

### parallelXXX(), spliterator(), stream()

이 외에도 `parallel`로 시작하는 이름의 메서드가 있는데, 이 메서드들은 빠른 결과를 얻기 위해 여러 쓰레드가 작업을 나누어 처리한다. `spliterator()`는 여러 쓰레드가 처리할 수 있게 하나의 작업을 여러 작업으로 나누는 `Spliterator`를 반환하며 `stream()`은 컬렉션을 스트림으로 변환한다.

## Comparator, Comparable

```java
public interface Comparator {
    int compare(Object o1, Object o2);
    boolean equals(Object obj);
}

public interface Comparable {
    public int compareTo(Object o);
}
```

`compare()`와 `compareTo()`는 두 객체를 비교한다는 같은 기능을 목적으로 고안된 것이다. `compareTo()`의 반환값은 `int`이지만 실제로는 비교하는 두 객체가 같으면 `0`, 비교하는 값보다 작으면 음수, 크면 양수를 반환하도록 구현해야 한다. `compare()`도 마찬가지이다.

```java
static void sort(Object[] a); // 객체 배열에 저장된 객체가 구현한 Comparable에 의한 정렬
static void sort(Object[] a, Comparator c); // 지정한 Comparator에 의한 정렬
```

## HashSet

`ArrayList`와 같이 `List` 인터페이스를 구현한 컬렉션과 달리 `HashSet`은 저장순서를 유지하지 않는다. 저장순서를 유지하고 싶다면 `LinkedHashSet`을 사용해야 한다.

> 참고 : HashSet은 내부적으로 HashMap을 이용해서 만들어졌다. HashSet이란 이름은 해싱(hashing)을 이용해서 구현했기 때문에 붙여진 것이다.

### HashSet의 메서드

| 생성자 또는 메서드                             | 설명                                                                            |
| ---------------------------------------------- | ------------------------------------------------------------------------------- |
| HashSet()                                      | HashSet 객체를 생성한다.                                                        |
| HashSet(Collection c)                          | 주어진 컬렉션을 포함하는 HashSet 객체를 생성한다.                               |
| HashSet(int initialCapacity)                   | 주어진 값을 초기용량으로 하는 HashSet 객체를 생성한다.                          |
| HashSet(int initialCapacity, float loadFactor) | 초기용량과 load factor를 지정하는 생성자                                        |
| Iterator iterator()                            | Iterator를 반환한다.                                                            |
| boolean remove(Object o)                       | 지정된 객체를 HashSet에서 삭제한다. 성공하면 true를, 실패하면 false를 반환한다. |
| boolean removeAll(Collection c)                | 주어진 컬렉션에 저장된 모든 객체와 동일한 객체를 HashSet에서 삭제한다.          |
| boolean retainAll(Collection c)                | 주어진 컬렉션에 저장된 객체와 동일한 객체만 남기고 나머지는 삭제한다.           |

> 참고 : load factor는 컬렉션 클래스에 저장공간이 가득 차기 전에 미리 용량을 확보하기 위한 것으로 이 값을 0.8로 지정하면, 저장공간의 80%가 채워졌을 때 용량이 두 배로 늘어난다. 기본값은 0.75, 즉 75%이다.

`HashSet`에 객체를 저장할 경우 `equals()`와 `hashCode()`를 재정의해야 한다. `add()` 메서드는 새로운 요소를 추가하기 전에 기전에 저장된 요소와 같은 것인지 판별하기 위해 추가하려는 요소의 `equals()`와 `hasCode()`를 호출하기 때문이다.

```java
public class HashSetEx4 {
    public static void main(String[] args) {
        HashSet set = new HashSet();

        set.add(new String("abc"));
        set.add(new String("abc"));
        set.add(new Person2("David",10));
        set.add(new Person2("David",10));

        System.out.println(set); // [abc, David:10]
    }
}

class Person2 {
    String name;
    int age;

    Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Person2) {
            Person2 tmp = (Person2)obj;
            return name.equals(tmp.name) && age==tmp.age;
        }

        return false;
    }

    public int hashCode() {
        return (name+age).hashCode();
    }

    public String toString() {
        return name +":"+ age;
    }
}
```

`hashCode()` 메서드를 오버라이딩할 때 세 가지 규칙을 지켜야 한다.

1. 실행 중인 애플리케이션 내의 동일한 객체에 대해서 여러 번 `hashCode()`를 호출해도 동일한 `int` 값을 반환해야 한다. 하지만 실행시마다 동일한 `int`값을 반환할 필요는 없다.

```java
Person2 p = new Person2("David", 10);
int hashCode1 = p.hashCode();
int hashCode2 = p.hashCode();
// hashCode1, hashCode2는 같아야 한다. 매번 실행할 때마다 같은 값일 필요는 없다.

p.age = 20;
int hashCode3 = p.hashCode();
// age가 변경되었으므로 hasCode3는 달라도 된다.
```

2. `equals()` 메서드를 이용한 비교에 의해서 `true`를 얻은 두 객체에 대해 각각 `hashCode()` 메서드를 호출해서 얻은 결과는 같아야 한다.

```java
Person2 p1 = new Person2("David", 10);
Person2 p2 = new Person2("David", 10);
boolean b = p1.equals(p2);
int hashCode1 = p1.hashCode();
int hashCode2 = p2.hashCode();
// b가 true일 때 hashCode1, hashCode2는 같아야 한다.
```

3. `equals()` 메서드를 호출했을 때 `false`를 반환하는 두 객체는 `hashCode` 호출에 대해 같은 `int` 값을 반환해도 괜찮지만, `해싱(hashing)`을 사용하는 컬렉션의 성능을 향상시키기 위해 다른 `int` 값을 반환하는 것이 좋다.

서로 다른 객체에 대해서 해시 값이 중복된느 경우가 많아지면 해싱을 사용하는 `Hashtable`, `HashMap`과 같은 컬렉션의 검색속도가 떨어진다.

## TreeSet

`TreeSet`은 `이진 검색 트리(binary search tree)`라는 자료구조의 형태로 데이터를 저장하는 컬렉션이다. 이진 검색 트리는 정렬, 검색, 범위검색에 높은 성능을 보이는 자료구조이며 `TreeSet`은 이진 검색 트리의 성능을 향상시킨 `레드-블랙 트리(Red-Black tree)`로 구현되어 있다.

`Set` 인터페이스를 구현했으므로 중복된 데이터의 저장을 허용하지 않으며 정렬된 위치에 저장하므로 저장순서를 유지하지도 않는다.

### 이진 검색 트리의 특징

- 모든 노드는 최대 두 개의 자식노드를 가질 수 있다.
- 왼쪽 자식노드의 값은 부모노드의 값보다 작고 오른쪽 자식노드의 값을 부모노드의 값보다 커야 한다.
- 노드의 추가/삭제에 `LinkedList`보다 시간이 더 걸린다(순차적으로 저장하지 않기 때문이다.)
- 검색(범위검색)고 정렬에 유리하다.
- 중복된 값을 저장하지 못한다.

### TreeSet의 생성자와 메서드

| 생성자 또는 메서드                                                                             | 설명                                                                                                                        |
| ---------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| TreeSet()                                                                                      | 기본 생성자                                                                                                                 |
| TreeSet(Collection c)                                                                          | 주어진 컬렉션을 저장하는 TreeSet을 생성한다.                                                                                |
| TreeSet(Comparator comp)                                                                       | 주어진 정렬조건으로 정렬하는 TreeSet을 생성한다.                                                                            |
| TreeSet(SortedSet s)                                                                           | 주어진 SortedSet을 구현한 컬렉션을 저장하는 TreeSet을 생성한다.                                                             |
| Object ceiling(Object o)                                                                       | 지정된 객체와 같은 객체를 반환한다. 없으면 큰 값을 가진 객체 중 가장 가까운 값의 객체를 반환하고, 없으면 null을 반환한다.   |
| Comparator comparator()                                                                        | TreeSet의 정렬기준(Comparator)를 반환한다.                                                                                  |
| NavigableSet descendingSet()                                                                   | TreeSet에 저장된 요소들을 역순으로 정렬해서 반환한다.                                                                       |
| Object first()                                                                                 | 정렬된 순서에서 첫 번째 객체를 반환한다.                                                                                    |
| Object last()                                                                                  | 정렬된 순서에서 마지막 객체를 반환한다.                                                                                     |
| Object lower(Object o)                                                                         | 지정된 객체보다 작은 값을 가진 객체 중 가장 가까운 값의 객체를 반환하고, 없으면 null을 반환한다.                            |
| Object higher(Object o)                                                                        | 지정된 객체보다 큰 값을 가진 객체 중 가장 가까운 값의 객체를 반환하고, 없으면 null을 반환한다.                              |
| Object floor(Object o)                                                                         | 지정된 객체와 같은 객체를 반환한다. 없으면 작은 값을 가진 객체 중 가장 가까운 값의 객체를 반환하고, 없으면 null을 반환한다. |
| SortedSet headSet(Object toElement)                                                            | 지정된 객체보다 작은 값의 객체들을 반환한다.                                                                                |
| NavigableSet headSet(Object toElement, boolean inclusive)                                      | 지정된 객체보다 작은 값의 객체들을 반환한다. inclusive가 true이면 같은 값의 객체도 포함한다.                                |
| Iterator iterator()                                                                            | TreeSet의 Iterator를 반환한다.                                                                                              |
| boolean retainAll(Collection c)                                                                | 주어진 컬렉션과 공통된 요소만을 남기고 삭제한다.(교집합)                                                                    |
| Spliterator spliterator()                                                                      | TreeSet의 spliterator를 반환한다.                                                                                           |
| SortedSet subSet(Object fromElement, Object toElement)                                         | 범위 검색(fromElement와 toElement 사이)의 결과를 반환한다. (끝 범위인 toElement는 범위에 포함되지 않음)                     |
| NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) | 범위 검색의 결과를 반환한다. (fromInclusive가 true면 시작값이 포함되고, toInclusive가 true면 끝값이 포함된다.)              |
| SortedSet tailSet(Object fromElement)                                                          | 지정된 객체보다 큰 값의 객체들을 반환한다.                                                                                  |

## HashMap, Hashtable

### HashMap의 생성자와 메서드

| 생성자 또는 메서드                                            | 설명                                                                         |
| ------------------------------------------------------------- | ---------------------------------------------------------------------------- |
| HashMap()                                                     | 기본 생성자                                                                  |
| HashMap(int initialCapacity)                                  | 지정된 값을 초기용량으로 하는 HashMap 객체를 생성한다.                       |
| HashMap(int initialCapacity, float loadFactor)                | 지정된 초기용량과 load factor의 HashMap 객체를 생성한다.                     |
| HashMap(Map m)                                                | 지정된 Map의 모든 요소를 포함하는 HashMap을 생성한다.                        |
| boolean containsKey(Object key)                               | HashMap에 지정된 키가 포함되어 있는지 알려준다.                              |
| boolean containsValue(Object value)                           | HashMap에 지정된 값이 포함되어 있는지 알려준다.                              |
| Set entrySet()                                                | HashMap에 저장된 키와 값을 엔트리의 형태로 Set에 저장해서 반환한다.          |
| Object getOrDefault(Object key, Object defaultValue)          | 짖어된 키의 값을 반환한다. 키를 못찾으면, 기본값으로 지정된 객체를 반환한다. |
| Set keySet()                                                  | HashMap에 저장된 모든 키가 저장된 Set을 반환한다.                            |
| void putAll(Map m)                                            | Map에 저장된 모든 요소를 HashMap에 저장한다.                                 |
| Object replace(Object key, Object value)                      | 지정된 키와 값을 지정된 객체로 대체한다.                                     |
| boolean replace(Object key, Object oldValue, Object newValue) | 지정된 키와 값이 모두 일치하는 경우에만 새로운 값으로 대체한다.              |
| Collection values()                                           | HashMap에 저장된 모든 값을 컬렉션의 형태로 반환한다.                         |
