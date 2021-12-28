
/*
[Hackerranl] Dynamic Programming : Abbreviation
You can perform the following operations on the string, a:

Capitalize zero or more of a’s lowercase letters.
Delete all of the remaining lowercase letters in a.
Given two strings, a and b, determine if it’s possible to make a equal to a as described. If so, print YES on a new line. Otherwise, print NO.

For example, given a = AbcDE and b = ABDE, in a we can convert b and delete c to match b. If a = AbcDE and b = AFDE, matching is not possible because letters may only be capitalized or discarded, not changed.
 */
import java.io.*;
import java.util.*;
import java.util.stream.*;

class Result {
    public static char upper(char c) {
        return (char) (c - 0x20);
    }

    public static String abbreviation(String aStr, String bStr) {
        char[] a = aStr.toCharArray();
        char[] b = bStr.toCharArray();
        boolean[][] dp = new boolean[b.length+1][a.length+1];
        dp[0][0] = true;
        for (int i = 0; i < b.length; i++)
            dp[i+1][0] = false;
        for (int j = 0; j < a.length ; j++)
            dp[0][j+1] = a[j] > 0x60;

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j] > 0x60)
                    //1. remove : dp[i+1][j]
                    //2. capitalize and use : (upper(a[j]) == b[i] && dp[i][j]  (b string contains only uppercase)
                    //dp[i+1][j+1] = 1 result || 2 result
                    dp[i+1][j+1] = dp[i+1][j] || (upper(a[j]) == b[i] && dp[i][j]);
                else
                    dp[i+1][j+1] = a[j] == b[i] && dp[i][j];
            }
        }
        return dp[b.length][a.length] ? "YES" : "NO";
    }
}

public class Solution {
    private static String getData() {
        StringBuilder builder = new StringBuilder();
        try {
            File myObj = new File("C:\\Users\\tr\\Desktop\\curtest\\algorithm\\programmers\\programmers-idea\\src\\com\\algorithm\\programmers\\test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                builder.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        

        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
		InputStream targetStream = new ByteArrayInputStream(getData().getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(targetStream));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();
                String result = Result.abbreviation(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
