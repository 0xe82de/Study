//#include <stdio.h>
//#include <stdlib.H>
//#include "NameCard.h"
//#include "ArrayList.h"
//
//int main(void) {
//	List list;
//	NameCard* pcard;
//	ListInit(&list);
//
//	pcard = MakeNameCard("kim", "010-1234-5678");
//	LInsert(&list, pcard);
//
//	pcard = MakeNameCard("lee", "010-5678-1234");
//	LInsert(&list, pcard);
//
//	pcard = MakeNameCard("park", "010-8765-4321");
//	LInsert(&list, pcard);
//
//	if (LFirst(&list, &pcard)) {
//		if (!NameCompare(pcard, "kim")) {
//			ShowNameCardInfo(pcard);
//		}
//
//		while (LNext(&list, &pcard)) {
//			if (!NameCompare(pcard, "kim")) {
//				ShowNameCardInfo(pcard);
//			}
//		}
//	}
//
//	if (LFirst(&list, &pcard)) {
//		if (!NameCompare(pcard, "lee")) {
//			ChangePhoneNum(pcard, "010-1234-5678");
//		}
//
//		while (LNext(&list, &pcard)) {
//			if (!NameCompare(pcard, "lee")) {
//				ChangePhoneNum(pcard, "010-1234-5678");
//			}
//		}
//	}
//
//	if (LFirst(&list, &pcard)) {
//		if (!NameCompare(pcard, "park")) {
//			pcard = LRemove(&list);
//			free(pcard);
//		}
//
//		while (LNext(&list, &pcard)) {
//			if (!NameCompare(pcard, "park")) {
//				pcard = LRemove(&list);
//				free(pcard);
//			}
//		}
//	}
//
//	printf("\n");
//	printf("현재 데이터의 수: %d\n", LCount(&list));
//
//	if (LFirst(&list, &pcard)) {
//		ShowNameCardInfo(pcard);
//
//		while (LNext(&list, &pcard)) {
//			ShowNameCardInfo(pcard);
//		}
//	}
//
//
//	return 0;
//}