package com.algorithm.programmers;

import java.util.*;


/*
DP :N으로 표현
문제 설명
아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
 */
class Solution {
    public int solution(int n, int number) {

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i < 9; i++) {
            Set<Integer> cur = new HashSet<>(Set.of(Integer.parseInt(String.valueOf(n).repeat(i))));

            for (int j = 1; j < i; j++) {
                Set<Integer> set1 = map.get(j);
                Set<Integer> set2 = map.get(i - j);
                for (int left : set1) {
                    for (int right : set2) {
                        cur.add(left * right);
                        if (right != 0) cur.add(left / right);
                        cur.add(left + right);
                        cur.add(left - right);
                    }
                }
            }
            map.put(i, cur);
            if (cur.contains(number))
                return i;
        }
        return -1;
    }
}

public class Main {

    public static void main(String[] args) {
        int N = 5;
        int number = 12;
        int result = new Solution().solution(N, 12);
        System.out.println(result);
    }
}
