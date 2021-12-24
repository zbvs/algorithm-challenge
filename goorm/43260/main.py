# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean

/*
2개의 계란
*/

/*
1번: 1
2번: 1 (2) 3
3번: 1 (2,3) 4 (5) 6
4번: 1 (2,3,4) 5 (6,7) 8 (9) 10
...
*/
user_input = input()
high = int(user_input)

cnt = 1
mul = 1
total = 0
while True:
	total += cnt
	if total >= high:
		break
	cnt += 1
print(cnt)