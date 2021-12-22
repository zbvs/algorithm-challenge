package com.company;

/*
 */


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(String number, int k) {

        Stack<Character> stack = new Stack<>();
        for (int i=0;i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k > 0){
                k--;
                stack.pop();
            }
            stack.push(c);
        }

        String result = stack.stream().map(c->String.valueOf(c)).collect(Collectors.joining());
        return result.substring(0, result.length() - k);
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

