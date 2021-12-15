
import java.io.*;
import java.util.*;
/*
강의실 배정 성공
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	12237	3608	2580	29.272%
문제
수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다. 

김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다. 

참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)

수강신청 대충한 게 찔리면, 선생님을 도와드리자!

입력
첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 200,000)

이후 N개의 줄에 Si, Ti가 주어진다. (0 ≤ Si < Ti ≤ 109)

출력
강의실의 개수를 출력하라.
*/

public class Main {
    public static class Term implements Comparable<Term> {
        public int start;
        public int end;

        public Term(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Term o) {
            return this.start - o.start;
        }
    }

    public static class Sum implements Comparable<Sum> {
        public int start;
        public int end;

        public Sum(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Sum o) {
            return this.end - o.end;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        long timeStart = System.currentTimeMillis();
        PriorityQueue<Term> terms = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            terms.add(new Term(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            if ((System.currentTimeMillis() - timeStart) > 1000)
                throw new NoSuchElementException("");
        }


        int cnt = 0;;
        PriorityQueue<Sum> sums = new PriorityQueue<>();

        while (terms.size() != 0) {
            Term term = terms.remove();

            if (sums.size() == 0 || sums.peek().end > term.start){
                sums.add(new Sum(term.start, term.end));
                cnt ++;
            } else {
                Sum sum = sums.remove();
                sum.end = term.end;
                sums.add(sum);

            }

            if ((System.currentTimeMillis() - timeStart) > 1000)
                throw new ArithmeticException("");
        }

        if ((System.currentTimeMillis() - timeStart) > 1000)
            throw new NumberFormatException("");

        System.out.println(cnt);

    }
}