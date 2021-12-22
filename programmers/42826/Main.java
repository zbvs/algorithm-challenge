
import java.util.*;

/*
greedy : 섬 연결하기

n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.

다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다. 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
*/
class Solution {
    public int solution(int n, int[][] costs) {
        int result = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));
        for (int[] cost : costs) {
            Set<Integer> srcSet;
            Set<Integer> dstSet;
            if (!map.containsKey(cost[0]))
                map.put(cost[0], new HashSet<>(Set.of(cost[0])));
            srcSet = map.get(cost[0]);
            if (!map.containsKey(cost[1]))
                map.put(cost[1], new HashSet<>(Set.of(cost[1])));
            dstSet = map.get(cost[1]);
            if (srcSet == dstSet)
                continue;
            srcSet.addAll(dstSet);
            for (int dst : dstSet) {
                map.put(dst, srcSet);
            }
            result += cost[2];
            if (--n == 1)
                break;
        }
        return result;
    }
}