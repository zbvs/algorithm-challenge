/*
[hackerrank] String Manipulation : Common Child
** LCS ( Longest Common Subsequence) Problem
A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the other string. Letters cannot be rearranged. Given two strings of equal length, what's the longest string that can be constructed such that it is a child of both?
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

class Result {

    /*
     * Complete the 'commonChild' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static int commonChild(String s1, String s2) {
        // Write your code here
        
        //strip s1, s2 common char?
        // ABKDCKBA , ABIBAICD
        // [optimize] K and I are not common char,  can remove
        // -> ABDCBA , ABBACD 
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int n1 = s1.length();
        int n2 = s2.length();
        int[][] map = new int[n1 + 1][n2 + 1];

        for (int i = 1; i < n1 + 1; i++) {
            for (int j = 1; j < n2 + 1; j++) {
                if (c1[i-1] == c2[j-1])
                    map[i][j] = map[i-1][j-1] + 1;
                else
                    map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
            }
        }
        return map[n1][n2];
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        int result = Result.commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
