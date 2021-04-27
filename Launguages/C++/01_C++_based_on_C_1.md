# 01 C++ based on C 1

## 01-1 입출력

### 데이터 출력: std::cout

```c++
#include <iostream>

int main(void) {
  int num = 20;

  std::cout << "Hello World!" << std::endl;
  std::cout << "Hello " << "world!" << std::endl;
  std::cout << num << ' ' << 'A';
  std::cout << ' ' << 3.14 << std::endl;

  return 0;
}
```

### 데이터 입력: std::cin

```c++
#include <iostream>

int main(void) {
  int val1;
  std::cout << "첫 번째 숫자 입력: ";
  std::cin >> val1;

  int val2;
  std::cout << "두 번째 숫자 입력: ";
  std::cin >> val2;

  int result = val1 + val2;
  std::cout << "덧셈결과 " << result << std::endl;

  return 0;
}
```

### 지역변수 선언

```c++
#include <iostream>

int main(void) {
	int val1, val2;
	int result = 0;

	std::cout << "두 개의 숫자 입력: ";
	std::cin >> val1 >> val2;

	if (val1 < val2) {
		for (int i = val1 + 1; i < val2; i++) {
			result += i;
		}
	}
	else {
		for (int i = val2 + 1; i < val1; i++) {
			result += i;
		}
	}

	std::cout << "두 수 사이의 정수 합: " << result << std::endl;

	return 0;
}
```

### 문자열 입출력

```c++
#include <iostream>

int main(void) {
	char name[100];
	char lang[200];

	std::cout << "이름은 무엇입니까? ";
	std::cin >> name;

	std::cout << "좋아하는 프로그래밍 언어는 무엇인가요? ";
	std::cin >> lang;

	std::cout << "내 이름은 " << name << "입니다.\n";
	std::cout << "제일 좋아하는 언어는 " << lang << "입니다." << std::endl;

	return 0;
}
```

### 문제 01-1-1

- 5개 정수를 입력받고 합계 출력하기.

<br>

- answer

```c++
#include <iostream>

int main(void) {
	int val[5];
	int sum = 0;

	for (int i = 0; i < sizeof(val) / sizeof(int); i++) {
		std::cout << i + 1 << "번째 정수 입력: ";
		std::cin >> val[i];
		sum += val[i];
	}

	std::cout << "합계: " << sum << std::endl;

	return 0;
}
```

### 문제 01-1-2

- 이름과 전화번호를 입력받고 그대로 출력하기.

<br>

- solve

```c++
#include <iostream>

int main(void) {
	char name[20], phoneNumber[20];

	std::cout << "이름 입력: ";
	std::cin >> name;

	std::cout << "전화번호 입력: ";
	std::cin >> phoneNumber;

	std::cout << "이름: " << name << std::endl;
	std::cout << "전화번호: " << phoneNumber << std::endl;

	return 0;
}
```

### 문제 01-1-3

- 숫자를 하나 입력받고 그 숫자의 구구단을 출력하기.

<br>

- solve

```c++
#include <iostream>

int main(void) {
	int num;

	std::cout << "구구단을 출력할 숫자 입력: ";
	std::cin >> num;

	for (int i = 0; i < 9; i++) {
		std::cout << num << " x " << i + 1 << " = " << num * (i + 1) << std::endl;
	}

	return 0;
}
```

### 문제 01-1-4

- 아래 내용에 맞게 급여 계산하기.
- 매달 50만원의 기본 급여와 물품 판매 가격의 12%에 해당하는 돈을 지급한다.
- 예를 들어 이번 달 물품 판매 가격이 100만원이라면, 50 + 100 \* 0.12 = 62이므로 62만원을 지급한다.

<br>

- solve

