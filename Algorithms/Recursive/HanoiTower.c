/*
HanoiTower Algorithm

> 기둥 3개
 - 출발지: A
 - 경유지: B
 - 도착지: C

> Disc 개수가 홀수일 경우 처음 움직일 가장 작은 Disc는 C로 옮겨야 한다. 반대의 경우 B로 옮겨야 한다.
 - 마지막 Disc의 경우 무조건 A에서 C로 딱 1번 이동.
 - Disc 개수: 5개
  => Disc 1: C
  => Disc 2: B
  => Disc 3: C
  => Disc 4: B
  => Disc 5: C
 - Disc 개수: 4개
  => Disc 1: B
  => Disc 2: C
  => Disc 3: B
  => Disc 4: C

> 아이디어
 - 간단하게 생각하면 마지막 Disc를 C에 옮기려면 마지막 Disc를 제외하고 모든 Disc가 B에 있어야 한다.
 - 따라서, 마지막 Disc를 옮기기 전에 모든 Disc는 A에서 B로 옮겨야 한다. => HanoiTower(num - 1, from, to, by)
 - 마지막 Disc를 C에 옮기고 나면 나머지 Disc는 B에 꽂혀있는 상태이다.
 - 따라서, 나머지 Disc들을 B에서 C로 옮겨야 한다. => HanoiTower(num - 1, by, from, to)
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