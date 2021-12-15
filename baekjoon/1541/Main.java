import java.io.*;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



//잃어버린 괄호 성공
//        시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
//        2 초	128 MB	33059	15558	12564	47.093%
//        문제
//        세준이는 양수와 +, -, 그리고 괄호를 가지고 식을 만들었다. 그리고 나서 세준이는 괄호를 모두 지웠다.
//
//        그리고 나서 세준이는 괄호를 적절히 쳐서 이 식의 값을 최소로 만들려고 한다.
//
//        괄호를 적절히 쳐서 이 식의 값을 최소로 만드는 프로그램을 작성하시오.
//
//        입력
//        첫째 줄에 식이 주어진다. 식은 ‘0’~‘9’, ‘+’, 그리고 ‘-’만으로 이루어져 있고, 가장 처음과 마지막 문자는 숫자이다. 그리고 연속해서 두 개 이상의 연산자가 나타나지 않고, 5자리보다 많이 연속되는 숫자는 없다. 수는 0으로 시작할 수 있다. 입력으로 주어지는 식의 길이는 50보다 작거나 같다.
//
//        출력
//        첫째 줄에 정답을 출력한다.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = st.nextToken();


        Pattern pattern = Pattern.compile("(\\d+|\\+|\\-)");
        Matcher matcher = pattern.matcher(line);

        boolean minus = false;
        int result = 0;
        while (matcher.find()) {
            for (int i = 1; i < matcher.groupCount() + 1; i++) {
                String value = matcher.group(i);
                if (value.equals("-"))
                    minus = true;
                else if (value.equals("+"))
                    continue;
                else {
                    int current = Integer.parseInt(value);
                    if (minus)
                        result -= current;
                    else
                        result += current;

                }

            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
