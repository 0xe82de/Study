#include <stdio.h>

#define MAX 500

void Fibonacci1(int f, int s, int max) {
	printf("%d ", f);

	if (s > max) {
		return;
	}
	Fibonacci1(s, f + s, max);
}

int Fibonacci2(int num) {
	if (num == 1) {
		return 0;
	}
	else if (num == 2) {
		return 1;
	}
	else {
		return Fibonacci2(num - 1) + Fibonacci2(num - 2);
	}
}

int main(void) {
	int first = 0;
	int second = 1;
	int max = MAX;

	printf("Fibonacci sequence(Maximum: %d): ", max);
	Fibonacci1(first, second, max);

	printf("\n\n");
	for (int i = 1; i < 10; i++) {
		printf("value of Fibonacci sequence(Number %d): %d\n", i, Fibonacci2(i));
	}

	return 0;
}