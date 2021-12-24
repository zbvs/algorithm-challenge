/*
구두 수선공
1
구두 수선공인 구름이는 N개의 일거리를 가지고 있습니다.

일이 서투른 구름이는 하루에 하나의 작업만을 수행할 수 있으며, 아직 수행되지 않은 작업은 작업을 시작하기 전까지 지연된 기간 만큼 하루 당 벌금을 내게 됩니다.

주어진 N개의 일거리에 대해서 구름이가 최소한의 벌금을 낼 수 있도록 하는 일의 순서를 출력하는 프로그랭을 작성하십시오.


입력
첫 줄에 일거리 수 N
다음 N줄에 일거리 마다 일에 걸리는 시간, 하루 지연당 벌금이 공백으로 구분되어 입력
출력
벌금이 최소화되는 일의 순서( 먼저 처리해야 하는 일의 순서 ) 출력
*/

from functools import cmp_to_key

n = int(input())

jobs = []
for i in range(0, n):
	line = input().split()
	jobs.append( (int(line[0]), int(line[1]), i) )

# (x1-1)*y2 -  (x1+x2-1)y1
# -> x1*y2 - x2*y1

# transitive :  아직 A->B, B->C 이면 A->C 이다 를 증명 못함 
def compare(a, b):
	return a[0]*b[1] - b[0]*a[1]
jobs = sorted(jobs, key=cmp_to_key(compare))
print(" ".join(list(map(lambda x: str(x[2]+1), jobs))))
