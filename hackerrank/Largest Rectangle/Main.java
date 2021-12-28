package com.algorithm.programmers;


/*
[Hackerrank] Stacks and Queues : Largest Rectangle

Skyline Real Estate Developers is planning to demolish a number of old, unoccupied buildings and construct a shopping mall in their place. Your task is to find the largest solid area in which the mall can be constructed.
There are a number of buildings in a certain two-dimensional landscape. Each building has a height, given by h[i] where 1≤i≤n. If you join k adjacent buildings, they will form a solid rectangle of area  k x min(h[i], h[i+1], ......, h[i+k-1]).
For example, the heights array h=[3,2,3]. A rectangle of height h=2 and length k=3 can be constructed within the boundaries. The area formed is h.k=2x3=6.
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.lang.Integer.MAX_VALUE;
class Result {

    /*
     * Complete the 'largestRectangle' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY h as parameter.
     */

    static class Pair {
        public long h;
        public int i;
        public Pair(int h, int i) {
            this.h = h;
            this.i = i;
        }
    }
    public static long largestRectangle(List<Integer> h) {
        // Write your code here
        long result = 0;
        int[] arr = h.stream().mapToInt(i->i).toArray();
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(arr[0], 0));
        for (int i=1;i<arr.length;i++) {
            if (arr[i-1] < arr[i])
                stack.push(new Pair(arr[i], i));
            else if (arr[i-1] > arr[i]) {
                int index = 0;
                while(stack.size() != 0 && stack.peek().h > arr[i]) {
                    Pair pair = stack.pop();
                    result = Math.max(result, pair.h * (i-pair.i));
                    index = pair.i;
                }
                if (stack.size() == 0 || stack.peek().h < arr[i]) {
                    stack.push(new Pair(arr[i], index));
                }
            }
        }
        while(stack.size() != 0) {
            Pair pair = stack.pop();
            result = Math.max(result, pair.h * (arr.length-pair.i));
        }
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {

        Result.largestRectangle(List.of(3, 2, 4, 9 , 7, 11));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

