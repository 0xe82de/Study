#include <stdio.h>
#include <stdlib.h>

typedef struct _node {
	int data;
	struct _node* next;
} Node;

int main(void) {
	Node* head = NULL;
	Node* tail = NULL;
	Node* cur = NULL;
	Node* dummy = (Node*)malloc(sizeof(Node));

	Node* newNode = NULL;
	int readData;

	// �����͸� �Է� �޴� ����
	while (1) {
		printf("�ڿ��� �Է�: ");
		scanf_s("%d", &readData);
		if (readData < 1) {
			break;
		}

		// ����� �߰�����
		newNode = (Node*)malloc(sizeof(Node));
		newNode->data = readData;
		newNode->next = NULL;

		if (head == NULL) { // 1
		//if (tail == NULL) { // 2
			//head = newNode; 1
			//tail = newNode; // 2

			// dummy
			dummy->next = newNode;
			head = dummy;
		}
		else {
			tail->next = newNode; // 1
			//newNode->next = head; // 2
		}

		tail = newNode; // 1
		//head = newNode; // 2
	}
	printf("\n");

	// �Է� ���� �������� ��°���
	printf("�Է� ���� �������� ��ü���!\n");
	if (head->next == NULL) {
		printf("����� �ڿ����� �������� �ʽ��ϴ�.\n");
	}
	else {
		// no dummy
		//cur = head;
		
		// dummy
		cur = head->next;
		printf("%d ", cur->data);

		while (cur->next != NULL) {
			cur = cur->next;
			printf("%d ", cur->data);
		}
	}
	printf("\n\n");

	// �޸��� ��������
	if (head->next == NULL) {
		return 0;
	}
	else {
		// no dummy
		//Node* delNode = head;
		//Node* delNextNode = head->next;

		// dummy
		Node* delNode = head->next;
		Node* delNextNode = head->next->next;

		printf("%d��(��) �����մϴ�.\n", delNode->data);
		free(delNode);

		while (delNextNode != NULL) {
			delNode = delNextNode;
			delNextNode = delNextNode->next;

			printf("%d��(��) �����մϴ�.\n", delNode->data);
			free(delNode);
		}
	}


	return 0;
}