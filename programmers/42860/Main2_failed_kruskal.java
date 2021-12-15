package com.company;

/*
조이스틱
문제 설명
조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

조이스틱을 각 방향으로 움직이면 아래와 같습니다.

▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동
예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.

- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.
 */


import java.util.*;

class Solution {
    // 틀림 : 출발점 부터 시작해야 하는데 Kruskal은 임의의 노드에서 시작해서 MST를 만드므로 적용 불가
    public class Node {
        int x;
        public Set<Node> set;
        public Node(int x) {
            this.x = x;
            this.set = new HashSet<>();
            set.add(this);
        }
    }
    public class Edge implements Comparable<Edge> {
        Node a;
        Node b;
        int distance;

        public Edge(Node a, Node b, int distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }

    public int solution(String name) {
        int alphaSize = ('Z' + 1 - 'A');
        int size = name.length();
        int total = 0;
        List<Node> nodes = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();


        for (int i = 0; i < size; i++) {
            int count = (alphaSize / 2) - (name.charAt(i) - 'A');
            count = count < 0 ? alphaSize - (name.charAt(i) - 'A') : (name.charAt(i) - 'A');
            if (count != 0 || i == 0) {
                total += count;
                Node cur = new Node(i);
                for (Node node : nodes) {
                    int distance = (size / 2) - (i - node.x);
                    distance = distance < 0 ? size - (i - node.x) : (i - node.x);
                    pq.add(new Edge(cur, node, distance));
                }
                nodes.add(cur);
            }
        }
        int remained = nodes.size() - 1;
        while (remained != 0) {
            Edge edge = pq.remove();
            if (edge.a.set != edge.b.set) {
                total += edge.distance;
                edge.a.set.addAll(edge.b.set);
                for (Node node : edge.b.set) {
                    node.set = edge.a.set;
                }
                remained--;
            }
        }
        return total;
    }
}


public class Main {
    public static void main(String[] args) {

        String c = "ABAAAAB";
        int result = new Solution().solution(c);
        System.out.println(List.of(result));
    }
}
