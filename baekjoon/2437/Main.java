package com.algorithm.programmers;
/*
저울 #그리디 알고리즘, #정렬
하나의 양팔 저울을 이용하여 물건의 무게를 측정하려고 한다. 이 저울의 양 팔의 끝에는 물건이나 추를 올려놓는 접시가 달려 있고, 양팔의 길이는 같다. 또한, 저울의 한쪽에는 저울추들만 놓을 수 있고, 다른 쪽에는 무게를 측정하려는 물건만 올려놓을 수 있다.

풀이:
숫자 n개가 있다고 하자.  (5개 : 5, 1, 2, 1, 11)
작은 순서대로 정렬을 해보자. ( 1, 1, 2, 5, 11)
가장 작은 순서대로 i+1번째인 숫자가 i번 째인 숫자까지 모두 더한 값 ( total) 보다 +1 을 초과해서 더 큰게 아니라면
i번째 까지의 숫자들로 i+1 바로 이전 까지의 숫자들을 모두 만들 수 잇다.
이걸 재귀적으로 만족하기 때문에 그 다음 i+2 번째인 숫자가 i+1번째인 숫자가지 모두 더한 값 보다 +1을 초과해서 더 큰게 아니라면
i+1번째 까지의 숫자들로 i+2바로 이전 까지의 숫자들을 모두 만들 수 잇다.

ex-1) 1번째 까지인 숫자들(1)의 총합은 1이므로 2번째 숫자인 1보다 작은 숫자 까지는모두 만들 수 잇다.
ex-2) 2번재 까지인 숫자들(1, 1)의 총합은 2이므로 3번째 숫자인 2보다 작은 숫자 까지는모두 만들 수 잇다.
ex-3) 3번재 까지인 숫자들(1, 1, 2)의 총합은 4이므로 4번째 숫자인 5보다 작은 숫자 까지는모두 만들 수 잇다.
ex-4) 4번재 까지인 숫자들(1, 1, 2, 5)의 총합은 9이므로 5번째 숫자인 11보다 작은 숫자 모두를 만들 수는 없다. 9가 최대이니깐 10은 만들 수 없다.
 */

import java.io.*;
import java.util.*;

public class Main {
    public static String inputs = "7\n" +
            "3 1 6 2 7 30 1\n";


    public static void main(String[] args) throws IOException {
//        InputStream targetStream = new ByteArrayInputStream(inputs.getBytes());
//        BufferedReader br = new BufferedReader(new InputStreamReader(targetStream));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        int total = 0;
        while (queue.size() != 0) {
            int value = queue.remove();
            if (value - total > 1)
                break;
            total += value;
        }

        System.out.println(total+1);
    }
}