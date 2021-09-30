# Contents

### [애너테이션](#애너테이션)

- [애너테이션 정의하는 방법](#애너테이션-정의하는-방법)
- [retention](#retention)
- [target](#target)
- [documented](#documented)
- [애너테이션 프로세서 ](#애너테이션-프로세서)

### [I/O](#IO)

- [스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O](#스트림-Stream--버퍼-Buffer--채널-Channel-기반의-IO)
- [InputStream과 OutputStream](#InputStream과-OutputStream)
- [Byte와 Character 스트림](#Byte와-Character-스트림)
- [표준 스트림 (System.in, System.out, System.err)](#표준-스트림-System.in-System.out-System.err)
- [파일 읽고 쓰기](#파일-읽고-쓰기)

### [제네릭](#제네릭)

- [제네릭 사용법](#제네릭-사용법)
- [제네릭 주요 개념](#제네릭-주요-개념)
- [제네릭 메소드 만들기](#제네릭-메소드-만들기)

### [람다식](#람다식)

- [람다식 사용법](#람다식-사용법)
- [항수형 인터페이스](#항수형-인터페이스)
- [Variable Capture](#Variable-Capture)
- [메소드, 생성자 레퍼런스](#메소드-생성자-레퍼런스)

# 7주차 애너테이션, IO, 제네릭, 람다식

# 애너테이션

애너테이션은 주석(comment)처럼 프로그래밍 언어에 영향을 미치지 않으면서 다른 프로그램에게 유용한 정보를 제공할 수 있는 기능입니다. 예를 들어, `@Test`라는 애너테이션을 메서드에 붙이면, 해당 메서드를 테스트해야 한다는 것을 테스트 프로그램에 알립니다. 메서드가 포함된 자체에는 아무런 영향을 미치지 않습니다.

```java
@Test // 이 메서드가 테스트 대상임을 테스트 프로그램에게 알린다.
public void method() {
    // ...
}
```

애너테이션은 `JDK`에서 제공하는 것과 다른 프로그램에서 제공하는 것들이 있는데, 어느 것이든 약속된 형식으로 정보를 제공하기만 하면 됩니다. `JDK`에서 제공하는 표준 애너테이션은 주로 컴파일러를 위한 것으로 컴파일러에게 유용한 정보를 제공하고 애너테이션을 정의할 때 사용하는 메타 애너테이션을 제공합니다.

> 참고 : `JDK`에서 제공하는 애너테이션은 `java.lang.annotation` 패키지에 포함되어 있습니다.

## 애너테이션 정의하는 방법

새로운 애너테이션을 정의하는 방법은 다음과 같습니다. `@` 기호를 붙이는 것을 제외하면 인터페이스를 정의하는 것과 동일합니다.

```java
@interface MyAnnotation {
    타입 요소이름(); // 애너테이션의 요소를 선언한다.
    // ...
}
```

### 애너테이션의 요소

애너테이션 내에 선언된 메서드를 `애너테이션의 요소(element)`라고 하며, 이러한 요소를 선언할 때 아래와 같은 규칙을 지켜야 합니다.

- 요소의 타입은 기본형, String, enum, 애너테이션, Class만 허용된다.
- ()안에 매개변수를 선언할 수 없다.
- 예외를 선언할 수 없다.
- 요소를 타입 매개변수로 정의할 수 없다.

```java
@interface AnnoTest {
    int id = 100; // Good. 상수 선언, static final int id = 100;
    String major(int i, int j); // Error. 매개변수를 선언할 수 없다.
    String minor() throws Exception; // Error. 예외를 선언할 수 없다.
    ArrayList<T> list(); // 요소의 타입에 타입 매개변수를 사용할 수 없다.
}
```

아래에 선언된 `TestInfo` 애너테이션은 다섯 개의 요소를 가집니다.

```java
@interface TestInfo {
    int count();
    String testedBy();
    String[] testTools();
    TestType testType(); // enum TestType { FIRST, FINAL }
    DateTime testDate(); // 자신이 아닌 다른 애너테이션(@DateTime)을 포함할 수 있다.
}
@interface DateTime {
    String yymmdd();
    String hhmmss();
}
```

## @retention

애너테이션이 유지되는 기간을 지정하는데 사용되며, 애너테이션의 유지 정책(retention policy)의 종류는 다음과 같습니다.

| 유지 정책 | 의미                                              |
| --------- | ------------------------------------------------- |
| SOURCE    | 소스 파일에만 존재. 클래스파일에는 존재하지 않음. |
| CLASS     | 클래스 파일에 존재. 실행시에 사요불가. 기본값     |
| RUNTIME   | 클래스 파일에 존재. 실행시에 사용가능.            |

`@Override`나 `@SuppressWarnings`처럼 컴파일러가 사용하는 애너테티션은 유지 정책이 `SOURCE`인데, 컴파일러가 작성할 것이 아니면, 이 유지정책은 필요없습니다.

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {}
```

유지 정책을 `RUNTIME`으로 하면, 실행 시에 `리플렉션(reflection)`을 통해 클래스 파일에 저장된 애너테이션의 정보를 읽어서 처리할 수 있습니다.`@FunctionalInterface`는 `@Override`처럼 컴파일러가 체크해주는 애너테이션이지만, 실행 시에도 사용되므로 유지 정책이 `RUNTIME`으로 되어 있습니다.

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionalInterface { }
```

유지 정책 `CLASS`는 잘 사용되지 않는 정책입니다. 컴파일러가 애너테이션의 정보를 클래스 파일에 저장할 수 있게는 하지만, 클래스 파일이 `JVM`에 로딩될 때는 애너테이션의 정보가 무시되어 실행 시에 애너테이션에 대한 정보를 얻을 수 없기 때문입니다.

## @target

애너테이션이 적용가능한 대상을 지정하는데 사용됩니다. 아래는 `@SuppressWarnings`를 정의한 것인데, 이 애너테이션에 적용할 수 있는 대상을 `@Target`으로 지정하였습니다.

```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    String[] value();
}
```

`@Target`으로 지정할 수 있는 애너테이션 적용대상의 종류는 다음과 같습니다.

| 대상 타입       | 의미                            |
| --------------- | ------------------------------- |
| ANNOTATION_TYPE | 애너테이션                      |
| CONSTRUCTOR     | 생성자                          |
| FIELD           | 필드(멤버변수, enum상수)        |
| LOCAL_VARIABLE  | 지역변수                        |
| METHOD          | 메서드                          |
| PACKAGE         | 패키지                          |
| PARAMETER       | 매개변수                        |
| TYPE            | 타입(클래스, 인터페이스, enum)  |
| TYPE_PARAMETER  | 타입 매개변수(JDK1.8)           |
| TYPE_USE        | 타입이 사용되는 모든 곳(JDK1.8) |

`TYPE`은 타입을 선언할 때, 애너테이션을 붙일 수 있다는 뜻이고 `TYPE_USE`는 해당 타입의 변수를 선언할 때 붙일 수 있다는 뜻입니다. 위 표의 값들은 `java.lang.annotation.ElementType`이라는 열거형에 정의되어 있으며, 아래와 같이 `static import`문을 쓰면 `ElementType.TYPE`을 `TYPE`과 같이 간단히 할 수 있습니다.

```java
import static java.lang.annotation.ElementType.*;

@Target({FIELD, TYPE, TYPE_USE}) // 적용대상이 FIELD, TYPE, TYPE_USE
public @interface MyAnnotation { } // MyAnnotation을 정의한다.

@MyAnnotation // 적용대상이 TYPE인 경우
class MyClass {
    @MyAnnotation // 적용대상이 FIELD인 경우
    int i;

    @MyAnnotation // 적용대상이 TYPE_USE인 경우
    MyClass mc;
}
```

## @documented

애너테이션에 대한 정보가 `javadoc`으로 작성한 문서에 포함되도록 한다. 자바에서 제공하는 기본 애너테이션 중에 `@Override`와 `@SuppressWarnings`를 제외하고는 모두 이 메타 애너테이션이 붙어 있습니다.

```java
@Documented // 이 애너테이션의 정보를 javadoc 문서에 포함되도록 한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionalInterface {}
```

## 애너테이션 프로세서

애너테이션 프로세서는 애너테이션을 이용하여 프로세스를 처리하는 것을 의미합니다. 컴파일러가 컴파일 중 새로운 소스코드를 생성하거나 기존의 소스코드를 변경할 수 있게 합니다.

관련 예로는 다음과 같은 것들이 있습니다. [출처](https://parkadd.tistory.com/54)

- 롬복(Lombok)

  - @Getter, @Setter, @Builder

- AutoService

  - java.util.ServiceLoader용 파일 생성 유틸리티
  - 리소스 파일을 만들어줍니다.
  - META-INF 밑의 service 밑에 ServiceLoader용 레지스트리 파일을 만들어줍니다.

- @Override

  - 컴파일러가 오버라이딩하는 메서드가 잘못된 대상임을 체크해주는것도 애노테이션 프로세서

- Dagger2 : 컴파일 타임 DI 제공 -> 런타임 비용이 없어집니다.

- Android Library

  - ButterKnife : @BindView (뷰 아이디와 애노테이션 붙인 필드 바인딩)
  - DeepLinkDispatcher : 특정 URI 링크를 Activity로 연결할 때 사용

# I/O

`I/O`는 `Input(입력)`과 `출력(Output)`을 말합니다. 데이터를 밖으로 보내는 것이 출력이며, 안으로 데이터를 보내는 것이 입력입니다. 안과 밖의 기준은 `JVM`이며, 입출력을 위해 자바는 `java.io` 패키지를 제공합니다.

## 스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O

입출력을 위한 통로를 `스트림(Stream)`이라고 합니다. 스트림은 단방향 통신만 가능하므로 입출력을 수행하기 위해 두 개의 스트림이 필요하며 연속된 데이터의 흐름으로 입출력을 진행하면 다른 작업을 할 수 없는 `블로킹(Blocking)` 상태가 됩니다. `java.io` 패키지에서는 다양한 종류의 스트림 클래스를 제공합니다.

| 스트림                                                                                                     | 설명                    |
| ---------------------------------------------------------------------------------------------------------- | ----------------------- |
| InputStream, BufferedInputStream, FileInputStream, OutputStream, BufferedOutputStream, FileOutputStream 등 | Byte 단위의 스트림      |
| Reader, BufferedReader, FileReader, Writer, BufferedWriter, FileWriter 등                                  | Character 단위의 스트림 |

`JDK 1.4`에서 `NIO(New I/O)`가 추가되었는데, `NIO`는 `버퍼(Buffer)`와 `채널(Channel)` 기반으로 데이터를 처리합니다. `NIO`의 모든 `I/O`는 채널로 시작하며 버퍼로 채널 데이터를 읽을 수 있고, 버퍼에서 채널로 데이터를 쓸 수 있습니다.

`버퍼(Buffer)`는 제한된 크기에 순서대로 데이터를 저장하는 저장소입니다. `byte`, `char`, `int` 등 기본형 데이터 타입을 저장할 수 있습니다. 채널을 통해 데이터를 입출력하게됨으로써 가비지량을 최소화시키고, GC 회수를 줄임으로써 서버의 전체 처리량을 증가시켜줍니다.

`채널(Channel)`은 스트림과 달리 양방향이며, 비동기적입니다. 그리고 데이터를 읽고 쓸때 버퍼가 사용됩니다.

## InputStream과 OutputStream

`InputStream`은 `Byte` 단위 입력 스트림의 최상위 추상 클래스이며, 모든 바이트 단위 입력 스트림은 이 클래스를 상속받아 만들어집니다. 데이터를 입력받는 기능을 수행합니다.

| 메서드                           | 설명                                                                                                                                         |
| -------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------- |
| read()                           | 입력 스트림으로부터 1 byte를 읽어서 반환합니다.                                                                                              |
| read(byte[] b )                  | 입력 스트림으로부터 읽은 bytes를 배열 b에 저장하고 실제로 읽은 bytes 수를 반환합니다.                                                        |
| read(byte[] b, int off, int len) | 배열 b[off]부터 len개를 저장하고 실제로 읽은 bytes 수인 len개를 반환합니다. 만약 len개를 모두 읽지 못하면 실제로 읽은 bytes 수를 반환합니다. |
| close()                          | 시스템 자원을 반납하고 입력스트림들 닫습니다.                                                                                                |

`OutputStream`은 `Byte` 단위 출력 스트림의 최상위 추상 클래스이며, 모든 바이트 단위 출력 스트림은 이 클래스를 상속받아 만들업집니다. 데이터를 출력하는 기능을 수행합니다.

| 메서드                            | 설명                                                        |
| --------------------------------- | ----------------------------------------------------------- |
| write(int b)                      | 출력 스트림으로 1 byte를 내보냅니다.                        |
| write(byte[] b)                   | 출력 스트림으로 배열 b를 내보냅니다.                        |
| write(byte[] b, int off, int len) | 출력 스트림으로 배열 b[off]부터 len개의 bytes를 내보냅니다. |
| flush()                           | 버퍼에 존재하는 모든 bytes를 출력합니다.                    |
| close()                           | 시스템 자원을 반납하고 출력 스트림을 닫습니다.              |

## Byte와 Character 스트림

| 스트림    | 설명                                                                | 데이터 단위(byte) |
| --------- | ------------------------------------------------------------------- | ----------------- |
| Byte      | 이미지, 동영상 등을 송수신할 때 주로 사용됩니다.                    | 1                 |
| Character | 일반적인 텍스트 및 `JSON`, `HTML` 등을 송수신할 때 주로 사용됩니다. | 2                 |

## 표준 스트림 (System.in, System.out, System.err)

표준 스트림의 입출력은 콘소을 통한 데이터 입출력을 말합니다. 자바에서는 표준 스트림으로 `System.in`, `System.out`, `System.err` 세 가지를 제공하며, 내부적으로는 `BufferedInputStream`, `BufferedOutputStream`을 사용합니다.

```java
// 콘솔로부터 입력을 받는 스트림을 스캐너에 할당한다.
Scanner sc = new Scanner(System.in);
int num = sc.nextInt();

try {
    // 콘솔로 출력한다. (정상)
    System.out.println(10 / num);
} catch (Exception e) {
    // 콘솔로 출력한다. (에러)
    System.err.println("Error");
}
```

## 파일 읽고 쓰기

텍스트 파일의 경우 `Character` 단위 스트림 클래스를 사용하고, 바이너리 파일의 경우 `Byte` 단위 스트림 클래스를 사용하여 파일을 읽고 쓸 수 있습니다.

```java
// 텍스트 파일
BufferedReader br = new BufferedReader(new FileReader("ti.txt"));
BufferedWriter bw = new BufferedWriter(new FileWriter("tw.txt"));
String data;
while ((data = br.readLine()) != null) bw.write(s + "\n");

// 바이너리 파일
BufferedInputStream bs = new BufferedInputStream(new FileInputStream("bi.png"));
BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("bw.png"));
byte[] data = new byte[16384];
while (bs.read(data) != -1) bw.write(data);
```

# 제네릭

## 제네릭 사용법

제네릭 타입으로 클래스를 선언하는 방법은 다음과 같습니다.

```java
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

위의 코드에서 사용된 `T`를 `타입 변수(type vaiable)`라고 하며 `Type`의 첫 글자에서 따온 것입니다.

타입 변수는 `T`가 아닌 다른 것을 사용해도 되는데, `ArrayList<E>`의 경우 타입 변수 `E`는 `Element(요소)`의 첫 글자를 따서 사용할 수 있습니다. 또한, `Map<K, V>`와 같이 타입 변수를 여러개 사용할 수 있는데, `K`는 `Key(키)`를 의미하고, `V`는 `Value(값)`을 의미합니다.

무조건 `T`를 사용하기보다 상황에 맞는 문자를 선택해서 사용하면 됩니다. 기호의 종류만 다를 뿐 `임의의 참조형 타입`을 의미한다는 것은 모두 같습니다.

지네릭 클래스의 `Box` 클래스의 객체를 생성할 때는 다음과 같이 참조변수와 생성자에 타입 `T` 대신에 사용할 실제 타입을 지정해줘야 합니다.

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

## 제네릭 주요 개념

### 와일드 카드

매개변수에 과일박스를 대입하면 주스를 만들어서 반환하는 `Juicer`라는 클래스가 있고, 이 클래스에는 과일을 주스로 만들어서 반환하는 `makeJuice()`라는 `static` 메서드가 다음과 같이 정의되어 있습니다.

```java
class Juicer {
    static Juice makeJuice(FruitBox<Fruit> box) { // <Fruit>으로 지정
        String tmp = "";
        for (Fruit f : box.getList()) tmp += f + " ";
        return new Juice(tmp);
    }
}
```

`Juicer` 클래스는 지네릭 클래스가 아닙니다. 지네릭 클래스라 해도 `static` 메서드에는 타입 매개변수 `T`를 매개변수에 사용할 수 없으므로 아예 지네릭스를 적용하지 않던가, 위와 같이 타입 매개변수 대신, 특정 타입을 지정해줘야 합니다.

```java
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();

System.out.println(Juicer.makeJuice(fruitBox)); // Good. FruitBox<Fruit>
System.out.println(Juicer.makeJuice(appleBox)); // Error. FruitBox<Apple>
```

이렇게 지네릭 타입을 `FruitBox<Fruit>`로 고정하면, 위의 코드에서 알 수 있듯이 `FruitBox<Apple>` 타입의 객체는 `makeJuice()`의 매개변수가 될 수 없으므로, 다음과 같이 오버로딩할 수 밖에 없습니다.

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

하지만 위와 같이 오버로딩하면, 컴파일 에러가 발생하는데, 지네릭 타입이 다른 것만으로 오버로딩이 성립하지 않기 때문입니다 지네릭 타입은 컴파일러가 컴파일할 때만 사용하고 제거해버리기 때문에, 위의 두 메서는 오버로딩이 아니라 `메서드 중복 정의`입니다. 이럴 때 사용할 수 있는 것이 `와일드 카드`이며, 와일드 카드는 기호 `?`로 표현하는데, 와일드 카드는 어떠한 타입도 될 수 있습니다.

| 와일드 카드   | 설명                                                   |
| ------------- | ------------------------------------------------------ |
| <? extends T> | 와일드 카드의 상한 제한. T와 그 자손들만 가능          |
| <? super T>   | 와일드 카드의 하한 제한. T와 그 조상들만 가능          |
| <?>           | 제한 없음. 모든 타입이 가능. <? extends Object>와 동일 |

> 참고 : 지네릭 클래스와 달리 와일드 카드에는 `&`를 사용할 수 없습니다.

와일드 카드를 적용하면 다음과 같이 코드를 작성할 수 있습니다.

```java
static Juice makeJuice(FruitBox<? extends Fruit> box) {
    String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}
```

이제 위 메서드의 매개변수로 `FruitBox<Fruit>` 뿐만 아니라, `FruitBox<Apple>`와 `FruitBox<Grape>`도 가능하게 됩니다.

## 제네릭 메소드 만들기

메서드의 선언부에 지네릭 타입이 선언된 메서드를 지네릭 메서드라 합니다. 지네릭 타입의 선언 위치는 반환 타입 바로 앞입니다.

```java
static <T> void sort(List<T> list, Comparator<? super T> c)
```

지네릭 클래스에 정의된 타입 매개변수와 지네릭 메서드에 정의된 타입 매개변수는 전혀 별개의 것인데, 같은 타입 문자 `T`를 사용해도 같은 것이 아니라는 것에 주의해야 합니다.

```java
class FruitBox<T> {
    // ...
    static <T> void sort (List<T> list, Comparator<? super T> c) {
        // ...
    }
}
```

위의 코드에서 지네릭 클래스 `FruitBox`에 선언된 타입 매개변수 `T`와 지네릭 메서드 `sort()`에 선언된 타입 매개변수 `T`는 타입 문자만 같을 뿐이고, 서로 다릅니다. 또한, `sort()` 메서드가 `static` 메서드인데, `static` 멤버에는 타입 매개변수를 사용할 수 없지만, 메서드에 지네릭 타입을 선언하고 사용하는 것은 가능합니다.

메서드에 선언된 지네릭 타입은 지역 변수를 선언한 것과 같다고 생각하면 이해하기 쉽습니다. 이 타입 매개변수는 메서드 내에서만 지역적으로 사용될 것이므로 메서드가 `static`이건 아니건 상관이 없습니다.

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

이제 이 메서드를 호출할 때는 아래와 같이 타입 변수에 타입을 대입해야 합니다.

```java
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBOx<Apple> appleBox = new FruitBox<Apple>();

System.out.println(Juicer.<Fruit>makeJuice(fruitBox));
System.out.println(Juicer.<Apple>makeJuice(appleBox));
```

하지만 대부분의 경우 컴파일러가 타입을 추정할 수 있기 때문에 생략할 수는 있습니다.

```java
// 타입 생략 가능
System.out.println(Juicer.makeJuice(fruitBox));
System.out.println(Juicer.makeJuice(appleBox));
```

한 가지 주의할 점으로는 지네릭 메서드를 호출할 때, 대입된 타입을 생략할 수 없는 경우에는 참조변수나 클래스 이름을 생략할 수 없다는 것입니다.

```java
System.out.println(<Fruit>makeJuice(fruitBox)); // Error. 클래스 이름 생략불가
System.out.println(this.<Fruit>makeJuice(fruitBox)); // Good.
System.out.println(Juicer.<Fruit>makeJuice(fruitBox)); // Good.
```

같은 클래스 내에 있는 멤버들끼리는 `this.`이나 `클래스이름.`을 생략하고 메서드 이름만으로 호출이 가능하지만, 대입된 타입이 있을 때는 반드시 써줘야 합니다.

지네릭 메서드는 매개변수의 타입이 복잡할 때 유용합니다.

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

# 람다식

람다식은 `JDK 1.8`에서 추가되었으며, 이로 인해 자바는 객체지향언어인 동시에 함수형 언어가 되었습니다. 람다식은 메서드를 하나의 식으로 표현한 것이며, 메서드의 이름과 반환값이 없어지므로 `익명 함수(anonymous function)`이라고도 합니다.

```java
int[] arr = new int[5];

// 람다식 표현
Arrays.setAll(arr, (i) -> (int)(Math.random() * 5) + 1);

// 메서드 표현
int method() {
    return (int)(Math.random() * 5) + 1;
}
```

## 람다식 사용법

람다식은 `익명 함수`답게 메서드에서 이름과 반환타입을 제거하고 매개변수 선언부와 몸통 `{}` 사이에 `->`를 추가합니다.

```java
// 메서드
반환타입 메서드이름(매개변수 선언) {
    // ...
}

// 람다식
(매개변수 선언) -> {
    // ...
}
```

두 값 중에서 작은 값을 반환하는 메서드 `min`를 람다식으로 변환하면 아래와 같습니다.

```java
// 메서드
int min(int a, int b) {
    return a < b ? a : b;
}

// 람다식
(int a, int b) -> {
    return a < b ? a : b;
}
```

반환값이 있는 메서드의 경우, `return`문 대신 `식(expression)`으로 대신 할 수 있습니다. 식의 연산결과가 자동으로 반환값이 되며, `문장(statement)`이 아닌 `식`이므로 끝에 `;`을 붙이지 않습니다.

```java
// 변경 전
(int a, int b) -> { return a < b ? a : b; }

// 변경 후
(int a, int b) -> a < b ? a : b
```

람다식에 선언된 매개변수의 타입은 추론이 가능한 경우는 생략할 수 있습니다.

```java
// 생략하지 않은 경우
(int a, int b) -> a < b ? a : b

// 타입을 생략한 경우
(a, b) -> a < b ? a : b
```

아래와 같이 선언된 매개변수가 하나일 때는 괄호 `()`를 생략할 수 있습니다. 반대로 매개변수의 타입이 있을 때는 생략할 수 없습니다.

```java
(a) -> a * a // Good.
int a -> a * a // Error.
```

괄호 `{}` 안의 문장이 하나일 때는 괄호 `{}`를 생략할 수 있습니다. 이 떄 문장의 끝에 `;`을 붙이면 안되고, 괄호 `{}` 안의 문장이 `return`문일 때는 괄호 `{}`를 생략할 수 없습니다.

```java
// 생략 전
(String name, int i) -> {
    System.out.println(name + "=" + i);
}

// 생략 후
(String name, int i) -> System.out.println(name + "=" + i); // Good.
(int a, int b) -> return a < b ? a : b // Error.
(int a, int b) -> { return a < b ? a : b; } // Good.
```

## 항수형 인터페이스

사실 람다식은 익명 클래스의 객체와 동등합니다.

```java
타입 f = (int a, int b) -> a > b ? a : b;
```

위의 코드에서 참조변수 f의 타입으로 클래스 또는 인터페이스가 가능합니다. 그리고 람다식과 동등한 메서드가 정의되어 있는 것이어야 합니다. 그래야만 참조변수로 익명 객체(람다식)의 메서드를 호출할 수 있기 때문입니다.

예를 들어, 아래와 같이 `min()`라는 메서드가 정의된 `MyFunction` 인터페이스가 정의되어 있고, 구현체는 그 다음과 같다고 합니다.

```java
interface MyFunction {
    public abstract int min(int a, int b);
}

MyFunction f = new MyFunction() {
    public int min(int a, int b) {
        return a < b ? a : b;
    }
};
int big = f.min(6, 1);
```

`MyFunction` 인터페이스에 정의된 메서드 `min()`는 람다식과 메서드의 선언부가 일치합니다. 따라서 위 코드의 익명 객체를 람다식으로 아래와 같이 대체할 수 있습니다.

```java
MyFunction f = (int a, int b) -> a < b ? a : b;
int big = f.min(6, 1);
```

이처럼 `MyFunction` 인터페이스를 구현한 익명 객체를 람다식으로 대체가 가능한 이유는, 람다식도 실제로는 익명 객체이고, `MyFunction` 인터페이스를 구현한 익명 객체의 메서드 `max()`와 람다식의 매개변수의 타입과 개수, 반환값이 일치하기 때문입니다.

하나의 메서드가 선언된 인터페이스를 정의해서 람다식을 다루는 것은 기존의 자바의 규칙들을 어기지 않으면서 자연스럽습니다. 그에 따라 람다식을 인터페이스로 다루기로 결정되었으며, 람다식을 다루기 위한 인터페이스를 `함수형 인터페이스(functional interface)`라고 부르기로 했습니다.

### 매개변수와 반환타입

```java
@FunctionalInterface
interface MyFunction { // 함수형 인터페이스 MyFunction을 정의한다.
    public abstract int min(int a, int b);
}
```

단, 함수형 인터페이스는 하나의 추상 메서드만 정의되어 있어야 한다는 제약이 있습니다. 그래야 람다식과 인터페이스의 메서드가 1:1로 연결될 수 있기 때문입니다. 반면에 `static` 메서드와 `default` 메서드의 개수에는 제약이 없습니다.

```java
@FunctionalInterface
interface MyFunction {
    void myMethod(); // 추상 메서드
}
```

위의 코드에서 메서드의 매개변수가 `MyFunction` 타입이면, 이 메서드를 호출할 때 람다식을 참조하는 참조변수를 매개변수로 지정해야한다는 뜻입니다.

```java
void aMethod(MyFunction f) { // 매개변수의 타입이 함수형 인터페이스
    f.myMethod(); // MyFunction에 정의된 메서드 호출
}

MyFunction f = () -> System.out.println("myMethod()");
aMethod(f);
```

또는 참조변수 없이 아래와 같이 직접 람다식을 매개변수로 지정하는 것도 가능합니다.

```java
aMethod(() -> System.out.println("myMethod()")); // 람다식을 매개변수로 지정
```

그리고 메서드의 반환타입이 함수형 인터페이스타입이라면, 이 함수형 인터페이스의 추상메서드와 동등한 람다식을 가리키는 참조변수를 반환하거나 람다식을 직접 반환할 수 있습니다.

```java
MyFunction myMethod() {
    MyFunction f = () -> {};
    return f;
    // 위 두 줄을 한 줄로 줄이면
    return () -> {};
}
```

### 형변환

람다식의 타입이 함수형 인터페이스의 타입과 일치하는 것은 아닙니다. 람다식은 익명 객체이고 타입은 있지만 컴파일러가 임의로 이름을 정하기 때문에 알 수 없습니다. 그에 따라 대입 연산자의 양변의 타입을 일치시키기 위해 아래와 같이 형변환이 필요합니다.

```java
MyFunction f = (MyFunction)(() -> {}); // 양변의 타입이 다르므로 형변환이 필요
```

람다식은 `MyFunction` 인터페이스를 직접 구현하지 않았지만, 이 인터페이스를 구현한 클래스의 객체와 완전히 동일하기 때문에 위와 같은 형변환을 허용합니다. 그리고 이 형변환은 생략할 수 있습니다.

람다식은 이름이 없을 뿐 분명히 객체인데도, 아래와 같이 `Object` 타입으로 형변환할 수 없습니다. 굳이 변환한다면, 먼저 함수형 인터페이스로 변환해야 합니다.

```java
Object obj = (Object)(() -> {}); // Error. 함수형 인터페이스로만 형변환 가능
Object obj = (Object)(MyFunction)(() -> {}); // Good.
String str = ((Object)(MyFunction)(() -> {})).toString(); // Good.
```

## Variable Capture

람다식의 body에서 인자로 넘어온 것 이외의 변수에 접근하는 것을 `Variable Capture`라고 합니다.

람다식에서 지역변수는 다음과 같은 제약사항이 있습니다.

1. `final`로 선언되어야 합니다.
2. `final`로 선언되어 있지 않는 경우 `effectively final`이어야 합니다. 지역변수의 값이 바뀌면 안됩니다.

이러한 제약사항이 있는 이유는 클래스 내부에 선언된 람다식이 지역변수를 참조할 때는 그 값을 복사해서 사용하기 때문입니다. 이 과정에서 `Variable Capture`가 발생합니다. 외부에서 선언된 변수를 직접 사용하지 않고 내부로 복사해오는 이유는 변수와 객체의 생명 주기와 관련이 있는데, 설명은 아래와 같습니다. [출처](https://wisdom-and-record.tistory.com/66)

"스태틱 변수는 메서드 영역에, 인스턴스 변수는 힙 영역에, 지역 변수는 호출스택에 각각 생성된다. 람다식 또한 익명 객체의 인스턴스이기 때문에 힙 영역에 생성된다. 지역 변수는 해당 변수를 선언한 메서드가 종료되는 순간 메모리에서 사라진다. 하지만 로컬 클래스는 아예 다른 위치에서 생성되기 때문에 생명주기가 메서드와 전혀 상관이 없다. 따라서 스태틱 변수, 인스턴스 변수와 달리 로컬 클래스가 지역 변수를 사용하려고 할때는 해당 지역 변수가 이미 호출스택에서 사라졌을 위험이 항상 존재한다(매개변수도 마찬가지다). 그럼 로컬 클래스는 절대로 외부에서 선언된 변수를 참조할 수 없도록 제약해야 할까? 그런 제약을 걸지 않으면서도 위에서 언급한 위험성을 제거하기 위해서 자바에서는 객체 외부에 선언된 변수의 값을 복사(Variable Capture)하면서 동시에 해당 변수는 반드시 final이어야 한다는 새로운 제약을 만들었다."

## 메소드, 생성자 레퍼런스

메서드를 람다식으로 간결하게 표현하는 방법을 배웠습니다. 아래의 방법을 통해 람다식을 더 간결하게 표현할 수 있으며, 이를 `메서드 참조`, `생성자 참조`라 합니다.

| 종류                          | 람다                       | 메서드 참조       |
| ----------------------------- | -------------------------- | ----------------- |
| static 메서드 참조            | (x) -> ClassName.method(x) | ClassName::method |
| 인스턴스메서드 참조           | (obj, x) -> obj.method(x)  | ClassName::method |
| 특정 객체 인스턴스메서드 참조 | (x) -> obj.method(x)       | obj::method       |
| 생성자                        | () -> new MyClass          | MyClass::new      |

```java
// 변경 전
Function<String, Integer> f = (String s) -> Integer.parseInt(s);

// 변경 후
Function<String, Integer> f = Integer::parseInt; // 메서드 참조
```

위 메서드 참조에서 람다식의 일부가 생략되었습니다. 하지만, 컴파일러는 생략된 부분을 우변의 `parseInt` 메서드의 선언부로부터, 또는 좌변의 `Function` 인터페이스에 지정된 지네릭 타입으로부터 쉽게 알아낼 수 있습니다.

```java
// 변경 전
BiFunction<String, String, Boolean> f = (s1, s2) -> s1.equals(s2);

// 변경 후
BiFunction<String, String, Boolean> f = String::equals; // 메서드 참조
```

위 매개변수 `s1`과 `s2`를 생략하면 `equals`만 남는데, 두 개의 `String`을 받아서 `Boolean`을 반환하는 `equals`라는 메서드는 다른 클래스에도 존재할 수 있기 때문에 `equals` 앞에 클래스 이름은 반드시 필요합니다.

메서드 참조를 사용할 수 있는 경우는 한 가지 더 있습니다. 이미 생성된 객체의 메서드를 람다식에서 사용한 경우에는 클래스 이름 대신 그 객체의 참조변수를 적어줘야 합니다.

```java
MyClass obj = new MyClass();
Function<String, Boolean> f = (x) -> obj.equals(x); // 람다식
Function<String, Boolean> f2 = obj::equals; // 메서드 참조
```

아래는 생성자를 호출하는 람다식을 메서드 참조로 표현한 것입니다.

```java
Supplier<MyClass> s = () -> new MyClass; // 람다식
Supplier<MyClass> s = MyClass::new; // 메서드 참조
```

매개변수가 있는 생성자라면, 매개변수의 개수에 따라 알맞은 함수형 인터페이스를 사용하면 되며, 필요하다면 새로운 함수형 인터페이스를 새로 정의하면 됩니다.

```java
Function<Integer, MyClass> f = (i) -> new MyClass(i); // 람다식
Function<Integer, MyClass> f2 = MyClass::new; // 메서드 참조

BiFunction<Integer, String, MyClass> bf = (i, s) -> new MyClass(i, s);
BiFunction<Integer, String, MyClass> bf2 = MyClass::new; // 메서드 참조
```
