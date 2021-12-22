package com.algorithm;


import java.util.*;
/*
동적계획법 : 등굣길

계속되는 폭우로 일부 지역이 물에 잠겼습니다. 물에 잠기지 않은 지역을 통해 학교를 가려고 합니다. 집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다.

*/
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int mod = 1000000007;
        int[][] map = new int[n][m];
        map[0][0] = 1;
        Arrays.stream(puddles).forEach(arr -> map[arr[1] - 1][arr[0] - 1] = -1);

        for (int i = 0; i < m + n - 2; i++) {
            int startX = Math.min(i, m - 1);
            int startY = i > m - 1 ? i - (m - 1) : 0;
            int endY = Math.min(i, n - 1);
            for (int j = 0; j <= endY - startY; j++) {
                int x = startX - j;
                int y = startY + j;
                if (map[y][x] != -1) {
                    if (x != m-1 && map[y][x + 1] != -1)
                        map[y][x + 1] = (map[y][x + 1] + map[y][x]) % mod;
                    if (y != n-1 && map[y + 1][x] != -1)
                        map[y + 1][x] = (map[y + 1][x] + map[y][x]) % mod;
                }
            }
        }
        return map[n-1][m-1];
    }
}
public class Main {
    public static void main(String[] args) {
        int a[][] = {{2, 2}};
        System.out.println(0%2);
        int result = new Solution().solution(80, 80, a);
        System.out.println(result);
    }
}