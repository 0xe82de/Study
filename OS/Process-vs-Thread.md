# 프로세스 vs 스레드

# Content

- [프로세스](#프로세스)
- [스레드](#스레드)
- [멀티 프로세스 대신 멀티 스레드를 사용하는 이유](#멀티-프로세스-대신-멀티-스레드를-사용하는-이유)

## 프로세스

프로세스는 실행 중인 프로그램을 의미하며 작업의 단위입니다.

> 프로그램 : 저장 장치에 저장되어 있는 파일

프로그램을 실행하면 운영체제로부터 시스템 자원을 할당받아 고유한 메모리 영역을 가지게 됩니다. 시스템 자원은 다음과 같습니다.

- CPU 시간
- 프로그램 실행을 위한 메모리 주소 공간
- Code, Data, Stack, Heap의 구조로 된 메모리 영역

#### 프로세스 특징

- 최소 1개의 스레드를 가지고 있다.
- 각 프로세스는 독립된 주소 공간에서 실행된다.
- 한 프로세스는 다른 프로세스의 변수, 데이터 구조에 접근할 수 없다.
- 한 프로세스가 다른 프로세스의 자원에 접근하려면 프로세스 간의 통신(IPC)을 사용해야 한다.

### 멀티 프로세스

멀티 프로세스는 하나의 프로그램을 여러 개의 프로세스로 나누고 각 프로세스가 하나의 작업을 처리하도록 하는 것입니다.

#### 장점

- 여러 개의 자식 프로세스 중 한 프로세스에 문제가 발생해도 다른 프로세스에 영향을 주지 않는다.

#### 단점

- 프로세스는 각각의 독립된 메모리 영역을 할당받았기 때문에 프로세스 사이에서 공유하는 메모리가 없다. Context Switching이 발생하면 캐시에 있는 모든 데이터를 모두 초기화하고 다시 캐시 정보를 불러와야 한다. 이 과정에서 발생하는 오버헤드가 크다.
- 프로세스는 각각의 독립된 메모리 영역을 할당받기 때문에 하나의 프로그램에 속하는 프로세스들 사이의 변수를 공유할 수 없다.

> Context Switching : CPU에서 여러 프로세스를 돌아가면서 작업을 처리하는 과정을 Context Switching이라고 한다. 동작 중인 프로세스가 대기를 하면서 해당 프로세스의 상태(Context)를 보관하고, 대기하고 있던 다음 순서의 프로세스가 동작하면서 이전에 보관했던 프로세스의 상태를 복구하는 작업을 말한다.

## 스레드

스레드는 프로세스 내에서 실행되는 실행 흐름의 단위입니다. 프로세스가 할당받은 자원을 사용하며 한 프로세스에 존재하는 스레드들은 자원을 공유합니다.

#### 스레드의 특징

- 스레드는 프로세스가 할당받은 메모리 영역에서 Stack만 따로 할당받고 Code, Data, Heap 영역은 공유한다.
- 같은 프로세스 안에 있는 여러 스레드들은 같은 힙 공간을 공유한다. 반면에 프로세스는 다른 프로세스의 메모리에 직접 접근할 수 없다.
- 각각의 스레드는 별도의 레지스터와 스택을 갖고 있지만, 힙 메모리는 서로 읽고 쓸 수 있다.
- 한 스레드가 프로세스 자원을 변경하면, 다른 이웃 스레드도 그 변경 결과를 즉시 확인할 수 있다.

### 멀티 스레드

멀티 스레드는 하나의 프로그램을 여러 개의 스레드로 나누고 각 스레드로 하나의 작업을 처리하도록 하는 것입니다.

#### 장점

- 프로세스를 생성해서 자원을 할당하는 시스템 콜이 줄어들어 자원을 효과적으로 관리할 수 있다.
- 스레드는 프로세스 내의 Stack 영역을 제외한 모든 메모리를 공유하기 때문에 통신의 부담이 적다.
- 처리 비용 감소
  - 스레드 간 데이터를 주고 받는 것이 간단해지고 시스템 자원 소모가 줄어들게 된다.
  - 스레드 사이의 작업량이 작아 Context Switching이 빠르다.

#### 단점

- 자원을 공유하므로 동기화 문제가 발생할 수 있다.
- 하나의 스레드에 문제가 발생하면 프로세스, 스레드가 영향을 받는다.
- 개발자의 주의 깊은 설계가 필요하다.
- 디버깅이 어렵다.
- 다른 프로세스에서 스레드를 제어할 수 없다.

## 멀티 프로세스 대신 멀티 스레드를 사용하는 이유

- 자원의 효율성 증대

  - 멀티 프로세스로 실행되는 작업을 멀티 스레드로 실행하면, 프로세스를 생성하여 자원을 할당하는 시스템 콜이 줄어들어 자원을 효율적으로 관리할 수 있다.
  - 프로세스 간의 Context Switching을 하면 단순히 CPU 레지스터 교체 뿐만 아니라 RAM과 CPU 사이의 캐시 메모리에 대한 데이터까지 초기화되므로 오버헤드가 크다.
  - 스레드는 프로세스 내의 메모리를 공유하기 때문에 독립적인 프로세스와 달리 스레드 간 데이터를 주고 받는 것이 간단해지고 시스템 자원 소모가 줄어들게 된다.

- 처리 비용 감소 및 응답 시간 단축

  - 스레드는 Stack 영역을 제외한 모든 메모리를 공유하므로 프로세스 간의 통신 비용보다 스레드 간의 통신 비용이 적다. 따라서 작업들 간의 통신 부담이 줄어든다.
  - 프로세스 간의 전환 속도보다 스레드 간의 전환 속도가 빠르다. Stack 영역만 Context Switching하면 되기 때문이다.

#### 참고

- [https://gmlwjd9405.github.io/2018/09/14/process-vs-thread.html](https://gmlwjd9405.github.io/2018/09/14/process-vs-thread.html)
- [https://velog.io/@raejoonee/프로세스와-스레드의-차이](https://velog.io/@raejoonee/%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%EC%99%80-%EC%8A%A4%EB%A0%88%EB%93%9C%EC%9D%98-%EC%B0%A8%EC%9D%B4)
- [https://velog.io/@chy0428/OS-멀티프로그래밍-멀티프로세싱](https://velog.io/@chy0428/OS-%EB%A9%80%ED%8B%B0%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%EB%A9%80%ED%8B%B0%ED%94%84%EB%A1%9C%EC%84%B8%EC%8B%B1)