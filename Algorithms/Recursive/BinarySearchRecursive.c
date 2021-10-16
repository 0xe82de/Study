#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define FALSE -1

int opCount;

float GetTime(clock_t start, clock_t finish) {
	return (float)(finish - start) / CLOCKS_PER_SEC;
}

int GetCenter(int first, int last) {
	return (first + last) / 2;
}

int BinarySearchRecursive(int array[], int first, int last, int target) {
	opCount++;

	if (first > last) {
		return FALSE;
	}

	if (target == array[GetCenter(first, last)]) {
		return GetCenter(first, last);
	}
	else if (target < array[GetCenter(first, last)]) {
		BinarySearchRecursive(array, first, GetCenter(first, last) - 1, target);
	}
	else {
		BinarySearchRecursive(array, GetCenter(first, last) + 1, last, target);
	}
}

int main(void) {
	int array[] = { 1, 3, 5, 7, 9 };
	int target[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int idx;
	clock_t start, finish;
	float time;

	for (int i = 0; i < (sizeof(target) / sizeof(int)); i++) {
		opCount = 0;

		start = clock();
		idx = BinarySearchRecursive(array, 0, sizeof(array) / sizeof(int) - 1, target[i]);
		finish = clock();
		time = GetTime(start, finish);

		if (idx == FALSE) {
			printf("1. Index of target %d: FAIL!!\n", target[i]);
		}
		else {
			printf("1. Index of target %d: %d\n", target[i], idx);
		}
		printf("2. Number of Operations: %d\n", opCount);
		printf("3. Run Time: %.5lf\n\n", time);
	}

	return 0;
}