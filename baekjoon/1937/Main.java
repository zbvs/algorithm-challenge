package com.algorithm.programmers;


/*
DP : 욕심쟁이 판다
문제
n × n의 크기의 대나무 숲이 있다. 욕심쟁이 판다는 어떤 지역에서 대나무를 먹기 시작한다. 그리고 그 곳의 대나무를 다 먹어 치우면 상, 하, 좌, 우 중 한 곳으로 이동을 한다. 그리고 또 그곳에서 대나무를 먹는다. 그런데 단 조건이 있다. 이 판다는 매우 욕심이 많아서 대나무를 먹고 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다.

이 판다의 사육사는 이런 판다를 대나무 숲에 풀어 놓아야 하는데, 어떤 지점에 처음에 풀어 놓아야 하고, 어떤 곳으로 이동을 시켜야 판다가 최대한 많은 칸을 방문할 수 있는지 고민에 빠져 있다. 우리의 임무는 이 사육사를 도와주는 것이다. n × n 크기의 대나무 숲이 주어져 있을 때, 이 판다가 최대한 많은 칸을 이동하려면 어떤 경로를 통하여 움직여야 하는지 구하여라.
 */
import java.io.*;
import java.util.*;


public class Main {
    public static String inputs = "4\n" +
            "14 9 12 10\n" +
            "1 11 5 4\n" +
            "7 15 2 13\n" +
            "6 3 16 8";

    public static class Point implements Comparable<Point> {
        int x;
        int y;
        int weight;
        public Point(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point o) {
            return o.weight - this.weight;
        }

    }

    public static void main(String[] args) throws IOException {
//        InputStream targetStream = new ByteArrayInputStream(inputs.getBytes());
//        BufferedReader br = new BufferedReader(new InputStreamReader(targetStream));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        PriorityQueue<Point> queue = new PriorityQueue<>();

        int[][] dp = new int[size][size];
        Point[][] pointMap = new Point[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                Point point = new Point(j, i, Integer.parseInt(st.nextToken()));
                queue.add(point);
                pointMap[i][j] = point;
            }
        }
        int max = 0;
        for (int i=1; i<size*size +1;i++) {
            Point point = queue.remove();
            int x = point.x;
            int y = point.y;
            int distance = 1;
            if (x != size - 1 && pointMap[y][x+1].weight > point.weight)
                distance = Math.max(dp[y][x+1] + 1 ,distance);

            if (x != 0 && pointMap[y][x-1].weight > point.weight)
                distance = Math.max(dp[y][x-1] +1,distance);

            if (y != size - 1 && pointMap[y+1][x].weight > point.weight)
                distance = Math.max(dp[y+1][x] +1,distance);

            if (y != 0 && pointMap[y-1][x].weight > point.weight)
                distance = Math.max(dp[y-1][x] +1,distance);
            dp[y][x] = distance;
            max = Math.max(max, distance);
        }
        System.out.println(max);
    }
}