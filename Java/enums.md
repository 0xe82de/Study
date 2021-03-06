# 참고

- 자바의 정석

# Contents

- [열거형이란?](#열거형이란)
- [열거형의 정의와 사용](#열거형의-정의와-사용)
- [열거형에 멤버 추가하기](#열거형에-멤버-추가하기)
- [열거형의 이해](#열거형의-이해)

# 열거형(enums)

## 열거형이란?

열거형은 서로 관련된 상수를 편리하게 선언하기 위한 것이다. `JDK 1.5`부터 새로 추가되었다.

```Java
class Space {
    static final int EMPTY = 0;
    static final int PERSON = 1;
    static final int WALL = 2;

    static final int TWO = 0;
    static final int THREE = 1;
    static final int FOUR = 2;
}

// Enum 사용
class Space {
    enum Kind { EMPTY, PERSON, WALL }
    enum Value { TWO, THREE, FOUR }

    final Kind kind;
    final Value value;
}
```

자바의 열거형은 `타입에 안전한 열거형(typesafe enum)`이라서 실제 값이 같아도 타입이 다르면 컴파일 에러가 발생한다. 그리고 상수의 값이 바뀌면, 해다 상수를 참조하는 모든 소스를 다시 컴파일 해야 했지만, 열거형 상수를 사용하면 컴파일하지 않아도 된다.

## 열거형의 정의와 사용

열거형을 정의하는 방법은 다음과 같다.

```Java
enum 열거형이름 { 상수명1, 상수명2, ... }
```

동서남북을 상수로 정의하는 열거형 `Direction`은 다음과 같다.

```Java
enum Direction { EAST, SOUTH, WEST, NORT }
```

정의된 상수를 사용하는 방법은 `열거형이름.상수명`이다. 클래스의 `static` 변수를 참조하는 것과 동일하다.

```Java
class Unit {
    int x, y; // 유닛의 위치
    Direction dir; // 열거형을 인스턴스 변수로 선언

    void init() {
        dir = Direction.EAST; // 유닛의 방향을 EAST로 초기화
    }
}
```

열거형 상수간의 비교에는 `==`를 사용할 수 있다. `euqlas()`가 아닌 `==`로 비교가 가능하다는 것은 빠른 성능을 제공한다는 것이다. 하지만 `<`, `>`와 같은 비교연산자는 사용할 수 없고 `compareTo()`는 사용할 수 있다. `compareTo()`는 두 비교대상이 같으면 0, 왼쪽이 크면 양수, 오른쪽이 크면 음수를 반환한다.

`switch`문의 조건식에도 열거형을 사용할 수 있는데, 이 때는 열거형의 이름은 적지 않고 상수의 이름만 적어야 한다.

```Java
void move() {
    switch(dir) {
        case EAST:
            ++x;
            break;
        case WEST:
            --x;
            break;
        case SOUTH:
            ++y;
            break;
        case NORTH:
            --y;
            break;
    }
}
```

### 모든 열거형의 조상 - java.lang.Enum

열거형 `Direction`에 정의된 모든 상수를 출력하려면, 다음과 같이 한다.

```Java
Direction[] dArr = Direction.values();

for (Drection d : dArr) { // for (Direction d : Direction.values())
    System.out.println("%s = %d%n", d.name(), d.ordinal());
}
```

`values()`는 열거형의 모든 상수를 배열에 담아 반환한다. `ordinal()`은 모든 열거형의 조상인 `java.lang.Enum` 클래스에 정의된 것으로, 열거형 상수의 정의된 순서(0부터 시작)를 정수로 반환한다.

`Enum` 클래스에는 다음과 같은 메서드가 정의되어 있다.

| 메서드                                    | 설명                                                      |
| ----------------------------------------- | --------------------------------------------------------- |
| Class<E> getDeclaringClass()              | 열거형의 Class 객체를 반환한다.                           |
| String name()                             | 열거형 상수의 이름을 문자열로 반환한다.                   |
| int ordinal()                             | 열거형 상수가 정의된 순서를 반환한다.(0부터 시작)         |
| T valueOf(Class<T> enumType, String name) | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다. |

이외에도 `values()`처럼 컴파일러가 자동으로 추가해주는 메서드가 하나 있다. 이 메서드는 열거형 상수의 이름으로 문자형 상수에 대한 참조를 얻을 수 있게 해준다.

```Java
Direction d = Direction.valueOf("WEST");
System.out.println(d); // WEST
System.out.println(Direction.WEST == Direction.valueOf("WEST")); // true
```

## 열거형에 멤버 추가하기

`Enum` 클래스에 정의된 `ordinal()`이 열거형 상수가 정의된 순서를 반환하지만, 이 값을 열거형 상수의 값으로 사용하지 않는 것이 좋다. 이 값은 내부적인 용도로만 사용되기 위한 것이기 때문이다.

열거형 상수의 값이 불연속적인 경우에는 다음과 같이 열거형 상수의 이름 옆에 원하는 값을 괄호와 함께 적어주면 된다.

```Java
enum Direction { EAST(1), SOUTH(5), WEST(-1), NORTH(10) }
```

그리고 지정된 값을 저장할 수 있는 인스턴스 변수와 생성자를 새로 추가해 줘야 한다. 이 때 주의해야할 점으로, 먼저 열거형 상수를 모두 정의한 다음에 다른 멤버들을 추개해야 한다는 것이다. 또한, 열거형 상수의 마지막에 `;`도 붙여야 한다.

```Java
enum Direciton {
    EAST(1), SOUTH(5), WEST(-1), NORTH(10); // 끝에 ';'를 추가한다.

    private final int value; // 정수를 저장할 필드(인스턴스 변수)를 추가
    Direction(int value) { // private Direction(int value)와 동일
        this.value = value; // 생성자를 추가
    }

    public int getValue() {
        return value;
    }
}
```

열거형의 인스턴스 변수는 반드시 `final`이어야 한다는 제약은 없지만, `value`는 열거형 상수의 값을 저장하기 위한 것이므로 `final`을 붙였다.

열거형 `Direction`에 새로운 생성자가 추가되었지만, 열거형의 객체를 생성할 수는 없다. 열거형의 생성자는 접근제어자가 묵시적으로 `private`이기 때문이다.

필요하다면, 다음과 같이 하나의 열거형 상수에 여러 값을 지정할 수 있다. 다만 그에 맞게 인스턴스 변수와 생성자 등을 새롭게 추가해야 한다.

```Java
enum Direction {
    EAST(1, ">"), SOUTH(2, "V"), WEST(3, "<"), NORTH(4, "^");

    private final int value;
    private final String symbol;

    Direction(int value, String symbol) { // 접근제어자 private이 생략됨
        this.value = value;
        this.symbol = symbol;
    }

    private int getValue() {
        return value;
    }

    private String getSymbol() {
        return symbol;
    }
}
```

### 열거형에 추상 메서드 추가하기

아래의 열거형 `Transportation`은 운송 수단의 종류 별로 상수를 정의하고 있으며, 각 운송 수단에는 기본요금(BASIC_FARE) 책정 되어 있다.

```Java
enum Transportation {
    BUS(100), TRAIN(150), SHIP(100), AIRPLANE(300);

    private final int BASIC_FARE;

    private Transportation(int basicFare) {
        BASIC_FARE = basicFare;
    }

    int fare() { // 운송 요금을 반환
        return BASIC_FARE;
    }
}
```

하지만 이것으로는 부족하다. 거리에 따라 요금을 계산하는 방식이 각 운송 수단마다 다를 것이기 때문이다. 이럴 때 열거형에 추상 메서드 `fare(int distance)`를 선언하면 각 열거형 상수가 이 추상 메서드를 반드시 구현해야 한다.

```Java
enum Transportation {
    BUS(100) {
        int fare (int distance) { return distance * BASIC_FARE; }
    },
    TRAIN(150) {int fare (int distance) { return distance * BASIC_FARE; }},
    SHIP(100) {int fare (int distance) { return distance * BASIC_FARE; }},
    AIRPLANE(300) {int fare (int distance) { return distance * BASIC_FARE; }};

    abstract int fare(int distance); // 거리에 따른 요금을 계산하는 추상 메서드

    protected final int BASIC_FARE; // protected로 해야 각 상수에서 접근가능

    Transportation(int basicFare) {
        BASIC_FARE = basicFare;
    }

    public int getBasicFare() { return BASIC_FARE; }
}
```

위의 코드는 열거형에 정의된 추상 메서드를 각 상수가 어떻게 구현하는지 보여준다.

## 열거형의 이해

```Java
enum Direction { EAST, SOUTH, WEST, NORTH }
```

위와 같이 `Direction`이 정의되어 있을 때 사실은 열거형 상수 하나하나가 `Direction` 객체이다. 위의 문장을 클래스로 정의한다면 다음과 같다.

```Java
class Direction {
    static final Direction EAST = new Direction("EAST");
    static final Direction EAST = new Direction("SOUTH");
    static final Direction EAST = new Direction("WEST");
    static final Direction EAST = new Direction("NORTH");

    private String name;

    private Direction(String name) {
        this.name = name;
    }
}
```

`Direction` 클래스의 `static` 상수 `EAST`, `SOUTH`, `WEST`, `NORTH`의 값은 객체의 주소이고, 이 값은 바뀌지 않는 값이므로 `==`로 비교가 가능한 것이다.

모든 열거형은 추상 클래스 `Enum`의 자손 클래스이므로, `Enum`을 상속할 수 있다.

```Java
abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T> {
    static int id = 0; // 객체에 붙일 일련번호(0부터 시작)

    int ordinal;
    String name = "";

    public int ordinal() { return ordinal; }

    MyEnum(String name) {
        this.name = name;
        ordinal = id++; // 객체를 생성할 때마다 id의 값을 증가시킨다.
    }

    public int compareTo(T t) {
        return ordinal - t.ordinal();
    }
}
```

위의 코드에서는 객체가 생성될 때마다 번호를 붙여서 인스턴스변수 `ordinal`에 저장한다. `Comparable` 인터페이스를 구현해서 열거형 상수간의 비교가 가능하도록 구현하였다. 비교하기 위해서는 두 열거형 상수의 `ordinal` 값을 빼주면 되면 된다.

만약 클래스를 `MyEnum<T>`와 같이 선언하였다면, `compareTo()`를 위와 같이 간단히 작성할 수 없었을 것이다. 타입 `T`에 `ordinal()`이 정의되어 있는지 확인할 수 없기 때문이다.

```Java
abstract class MyEnum<T> implements Comoparable<T> {
    ...
    public int compareTo(T t) {
        return ordinal - t.ordinal(); // Error. 타입 T에 ordinal()이 있는지 알 수 없다.
    }
}
```

그래서 `MyEnum<T extends<MyEnum<T>>`와 같이 선언한 것이며, 이것은 타입 `T`가 `MyEnum<T>`의 자손이어야 한다는 것이다. 타입 `T`가 `MyEnum`의 자손이므로 `ordinal()`이 정의되어 있는 것은 분명하므로 형변환 없이도 에러가 나지 않는다.
