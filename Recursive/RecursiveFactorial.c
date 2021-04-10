#include <stdio.h>

int Factorial(int num) {
	if (num <= 1) {
		return num;
	}
	else {
		return num * Factorial(num - 1);
	}
}

int main(void) {
	for (int i = 1; i <= 10; i++) {
		printf("%d! = %d\n", i, Factorial(i));
	}

	return 0;
}