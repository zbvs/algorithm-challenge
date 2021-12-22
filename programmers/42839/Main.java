package com.company;

/*
소수 찾기
문제 설명
한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

제한사항
numbers는 길이 1 이상 7 이하인 문자열입니다.
numbers는 0~9까지 숫자만으로 이루어져 있습니다.
"013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
 */


import java.util.*;

class Solution {
    public int solution(String numbers) {
        int ans = 0;
        Set<Integer> allNumbers = new HashSet<>();

        permutation("", numbers, allNumbers);
        for (int target : allNumbers)
            if (isPrime(target)) ans++;

        return ans;
    }

    public boolean isPrime(int n) {
        if (n == 0 || n == 1) return false;
        if (n % 2 == 0) return n == 2;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    void permutation(String prefix, String str, Set<Integer> allNumber) {
        if (!prefix.equals("")) allNumber.add(Integer.parseInt(prefix));
        for (int i=0; i<str.length(); i++) {
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1), allNumber);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        String v = "17";
        int result = new Solution().solution(v);
        System.out.println(List.of(result));
    }
}