```c++
#include <iostream>

int main(void) {
	int pay;

	while (1) {
		std::cout << "판매 금액을 만원 단위로 입력(-1 to end): ";
		std::cin >> pay;

		if (pay == -1) {
			std::cout << "프로그램을 종료합니다." << std::endl;
			break;
		}

		std::cout << "이번 달 급여: " << 50 + (pay * 0.12) << "만원" << std::endl;
	}

	return 0;
}
```

## 01-2 함수 오버로딩

### 함수 오버로딩의 이해

- C언어는 함수 이름만으로 함수를 찾는다.
- C++은 함수의 이름과 매개변수의 선언 정보 2가지로 함수를 찾는다.
- 함수 오버로딩의 조건: 매개변수의 자료형 또는 개수가 달라야 한다.

```c++
#include <iostream>

void MyFunc(void) {
	std::cout << "MyFunc(void) called" << std::endl;
}

void MyFunc(char c) {
	std::cout << "MyFunc(char c) called" << std::endl;
}

void MyFunc(int a, int b) {
	std::cout << "MyFunc(int a, int b) called" << std::endl;
}


int main(void) {
	MyFunc();

	MyFunc('A');

	MyFunc(12, 13);

	return 0;
}
```

### 문제 01-2

- 다음 main 함수에서 필요로 하는 swap 함수를 오버로딩 해서 구현하기.

```c++
int main(void) {
	int num1 = 20, num2 = 30;
	swap(&num1, &num2);
	std::cout << num1 << ' ' << num2 << std::endl;

	char ch1 = 'A', ch2 = 'Z';
	swap(&ch1, &ch2);
	std::cout << ch1 << ' ' << ch2 << std::endl;

	double dbl1 = 1.111, dbl2 = 5.555;
	swap(&dbl1, &dbl2);
	std::cout << dbl1 << ' ' << dbl2 << std::endl;

	return 0;
}
```

<br>

- solve

```c++
void swap(int* n1, int* n2) {
	int temp = *n1;
	*n1 = *n2;
	*n2 = temp;

	return;
}

void swap(char* c1, char* c2) {
	char temp = *c1;
	*c1 = *c2;
	*c2 = temp;

	return;
}

void swap(double* d1, double* d2) {
	double temp = *d1;
	*d1 = *d2;
	*d2 = temp;

	return;
}
```

## 01-3 매개변수의 디폴트 값

### 디폴트 값의 의미

- 매개변수에 디폴트 값이 설정되어 있으면, 선언된 매개변수의 수보다 적은 수의 인자 전달이 가능하다.
- 전달되는 인자는 왼쪽에서부터 전달되고, 부족한 인자는 디폴트 값으로 설정된다.

```c++
#include <iostream>

int Adder(int num1 = 1, int num2 = 2) {
	return num1 + num2;
}

int main(void) {
	// 인자를 전달하지 않았으니, 디폴트 값인 1과 2가 전달된 것으로 간주한다.
	std::cout << Adder() << std::endl;

	// 첫 번째 인자만 전달하였으니, 두 번째 인자로 디폴트 값인 2가 전달된 것으로 간주한다.
	std::cout << Adder(5) << std::endl;

	// 두 개의 인자가 전달되었으므로 디폴트 값을 무시한다.
	std::cout << Adder(3, 5) << std::endl;

	return 0;
}
```

### 디폴트 값 선언

- 함수의 원형을 별도로 선언하는 경우, 매개변수의 디폴트 값은 함수의 원형 선언에만 위치시키면 된다.

```c++
#include <iostream>

int Adder(int num1 = 1, int num2 = 2);

int main(void) {
	std::cout << Adder() << std::endl;
	std::cout << Adder(5) << std::endl;
	std::cout << Adder(3, 5) << std::endl;

	return 0;
}

int Adder(int num1, int num2) {
	return num1 + num2;
}

```

### 부분적 디폴트 값 설정

- 함수에서 선언하는 매개변수의 디폴트 값은 오른쪽부터 채울 수 있다.

