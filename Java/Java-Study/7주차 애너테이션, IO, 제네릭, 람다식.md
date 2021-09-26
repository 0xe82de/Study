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

## @target

## @documented

## 애너테이션 프로세서

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
