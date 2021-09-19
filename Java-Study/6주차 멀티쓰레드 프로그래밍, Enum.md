# 6주차 멀티쓰레드 프로그래밍, Enum

## Categories

### 멀티쓰레드 프로그래밍

- [Thread 클래스와 Runnable 인터페이스](#Thread-클래스와-Runnable-인터페이스)
- [쓰레드의 상태](#쓰레드의-상태)
- [쓰레드의 우선순위](#쓰레드의-우선순위)
- [Main 쓰레드](#Main-쓰레드)
- [동기화](#동기화)
- [데드락](#데드락)

### Enum

- [enum 정의하는 방법](#enum-정의하는-방법)
- [java.lang.Enum](#javalangEnum)
- [enum이 제공하는 메소드 (values()와 valueOf())](#enum이-제공하는-메소드-values와-valueOf)
- [EnumSet](#EnumSet)

# 멀티쓰레드 프로그래밍

## Thread 클래스와 Runnable 인터페이스

## 쓰레드의 상태

## 쓰레드의 우선순위

## Main 쓰레드

## 동기화

## 데드락

# Enum

## enum 정의하는 방법

`enum`은 서로 관련된 상수를 편리하게 선언하기 위한 것으로, `JDK 1.5`에서 추가되었습니다.

`enum`을 정의하는 방법은 아래와 같습니다.

```Java
public class EnumTest {
	enum Day { SUN, MON, TUE, WED, THR, FRI, SAT }

	public static void main(String[] args) {
		for (Day day : Day.values()) {
			System.out.println(day.name() + " : " + day.ordinal());
		}
    System.out.println(Day.SUN + " : " + Day.SUN.ordinal());
	}
}
/* output
SUN : 0
MON : 1
TUE : 2
WED : 3
THR : 4
FRI : 5
SAT : 6
SUN : 0
*/
```

위의 코드에서 `name()`, `ordinal()` 메서드로 상수의 이름과 값을 가져와서 출력하였습니다. 상수의 이름과 값을 가져오는 방법으로는 `static` 변수를 참조하는 것처럼 `열거형이름.상수명`도 가능합니다.

| 메서드        | 설명                                              |
| ------------- | ------------------------------------------------- |
| String name() | 열거형 상수의 이름을 문자열로 반환한다.           |
| int ordinal() | 열거형 상수가 정의된 순서를 반환한다.(0부터 시작) |

기본적으로 숫자 `0`부터 정수 값이 연속적으로 저장됩니다. 불연속적인 값을 저장하려면 다음과 같이 열거형 상수의 이름 옆에 특정 값을 괄호와 함께 작성하고, 인스턴스 변수와 생성자를 추가해주면 됩니다.

```Java
enum Day {
    MON(1), WED(3), FRI(5);

    private final int value;

    Day(int value) {
        this.value = value;
    }
}
```

또한, 상수에 여러 개의 값을 저장할 수도 있습니다.

```Java

public class EnumTest {
	enum Direction {
		RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1), UP(-1, 0);

		private final int x, y;

		Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int[] getPos() {
			return new int[] {x, y};
		}
	};

	public static void main(String[] args) {
		for (Direction dir : Direction.values()) {
			int[] pos = dir.getPos();
			System.out.println(dir.name() + "\t-> x : " + pos[0] + ",\ty : " + pos[1]);
		}
	}
}
/* output
RIGHT	-> x : 0,	y : 1
DOWN	-> x : 1,	y : 0
LEFT	-> x : 0,	y : -1
UP	-> x : -1,	y : 0
*/
```

## java.lang.Enum

모든 `enum`은 `java.lang.Enum` 클래스를 조상 클래스로 가집니다.

`Enum` 클래스에는 다음과 같은 메서드가 제공됩니다.

| 메서드                                    | 설명                                                      |
| ----------------------------------------- | --------------------------------------------------------- |
| Class<E> getDeclaringClass()              | 열거형의 Class 객체를 반환한다.                           |
| String name()                             | 열거형 상수의 이름을 문자열로 반환한다.                   |
| int ordinal()                             | 열거형 상수가 정의된 순서를 반환한다.(0부터 시작)         |
| T valueOf(Class<T> enumType, String name) | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다. |

## enum이 제공하는 메소드 (values()와 valueOf())

`enum`의 조상 클래스인 `java.lang.Enum` 클래스에서는 `values()`, `valueOf()` 메서드를 제공해주지 않습니다.

[enum 정의하는 방법](#enum-정의하는-방법)의 예제 코드에서 `values()` 메서드를 사용하여 열거형의 모든 상수를 배열에 담아 반환할 수 있었습니다. 조상 클래스에서 제공해주지 않는 메서드이지만 사용할 수 있는 이유는 컴파일러가 `values()`, `valueOf()` 메서드를 자동으로 추가해주기 때문입니다.

| 메서드                        | 설명                                          |
| ----------------------------- | --------------------------------------------- |
| static E values()             | 열거형의 모든 상수를 배열에 담아 반환한다.    |
| static E valueOf(String name) | 매개변수와 일치하는 열거형의 상수를 반환한다. |

## EnumSet

`EnumSet`은 열거형을 위한 `Set` 인터페이스 구현체입니다. `HashSet`과 비교했을 때 성능 상의 이점이 많으므로, 열거형 데이터를 위한 `Set`이 필요할 경우 `EnumSet`을 사용하는 것이 좋습니다.

`EnumSet`의 특징은 다음과 같습니다.

- `EnumSet`은 `AbstractSet` 클래스를 상속하고 `Set` 인터페이스를 구현한다.
- 오직 열거형 상수만을 값으로 가질 수 있다. 또한 모든 값은 같은 `enum type`이어야 한다.
- `null value`를 추가하는 것을 허용하지 않는다. `NullPointerException`을 던지는 것도 허용하지 않는다.
- `ordinal` 값의 순서대로 요소가 저장된다.
- `tread-safe`하지 않다. 동기식으로 사용하려면 `Collections.synchronizedMap`을 사용하거나, 외부에서 동기화를 구현해야한다.
- 모든 메서드는 `arithmetic bitwise operation`을 사용하기 때문에 모든 기본 연산의 시간 복잡도가 O(1)이다.
- [출처](https://wisdom-and-record.tistory.com/52)

| 메서드                       | 설명                                                             |
| ---------------------------- | ---------------------------------------------------------------- |
| allOf(Class<E> elementType)  | 매개변수 enum 타입으로 모든 상수를 포함하는 enum set을 반환한다. |
| of(E first, E... rest)       | 지정된 값들로 enum set을 반환한다.                               |
| complementOf(EnumSet<E> s)   | 지정된 값을 제외하고 enum set을 반환한다.                        |
| range(E from, E to)          | from, to를 포함하는 범위의 enum set을 반환한다.                  |
| noneOf(Class<E> elementType) | 지정한 enum 타입으로 빈 enum set을 반환한다.                     |

```Java
import java.util.EnumSet;

public class EnumSetTest {

	enum Day { SUN, MON, TUE, WED, THR, FRI, SAT }

	public static void main(String[] args) {
		EnumSet<Day> set1, set2, set3, set4, set5;

		set1 = EnumSet.allOf(Day.class);
		set2 = EnumSet.of(Day.SUN, Day.TUE, Day.FRI);
		set3 = EnumSet.complementOf(set2);
		set4 = EnumSet.range(Day.MON, Day.THR);
		set5 = EnumSet.noneOf(Day.class);
		set5.add(Day.WED);
		set5.add(Day.SAT);
		set5.remove(Day.WED);

		System.out.println("set1 = " + set1);
		System.out.println("set2 = " + set2);
		System.out.println("set3 = " + set3);
		System.out.println("set4 = " + set4);
		System.out.println("set5 = " + set5);
		System.out.println(set5.contains(Day.WED));
		System.out.println(set5.contains(Day.SAT));
	}
}
/* output
set1 = [SUN, MON, TUE, WED, THR, FRI, SAT]
set2 = [SUN, TUE, FRI]
set3 = [MON, WED, THR, SAT]
set4 = [MON, TUE, WED, THR]
set5 = [SAT]
false
true
*/
```
