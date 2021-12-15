package com.company;

/*
미로 탐색
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	192 MB	99787	40370	25755	39.092%
문제
N×M크기의 배열로 표현되는 미로가 있다.

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 */
import java.io.*;
import java.util.*;


public class Main {
    public static class Point {
        public int x;
        public int y;
        public int step;

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long timeStart = System.currentTimeMillis();

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(String.valueOf(str.charAt(j)));
                if (value == 0)
                    arr[i][j] = value;
                else
                    arr[i][j] = -1;
            }
        }


        Queue<Point> queue = new LinkedList<>();

        int step = 1;
        arr[0][0] = step;
        queue.add(new Point(0, 0, step));

        int END_X = M - 1;
        int END_Y = N - 1;

        while (queue.size() != 0) {
            Point point = queue.remove();
            int x = point.x;
            int y = point.y;
            step = point.step;
            if (x == END_X && y == END_Y) {
                break;
            }

            if (x != END_X && arr[y][x + 1] == -1 ) {
                arr[y][x + 1] = step + 1;
                queue.add(new Point(x + 1, y, step + 1));
            }
            if (x != 0 && arr[y][x - 1] == -1) {
                arr[y][x - 1] = step + 1;
                queue.add(new Point(x - 1, y, step + 1));
            }
            if (y != END_Y && arr[y + 1][x] == -1 ) {
                arr[y + 1][x] = step + 1;
                queue.add(new Point(x, y + 1, step + 1));
            }
            if (y != 0 && arr[y - 1][x] == -1 ) {
                arr[y - 1][x] = step + 1;
                queue.add(new Point(x, y - 1, step + 1));
            }
        }



        System.out.println(step);

    }
}