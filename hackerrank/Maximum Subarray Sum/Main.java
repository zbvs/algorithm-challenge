/*
[Hackerrank] Search : Maximum Subarray Sum
We define the following:

A subarray of array a of length n is a contiguous segment from a[ i ] through a[ j ]  where 0  <= i <= j < n.
The sum of an array is the sum of its elements.

Given an n element array of integers, a, and an integer, m , determine the maximum value of the sum of any of its subarrays modulo m.
 */

package com.algorithm.programmers;
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

class Result {

    /*
     * Complete the 'maximumSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER_ARRAY a
     *  2. LONG_INTEGER m
     */
    public static long maximumSum(List<Long> a, long m) {
        long[] arr = new long[a.size()];
        long acc = 0;
        for (int i = 0; i < a.size(); i++) {
            acc = (acc + a.get(i)) % m;
            arr[i] = acc;
        }
        TreeSet<Long> set = new TreeSet<>();
        long max = 0;
        for (int i = 0; i < a.size(); i++) {
            max = Math.max(max, arr[i]);
            Long value = set.ceiling(arr[i]);
            if (value != null)
                max = Math.max(max, (arr[i] - value + m) % m);
            set.add(arr[i]);
        }
        return max;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                long m = Long.parseLong(firstMultipleInput[1]);

                List<Long> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Long::parseLong)
                        .collect(toList());

                long result = Result.maximumSum(a, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
