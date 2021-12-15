package com.company;

/*
여행경로
문제 설명
주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.

항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한사항
모든 공항은 알파벳 대문자 3글자로 이루어집니다.
주어진 공항 수는 3개 이상 10,000개 이하입니다.
tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
주어진 항공권은 모두 사용해야 합니다.
만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
입출력 예
tickets	return
[["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]	["ICN", "JFK", "HND", "IAD"]
[["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]]	["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
입출력 예 설명
예제 #1

["ICN", "JFK", "HND", "IAD"] 순으로 방문할 수 있습니다.

예제 #2

["ICN", "SFO", "ATL", "ICN", "ATL", "SFO"] 순으로 방문할 수도 있지만 ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"] 가 알파벳 순으로 앞섭니다.
 */


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


class Solution {
    public class Node {
        String name;
        List<Edge> edges = new ArrayList<>();
        public Node(String name){
            this.name = name;
        }
        public void add (Edge edge) {
            //https://www.geeksforgeeks.org/arrays-binarysearch-java-examples-set-1/
            int idx = Collections.binarySearch(edges, edge);
            if (idx < 0) idx = -(idx+1);
            edges.add(idx, edge);
        }
    }
    public class Edge implements Comparable<Edge> {
        Node src;
        Node dst;
        boolean visited = false;
        public Edge(Node src, Node dst) {
            this.src = src;
            this.dst = dst;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dst.name.compareTo(o.dst.name);
        }
    }

    public String[] solution(String[][] tickets) {
        Map<String, Node> map = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            Node src;
            if (map.containsKey(tickets[i][0]))
                src = map.get(tickets[i][0]);
            else {
                src = new Node(tickets[i][0]);
                map.put(tickets[i][0], src);
            }
            Node dst;
            if (map.containsKey(tickets[i][1]))
                dst = map.get(tickets[i][1]);
            else {
                dst = new Node(tickets[i][1]);
                map.put(tickets[i][1], dst);
            }
            src.add(new Edge(src, dst));
        }
        List<Node> result = dfs(map.get("ICN"), tickets.length, 0);
        return result.stream().map(o->o.name).collect(Collectors.toList()).toArray(new String[result.size()]);
    }

    public List<Node> dfs(Node src, int target , int depth) {
        if (target == depth)
            return new ArrayList<>(List.of(src));
        List<Node> result = null;
        for (Edge edge : src.edges) {
            if (!edge.visited) {
                edge.visited = true;
                result = dfs(edge.dst, target, depth + 1);
                if (result != null) {
                    result.add(0, src);
                    break;
                }
                edge.visited = false;
            }
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};

        String[] result = new Solution().solution(tickets);
        System.out.println(List.of(result));

    }
}