```c++
// 올바른 코드
int RightFunc(int num1, int num2, int nu3 = 30);
int RightFunc(int num1, int num2 = 20, int nu3 = 30);
int RightFunc(int num1 = 10, int num2 = 20, int nu3 = 30);

// 잘못된 코드
int WrongFunc(int num1 = 10, int num2, int nu3);
int WrongFunc(int num1 = 10, int num2 = 20, int nu3);

// 아래와 같이 호출하면 RightFunc(10, 20, 30); 호출과 같다.
RightFunc(10, 20);
```

```c++
#include <iostream>

int BoxVolume(int length, int width = 1, int height = 1);

int main(void) {
	std::cout << "[3, 3, 3] : " << BoxVolume(3, 3, 3) << std::endl;
	std::cout << "[5, 5, D] : " << BoxVolume(5, 5) << std::endl;
	std::cout << "[7, D, D] : " << BoxVolume(7) << std::endl;

	// 아래처럼 호출 시 에러가 발생한다. BoxVolume 함수에서 선언된 가장 왼쪽 매개변수는 디폴트 값이 없기 때문이다.
	//std::cout << "[D, D, D] : " << BoxVolume() << std::endl;

	return 0;
}

int BoxVolume(int length, int width, int height) {
	return length * width * height;
}
```

### 문제 01-3-1

- 아래 예제에 정의된 함수 BoxVolume를 '매개변수의 디폴트 값 지정' 형태가 아닌, '함수 오버로딩'의 형태로 재 구현하기.
- main 함수는 변경하지 않아야 하며, 실행결과도 동일해야 한다.

```c++
#include <iostream>

int BoxVolume(int length, int width = 1, int height = 1);

int main(void) {
	std::cout << "[3, 3, 3] : " << BoxVolume(3, 3, 3) << std::endl;
	std::cout << "[5, 5, D] : " << BoxVolume(5, 5) << std::endl;
	std::cout << "[7, D, D] : " << BoxVolume(7) << std::endl;

	return 0;
}

int BoxVolume(int length, int width, int height) {
	return length * width * height;
}
```

<br>

- solve

```c++
#include <iostream>

int BoxVolume(int length) {
	return length * 1 * 1;
}

int BoxVolume(int length, int width) {
	return length * width * 1;
}

int BoxVolume(int length, int width, int height) {
	return length * width * height;
}

int main(void) {
	std::cout << "[3, 3, 3] : " << BoxVolume(3, 3, 3) << std::endl;
	std::cout << "[5, 5, D] : " << BoxVolume(5, 5) << std::endl;
	std::cout << "[7, D, D] : " << BoxVolume(7) << std::endl;

	return 0;
}
```

### 문제 01-3-2

- 다음과 같은 형태의 함수 오버로딩은 문제가 있다. 어떤 문제가 있는가?

```c++
int SimpleFunc(int a = 10) {
	return a + 1;
}

int SimpleFunc(void) {
	return 10;
}
```

<vr>

- solve

1. 아랫 부분의 함수는 매개변수가 선언되지 않았기 때문에 인자를 전달하지 않고 호출할 수 있다.
2. 그런데, 윗 부분의 함수의 매개변수가 디폴트 값이 설정되었기 때문에 이 함수도 인자를 전달하지 않고 호출할 수 있다.
3. 따라서, 두 함수 모두 인자를 전달하지 않고 호출할 수 있기 때문에 컴파일 에러가 발생할 것이다.
4. (추가) 함수 오버로딩의 조건을 만족(함수의 이름, 매개변수의 자료형과 개수가 달라야 한다.)하므로 인자를 전달하면 컴파일이 된다.
5. (추가) 인자를 전달하지 않으면 컴파일 에러가 발생한다.

## 01-4 인라인 함수

### 매크로 함수의 장점

