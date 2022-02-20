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
  - 순차적으로 데이터를 추가/삭제하는 경우 ArrayList가 더 빠르다.
  - 중간에 데이터를 추가/삭제하는 경우 LinkedList가 더 빠르다.
  - 데이터의 양이 적을 경우 두 컬렉션의 성능이 비슷할 수 있다.
- 접근
  - 특정 인덱스에 접근할 때는 ArrayList가 더 빠르다.

## Stack, Queue

- 스택은 순차적으로 데이터를 추가하고 삭제하므로 ArrayList와 같은 배열 기반의 컬렉션 클래스로 구현하는 것이 적합하다.
- 큐는 데이터를 꺼낼 때 항상 첫 번째 데이터를 삭제하므로, LinkedList로 구현하는 것이 적합하다.

### 스택의 메서드

| 메서드               | 설명                                                                                       |
| -------------------- | ------------------------------------------------------------------------------------------ |
| int search(Object o) | 스택에서 주어진 객체를 찾아서 그 위치를 반환한다. 없으면 -1을 반환한다. 시작 위치는 1이다. |

### 큐의 메서드

| 메서드           | 설명                                                                                            |
| ---------------- | ----------------------------------------------------------------------------------------------- |
| Object element() | 삭제없이 요소를 읽어온다. peek와 달리 Queue가 비었을 때 NoSuchElementException 예외가 발생한다. |

## Iterator, ListIterator, Enumeration

Iterator, ListIterator, Enumeration은 모두 컬렉션에 저장된 요소에 접근하는데 사용되는 인터페이스다. Enumeration은 Iterator의 구버전이며, ListIterator는 Iterator의 기능을 향상시킨 것이다.

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

ListIterator는 Iterator를 상속받아서 기능을 추가한 것으로, 양방향으로의 이동이 가능하다. 다만, ArrayList나 LinkedList와 같이 List 인터페이스를 구현한 컬렉션에서만 사용할 수 있다.

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
