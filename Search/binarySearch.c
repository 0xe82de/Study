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

int BinarySearch(int array[], int len, int target) {
	int first = 0;
	int last = len - 1;
	opCount = 0;
	
	while (first <= last) {
		opCount += 1;

		if (target == array[GetCenter(first, last)]) {
			return GetCenter(first, last);
		}
		else if (target > array[GetCenter(first, last)]) {
			first = GetCenter(first, last) + 1;
		}
		else if (target < array[GetCenter(first, last)]) {
			last = GetCenter(first, last) - 1;
		}
	}

	return FALSE;
}

int main(void) {
	int array[999];
	int target[10] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 999 };
	int idx;
	clock_t start, finish;
	float time;

	for (int i = 0; i < (sizeof(array) / sizeof(int)); i++) {
		array[i] = 999;
	}

	for (int i = 0; i < (sizeof(target) / sizeof(int)); i++) {
		start = clock();
		idx = BinarySearch(array, sizeof(array) / sizeof(int), target[i]);
		finish = clock();
		time = GetTime(start, finish);

		printf("## Result of target %d ##\n", target[i]);
		if (idx == FALSE) {
			printf("FAIL! There are no target %d in the array.\n", target[i]);
		}
		else {
			printf("SUCCESS! Index of target %d is %d.\n", target[i], idx);
		}
		printf("Number of Operations: %d\n", opCount);
		printf("Run Time: %.5lf\n\n", time);
	}

	return 0;
}