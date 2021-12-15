package com.company;

/*
이중우선순위큐
문제 설명
이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.

명령어	수신 탑(높이)
I 숫자	큐에 주어진 숫자를 삽입합니다.
D 1	큐에서 최댓값을 삭제합니다.
D -1	큐에서 최솟값을 삭제합니다.
이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.

제한사항
operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
operations의 원소는 큐가 수행할 연산을 나타냅니다.
원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
 */


import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0 , 0};
        PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minq = new PriorityQueue<>();

        int size = 0;
        for (String op : operations) {
            if (op.startsWith("I ")) {
                maxq.add(Integer.parseInt(op.substring(2)));
                minq.add(Integer.parseInt(op.substring(2)));
                size++;
            } else {
                if (size == 0)
                    continue;
                if (op.startsWith("D 1"))
                    maxq.remove();
                else
                    minq.remove();
                size--;
                if (size==0) {
                    maxq.clear();
                    minq.clear();
                }
            }
        }
        if (size > 0)
            answer = new int[]{maxq.remove(), minq.remove()};
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        String[] cos = {"I 7","I 5","I -5","D -1"};
        int[] result = new Solution().solution(cos);
        System.out.println(List.of(result));
    }
}
