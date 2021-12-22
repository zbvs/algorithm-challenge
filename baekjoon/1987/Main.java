package com.algorithm.programmers;

/*
DFS : 알파벳

문제
세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.

말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
*/
import java.io.*;
import java.util.*;

public class Main {
    public static String inputs = "3 6\n" +
            "HFDFFB\n" +
            "AJHGDH\n" +
            "DGAGEH";


    public static int bfs(int x, int y, int[][] history, char[][] map, Set<Character> set, int depth) {
        int max = depth;
        if (x != history[y].length - 1 && history[y][x + 1] != 1 && !set.contains(map[y][x + 1])) {
            history[y][x + 1] = 1;
            set.add(map[y][x + 1]);
            max = Math.max(bfs(x + 1, y, history, map, set, depth + 1), max);
            set.remove(map[y][x + 1]);
            history[y][x + 1] = 0;
        }

        if (y != history.length - 1 && history[y + 1][x] != 1 && !set.contains(map[y + 1][x])) {
            history[y + 1][x] = 1;
            set.add(map[y + 1][x]);
            max = Math.max(bfs(x, y + 1, history, map, set, depth + 1), max);
            set.remove(map[y + 1][x]);
            history[y + 1][x] = 0;
        }
        if (x != 0 && history[y][x - 1] != 1 && !set.contains(map[y][x - 1])) {
            history[y][x - 1] = 1;
            set.add(map[y][x - 1]);
            max = Math.max(bfs(x - 1, y, history, map, set, depth + 1), max);
            set.remove(map[y][x - 1]);
            history[y][x - 1] = 0;
        }
        if (y != 0 && history[y - 1][x] != 1 && !set.contains(map[y - 1][x])) {
            history[y - 1][x] = 1;
            set.add(map[y - 1][x]);
            max = Math.max(bfs(x, y - 1, history, map, set,depth + 1), max);
            set.remove(map[y - 1][x]);
            history[y - 1][x] = 0;
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
//        InputStream targetStream = new ByteArrayInputStream(inputs.getBytes());
//        BufferedReader br = new BufferedReader(new InputStreamReader(targetStream));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Character> set = new HashSet<>();
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        char[][] map = new char[row][col];
        for (int i = 0; i < row; i++) {
            String msg = new StringTokenizer(br.readLine()).nextToken();
            for (int j = 0; j < col; j++) {
                map[i][j] = msg.charAt(j);
            }
        }
        int[][] history = new int[row][col];
        history[0][0] = 1;
        set.add(map[0][0]);
        System.out.println(bfs(0, 0, history, map, set,1));
    }
}