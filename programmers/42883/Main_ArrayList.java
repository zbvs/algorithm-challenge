package com.company;

/*
큰 수 만들기
문제 설명
어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.

예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.

문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.

제한 조건
number는 1자리 이상, 1,000,000자리 이하인 숫자입니다.
k는 1 이상 number의 자릿수 미만인 자연수입니다.
 */


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    class Pair implements Comparable<Pair> {
        public int index;
        public int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
        @Override
        public int compareTo(Pair o) {
            return o.value == this.value ? this.index - o.index : o.value - this.value;
        }
    }

    public String solution(String number, int k) {
        int remained = k;
        List<Pair> pq = new ArrayList<>();
        List<Pair> result = new ArrayList<>();

        int pos = 0;

        int end1 = 0;
        int end2 = 0;
        while (remained != 0 && (pos + remained) != number.length()) {
            long timeStart1 = System.currentTimeMillis();
            ////////////////////////////////////////////////


            for (int i = pos + pq.size() ; i < Math.min(pos + remained + 1, number.length()); i++) {
                Pair pair = new Pair(i, number.charAt(i) - 0x30);
                int idx = Collections.binarySearch(pq, pair);
                if (idx < 0) idx = -(idx+1);
                pq.add(idx, pair);
                if (pair.value == 9)
                    break;
            }
            Pair pair = pq.remove(0);
            result.add(pair);
            remained -= (pair.index - pos);


            ////////////////////////////////////////////////
            end1 += System.currentTimeMillis() -timeStart1;
            long timeStart2 = System.currentTimeMillis();
            ////////////////////////////////////////////////


            pq = pq.stream().filter(p-> p.index > pair.index).
                    collect(Collectors.toList());
            pos = pair.index + 1;


            ////////////////////////////////////////////////
            end2 += System.currentTimeMillis() -timeStart2;
        }
        System.out.println("end1:" + end1);
        System.out.println("end2:" + end2);
        number = number.substring(pos, number.length() - remained);
        return result.stream().map(p->String.valueOf(p.value)).collect(Collectors.joining()) + number;
    }
}

public class Main {
    public static void main(String[] args) {
        String result2 = new Solution().solution("12322", 4);
        //22214
        String c = "98765123450";
        String sss = "";
        for (int i=0;i<100000/10;i++)
            sss = sss + c;
        System.out.println(sss.length());
        Random random = new Random();


        String result = new Solution().solution(sss, 100000);
        //System.out.println(List.of(result));
    }
}
