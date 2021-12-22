import java.util.*;

/*
greedy : 단속카메라 
고속도로를 이동하는 모든 차량이 고속도로를 이용하면서 단속용 카메라를 한 번은 만나도록 카메라를 설치하려고 합니다.

고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때, 모든 차량이 한 번은 단속용 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.
*/
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(a->a[1]));
        int result = 1;
        int end = routes[0][1];
        for (int i = 1; i < routes.length ; i++) {
            if (end < routes[i][0]) {
                end = routes[i][1]; result++;
            }
        }
        return result;
    }
}