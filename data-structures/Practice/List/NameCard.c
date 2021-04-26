#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "NameCard.h"

NameCard* MakeNameCard(char* name, char* phone) {
	NameCard* pcard;
	pcard = (NameCard*)malloc(sizeof(NameCard));
	
	strcpy_s(pcard->name, sizeof(pcard->name), name);
	strcpy_s(pcard->phone, sizeof(pcard->name), phone);
	
	return pcard;
}

void ShowNameCardInfo(NameCard* pcard) {
	printf("[%s, %s]\n", pcard->name, pcard->phone);
}

int NameCompare(NameCard* pcard, char* name) {
	return strcmp(pcard->name, name);
}

void ChangePhoneNum(NameCard* pcard, char* phone) {
	strcpy_s(pcard->phone, sizeof(pcard->phone), phone);
}