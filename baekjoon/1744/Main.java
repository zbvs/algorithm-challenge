package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
수 묶기 성공
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	17930	4890	3928	26.871%
문제
길이가 N인 수열이 주어졌을 때, 그 수열의 합을 구하려고 한다. 하지만, 그냥 그 수열의 합을 모두 더해서 구하는 것이 아니라, 수열의 두 수를 묶으려고 한다. 어떤 수를 묶으려고 할 때, 위치에 상관없이 묶을 수 있다. 하지만, 같은 위치에 있는 수(자기 자신)를 묶는 것은 불가능하다. 그리고 어떤 수를 묶게 되면, 수열의 합을 구할 때 묶은 수는 서로 곱한 후에 더한다.

예를 들면, 어떤 수열이 {0, 1, 2, 4, 3, 5}일 때, 그냥 이 수열의 합을 구하면 0+1+2+4+3+5 = 15이다. 하지만, 2와 3을 묶고, 4와 5를 묶게 되면, 0+1+(2*3)+(4*5) = 27이 되어 최대가 된다.

수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.

수열이 주어졌을 때, 수열의 각 수를 적절히 묶었을 때, 그 합이 최대가 되게 하는 프로그램을 작성하시오.

입력
첫째 줄에 수열의 크기 N이 주어진다. N은 10,000보다 작은 자연수이다. 둘째 줄부터 N개의 줄에, 수열의 각 수가 주어진다. 수열의 수는 -10,000보다 크거나 같고, 10,000보다 작거나 같은 정수이다.

출력
수를 합이 최대가 나오게 묶었을 때 합을 출력한다. 정답은 항상 231보다 작다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cnt = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            list.add(Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()));
        }


        List<Integer> listPlus = list.stream().filter(a -> a > 0).collect(Collectors.toList());
        List<Integer> listMinus = list.stream().filter(a -> a <= 0).collect(Collectors.toList());


        listPlus.sort((a, b) -> b - a);
        listMinus.sort((a, b) -> a - b);

        int result = 0;
        for (int i = 0; i < listMinus.size()/2; i++) {
            int index = i * 2;
            int a = listMinus.get(index);
            int b = listMinus.get(index + 1);
            if (a * b > a + b)
                result += a * b;
            else
                result += (a + b);
        }

        if (listMinus.size() % 2 == 1) {
            result += listMinus.get(listMinus.size()-1);
        }


        for (int i = 0; i < listPlus.size()/2; i++) {
            int index = i * 2;
            int a = listPlus.get(index);
            int b = listPlus.get(index + 1);
            if (a * b > a + b)
                result += a * b;
            else
                result += (a + b);
        }

        if (listPlus.size() % 2 == 1) {
            result += listPlus.get(listPlus.size()-1);
        }
        bw.write( String.valueOf(result)  );
        bw.flush();
        bw.close();
    }
}
