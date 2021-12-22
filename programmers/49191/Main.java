package com.algorithm.programmers;


/*
그래프 : 순위
n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다. 권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다. 심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다. 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.

선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.
 */

class Solution {
    public int solution(int n, int[][] results) {

        boolean[][] map = new boolean[n][n];
        for (int[] edge : results) {
            map[edge[0] - 1][edge[1] - 1] = true;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][k] && map[k][j]) map[i][j] = true;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] || map[j][i]) cnt++;
            }
            if (cnt == n - 1) answer++;
        }


        return answer;
    }
}


public class Main {
    public static void main(String[] args) {
        int[][] a = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int result = new Solution().solution(5, a);
        System.out.println(result);
    }
}
