package com.company;

/*
가장 먼 노드
문제 설명
n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
 */


import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 1; i < n + 1; i++) {
            map.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }

        Set<Integer> to = map.get(1);
        List<Integer> queue = new ArrayList<>(to);
        Set<Integer> visited = new HashSet<>(to);
        visited.add(1);

        int size = 0;
        while (queue.size() != 0) {
            List<Integer> next = new ArrayList<>();
            queue.forEach(node -> {
                if (map.containsKey(node)) {
                    Set<Integer> dsts = map.get(node);
                    dsts.forEach(dst -> {
                        if (!visited.contains(dst)) {
                            next.add(dst);
                            visited.add(dst);
                        }
                    });
                }
            });
            size = queue.size();
            queue = next;

        }
        return size;
    }
}


public class Main {
    public static void main(String[] args) {

        int[][] q = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}, {5, 7}, {7, 8}, {7, 9}, {7, 10}, {7, 11}, {3, 7}, {6, 12}, {6, 13}};
        long result = 0;

        result = new Solution().solution(13, q);
        System.out.println(List.of(result));
    }
}