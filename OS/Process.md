### Content

- [프로세스](#프로세스)
- [프로세스의 상태](#프로세스의-상태)
- [프로세스 제어블록 (PCB)](#프로세스-제어블록-pcb)
- [준비 큐, 장치 큐, 작업 큐](#준비-큐-장치-큐-작업-큐)
- [프로세스의 생성](#프로세스의-생성)
- [IPC](#ipc)

# 프로세스

프로세스는 `실행 중인 프로그램`을 의미합니다. 프로그램을 실행하면 운영체제로부터 시스템 자원을 할당받아 고유한 메모리 영역을 가지게 됩니다.

시분할 시스템 환경에서는 프로세스가 번갈아가며 수행되는데, CPU를 확보했을 때 이전 작업을 복구하기 위해 `프로세스의 문맥` 정보를 활용합니다.

프로세스의 문맥은 다음과 같은 정보를 가집니다.

- 주소 공간(코드, 데이터, 스택)
- 레지스터 값
- 시스템 콜 등을 통해 커널에서 수행한 일의 상태
- 커널이 관리하고 있는 각종 정보

## 프로세스의 상태

프로세스의 상태는 다음과 같으며, 하나의 프로세스는 하나의 상태에 머물러 있게 됩니다.

|            구분             | 설명                                                                                                                                                |
| :-------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------- |
|        실행(running)        | 프로세스가 CPU를 확보하여 기계어 명령을 실행하고 있는 상태                                                                                          |
|         준비(ready)         | CPU를 확보하지 못한 상태. CPU를 확보하면 명령을 당장 실행할 수 있다.                                                                                |
| 봉쇄(blocked, wait, sleep)  | CPU를 확보하더라도 당장 명령을 실행할 수 없는 상태                                                                                                  |
|          시작(new)          | 프로세스가 시작되어 각종 자료구조는 생성되었지만 메모리 획득을 승인받지 못한 상태                                                                   |
|      완료(terminated)       | 프로세스가 종료되었으나 운영체제가 프로세스와 관련된 자료구조를 정리하지 못한 상태                                                                  |
| 중지봉쇄(suspended blocked) | 봉쇄 상태의 프로세스가 중기 스케줄러에 의해 디스크로 스왑아웃된 상태. 메모리를 확보하지 않은 상태이다. 봉쇄 조건을 만족하면 중지준비 상태로 바뀐다. |
|  중지준비(suspended ready)  | 준비 상태의 프로세스가 중기 스케줄러에 의해 디스크로 스왑 아웃된 상태. 메모리를 확보하지 않은 상태이다.                                             |

## 프로세스 제어블록 (PCB)

프로세스 제어블록(이하 PCB)은 운영체제가 프로세스를 관리하기 위한 커널 내의 자료구조를 의미합니다. PCB는 다음과 같은 정보를 가집니다.

- 프로세스의 상태 : CPU 할당 여부를 결정할 때 사용한다.
- 프로그램 카운터의 값 : 다음에 수행할 명령의 위치이다.
- CPU 레지스터의 값 : 현 시점에 레지스터에 어떤 값을 저장하는지 나타낸다.
- CPU 스케줄링 정보 : CPU 스케줄링용이다.
- 메모리 관리 정보 : 메모리 할당용이다.
- 자원 사용 정보 : 사용자에게 자원 사용 비용을 계산해 청구하는 용도로 사용된다.
- 입출력 상태 정보 : 프로세스가 오픈한 파일 정보 등의 입출력 관련 상태 정보를 나타낸다.

## 문맥 교환 (context switching)

실행 상태에 있는 프로세스에 인터럽트가 발생하고 새로운 프로세스가 CPU를 확보하게 되는데, 이를 문맥 교환이라 합니다.

CPU의 제어권은 운영체제로 넘어가며, 운영체제는 실행 상태의 프로세스 문맥을 저장하고 새롭게 CPU를 사용할 준비 상태의 프로세스를 선택합니다. 이처럼 준비 상태의 프로세스가 실행 상태로 바뀌는 것을 CPU 디스패치라 합니다.

실행 중이던 프로세스는 준비 또는 봉쇄 상태로 변경됩니다.

## 준비 큐, 장치 큐, 작업 큐

### 준비 큐 (ready queue)

운영체제는 준비 상태의 프로세스들을 준비 큐로 관리합니다. 큐에 들어가는 순서는 CPU 스케줄링 방법에 따라 달라집니다.

### 장치 큐 (device queue)

준비 큐 외에도 특정 자원을 기다리는 프로세스를 위해 장치 큐를 사용합니다. 예를 들어 디스크에 입출력 서비스를 요청한 프로세스들은 디스크 입출력 큐(disk I/O queue)에서 관리됩니다. 입출력 작업이 완료되면 디스크 컨트롤러가 CPU에 인터럽트를 발생시키고 운영체제는 입출력 큐의 프로세스를 준비 큐로 옮깁니다.

### 작업 큐 (job queue)

작업 큐는 시스템 내의 모든 프로세스를 관리하기 위한 큐입니다. 프로세스 상태와 무관하게 모든 프로세스가 작업 큐게 속하게 됩니다. 따라서 작업 큐에 속한 프로세스는 메모리를 가지고 있지 않을 수 있습니다.

작업 큐가 가장 넓은 개념이며, 준비 큐와 장치 큐에 속한 프로세스들은 모두 작업 큐에 속합니다.

## 프로세스의 생성

운영체제가 시작되면 최초의 프로세스를 생성합니다. 이후의 프로세스들은 기존의 프로세스를 복제하여 생성됩니다. 이때 프로세스를 생성한 프로세스르 부모 프로세스라 하고, 생성된 프로세스를 자식 프로세스라 합니다.

생성된 프로세스가 작업을 수행하기 위해서는 자원이 필요한데, 자원을 획득하는 방법은 운영체제에 따라 다릅니다. 운영체제로부터 직접 자원을 할당받거나, 부모 프로세스와 자원을 공유할 수도 있습니다.

프로세스가 생성되면 고유한 주소 공간을 가지게 되는데, 처음 주소 공간을 생성할 때 부모 프로세스의 주소 공간을 복제해서 생성합니다. 즉, 프로그램 카운터, 레지스터 상태, PCB 및 커널스택 등 모든 프로세스 문맥을 복제하여 자식 프로세스 문맥을 생성합니다. 따라서 자식 프로세스는 부모 프로세스의 처음 명령부터 수행하는 것이 아니라 현재 시점(프로그램 카운터 지점)부터 명령을 수행하게 됩니다.

## IPC

프로세스는 독립적인 주소 공간을 가지며, 다른 프로세스의 주소 공간을 참조하는 것이 허용되지 않습니다. 따라서 운영체제는 프로세스 간의 협력 수단을 제공하여 프로세스가 다른 프로세스의 수행에 영향을 미칠 수 있도록 합니다.

대표적인 협력 수단으로 IPC(Inter Process Communication)가 있습니다. IPC는 하나의 컴퓨터에서 실행 중인 서로 다른 프로세스 간에 발생하는 통신을 의미합니다.

이러한 통신에서는 의사소통 기능과 함께 동기화를 보장해줄 필요가 있습니다. 공유 데이터를 서로 다른 두 프로세스가 사용할 때 데이터의 무결성 문제가 발생할 수 있기 때문입니다. 그러므로 한 프로세스가 공유 데이터의 값을 변경하는 동안 다른 프로세스는 공유 데이터에 접근할 수 없게 해야 합니다.

IPC는 이러한 동기화를 보장하면서 `메시지 전달(message passing)` 방식와 `공유메모리(shared memory)` 방식을 제공합니다. 이 두 방식의 차이는 `프로세스 사이에 공유 데이터를 사용하는가`입니다.

### 메시지 전달

프로세스 간에 공유 데이터를 사용하지 않고 메시지를 주고 받으면서 통신하는 방식입니다. 이때 두 프로세스의 주소 공간이 다르므로 메시지 전달을 직접 할 수는 없으며, 커널이 대신 전달합니다. 구현 방식으로는 상대방 프로세스의 이름을 명시적으로 표시하여 전달하는 직접통신 방식과 메시지를 메일박스 또는 포트로 전달하는 간접통신 방식이 있습니다.

### 공유메모리

프로세스들이 주소 공간의 일부를 공유하는 방식입니다. 원칙적으로 각 프로세스는 독립적인 주소 공간을 가지지만, 운영체제는 공유메모리를 사용하는 시스템 콜을 지원해, 서로 다른 프로세스들이 그들의 주소 공간 중 일부를 공유할 수 있도록 합니다. 이러한 공유메모리 영역은 각자의 주소 공간에 데이터를 읽고 쓰는 것이 가능합니다.

[참고(운영체제와 정보기술의 원리)](http://www.yes24.com/Product/Goods/90124877)