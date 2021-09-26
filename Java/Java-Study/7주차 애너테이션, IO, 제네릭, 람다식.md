# 7주차 애너테이션, IO, 제네릭, 람다식

## Categories

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
- [제네릭 주요 개념 (바운디드 타입, 와일드 카드)](#제네릭-주요-개념-바운디드-타입-와일드-카드)
- [제네릭 메소드 만들기](#제네릭-메소드-만들기)
- [Erasure](#Erasure)

### [람다식](#람다식)

- [람다식 사용법](#람다식-사용법)
- [항수형 인터페이스](#항수형-인터페이스)
- [Variable Capture](#Variable-Capture)
- [메소드, 생성자 레퍼런스](#메소드-생성자-레퍼런스)

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

## 스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O

## InputStream과 OutputStream

## Byte와 Character 스트림

## 표준 스트림 (System.in, System.out, System.err)

## 파일 읽고 쓰기

# 제네릭

## 제네릭 사용법

## 제네릭 주요 개념 (바운디드 타입, 와일드 카드)

## 제네릭 메소드 만들기

## Erasure

# 람다식

## 람다식 사용법

## 항수형 인터페이스

## Variable Capture

## 메소드, 생성자 레퍼런스
