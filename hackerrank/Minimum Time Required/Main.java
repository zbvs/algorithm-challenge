/*
[Hackerrank] Search : Minimum Time Required

You are planning production for an order. You have a number of machines that each have a fixed number of days to produce an item. Given that all the machines operate simultaneously, determine the minimum number of days to produce the required order.

For example, you have to produce  items. You have three machines that take  days to produce an item. The following is a schedule of items produced:
 */
package com.algorithm.programmers;

import java.io.*;
import java.util.*;


public class Solution {
    // Complete the minTime function below.
    static int diff(long[] machines, int upto, long day, long goal) {
        long acc = 0;
        for (int i = 0; i < upto; i++) {
            long m = machines[i];
            acc += day / m;
            if (acc > goal)
                return 1;
        }
        if (acc == goal)
            return 0;
        return -1;
    }

    static long count(long[] machines, int upto, long day) {
        long acc = 0;
        for (int i = 0; i < upto; i++) {
            long m = machines[i];
            acc += day / m;
        }
        return acc;
    }

    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
        long cnt = 0;
        long start = 0;
        long end = 0;
        int inc = Math.max(1, machines.length/100);
        for (int i = 0; i < machines.length; i += inc) {
            end = machines[i];
            if (diff(machines, i + 1, end, goal) != -1) {
                cnt = count(machines, i + 1, end);
                break;
            }
        }

        if (cnt == 0) {
            cnt = count(machines, machines.length, end);
            end = (goal / cnt + 1) * machines[machines.length - 1];
        }
        long mid = end / 2;

        while (true) {
            int cmp = diff(machines, machines.length, mid, goal);
            if (cmp == -1) {
                if (mid == end - 1)
                    return end;
                start = mid;
                mid = (start + end) / 2;
            } else {
                if (mid == start + 1)
                    return mid;
                end = mid;
                mid = (start + end) / 2;
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    private static long[] getTestArray() {
        StringBuilder builder = new StringBuilder();
        try {
            File myObj = new File("C:\\Users\\tr\\Desktop\\curtest\\algorithm\\programmers\\programmers-idea\\src\\com\\algorithm\\programmers\\test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                builder.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String data = builder.toString();
        String[] splited = data.split(" ");

        long[] result = new long[splited.length];
        for (int i = 0; i < splited.length; i++) {
            result[i] = Long.parseLong(splited[i]);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        assert(minTime(getTestArray(), 284898905) == 6225);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
