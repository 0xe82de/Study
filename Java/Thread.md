# 참고

- 자바의 정석

# Contents

- [start()와 run()](#start와-run)
- [싱글쓰레드와 멀티쓰레드](#싱글쓰레드와-멀티쓰레드)
- [데몬 쓰레드(daemon thread)](#데몬-쓰레드daemon-thread)

# 쓰레드

`프로세스(process)`란 `실행 중인 프로그램(process)`를 의미한다. 프로세스는 데이터, 메모리 등의 자원과 `쓰레드`로 구성되어 있다. 이 때, 프로세스의 자원을 이용해서 실제로 작업을 수행하는 것이 바로 `쓰레드`이다.

모든 프로세스에는 최소 1개 이상의 쓰레드가 존재하며, 2개 이상의 쓰레드를 가진 프로세스를 `멀티쓰레드 프로세스(multi-threaded process)`라고 한다. 프로세스가 가질 수 있는 쓰레드의 개수는 한정적이지 않지만, 일반적으로 프로세스의 메모리 한계에 따라 가질 수 있는 쓰레드의 개수가 정해진다. 쓰레드가 작업을 수행할 때 별개의 메모리 공간(호출스택)을 필요로 하기 때문이다.

## start()와 run()

`run()` 메서드를 호출하는 것은 생성된 쓰레드를 실행시키는 것이 아니라 단순히 클래스에 선언된 메서드를 호출하는 것이다.

반면에 `start()` 메서드는 새로운 쓰레드가 작업을 수행하는데 필요한 `호출스택(call stack)`을 생성하고 `run()` 메서드를 호출해서 생성된 호출스택에 `run()` 메서드가 첫 번째로 올라가게 한다.

1. main 메서드에서 쓰레드의 start()를 호출한다.
2. start()는 새로운 쓰레드를 생성하고, 쓰레드가 작업하는데 사용될 호출스택을 생성한다.
3. 새로 생성된 호출스택에 run()이 호출되어, 쓰레드가 독립된 공간에서 작업을 수행한다.
4. 이제는 호출스택이 2개이므로, 스케줄러가 정한 순서에 의해서 번갈아 가면서 실행된다.

스케줄러는 실행대기중인 쓰레드들의 우선순위를 고려하여 실행순서와 실행시간을 결정하고 각 쓰레드들은 작성된 스케줄에 따라 자신의 순서가 되면 지정된 시간동안 작업을 수행한다. 이 때 주어진 시간동안 작업을 맞치지 못하면 다시 자신의 차례가 돌아올 때까지 대기 상태로 있게 되며, 작업을 마친 쓰레드, 즉 `run()`의 수행이 종료된 쓰레드는 호출스택이 모두 비워지고 사라진다.

### main쓰레드

`main` 메서드의 작업을 수행하는 것도 쓰레드이다. 이를 `main` 쓰레드라고 한다. `main` 메서드가 수행을 마쳤다고 해도 다른 쓰레드가 아직 작업을 마치지 않은 상태라면 프로그램은 종료되지 않는다. 따라서, 실행 중인 사용자 쓰레드가 하나도 없을 때 프로그램은 종료된다.

## 싱글쓰레드와 멀티쓰레드

### 싱글쓰레드

두 개의 작업을 하나의 쓰레드로 처리하는 경우와 두 개의 쓰레드로 처리하는 경우를 가정해보자. 두 개의 작업을 수행한 시간은 거의 같다. 오히려 한 개의 싱글쓰레드로 작업한 시간이 더 빠르게 종료되는데, 이는 쓰레드간의 `작업 전환(context switching)`에 시간이 걸리기 때문이다. `작업 전환`을 할 때는 현재 진행 중인 작업의 상태, 예를 들면 다음에 실행해야할 위치(PC, 프로그램 카운터) 등의 정보를 저장하고 읽어 오는 시간이 소요된다. 참고로 쓰레드의 스위칭에 비해 프로세스의 스위칭이 더 많은 정보를 저장해야하므로 더 많은 시간이 소요된다. 프로세스 또는 쓰레드 간의 작업 전환을 `컨텍스트 스위칭`이라고 한다. 따라서, 싱글 코어에서 CPU만을 사용하는 계산작업이라면 싱글쓰레드로 개발하는 것이 더 효율적이다.

### 멀티쓰레드

멀티쓰레드로 작업하더라도 코어의 개수에 따라서 차이가 발생하게 된다. `싱글 코어`인 경우에는 멀티쓰레드라도 하나의 코어가 작업을 번갈아 가며 수행하기 때문에, 두 작업이 겹치지 않는다. `멀티 코어`의 경우 멀티쓰레드로 두 작업을 수행하면, 동시에 두 쓰레드가 수행될 수 있으므로 두 작업이 겹치는 부분이 발생한다. 즉, 화면(console)이라는 자원을 놓고 두 쓰레드가 경쟁하게 되는 것이다. 따라서 두 쓰레드가 서로 다른 자원을 사용하는 작업의 경우, 멀티쓰레드 프로세스가 더 효율적이다.

예를 들면, 사용자로부터 데이터를 입력받는 작업, 네트워크로 파일을 주고받는 작업, 프린터로 파일을 출력하는 작업과 같이 외부기기와의 입출력을 필요로 하는 경우가 이에 해당한다. 만약 사용자로부터 입력받는 작업 A와 출력하는 작업 B를 하나의 쓰레드로 처리한다면 사용자가 입력을 마칠때까지 작업 B는 아무 일도 하지 못하고 기다려야만 한다. 그러나, 두 개의 쓰레드로 처리한다면 작업 A가 대기중이더라도 작업 B는 일을 할 수 있게 된다.

## 데몬 쓰레드(daemon thread)

데몬 쓰레드는 다른 일반 쓰레드(데몬 쓰레드가 아닌 쓰레드)의 작업을 돕는 보조 역할을 수행하는 쓰레드이다. 일반 쓰레드가 모두 종료되면 데몬 쓰레드는 강제적으로 자동종료된다. 데몬 쓰레드의 예로는 가비지 컬렉터, 워드프로세서의 자동저장, 화면자동갱신 등이 있다.

데몬 쓰레드는 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 코드를 작성한다. 다만, 쓰레드를 생성한 다음 실행하기 전에 `setDaemon(true)`를 호출하기만 하면 된다. 또한, 데몬 쓰레드가 생성한 쓰레드는 자동으로 데몬 쓰레드가 된다.

| 메서드                     | 설명                                                                                                             |
| -------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| boolean isDaemon()         | 쓰레드가 데몬 쓰레드인지 확인한다. 데몬 쓰레드이면 `true`를 반환한다.                                            |
| void setDaemon(boolean on) | 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경한다. 매개변수 `on`의 값을 `true`로 지정하면 데몬 쓰레드가 된다. |
