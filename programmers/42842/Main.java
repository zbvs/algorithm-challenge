import java.util.*;
import java.util.stream.Collectors;

/*
문제 설명
Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.
*/

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};
        for (int x = 3; x < brown/2; x++) {
            int y = (brown - x*2 + 4)/2;
            if ((x-2)*(y-2) == yellow) {
                answer[0] = x;
                answer[1] = y;
            }

        }
        return answer;
    }
}