- 장점: 일반적인 함수에 비해서 실행속도의 이점이 있다.
- 단점: 복잡한 함수를 매크로의 형태로 정의하기 어렵다.
- 매크로(#define)를 이용한 함수의 인라인화는 전처리기에 의해 처리되지만, 키워드 "inline"을 이용한 함수의 인라인화는 컴파일러에 의해서 처리가 된다.
- 따라서 컴파일러는 함수의 인라인화가 성능에 해가 된다고 판단하면 "inline" 키워드를 무시하기도 한다. 또한, 컴파일러는 일부 함수를 임의로 인라인화 하기도 한다.

```c++
#include <iostream>

inline int SQUARE(int x) {
	return x * x;
}


int main(void) {
	std::cout << SQUARE(1) << std::endl;
	std::cout << SQUARE(9) << std::endl;
	std::cout << SQUARE(9.1) << std::endl; // 81 출력.. 자료형에 의존..

	return 0;
}
```

- 위와 같이 "inline" 키워드로 정의된 인라인 함수는 매개변수로 전달되는 인자의 자료형에 의존하게 된다.
- 아래와 같이 템플릿을 이용하면 자료형에 의존하지 않고 인라인 함수를 사용할 수 있다.

```c++
#include <iostream>

template <typename A>
inline A SQUARE(A x) {
	return x * x;
}

int main(void) {
	std::cout << SQUARE(5.5) << std::endl;
	std::cout << SQUARE(12) << std::endl;

	return 0;
}
```

## 01-5 이름 공간(namespace)

### 원리

```c++
#include <iostream>

namespace BestComImpl {
	void SimpleFunc(void) {
		std::cout << "BestCom이 정의한 함수" << std::endl;
	}
}

namespace ProgComImpl {
	void SimpleFunc(void) {
		std::cout << "ProgCom이 정의한 함수" << std::endl;
	}
}

int main(void) {
	BestComImpl::SimpleFunc();
	ProgComImpl::SimpleFunc();

	return 0;
}
```

- 연산자 "::": 범위지정 연산자(scope resolution operator)라고 하며, 이름 공간을 지정할 때 사용하는 연산이다.

### 이름공간 기반의 함수 선언과 정의

```c++
#include <iostream>

namespace BestComImpl {
	void SimpleFunc(void);
	void PrettyFunc(void);
}

namespace ProgComImpl {
	void SimpleFunc(void);
}

int main(void) {
	BestComImpl::SimpleFunc();

	return 0;
}

void BestComImpl::SimpleFunc(void) {
	std::cout << "BestCom이 정의한 함수" << std::endl;
	PrettyFunc(); // BestComImpl 이름 공간
	ProgComImpl::SimpleFunc(); // ProgComImpl 이름 공간
}

void BestComImpl::PrettyFunc(void) {
	std::cout << "Pretty" << std::endl;
}

void ProgComImpl::SimpleFunc(void) {
	std::cout << "ProgCom이 정의한 함수" << std::endl;
}
```

### 이름 공간의 중첩

```c++
#include <iostream>

namespace P {
	int num = 1;

	namespace C1 {
		int num = 2;
	}

	namespace C2 {
		int num = 3;
	}
}

int main(void) {
	std::cout << P::num << std::endl;
	std::cout << P::C1::num << std::endl;
	std::cout << P::C2::num << std::endl;

	return 0;
}
```

### 문제 01-4

- 아래의 소스코드를 헤더파일 1개와 소스파일 2개로 분할하기.
- 헤더파일: main 함수를 제외한 두 함수를 선언한다.
- 소스파일1: main 함수를 삽입한다.
- 소스파일2: main 함수를 제외한 두 함수 정의한다.

```c++
#include <iostream>

namespace BestComImpl {
	void SimpleFunc(void);
	void PrettyFunc(void);
}

namespace ProgComImpl {
	void SimpleFunc(void);
}

int main(void) {
	BestComImpl::SimpleFunc();

	return 0;
}

void BestComImpl::SimpleFunc(void) {
	std::cout << "BestCom이 정의한 함수" << std::endl;
	PrettyFunc(); // BestComImpl 이름 공간
	ProgComImpl::SimpleFunc(); // ProgComImpl 이름 공간
}

void BestComImpl::PrettyFunc(void) {
	std::cout << "Pretty" << std::endl;
}

void ProgComImpl::SimpleFunc(void) {
	std::cout << "ProgCom이 정의한 함수" << std::endl;
}
```

<vr>

- solve

```c++
// header.h

#ifndef NAMESPACE
#define NAMEPACE

namespace BestComImpl {
	void SimpleFunc(void);
	void PrettyFunc(void);
}

namespace ProgComImpl {
	void SimpleFunc(void);
}

#endif
```

```c++
// main.cpp

#include "header.h"

int main(void) {
	BestComImpl::SimpleFunc();

	return 0;
}
```

```c++
// function.cpp

#include <iostream>
#include "header.h"

void BestComImpl::SimpleFunc(void) {
	std::cout << "BestCom이 정의한 함수" << std::endl;
	PrettyFunc(); // BestComImpl 이름 공간
	ProgComImpl::SimpleFunc(); // ProgComImpl 이름 공간
}

void BestComImpl::PrettyFunc(void) {
	std::cout << "Pretty" << std::endl;
}

void ProgComImpl::SimpleFunc(void) {
	std::cout << "ProgCom이 정의한 함수" << std::endl;
}
```

### using을 이용한 이름 공간의 명시

```c++
#include <iostream>

namespace Hybrid {
	void HybFunc(void) {
		std::cout << "So simple function!" << std::endl;
		std::cout << "In namespace Hybrid!" << std::endl;
	}
}

int main(void) {
	using Hybrid::HybFunc;

	HybFunc();

	return 0;
}
```

```c++
#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main(void) {
	int num = 20;

	cout << "Hello World!" << endl;
	cout << "Hello " << "World!" << endl;
	cout << num << ' ' << 'A';
	cout << ' ' << 3.14 << endl;

	return 0;
}
```

```c++
#include <iostream>

// 아래처럼 사용 시 충돌이 발생할 수 있다.
using namespace std;

int main(void) {
	int num = 20;

	cout << "Hello World!" << endl;
	cout << "Hello " << "World!" << endl;
	cout << num << ' ' << 'A';
	cout << ' ' << 3.14 << endl;

	return 0;
}
```

### 이름 공간의 별칭 지정

```c++
#include <iostream>

using std::cout;
using std::endl;

namespace AAA {
	namespace BBB {
		namespace CCC {
			int num1, num2;
		}
	}
}

int main(void) {
	namespace ABC = AAA::BBB::CCC;

	ABC::num1 = 20;
	ABC::num2 = 30;

	cout << ABC::num1 << endl;
	cout << ABC::num2 << endl;

	return 0;
}
```

### 범위지정 연산자(Scope Resolution Operator)의 추가 기능

```c++
#include <iostream>

using std::cout;
using std::endl;

int val = 200;

int main(void) {
	int val = 100;

	val++;
	::val++;

	cout << val << endl;
	cout << ::val << endl;

	return 0;
}
```

## 01-6 OOP 단계별 프로젝트 01단계

### 프로그램 설명

- 프로젝트 01단계에서는 C 스타일로 구현한다.
- 기능
  - 기능 1: 계좌개설
  - 기능 2: 입금
  - 기능 3: 출금
  - 기능 4: 전체고객 잔액조회
- 임의의 가정
  - 통장의 계좌번호는 중복되지 않는다(중복검사를 하지 않겠다는 의미이다.).
  - 입금 및 출금액은 무조건 0보다 크다(입금 및 출금액의 오류검사를 하지 않겠다는 의미이다.).
  - 고객의 계좌정보는 계좌번호, 고객이름, 고객의 잔액, 세가지만 저장하고 관리한다.
  - 둘 이상의 고객 정보 저장을 위해 배열을 사용한다.
  - 계좌번호는 정수의 형태이다.
