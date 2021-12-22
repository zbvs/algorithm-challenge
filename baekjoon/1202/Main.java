import java.io.*;
import java.util.*;
/*
문제
세계적인 도둑 상덕이는 보석점을 털기로 결심했다.

상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 Mi와 가격 Vi를 가지고 있다. 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다. 가방에는 최대 한 개의 보석만 넣을 수 있다.

상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)

다음 N개 줄에는 각 보석의 정보 Mi와 Vi가 주어진다. (0 ≤ Mi, Vi ≤ 1,000,000)

다음 K개 줄에는 가방에 담을 수 있는 최대 무게 Ci가 주어진다. (1 ≤ Ci ≤ 100,000,000)

모든 숫자는 양의 정수이다.

출력
첫째 줄에 상덕이가 훔칠 수 있는 보석 가격의 합의 최댓값을 출력한다.
*/

class Jewel implements Comparable<Jewel> {
    public int weight;
    public int price;

    public Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(Jewel target) {
        return this.weight <= target.weight ? -1 : 1;
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numJ = Integer.parseInt(st.nextToken());
        int numB = Integer.parseInt(st.nextToken());

        List<Jewel> jewelList = new ArrayList<>();
        for (int i = 0; i < numJ; i++) {
            st = new StringTokenizer(br.readLine());
            jewelList.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        List<Integer> bagList = new ArrayList<>();
        for (int i = 0; i < numB; i++) {
            bagList.add(Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()));
        }

        bagList.sort((a, b) -> a - b);
        jewelList.sort((a, b) -> a.weight - b.weight);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        long result = 0;
        int idx = 0;
        for (int bagWeight : bagList) {
            for (; idx < jewelList.size(); idx++) {
                Jewel jewel = jewelList.get(idx);
                if (jewel.weight > bagWeight)
                    break;
                pq.add(jewel.price);
            }

            if (!pq.isEmpty()) result += pq.remove();
        }


        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}