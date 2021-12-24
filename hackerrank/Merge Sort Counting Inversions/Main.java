package com.algorithm.programmers;

/*
[Hackerrank] SortingMerge Sort: Counting Inversions

Probleam
In an array, arr, the elements at indices i and j(where i<j) form an inversion if arr[i] > arr[j]. In other words, inverted elements arr[i] and arr[j] are considered to be “out of order”. To correct an inversion, we can swap adjacent elements.

For example, consider the dataset arr = [2,4,1]. It has two inversions: (4, 1) and (2, 1). To sort the array, we must perform the following two swaps to correct the inversions:


           swap(arr[1],arr[2]) -> swap(arr[0],arr[1])
arr = [2,4,1] ---------------------------------> [1,2,4]
Given d datasets, print the number of inversions that must be swapped to sort each dataset on a new line.
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
import static java.lang.String.format;

class Result {

    /*
     * Complete the 'countInversions' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    private static int[] buffer;
    public static long merge(int[] arr, int start, int end, int mid){
        int fst = start;
        int snd = mid;
        long cnt = 0;
        while (fst != mid && snd != end) {
            int bufIdx = (fst - start) + (snd-mid);
            if (arr[fst] > arr[snd]) {
                buffer[bufIdx] = arr[snd++];
                cnt += mid - fst;
            } else {
                buffer[bufIdx] = arr[fst++];
            }
        }
        {
            int bufIdx = (fst - start) + (snd-mid);
            int arrIdx;
            int endCnt;
            if (fst != mid) {
                arrIdx = fst;
                endCnt = mid - fst;
            }
            else {
                arrIdx = snd;
                endCnt = end - snd;
            }
            for (int i=0; i<endCnt; i++) {
                buffer[bufIdx+i] = arr[arrIdx + i];
            }
        }

        for (int i=0; i<(end-start);i++) {
            arr[start + i] = buffer[i];
        }
        return cnt;
    }

    public static long mergeSort(int[] arr, int start, int end, int mid){
        long cnt = 0;
        int size = end - start;
        if (size == 1)
            return 0;


        cnt += mergeSort(arr, start, mid, (start+mid)/2);
        cnt += mergeSort(arr, mid, end, (mid+end)/2);
        cnt += merge(arr, start, end, mid);
        return cnt;
    }

    public static long countInversions(List<Integer> data) {
        // Write your code here
        int[] arr = new int[data.size()];
        buffer = new int[data.size()];
        for (int i=0;i<data.size(); i++) {
            arr[i] = data.get(i);
        }
        return mergeSort(arr, 0, arr.length, arr.length/2);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                long result = Result.countInversions(arr);

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
