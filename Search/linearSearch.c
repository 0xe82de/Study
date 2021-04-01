#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define FALSE -1

float GetTime(clock_t start, clock_t finish) {
	return (float)(finish - start) / CLOCKS_PER_SEC;
}

int LinearSearch(int array[], int len, int target) {
	int index;

	for (index = 0; index < len; index++) {
		printf("#");
		if (array[index] == target) {
			return index;
		}
	}

	return FALSE;
}

int main(void) {
	int array[100] = { 1, 2, 3, 4, 5, 0, };
	int target[10] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int idx;
	clock_t start, finish;
	float time;

	for (int i = 0; i < (sizeof(target) / sizeof(int)); i++) {
		start = clock();
		idx = LinearSearch(array, sizeof(array)/sizeof(int), target[i]);
		finish = clock();
		time = GetTime(start, finish);
	
		printf("\n< Result of target %d >\n", target[i]);
		if (idx == FALSE) {
			printf("There are no target %d in the array.\n", target[i]);
		}
		else {
			printf("Index of target %d is %d.\n", target[i], idx);
		}
		printf("Run Time: %.5lf\n\n", time);
	}

	return 0;
}