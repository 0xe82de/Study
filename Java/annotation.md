# Contents

- [어노테이션이란?](#어노테이션이란)
- [표준 어노테이션](#표준-어노테이션)
- [메타 어노테이션](#메타-어노테이션)
- [어노테이션 타입 정의하기](#어노테이션-타입-정의하기)

# 어노테이션(Annotation)

## 어노테이션이란?

자바를 개발한 사람들은 소스코드에 대한 문서를 별도로 만드는 것보다 소스코드와 문서를 하나의 파일로 관리하는 것이 낫다고 생각했다. 어노테이션은 주석(comment)처럼 프로그래밍 언어에 영향을 미치지 않으면서 다른 프로그램에게 유용한 정보를 제공할 수 있는 기능이다.

> 참고 : 어노테이션(annotation)의 뜻은 주석, 주해, 메모이다.

예를 들어, `@Test`라는 어노테이션을 메서드에 붙이면, 해당 메서드를 테스트해야 한다는 것을 테스트 프로그램에 알린다. 메서드가 포함된 자체에는 아무런 영향을 미치지 않는다.

```java
@Test // 이 메서드가 테스트 대상임을 테스트 프로그램에게 알린다.
public void method() {
    // ...
}
```

어노테이션은 `JDK`에서 제공하는 것과 다른 프로그램에서 제공하는 것들이 있다. 어느 것이든 약속된 형식으로 정보를 제공하기만 하면 된다. `JDK`에서 제공하는 표준 어노테이션은 주로 컴파일러를 위한 것으로 컴파일러에게 유용한 정보를 제공한다. 그리고 어노테이션을 정의할 때 사용하는 메타 어노테이션을 제공한다.

> 참고 : `JDK`에서 제공하는 어노테이션은 `java.lang.annotation` 패키지에 포함되어 있다.

## 표준 어노테이션

자바에서 기본적으로 제공하는 어노테이션은 몇 개 없는데, 이들의 일부는 `메타 어노테이션(meta annotation)`으로 어노테이션을 정의하는데 사용되는 어노테이션의 어노테이션이다.

| 어노테이션           | 설명                                                         |
| -------------------- | ------------------------------------------------------------ |
| @Override            | 컴파일러에게 오버라이딩하는 메서드라는 것을 알린다.          |
| @Deprecated          | 앞으로 사용하지 않을 것을 권장하는 대상에 붙인다.            |
| @SuppressWarnings    | 컴파일러의 특정 경고메시지가 나타나지 않게 해준다.           |
| @SafeVarargs         | 지네릭스 타입의 가변인자에 사용한다.(JDK1.7)                 |
| @FunctionalInterface | 함수형 인터페이스라는 것을 알린다.(JDK1.8)                   |
| @Native              | native 메서드에서 참조되는 상수 앞에 붙인다.(JDK1.8)         |
| @Target\*            | 어노테이션이 적용가능한 대상을 지정하는데 사용한다.          |
| @Documented\*        | 어노테이션 정보가 `javadoc`으로 작성된 문서에 포함되게 한다. |
| @Inherited\*         | 어노테이션이 자손 클래스에 상속되도록 한다.                  |
| @Retention\*         | 어노테이션이 유지되는 범위를 지정하는데 사용한다.            |
| @Repeatable\*        | 어노테이션을 반복해서 적용할 수 있게 한다.(JDK1.8)           |

### @Override

메서드 앞에만 붙일 수 있는 어노테이션으로, 조상의 메서드를 오버라이딩한 메서드라는 것을 컴파일러에게 알려준다. 예를 들어, 메서드 앞에 `@Override`를 붙이면 같은 이름의 메서드가 조상에 있는지 확인하고 없으면 에러메시지를 출력한다.

### @Deprecated

새로운 버전의 `JDK`가 소개될 때, 새로운 기능이 추가될 뿐만 아니라 기존의 부족했던 기능들을 개선하기도 한다. 이 과정에서 기존의 기능을 대체할 것들이 추가되어도 이미 여러 곳에서 사용되고 있을 수 있으므로 함부로 삭제는 할 수 없다.

그래서 생각해낸 방법이 사용되지 않는 필드나 메서드에 `@Deprecated`를 붙이는 것이다. 이 어노테이션이 붙은 대상은 다른 것으로 대체되었으니 더 이상 사용하지 않는 것을 권장한다는 의미이다. 만약 `@Deprecated`가 붙은 대상을 사용하는 코드를 작성하고 컴파일하면 아래와 같은 메시지가 나타난다.

```java
Note: Test.java uses or overrides a deprecated API.
Note: Recompile with ~Xlint:deprecation for details.
```

해당 소스파일이 `deprecated`된 대상을 사용하고 있으며, `-Xlint:deprecation` 옵션을 붙여서 컴파일하면 자세한 내용을 알 수 있다는 뜻이다.

### @FunctionalInterface

`함수형 인터페이스(functional interface)`를 선언할 때, 이 어노테이션을 붙이면 컴파일러가 `함수형 인터페이스`를 올바르게 선언했는지 확인하고, 잘못된 경우 에러를 발생시킨다. 필수는 아니지만, 붙이면 실수릉 방지할 수 있으므로 `함수형 인터페이스`를 선언할 때는 이 어노테이션을 붙이면 좋을 것이다.

> 참고 : 함수형 인터페이스는 추상 메서드가 하나뿐이어야 한다는 제약이 있다.

```java
@FunctionalInterface
public interface Runnable {
    public abstract void run(); // 추상 메서드
}
```

### @SuppressWarnings

컴파일러가 보여주는 경고메시지가 나타나지 않게 처리해준다. `@SuppressWarnings`로 삭제할 수 있는 경고 메시지의 종류는 여러 가지가 있다. `JDK`의 버전이 올라가면서 계속 추가될 것이다. 이 중에 자주 사용되는 것은 `deprecation`, `unchecked`, `rawtypes`, `varargs` 정도이다.

`deprecation`은 앞서 살펴보았듯이 `@Deprecated`가 붙은 대상을 사용해서 발생하는 경고를, `unchecked`는 지네릭스로 타입을 지정하지 않았을 때 발생하는 경고를, `rawtypes`는 지네릭스를 사용하지 않아서 발생하는 경고를, `varargs`는 가변인자의 타입이 지네릭 타입일 때 발생하는 경고를 억제할 때 사용한다.

억제하려는 경고메시지를 어노테이션의 뒤에 괄호 안에 문자열로 지정하면 된다.

```java
@SuppressWarnings("unchecked") // 지네릭스와 관련된 경고를 억제한다.
ArrayList lis = new ArrayList(); // 지네릭 타입을 지정하지 않았다.
list.add(obj); // 여기서 경고가 발생한다.
```

`@SuppressWarnings`로 억제할 수 있는 경고 메시지의 종류는 앞으로 추가될 것이기 때문에, 이전 버전에서 발생하지 않던 경고가 새로운 버전에서 발생할 수 있다. 이러한 경고 메시지를 억제하려면 경고 메시지의 종류를 알아야 하는데, `-Xlint` 옵션으로 컴파일해서 나타는 경고의 내용 중 대괄호 안에 있는 것이 바로 메시지의 종류이다.

두 종류 이상의 경고를 억제하려면 아래와 같이 작성하면 된다.

```java
// main 메서드 내의 "deprecation"과 "unchecked" 관련 경고를 모두 억제한다.
@SuppressWarnings({"deprecation", "unchecked"})
public static void main(String args[]) {
    // ...
}
```

위와 같이 코드를 작성하면 나중에 추가된 코드에서 발생할 수 있는 경고까지 억제될 수 있는데, 이는 바람직하지 않다. 따라서, 해당 대상에만 어노테이션을 붙여서 경고의 억제범위를 최소화하는 것이 좋다.

### @SafeVarargs

메서드에 선언된 가변인자의 타입이 `non-reifiable` 타입일 경우, 해당 메서드를 선언하는 부분과 호출하는 부분에서 `unchecked` 경고가 발생한다. 해당 부분에 문제가 없다면 이 경고를 억제하기 위하여 `@SafeVarargs`를 사용해야 한다.

이 어노테이션은 `static`이나 `final`이 붙은 메서드와 생성자에만 붙일 수 있다. 따라서, 오버라이딩될 수 있는 메서드에는 사용할 수 없다.

지네릭스에서 살펴본 것과 같이 어떤 타입들은 컴파일 이후에 제거되는데, 컴파일 후에도 제거되지 않는 타입을 `reifiable` 타입이라고 하고, 제거되는 타입을 `non-reifiable` 타입이라고 한다. 지네릭 타입들은 대부분 컴파일 시에 제거되므로 `non-reifiable` 타입이다.

> 참고 : `reifiable`은 're(다시)' + '-ify(~화 하다)' + '-able(~할 수 있는)'의 합성어로 직역하면 '다시 ~화 할 수 있는'이라는 뜻이다.`리어화이어블`이라고 읽는다. 컴파일 후에도 타입정보가 유지되면 `reifiable` 타입이다.

예를 들어, `java.util.Arrays`의 `asList()`는 다음과 같이 정의되어 있으며, 이 메서드는 매개변수로 전달받은 값들로 배열을 만들고 새로운 `ArrayList` 객체를 생성하여 반환한다. 이 과정에 경고가 발생하게 된다.

> 주의 : 아래의 코드에 사용된 `ArrayList`는 `Array` 클래스의 내부 클래스이다. `java.util.ArrayList`와 혼동하지 말자.

```java
public static <T> List<T> asList(T... a) {
    return new ArrayList<T>(a); // ArrayList(E[] array)를 호출. 경고발생
}
```

`asList()`의 매개변수가 가변인자인 동시에 지네릭 타입이다. 메서드에 선언된 타입 `T`는 컴파일 과정에서 `Object`로 바뀌게 된다. 즉, `Object[]`가 되고, `Object[]`에는 모든 타입의 객체가 들어있을 수 있으므로 이 배열로 `ArrayList<T>`를 생성하는 것은 위험하다고 경고하는 것이다. 하지만, `asList()`가 호출되는 부분을 컴파일러가 체크해서 타입 `T`가 아닌 타입이 들어가지 못하게 할 것이므로 위의 코드는 아무런 문제가 없다.

이런 상황에서 메서드 앞에 `@SafeVarargs`를 붙여서 `이 메서드의 가변인자는 타입 안정성이 있다.`고 컴파일러에게 알려서 경고를 억제해야 한다.

메서드를 선언할 때 `@SafeVarargs`를 붙이면, 이 메서드를 호출하는 곳에서 발생하는 경고도 억제된다. 반면에 `@SafeVarargs` 대신, `@SuppressWarnings("unchecked")`로 경고를 억제하려면, 메서드 선언 뿐만 아니라 메서드를 호출하는 곳에도 어노테이션을 붙여야 한다. 그리고 `@SafeVarargs`로 `unchecked` 경고는 억제할 수 있지만, `varargs` 경고는 억제할 수 없기 때문에 습관적으로 `@SafeVarargs`와 `@SuppressWarnings("varargs")`를 같이 붙인다.

```java
@SafeVarargs // 'unchecked' 경고를 억제한다.
@SuppressWarnings("varargs") // 'varargs' 경고를 억제한다.
public static <T> List<T> asLit(T... a) {
    return new ArrayList<>(a);
}
```

## 메타 어노테이션

메타 어노테이션은 `어노테이션을 위한 어노테이션`, 즉 어노테이션에 붙이는 어노테이션으로 어노테이션을 정의할 때 어노테이션의 적용대상(target)이나 유지기간(retention) 등을 지정하는데 사용된다.

> 참고 : 메타 어노테이션은 `java.lang.annotation` 패키지에 포함되어 있다.

### @Target

어노테이션이 적용가능한 대상을 지정하는데 사용된다. 아래는 `@SuppressWarnings`를 정의한 것인데, 이 어노테이션에 적용할 수 있는 대상을 `@Target`으로 지정하였다.

```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    String[] value();
}
```

`@Target`으로 지정할 수 있는 어노테이션 적용대상의 종류는 다음과 같다.

| 대상 타입       | 의미                            |
| --------------- | ------------------------------- |
| ANNOTATION_TYPE | 어노테이션                      |
| CONSTRUCTOR     | 생성자                          |
| FIELD           | 필드(멤버변수, enum상수)        |
| LOCAL_VARIABLE  | 지역변수                        |
| METHOD          | 메서드                          |
| PACKAGE         | 패키지                          |
| PARAMETER       | 매개변수                        |
| TYPE            | 타입(클래스, 인터페이스, enum)  |
| TYPE_PARAMETER  | 타입 매개변수(JDK1.8)           |
| TYPE_USE        | 타입이 사용되는 모든 곳(JDK1.8) |

`TYPE`은 타입을 선언할 때, 어노테이션을 붙일 수 있다는 뜻이고 `TYPE_USE`는 해당 타입의 변수를 선언할 때 붙일 수 있다는 뜻이다. 위 표의 값들은 `java.lang.annotation.ElementType`이라는 열거형에 정의되어 있으며, 아래와 같이 `static import`문을 쓰면 `ElementType.TYPE`을 `TYPE`과 같이 간단히 할 수 있다.

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

`FIELD`는 기본형에, `TYPE_USE`는 참조형에 사용된다는 점에 주의하자.

### @Retention

어노테이션이 유지되는 기간을 지정하는데 사용된다. 어노테이션의 유지 정책(retention policy)의 종류는 다음과 같다.

| 유지 정책 | 의미                                              |
| --------- | ------------------------------------------------- |
| SOURCE    | 소스 파일에만 존재. 클래스파일에는 존재하지 않음. |
| CLASS     | 클래스 파일에 존재. 실행시에 사요불가. 기본값     |
| RUNTIME   | 클래스 파일에 존재. 실행시에 사용가능.            |

`@Override`나 `@SuppressWarnings`처럼 컴파일러가 사용하는 애너테티션은 유지 정책이 `SOURCE`이다. 컴파일러가 작성할 것이 아니면, 이 유지정책은 필요없다.

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {}
```

유지 정책을 `RUNTIME`으로 하면, 실행 시에 `리플렉션(reflection)`을 통해 클래스 파일에 저장된 어노테이션의 정보를 읽어서 처리할 수 있다.`@FunctionalInterface`는 `@Override`처럼 컴파일러가 체크해주는 어노테이션이지만, 실행 시에도 사용되므로 유지 정책이 `RUNTIME`으로 되어 있다.

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionalInterface { }
```

유지 정책 `CLASS`는 잘 사용되지 않는 정책이다. 컴파일러가 어노테이션의 정보를 클래스 파일에 저장할 수 있게는 하지만, 클래스 파일이 `JVM`에 로딩될 때는 어노테이션의 정보가 무시되어 실행 시에 어노테이션에 대한 정보를 얻을 수 없기 때문이다.

> 참고 : 지역 변수에 붙은 어노테이션은 컴파일러만 인식할 수 있으므로, 유지정책이 `RUNTIME`인 어노테이션을 지역변수에 붙여도 실행 시에는 인식되지 않는다.

### @Documented

어노테이션에 대한 정보가 `javadoc`으로 작성한 문서에 포함되도록 한다. 자바에서 제공하는 기본 어노테이션 중에 `@Override`와 `@SuppressWarnings`를 제외하고는 모두 이 메타 어노테이션이 붙어 있다.

```java
@Documented // 이 어노테이션의 정보를 javadoc 문서에 포함되도록 한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionalInterface {}
```

### @Inherited

어노테이션이 자손 클래스에 상속되도록 한다. 이 어노테이션이 붙은 어노테이션을 조상 클래스에 붙이면, 자손 클래스도 이 어노테이션이 붙은 것과 같은 효과가 있다.

### @Repeatable

보통은 하나의 대상에 한 종류의 어노테이션을 붙이는데, `@Repeatable`이 붙은 어노테이션은 여러 번 붙일 수 있다.

```java
@Repeatable(ToDos.class) // ToDo 어노테이션을 여러 번 반복해서 쓸 수 있게 한다.
@interface ToDo {
    String value();
}
```

예를 들어, `@ToDo`라는 어노테이션이 위와 같이 정의되어 있을 때, 다음과 같이 `MyClass`에 `@ToDo`를 여러 번 붙이는 것이 가능하다.

```java
@ToDo("delete test codes.")
@ToDo("override inherited methods")
class MyClass {
    // ...
}
```

일반적인 어노테이션과 달리 같은 이름의 어노테이션 여러 개가 하나의 대상에 적용될 수 있기 때문에, 이 어노테이션들을 하나로 묶어서 다룰 수 있는 어노테이션도 추가로 정의해야 한다.

```java
@interface ToDos { // 여러 개의 ToDo 어노테이션을 담을 컨테이너 어노테이션 ToDos
    ToDo[] value(); // ToDo 어노테이션의 배열타입의 요소를 선언한다. 이름이 반드시 value이어야 한다.
}

@Repeatable(ToDos.class) // 괄호 안에 컨테이너 어노테이션을 지정해 줘야 한다.
@interface ToDo {
    String value();
}
```

### @Native

네이티브 메서드(native method)에 의해 참조되는 `상수 필드(constant field)`에 붙이는 어노테이션이다. 아래는 `java.lang.Long` 클래스에 정의된 상수이다.

```java
@Native public static final long MIN_VALUE = 0x8000000000000000L;
```

네이티브 메서드는 `JVM`이 설치된 `OS`의 메서드를 말한다. 네이티브 메서드는 보통 `C언어`로 작성되어 있는데, 자바에서는 메서드의 선언부만 정의하고 구현은 하지 않는다.

```java
public class Object {
    private static native void registerNatives(); // 네이티브 메서드

    static {
        registerNatives(); // 네이티브 메서드를 호출한다.
    }

    protected native Object clone() throws CloneNotSupportedException;
    public final native Class<T> getClass();
    // ...
}
```

이처럼 모든 클래스의 조상인 `Object` 클래스의 메서드들은 대부분 네이티브 메서드이다. 네이티브 메서드는 자바로 정의되어 있기 때문에 호출하는 방법은 자바의 일반 메서드와 다르지 않지만 실제로 호출하는 것은 `OS`의 메서드이다. 아무런 내용도 없는 네이티브 메서드를 선언해놓고 호출한다고 되는 것은 아니고, 자바에 정의된 네이티브 메서드와 `OS`의 메서드를 연결해주는 작업이 추가로 필요하다. 이 역할은 `JNI(Java Native Interface)`가 한다.

## 어노테이션 타입 정의하기

새로운 어노테이션을 정의하는 방법은 다음과 같다. `@` 기호를 붙이는 것을 제외하면 인터페이스를 정의하는 것과 동일하다.

```java
@interface MyAnnotation {
    타입 요소이름(); // 어노테이션의 요소를 선언한다.
    // ...
}
```

엄밀히 말해서 `@Override`는 어노테이션이고, `Override`는 어노테이션의 타입이다.

### 어노테이션의 요소

어노테이션 내에 선언된 메서드를 `어노테이션의 요소(element)`라고 하며, 아래에 선언된 `TestInfo` 어노테이션은 다섯 개의 요소를 가진다.

> 참고 : 어노테이션도 인터페이스처럼 상수를 정의할 수 있지만, 디폴트 메서드는 정의할 수 없다.

```java
@interface TestInfo {
    int count();
    String testedBy();
    String[] testTools();
    TestType testType(); // enum TestType { FIRST, FINAL }
    DateTime testDate(); // 자신이 아닌 다른 어노테이션(@DateTime)을 포함할 수 있다.
}
@interface DateTime {
    String yymmdd();
    String hhmmss();
}
```

어노테이션의 요소는 반환값이 있고 매개변수는 없는 추상 메서드의 형태를 가지며, 상속을 통해 구현하지 않아도 된다. 하지만, 어노테이션을 적용할 때 이 요소들의 값을 빠짐없이 지정해줘야 한다. 요소의 이름도 같이 적어주므로 순서는 상관없다.

```java
@TestInfo (
    count = 3, testedBy = "Kim",
    testTools = {"JUnit", "AutoTester"},
    testType = TestType.FIRST,
    testDate = @DateTime(yymmdd = "160101", hhmmss = "235959")
)
public class NewClass { /* ... */ }
```

어노테이션의 각 요소는 기본값을 가질 수 있으며, 기본값이 있는 요소는 어노테이션을 적용할 때 값을 지정하지 않으면 기본값이 사용된다.

> 참고 : 기본값으로 `null`을 제외한 모든 리터럴이 가능하다.

```java
@interface TestInfo {
    int count() default 1; // 기본값을 1로 지정

}

@TestInfo // @TestInfo(count = 1)과 동일하다.
public class NewClass { /* ... */ }
```

어노테이션 요소가 오직 하나이고, 이름이 `value`인 경우, 어노테이션을 적용할 때 요소의 이름을 생략하고 값만 적어도 된다.

```java
@interface TestInfo {
    String value();
}

@TestInfo("passed") // @TestInfo(value="passed")와 동일하다.
class NewClass { /* ... */ }
```

요소의 타입이 배열인 경우, 괄호`{}`를 사용해서 여러 개의 값을 지정할 수 있다.

```java
@interface TestInfo {
    String[] testTools();
}

@Test(testTools = {"JUnit", "AutoTester"}) // 값이 여러 개인 경우
@Test(testTools = "JUnit") // 값이 하나일 때는 괄호{} 생략 가능
@Test(testTools = {}) // 값이 없을 때는 괄호{}가 반드시 필요
```

기본값을 지정할 때도 괄호`{}`를 사용할 수 있다.

```java
@interface TestInfo {
    String[] info() default {"aaa", "bbb"}; // 기본값이 여러 개인 경우, 괄호{} 사용
    String[] info2() default "ccc"; // 기본값이 하나인 경우, 괄호 생략 가능
}

@TestInfo // @TestInfo(info = {"aaa", "bbb"}, info = "ccc")와 동일
@testInfo(info2 = {}) // @TestInfo(info = {"aaaa", "bbb"}, info2 = {})와 동일
class NewClass { /* ... */ }
```

요소의 타입이 배열일 때도 요소의 이름이 `value`이면, 요소의 이름을 생략할 수 있다. 에를 들어, `@SuppressWarnings`의 경우, 요소의 타입이 `String` 배열이고 이름이 `value`이다.

### java.lang.annotation.Annotation

모든 어노테이션의 조상은 `Annotation`이다. 하지만, 어노테이션은 상속이 되지 않으므로 아래와 같이 명시적으로 `Annotation`을 조상으로 지정할 수 없다.

```java
@interface TestInfo extends Annotation { // Error. 허용되지 않는 표현이다.
    int count();
    String testedBy();
    // ...
}
```

게다가 아래의 소스에서 볼 수 있듯이 `Annotation`은 어노테이션이 아니라 일반적인 인터페이스로 정의되어 있다.

```java
package java.lang.annotation;

public interface Annotation { // Annotation 자신은 인터페이스이다.
    boolean equals(Object obj);
    int hashCode();
    String toString();

    Class<? extends Annotation> annotationType(); // 어노테이션의 타입을 반환한다.
}
```

`Annotation` 인터페이스가 위와 같이 정의되어 있기 때문에, 모든 어노테이션 객체에 대해 `equals()`, `hashCode()`, `toString()` 메서드를 호출할 수 있다.

### 마커 어노테이션 Marker Annotation

값을 지정할 필요가 없는 경우, 어노테이션의 요소를 하나도 정의하지 않을 수 없다. `Serializable`이나 `Cloneable` 인터페이스처럼, 요소가 하나도 정의되지 않은 어노테이션을 마커 어노테이션이라고 한다.

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {} // 마커 어노테이션, 정의된 요소가 하나도 없다.

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Test {} // 마커 어노테이션, 정의된 요소가 하나도 없다.
```

### 어노테이션 요소의 규칙

어노테이션의 요소를 선언할 때 아래와 같은 규칙을 지켜야 한다.

- 요소의 타입은 기본형, String, enum, 어노테이션, Class만 허용된다.
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
