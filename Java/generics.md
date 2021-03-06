# 참고

- 자바의 정석

# Contents

- [지네릭스란?](#지네릭스란)
- [지네릭 클래스의 선언](#지네릭-클래스의-선언)
- [지네릭 클래스의 객체 생성과 사용](#지네릭-클래스의-객체-생성과-사용)
- [제한된 지네릭 클래스](#제한된-지네릭-클래스)
- [와일드 카드](#와일드-카드)
- [지네릭 메서드](#지네릭-메서드)
- [지네릭 타입의 형변환](#지네릭-타입의-형변환)
- [지네릭 타입의 제거](#지네릭-타입의-제거)

# 지네릭스(Generics)

## 지네릭스란?

`지네릭스`는 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입 체크(compile-time type check)를 해주는 기능이다. 객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고 형변환의 번거로움이 줄어든다.

타입 안정성을 높임으로써 의도하지 않은 타입의 객체가 저장되는 것을 방지하고, 저장된 객체를 가져올 때 원래의 타입과 다른 타입으로 잘못 형변환되는 오류를 줄여준다.

### 지네릭스의 장점

1. 타입 안정성을 제공한다.
2. 타입체크와 형변환을 생략할 수 있으므로 코드가 간결해 진다.

## 지네릭 클래스의 선언

지네릭 타입으로 클래스를 선언하는 방법은 다음과 같다.

```Java
// 지네릭 미사용
class Box {
    Object item;
    void setItem(Object item) {
        this.item = item;
    }
    Object getItem() {
        return item;
    }
}

// 지네릭 사용
class Box<T> { // 지네릭 타입 T를 선언
    T item;
    void setItem(T item) {
        this.item = item;
    }
    T getItem( {
        return item;
    })
}
```

위의 코드에서 사용된 `T`를 `타입 변수(type vaiable)`라고 하며 `Type`의 첫 글자에서 따온 것이다.

타입 변수는 `T`가 아닌 다른 것을 사용해도 되는데, `ArrayList<E>`의 경우 타입 변수 `E`는 `Element(요소)`의 첫 글자를 따서 사용할 수 있다. 또한, `Map<K, V>`와 같이 타입 변수를 여러개 사용할 수 있는데, `K`는 `Key(키)`를 의미하고, `V`는 `Value(값)`을 의미한다.

무조건 `T`를 사용하기보다 상황에 맞는 문자를 선택해서 사용하면 된다. 기호의 종류만 다를 뿐 `임의의 참조형 타입`을 의미한다는 것은 모두 같다.

지네릭 클래스의 `Box` 클래스의 객체를 생성할 때는 다음과 같이 참조변수와 생성자에 타입 `T` 대신에 사용할 실제 타입을 지정해줘야 한다.

```Java
Box<String> box = new Box<String>(); // 타입 T 대신에 실제 타입을 지정한다.
box.setItem(new Object()); // Error. 위쪽 코드에서 String으로 지정하였으므로, String 이외의 타입은 불가하다.
box.setItem("ABC"); // Good. String 타입이므로 가능하다.
String item = box.getItem(); // 형변환이 필요없다.
```

지네릭이 도입되기 이전의 코드와 호환을 위해 지네릭 클래스임에도 불구하고 예전의 방식으로 객체를 생성하는 것이 허용된다.

```Java
Box box = new Box(); // T는 Object로 간주된다.
box.setItem("ABC"); // 경고. unchecked or unsafe operation
box.setItem(new Object()); // 경고. unchecked or unsafe operation

// 아래와 같이 변수 T에 Object 타입을 지정하면, 경고는 발생하지 않는다.
Box<Object> box = new Box<Object>();
box.setItem("ABC");
box.setItem(new Object());
```

### 지네릭스의 용어

```java
class Box<T> { }
```

위와 같이 지네릭 클래스 `Box`가 선언되어 있을 때 용어는 아래와 같다.

| 용어   | 설명                                              |
| ------ | ------------------------------------------------- |
| Box<T> | 지네릭 클래스, 'T의 Box' 또는 'T Box'라고 읽는다. |
| T      | 타입 변수 또는 타입 매개변수.(T는 타입 문자)      |
| Box    | 원시 타입(raw type)                               |

타입 문자 `T`는 지네릭 클래스 `Box<T>`의 타입 변수 또는 타입 매개변수라고 하는데, 메서드의 매개변수와 비슷한 면이 있기 때문이다. 아래와 같이 타입 매개변수에 타입을 지정하는 것을 `지네릭 타입 호출`이라고 하고, 지정된 타입 `String`을 `매개변수화된 타입(parameterized type)`이라고 한다. 매개변수화된 타입이라는 용어가 길기 때문에 `대입된 타입`이라는 용어를 사용하겠다(자바의 정석).

```java
Box<String> b = new Box<String>();
```

예를 들어, `Box<String>`과 `Box<Integer>`는 지네릭 클래스 `Box<T>`에 서로 다른 타입을 대입하여 호출한 것일 뿐이다. 마치 `Math.max(3, 5)`와 `Math.max(6, 3)`가 서로 다른 메서드를 호출하는 것이 아닌 것과 같다.

### 지네릭스의 제한

지네릭 클래스 `Box`의 객체를 생성할 때, 객체별로 타입을 다르게 지정하는 것은 가능하다. 하지만, 모든 객체에 대해 동일하게 동작해야 하는 `static` 멤버에 타입 변수 `T`를 사용할 수 없다. `T`는 인스턴스 변수로 간주되기 때문이며, `static` 멤버는 인스턴스 변수를 참조할 수 없다.

```java
class Box<T> {
    static T item; // Error.
    static int compare(T t1, T t2) { // Error.
        //...
    }
}
```

그리고 지네릭 타입의 배열을 생성하는 것도 허용되지 않는다. 지네릭 배열 타입의 참조변수를 선언하는 것은 가능하지만 `new T[10]`과 같이 배열을 생성하는 것은 안 된다.

```java
class Box<T> {
    T[] itemArr; // Good. T 타입의 배열을 위한 참조변수
    // ...
    T[] toArray() {
        T[] tmpArr = new T[itemArr.length]; // Error. 지네릭 배열 생성불가
        // ...
        return tmpArr;
    }
}
```

지네릭 배열을 생성할 수 없는 이유는 `new` 연산자의 특성 때문이다. `new` 연산자는 컴파일 시점에 타입 `T`가 뭔지 정확히 알아야 하는데, 컴파일 시점에는 `T`가 어떤 타입이 될지 전혀 알 수 없다. 같은 이유로 `instanceof` 연산자도 `T`를 피연산자로 사용할 수 없다. 지네릭 배열을 생성해야할 필요가 있을 때는 `new` 연산자 대신 `Reflection API`의 `newInstance()`와 같이 동적으로 객체를 생성하는 메서드로 배열을 생성하거나, `Object` 배열을 생성해서 복사하고 `T[]`로 형변환하는 방법 등이 사용하면 된다.

## 지네릭 클래스의 객체 생성과 사용

지네릭 클래스 `Box<T>`가 다음과 같이 정의되어 있다고 가정한다.

```java
class Box<T> {
    ArrayList<T> list = new ArrayList<T>();

    void add(T item) { list.add(item); }
    T get(int i) { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size() { return list.size(); }
    public String toString() { return list.toString(); }
}
```

`Box<T>`의 객체를 생성할 때는 참조변수와 생성자에 대입된 타입이 같아야 한다. 상속 관계에 있어도 마찬가지이다.

```java
Box<Apple> appleBox = new Box<Apple>(); // Good.
Box<Apple> appleBox = new Box<Grape>(); // Error.
Box<Fruit> appleBox = new Box<Apple>(); // Error. Apple이 Fruit의 자손이어도 에러가 발생한다.
```

단, 두 지네릭 클래스의 타입이 상속관계에 있고, 대입된 타입이 같으면 괜찮다. `FruitBox`는 `Box`의 자손이라고 가정한다.

```java
Box<Apple> appleBox = new FruitBox<Apple>(); // Good.
```

생성된 `Box<T>`의 객체에 `void add(T item)`으로 객체를 추가할 때, 대입된 타입과 다른 타입의 객체는 추가할 수 없다.

```java
Box<Apple> appleBox = new Box<Apple>();
appleBox.add(new Apple()); // Good.
appleBox.add(new Grape()); // Error.
```

하지만 타입 `T`가 `Fruit`인 경우 `Fruit`의 자손들은 `void add(Fruit item)` 메서드의 매개변수가 될 수 있다.

```java
Box<Fruit> fruitBox = new Box<Fruit>();
fruitBox.add(new Fruit()); // Good.
fruitBox.add(new Apple()); // Good.
```

## 제한된 지네릭 클래스

타입 매개변수 `T`에 지정할 수 있는 타입의 종류를 제한할 수 있을까?

```java
FruitBox<Toy> fruitBox = FruitBox<Toy>();
fruitBox.add(new Toy()); // Good. 과일상자에 장난감을 담을 수 있게 된다..
```

다음과 같이 지네릭 타입에 `extends`를 사용하면, 특정 타입의 자손들만 대입할 수 있게 제한할 수 있다.

```java
class FruitBox<T extends Fruit> { // Fruit의 자손만 타입으로 지정할 수 있다.
    // ...
}
```

만약 클래스가 아니라 인터페이스를 구현해야 한다는 제약이 필요하다면, 이 때도 `extends`를 사용한다. `implements`가 아니다.

```java
interface  Eatable {}
class FruitBox<T extends Eatable> {
    // ...
}
```

클래스 `Fruit`의 자손이면서 `Eatable` 인터페이스도 구현해야 한다면 다음과 같이 `&` 기호로 연결한다.

```java
class Fruit<T extends Fruit & Eatable> {
    // ...
}
```

## 와일드 카드

매개변수에 과일박스를 대입하면 주스를 만들어서 반환하는 `Juicer`라는 클래스가 있고, 이 클래스에는 과일을 주스로 만들어서 반환하는 `makeJuice()`라는 `static` 메서드가 다음과 같이 정의되어 있다.

```java
class Juicer {
    static Juice makeJuice(FruitBox<Fruit> box) { // <Fruit>으로 지정
        String tmp = "";
        for (Fruit f : box.getList()) tmp += f + " ";
        return new Juice(tmp);
    }
}
```

`Juicer` 클래스는 지네릭 클래스가 아니다. 지네릭 클래스라 해도 `static` 메서드에는 타입 매개변수 `T`를 매개변수에 사용할 수 없으므로 아예 지네릭스를 적용하지 않던가, 위와 같이 타입 매개변수 대신, 특정 타입을 지정해줘야 한다.

```java
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();

System.out.println(Juicer.makeJuice(fruitBox)); // Good. FruitBox<Fruit>
System.out.println(Juicer.makeJuice(appleBox)); // Error. FruitBox<Apple>
```

이렇게 지네릭 타입을 `FruitBox<Fruit>`로 고정하면, 위의 코드에서 알 수 있듯이 `FruitBox<Apple>` 타입의 객체는 `makeJuice()`의 매개변수가 될 수 없으므로, 다음과 같이 오버로딩할 수 밖에 없다.

```java
static Juice makeJuice(FruitBox<Fruit> box) { // Error.
    String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}
static Juice makeJuice(FruitBox<Apple> box) { // Error.
    String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}
```

하지만 위와 같이 오버로딩하면, 컴파일 에러가 발생한다. 지네릭 타입이 다른 것만으로 오버로딩이 성립하지 않기 때문이다. 지네릭 타입은 컴파일러가 컴파일할 때만 사용하고 제거해버리기 때문에, 위의 두 메서는 오버로딩이 아니라 `메서드 중복 정의`이다. 이럴 때 사용할 수 있는 것이 `와일드 카드`이다. 와일드 카드는 기호 `?`로 표현하는데, 와일드 카드는 어떠한 타입도 될 수 있다.

| 와일드 카드   | 설명                                                     |
| ------------- | -------------------------------------------------------- |
| <? extends T> | 와일드 카드의 상한 제한. `T`와 그 자손들만 가능          |
| <? super T>   | 와일드 카드의 하한 제한. `T`와 그 조상들만 가능          |
| <?>           | 제한 없음. 모든 타입이 가능. `<? extends Object>`와 동일 |

> 참고 : 지네릭 클래스와 달리 와일드 카드에는 `&`를 사용할 수 없다.

와일드 카드를 적용하면 다음과 같이 코드를 작성할 수 있다.

```java
static Juice makeJuice(FruitBox<? extends Fruit> box) {
    String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}
```

이제 위 메서드의 매개변수로 `FruitBox<Fruit>` 뿐만 아니라, `FruitBox<Apple>`와 `FruitBox<Grape>`도 가능하게 된다.

## 지네릭 메서드

메서드의 선언부에 지네릭 타입이 선언된 메서드를 지네릭 메서드라 한다. 지네릭 타입의 선언 위치는 반환 타입 바로 앞이다.

```java
static <T> void sort(List<T> list, Comparator<? super T> c)
```

지네릭 클래스에 정의된 타입 매개변수와 지네릭 메서드에 정의된 타입 매개변수는 전혀 별개의 것이다. 같은 타입 문자 `T`를 사용해도 같은 것이 아니라는 것에 주의해야 한다.

```java
class FruitBox<T> {
    // ...
    static <T> void sort (List<T> list, Comparator<? super T> c) {
        // ...
    }
}
```

위의 코드에서 지네릭 클래스 `FruitBox`에 선언된 타입 매개변수 `T`와 지네릭 메서드 `sort()`에 선언된 타입 매개변수 `T`는 타입 문자만 같을 뿐이고, 서로 다르다. 또한, `sort()` 메서드가 `static` 메서드인데, `static` 멤버에는 타입 매개변수를 사용할 수 없지만, 메서드에 지네릭 타입을 선언하고 사용하는 것은 가능하다.

메서드에 선언된 지네릭 타입은 지역 변수를 선언한 것과 같다고 생각하면 이해하기 쉽다. 이 타입 매개변수는 메서드 내에서만 지역적으로 사용될 것이므로 메서드가 `static`이건 아니건 상관이 없다.

```java
// 지네릭 메서드 변경 전
static Juice makeJuice(FruitBox<? extends Fruit> box) {
    String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}

// 지네릭 메서드 변경 후
static <T extends Fruit> Juice makeJuice(FruitBox<T> box) {
    String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}
```

이제 이 메서드를 호출할 때는 아래와 같이 타입 변수에 타입을 대입해야 한다.

```java
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBOx<Apple> appleBox = new FruitBox<Apple>();

System.out.println(Juicer.<Fruit>makeJuice(fruitBox));
System.out.println(Juicer.<Apple>makeJuice(appleBox));
```

하지만 대부분의 경우 컴파일러가 타입을 추정할 수 있기 때문에 생략할 수는 있다.

```java
// 타입 생략 가능
System.out.println(Juicer.makeJuice(fruitBox));
System.out.println(Juicer.makeJuice(appleBox));
```

한 가지 주의할 점으로는 지네릭 메서드를 호출할 때, 대입된 타입을 생략할 수 없는 경우에는 참조변수나 클래스 이름을 생략할 수 없다는 것이다.

```java
System.out.println(<Fruit>makeJuice(fruitBox)); // Error. 클래스 이름 생략불가
System.out.println(this.<Fruit>makeJuice(fruitBox)); // Good.
System.out.println(Juicer.<Fruit>makeJuice(fruitBox)); // Good.
```

같은 클래스 내에 있는 멤버들끼리는 `this.`이나 `클래스이름.`을 생략하고 메서드 이름만으로 호출이 가능하지만, 대입된 타입이 있을 때는 반드시 써줘야 한다.

지네릭 메서드는 매개변수의 타입이 복잡할 때 유용하다.

```java
// 지네릭 메서드 변경 전
public static void printAll(ArrayList<? extends Product> list, ArrayList<? extends Product> list2) {
    // ...
}
// 지네릭 메서드 변경 후
public static <T extends Product> void printAll(ArrayList<T> list, ArrayList<T> list2) {
    // ...
}
```

### Collection.sort()

```java
public static <T extends Comparable<? super T>> void sort(List<T> list)
```

1. 타입 T를 요소로 하는 List를 매개변수로 허용한다.
2. 타입 T는 Comparable을 구현한 클래스이어야 하며(<T extends Comparable>), T 또는 그 조상의 타입을 비교하는 Comparable이어야한다는 것(Comparable<? super T>)을 의미한다. 만약 T가 Student이고, Person의 자손이라면, <? super T>는 Student, Person, Object가 모두 가능하다.

## 지네릭 타입의 형변환

지네릭 타입과 원시 타입(raw type)간의 형변환이 가능할까..?!

```java
Box box = null;
Box<Object> objBox = null;

box = (Box)objBox; // Good. 지네릭 타입 -> 원시 타입. 경고가 발생한다.
objBox = (Box<Object>)box; // Good. 원시 타입 -> 지네릭 타입. 경고가 발생한다.
```

지네릭 타입과 넌지네릭(non-generic) 타입간의 형변환은 가능하지만 경고가 발생한다. 그러면, 대입된 타입이 다른 지네릭 타입 간에는 형변환이 가능할까..?!

```java
Box<Object> objBox = null;
Box<String> strBOx = null;

objBox = (Box<Object>)strBox; // Error.
strBox = (Box<String>)objBox; // Error.
```

불가능하다. 다음의 문장은 어떨까..?!

```java
Box<? extends Object> wBox = new Box<String>(); // Good.
Box<? extends Fruit> wBox = new Box<Fruit>(); // Good.
Box<? extends Fruit> wBox = new Box<Apple>(); // Good.
Box<? extends Fruit> wBox = new Box<Grape>(); // Good.
```

형변환이 된다.

반대로의 형변환도 성립하지만, 확인되지 않은 형변환이라는 경고가 발생한다. `FruitBox<? extends Fruit>`에 대입될 수 있는 타입이 여러 개 인데다, `FruitBox<Apple>`를 제외한 다른 타입은 `FruitBox<Apple>`로 형변환될 수 없기 때문이다.

```java
FruitBox<? extends Fruit> box = null;
FruitBox<Apple> appleBox = (FruitBox<Apple>)box; // Good. 미확인 타입으로 형변환 경고
```

### java.util.Optional Class

실질적인 예를 살펴보자. 다음은 `java.util.Optional` 클래스의 실제 소스의 일부이다.

```java
public final class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>();
    private final T value;
    // ...
    public static<T> Optional<T> empty() {
        Optional<T> t = (Optional<T>) EMPTY; // Optional<?> -> Optional<T>
        return t;
    }
    // ...
}
```

`static` 상수 `EMPTY`에 비어있는 `Optional` 객체를 생성해서 저장해두고 `empty()` 메서드를 호출하면 `EMPTY`를 형변환해서 반환한다. `<?>`는 `<? extends Object>`를 줄여 쓴 것이며, `<>`안에 생략된 타입은 `?`가 아니라 `Object`이다.

```java
Optional<?> EMPTY = new Optional<?>(); // Error. 미확인 타입의 객체는 생성불가
Optional<?> EMPTY = new Optional<Object>(); // Good.
Optional<?> EMPTY = new Optional<>(); // Good. 위의 문장과 동일하다.
```

> 주의 : class Box<T extends Fruit>의 경우 Box<?> b = new Box();는 Box<?> b = new Box<Fruit>이다.

위의 코드에서 `EMPTY`의 타입을 `Optional<Object>`가 아닌 `Optional<?>`로 한 이유는 `Optional<T>`로 형변환이 가능하기 때문이다.

```java
Optional<?> wopt = new Optional<Object>();
Optional<Object> oopt = new Optional<Object>();

Optional<String> sopt = (Optional<String>)wopt; // Good. 형변환이 가능하다.
Optional<String> sopt = (Optional<String>)oopt; // Error. 형변환이 불가능하다.
```

`empty()`의 반환 타입이 `Optional<T>`이므로 `EMPTY`를 `Optional<T>`로 형변환해야 하는데, 위의 코드에서 알 수 있듯이 `Optional<Object>`는 `Optional<T>`로 형변환할 수 없다.

정리하자면, `Optional<Object>`를 `Optional<String>`으로 직접 형변환하는 것은 불가능하지만, 와일드 카드가 포함된 지네릭 타입으로 형변환할 수 있다는 것이다. 대신 확인되지 않은 타입으로의 형변환이라는 경고가 발생한다.

그리고 다음과 같이 와일드 카드가 사용된 지네릭 타입끼리도 형변환이 가능하다.

```java
FruitBox<? extends Object> objBox = null;
FruitBox<? extends String> strBox = null;

strBox = (FruitBox<? extends String>)objBox; // Good. 미확정 타입으로의 형변환 경고가 발생한다.
objBox = (FruitBox<? extends Object>)strBox; // Good. 미확정 타입으로의 형변환 경고가 발생한다.
```

와일드 카드가 타입이 확정된 타입이 아니기 때문에 컴파일러가 경고를 발생시키지만, 형변환이 가능하긴 하다.

## 지네릭 타입의 제거

컴파일러는 지네릭 타입을 이용해서 소스파일을 체크하고, 필요한 곳에 형변환을 넣어준다. 그 이후에는 지네릭 타입을 제거한다. 따라서, 컴파일된 파일(\*.class)에는 지네릭 타입에 대한 정보가 없는 것이다.

이렇게 하는 주된 이유는 지네릭이 도입되기 이전의 소스 코드와의 호환성을 유지하기 위함이다. `JDK 1.5`부터 지네릭스가 도입되었지만, 아직도 원시 타입을 사용해서 코드를 작성하는 것을 허용하고 있다.

지네릭 타입의 제거 과정은 꽤 복잡한데, 기본적인 제거 과정은 다음과 같다.

1. 지네릭 타입의 경계(bound)를 제거한다.

   지네릭 타입이 `<T extends Fruit>`라면 `T`는 `Fruit`로 치환된다. `<T>`인 경우는 `T`는 `Object`로 치환된다. 그리고 클래스 옆의 선언은 제거된다.

   ```java
   // 제거 전
   class Box<T extends Fruit> {
       void add(T t) { }
   }
   // 제거 후
   class Box {
       void add(Fruit t) { }
   }
   ```

2. 지네릭 타입을 제거한 후에 타입이 일치하지 않으면, 형변환을 추가한다.

   `List`의 `get()`은 `Object` 타입을 반환하므로 형변환이 필요하다.

   ```java
   // 제거 전
   T get(int i) return list.get(i);
   // 제거 후
   Fruit get(int i) return (Fruit)list.get(i);
   ```

   와일드 카드가 포함되어 있는 경우에는 다음과 같이 적절한 타입으로의 형변환이 추가된다.

   ```java
   // 제거 전
   static Juice makeJuice(FruitBox<? extends Fruit> box) {
       String tmp = "";
       for (Fruit f : box.getList()) tmp += f + " ";
       return new Juice(tmp);
   }
   // 제거 후
   static Juice makeJuice(FruitBox box) {
       String tmp = "";
       Iterator it = box.getList().iterator();
       while (it.hasNext()) tmp += (Fruit)it.next() + " ";
       return new Juice(tmp);
   }
   ```
