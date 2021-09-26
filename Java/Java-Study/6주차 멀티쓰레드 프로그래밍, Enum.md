# 6주차 멀티쓰레드 프로그래밍, Enum

> 참고: [자바의 정석](https://cafe.naver.com/javachobostudy), [자바의 정석 정리](https://github.com/0xe82de/Study/tree/main/%EC%9E%90%EB%B0%94%EC%9D%98%20%EC%A0%95%EC%84%9D)

## Categories

### [멀티쓰레드 프로그래밍](#멀티쓰레드-프로그래밍)

- [Thread 클래스와 Runnable 인터페이스](#Thread-클래스와-Runnable-인터페이스)
- [쓰레드의 상태](#쓰레드의-상태)
- [쓰레드의 우선순위](#쓰레드의-우선순위)
- [Main 쓰레드](#Main-쓰레드)
- [동기화](#동기화)
- [데드락](#데드락)

### [Enum](#enum)

- [enum 정의하는 방법](#enum-정의하는-방법)
- [java.lang.Enum](#javalangEnum)
- [enum이 제공하는 메소드 (values()와 valueOf())](#enum이-제공하는-메소드-values와-valueOf)
- [EnumSet](#EnumSet)

# 멀티쓰레드 프로그래밍

`프로세스(process)`란 `실행 중인 프로그램(process)`를 의미합니다. 프로그램을 실행하면 운영체제로부터 실행에 필요한 자원(메모리)을 할당받아 프로세스가 되는데, 프로세스는 데이터, 메모리 등의 자원과 `쓰레드`로 구성되어 있습니다. 이 쓰레드가 프로세스의 자원을 실제로 작업을 수행합니다.

모든 프로세스에는 최소 1개 이상의 쓰레드가 존재하며, 2개 이상의 쓰레드를 가진 프로세스를 멀티쓰레드 프로세스(multi-threaded process)라고 합니다. 프로세스가 가질 수 있는 쓰레드의 개수는 한정적이지 않지만, 일반적으로 프로세스의 메모리 한계에 따라 가질 수 있는 쓰레드의 개수가 정해집니다. 쓰레드가 작업을 수행할 때 별개의 메모리 공간(호출스택)을 필요로 하기 때문입니다.

멀티쓰레딩은 하나의 프로세스에서 여러 쓰레드가 동시에 작업을 수행하는 것입니다. CPU의 코어가 한 번에 하나의 작업만 수행할 수 있기 때문에 실제로 동시에 처리되는 작업의 개수는 코어의 개수와 동일합니다. 그러나 일반적으로 작업의 개수가 코어의 개수보다 많기 때문에 각 코어가 여러 작업을 번갈아 가며 수행함으로써 여러 작업들이 동시에 수행되는 것처럼 보이게 됩니다.

### 멀티쓰레딩의 장단점

- CPU의 사용률을 향상시킨다.
- 자원을 보다 효율적으로 사용할 수 있다.
- 사용자에 대한 응답성이 향상된다.
- 작업이 분리되어 코드가 간결해진다.

## Thread 클래스와 Runnable 인터페이스

쓰레드를 생성하는 방법은 두 가지가 있습니다.

1. Thread 클래스를 상속받는다.
2. Runnalbe 인터페이스를 구현한다.

두 방법 큰 차이는 없지만, 자바에서는 클래스의 다중상속이 불가능하기 때문에 일반적으로 `Runnable` 인터페이스를 구현합니다.

```Java
// 1. Thread 클래스 상속
class MyThread extends Thread {
    public void run() { // Thread 클래스의 run()을 오버라이딩
				/* 작업내용 */
		}
}
// 2. Runnable 인터페이스 구현
class MyThead implements Runnable {
    public void run() { // Runnable 인터페이스의 run()을 구현
				/* 작업내용 */
		}
}
```

### 구현부

```Java
class ThreadEx01 {
	public static void main(String args[]) {
		// Thread의 자손 클래스의 인스턴스 생성
		ThreadEx1_1 t1 = new ThreadEx1_1();

		// Runnable을 구현한 클래스의 인스턴스 생성
		Runnable r  = new ThreadEx1_2();
		Thread   t2 = new Thread(r); // 생성자 Thread(Runnable target)
		// Thread t2 = new Thread(new ThreadEx1_2()); // 위의 두 줄을 한 줄로 작성

		t1.start();
		t2.start();
	}
}

class ThreadEx1_1 extends Thread {
	public void run() {
		for(int i=0; i < 5; i++) {
			System.out.println(getName()); // 조상 클래스 Thread의 getName() 메서드를 호출
		}
	}
}

class ThreadEx1_2 implements Runnable {
	public void run() {
		for(int i=0; i < 5; i++) {
				// Thread.currentThread() : 현재 실행 중인 Thread를 반환한다.
				System.out.println(Thread.currentThread().getName());
		}
	}
}
/* output
Thread-0
Thread-0
Thread-0
Thread-0
Thread-0
Thread-1
Thread-1
Thread-1
Thread-1
Thread-1
*/
```

위의 코드는 쓰레드를 구현하는 두 가지 방법의 예제입니다. 각각의 인스턴스 생성이 다른 것을 알 수 있습니다.

`Runnable` 인터페이스를 구현항 경우 구현한 클래스의 인스턴스를 생성한 다음, 이 인스턴스를 `Thread` 클래스의 생성자의 매개변수로 제공해야 합니다.

## 쓰레드의 상태

쓰레드의 상태는 다음 중 한 가지에 해당합니다.

| 상태                     | 설명                                                                                                                                    |
| ------------------------ | --------------------------------------------------------------------------------------------------------------------------------------- |
| NEW                      | 쓰레드가 생성되고 아직 start()가 호출되지 않은 상태                                                                                     |
| RUNNABLE                 | 실행 중 또는 실행 가능한 상태                                                                                                           |
| BLOCKED                  | 동기화블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태)                                                                   |
| WAITING<br>TIMED_WAITING | 쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은(unrunnable) 일시정지 상태. TIMED_WAITING은 일시정지시간이 지정된 경우를 의미한다. |
| TERMINATED               | 쓰레드의 작업이 종료된 상태                                                                                                             |

이러한 상태를 확인하는 메서드는 다음과 같습니다.

| 메서드                         | 설명                                                                                                                            |
| ------------------------------ | ------------------------------------------------------------------------------------------------------------------------------- |
| public Thread.State getState() | 쓰레드의 상태를 반환한다.                                                                                                       |
| void interrupt()               | 쓰레드의 interrupted 상태를 false에서 true로 변경한다.                                                                          |
| boolean isInterrupted()        | 쓰레드의 interrupted 상태를 반환한다.                                                                                           |
| static boolean interrupted()   | 현재 쓰레드의 interrupted 상태를 반환하고, false로 변경한다.                                                                    |
| void checkAccess()             | 현재 수행 중인 쓰레드가 해당 스레드를 수정할 수 있는 권한이 있는지 확인하고, 권한이 없다면 SecurityException 예외를 발생시킨다. |
| boolean isAlive()              | 쓰레드가 살아있는지 확인하고, run() 메서드가 종료되었는지 확인한다.                                                             |

쓰레드의 상태를 제어하는 메서드는 다음과 같습니다.

| 메서드                                                                      | 설명                                                                                                                                                                |
| --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| static void sleep(long millis)<br>static void sleep(long millis, int nanos) | 지정된 시간(천분의 일초 단위)동안 쓰레드를 일시정지시킨다. 지정한 시간이 지나고 나면, 자동으로 다시 실행대기상태가 된다.                                            |
| void join()<br>void join(long millis)<br>void join(long millis, int nanos)  | 지정된 시간동안 쓰레드가 실행되도록 한다. 지정된 시간이 지나거나 작업이 종료되면 join()을 호출한 쓰레드로 다시 돌아와 실행을 계속한다.                              |
| void interrupt()                                                            | sleep()이나 join()에 의해 일시정지상태인 쓰레드를 깨워서 실행대기상태로 만든다. 해당 쓰레드에서는 interruptedException이 발생함으로써 일시정지상태를 벗어나게 된다. |
| void stop()                                                                 | 쓰레드를 즉시 종료시킨다.                                                                                                                                           |
| void sustpend()                                                             | 쓰레드를 일시정지시킨다. resume()을 호출하면 다시 실행대기상태가 된다.                                                                                              |
| void resume()                                                               | suspend()에 의해 일시정지상태에 있는 쓰레드를 실행대기상태로 만든다.                                                                                                |
| static voic yield()                                                         | 실행 중에 자신에게 주어진 실행시간을 다른 쓰레드에게 양보(yield)하고 자신은 실행대기상태가 된다.                                                                    |

## 쓰레드의 우선순위

쓰레드는 `우선순위(priority)`라는 속성(멤버변수)을 가지고 있는데, 이 우선순위의 값에 따라 쓰레드가 얻는 실행시간이 달라집니다. 쓰레드가 수행하는 작업의 중요도에 따라 쓰레드의 우선순위를 다르게 지정하여 특정 쓰레드가 더 많은 작업시간을 갖도록 할 수 있습니다.

쓰레드의 우선순위를 지정하고 확인하는 메서드는 다음과 같습니다.

| 메서드                            | 설명                                        |
| --------------------------------- | ------------------------------------------- |
| void setPriority(int newPriority) | 쓰레드의 우선순위를 지정한 값으로 변경한다. |
| int getPriority()                 | 쓰레드의 우선순위를 반환한다.               |

```Java
public static final int MAX_PRIORITY = 10 // 최대우선순위
public static final int MIN_PRIORITY = 1  // 최소우선순위
public static final int NORM_PRIORITY = 5 // 보통우선순위
```

쓰레드가 가질 수 있는 우선순위의 범위는 `1 ~ 10`이며 숫자가 높을수록 우선순위가 높습니다. 또한, 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받게 됩니다. 즉, `main` 메서드를 수행하는 쓰레드는 우선순위가 `5`이므로 `main` 메서드에서 생성한 쓰레드의 우선순위는 `5`가 됩니다.

싱글 코어에서 우선순위를 다르게 설정하고 두 개의 쓰레드에서 작업을 수행하면 우선순위가 높은 쪽의 쓰레드의 작업에 더 많은실행시간이 주어집니다. 하지만, 멀티 코어부터는 우선순위에 따른 차이가 전혀 없게 됩니다. 왜냐하면 각각의 코어에서 두 개의 쓰레드가 동시에 수행되기 때문입니다. 따라서, 쓰레드에 우선순위를 부여하는 대신 작업에 우선순위를 두어 `PriorityQueue`에 저장해 놓고, 우선순위가 높은 작업이 먼저 처리되도록 하는 것이 나을 수 있습니다.

## Main 쓰레드

`main` 메서드의 작업을 수행하는 것도 쓰레드를 `main` 쓰레드라고 한니다. `main` 메서드가 수행을 마쳤다고 해도 다른 쓰레드가 아직 작업을 마치지 않은 상태라면 프로그램은 종료되지 않습니다. 따라서, 실행 중인 사용자 쓰레드가 하나도 없을 때 프로그램은 종료됩니다.

### Daemon 쓰레드

데몬 쓰레드는 다른 일반 쓰레드(데몬 쓰레드가 아닌 쓰레드)의 작업을 돕는 보조적인 역할을 수행하는 쓰레드입니다. 일반 쓰레드가 모두 종료되면 데몬 쓰레드는 강제적으로 자동종료됩니다. 왜냐하면, 데몬 쓰레드는 일반 쓰레드의 보조역할을 수행하므로 일반 쓰레드가 모두 종료되면 데몬 쓰레드가 필요없기 때문입니다.

데몬 쓰레드는 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 코드를 작성하고, 쓰레드를 생성한 다음 실행하기 전에 `setDaemon(true)`를 호출하기만 하면 됩니다. 또한, 데몬 쓰레드가 생성한 쓰레드는 자동으로 데몬 쓰레드가 됩니다.

| 메서드                     | 설명                                                                                                         |
| -------------------------- | ------------------------------------------------------------------------------------------------------------ |
| boolean isDaemon()         | 쓰레드가 데몬 쓰레드인지 확인한다. 데몬 쓰레드이면 true를 반환한다.                                          |
| void setDaemon(boolean on) | 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경한다. 매개변수 on의 값을 true로 지정하면 데몬 쓰레드가 된다. |

## 동기화

싱글쓰레드 프로세스의 경우 프로세스 내에서 단 하나의 쓰레드로 작업하기 때문에 프로세스의 자원을 가지고 작업하는데 특별한 문제가 없습니다. 하지만, 멀티쓰레드 프로세스의 경우 여러 쓰레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에 서로의 작업에 영향을 주게 됩니다.

만약 쓰레드A가 작업하던 도중, 다른 쓰레드B에게 제어권이 넘어갔을 때, 쓰레드B가 쓰레드A가 작업하던 공유데이터를 변경하였다면, 쓰레드A가 제어권을 받아서 나머지 작업을 마쳤을 때 의도했던 것과는 다른 결과를 얻을 수 있습니다.

이러한 상황을 방지하기 위하여 도입된 개념이 바로 `임계 영역(critical section)`과 `잠금(락, lock)`입니다.

[더보기](<https://github.com/0xe82de/Study/blob/main/%EC%9E%90%EB%B0%94%EC%9D%98%20%EC%A0%95%EC%84%9D/13%20%EC%93%B0%EB%A0%88%EB%93%9C(Thread)/9.%20%EC%93%B0%EB%A0%88%EB%93%9C%EC%9D%98%20%EB%8F%99%EA%B8%B0%ED%99%94.md>)

## 데드락

데드락(교착상태, Deadlock)이란, 둘 이상의 쓰레드가 자원을 점유한 상태에서 서로 상대편이 점유한 자원을 사용하려고 대기중인 상태를 말합니다.

데드락은 다음의 네 가지 조건을 모두 만족하면 발생하게 됩니다.

| 조건                        | 설명                                                                                                           |
| --------------------------- | -------------------------------------------------------------------------------------------------------------- |
| 상호 배제(Mutual Exclusion) | 모든 자원은 동시에 한 프로세스만 사용할 수 있다.                                                               |
| 점유 대기(Hold & wait)      | 최소한 하나의 자원을 점유하면서 다른 프로세스가 점유한 자원을 점유하기 위해 대기하는 프로세스가 존재해야 한다. |
| 비선점(No preemption)       | 다른 프로세스에서 점유한 자원은 사용을 마칠 때까지 빼앗을 수 없다.                                             |
| 순환 대기(Cirular wait)     | 프로세스의 집합에서 순환 형대로 자원을 기다리고 있어야 한다.                                                   |
| 출처                        | [Link](https://lksa4e.oopy.io/6a2eea60-90be-49f7-bb79-00b039be7d68)                                            |

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
