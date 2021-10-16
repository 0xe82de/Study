/*
HanoiTower Algorithm

> ��� 3��
 - �����: A
 - ������: B
 - ������: C

> Disc ������ Ȧ���� ��� ó�� ������ ���� ���� Disc�� C�� �Űܾ� �Ѵ�. �ݴ��� ��� B�� �Űܾ� �Ѵ�.
 - ������ Disc�� ��� ������ A���� C�� �� 1�� �̵�.
 - Disc ����: 5��
  => Disc 1: C
  => Disc 2: B
  => Disc 3: C
  => Disc 4: B
  => Disc 5: C
 - Disc ����: 4��
  => Disc 1: B
  => Disc 2: C
  => Disc 3: B
  => Disc 4: C

> ���̵��
 - �����ϰ� �����ϸ� ������ Disc�� C�� �ű���� ������ Disc�� �����ϰ� ��� Disc�� B�� �־�� �Ѵ�.
 - ����, ������ Disc�� �ű�� ���� ��� Disc�� A���� B�� �Űܾ� �Ѵ�. => HanoiTower(num - 1, from, to, by)
 - ������ Disc�� C�� �ű�� ���� ������ Disc�� B�� �����ִ� �����̴�.
 - ����, ������ Disc���� B���� C�� �Űܾ� �Ѵ�. => HanoiTower(num - 1, by, from, to)
*/

#include <stdio.h>

void HanoiTower(int num, char from, char by, char to) {
	if (num == 1) {
		printf("Move disc %d from %c to %c\n", num, from, to);
	}
	else {
 		HanoiTower(num - 1, from, to, by);
		printf("Move disc %d from %c to %c\n", num, from, to);
		HanoiTower(num - 1, by, from, to);
	}
}

int main(void) {
	int numOfDisc = 5;

	HanoiTower(numOfDisc, 'A', 'B', 'C');
	
	return 0;
}