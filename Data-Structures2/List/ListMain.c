//#include <stdio.h>
//#include "ArrayList.h"
//
//int main(void) {
//	List list;
//	LData data;
//	int sum = 0;
//	int i = 0;
//
//	ListInit(&list);
//
//	for (i = 1; i < 10; i++) {
//		LInsert(&list, i);
//	}
//
//	if (LFirst(&list, &data)) {
//		sum += data;
//
//		while (LNext(&list, &data)) {
//			sum +=data;
//		}
//	}
//
//	printf("Sum of List's Data: %d\n", sum);
//
//	if (LFirst(&list, &data)) {
//		if (data % 2 == 0 || data % 3 == 0) {
//			LRemove(&list);
//		}
//
//		while (LNext(&list, &data)) {
//			if (data % 2 == 0 || data % 3 == 0) {
//				LRemove(&list);
//			}
//		}
//	}
//
//	printf("List's Data: ");
//	if (LFirst(&list, &data)) {
//		printf("%d ", data);
//
//		while (LNext(&list, &data)) {
//			printf("%d ", data);
//		}
//	}
//
//	return 0;
//}