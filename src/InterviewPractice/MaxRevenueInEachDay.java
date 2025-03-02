package InterviewPractice;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MaxRevenueInEachDay {

    public static void main(String[] args) {
        // Maximum revenue
        // Problem statement : Amit is a salesman who wishes to know the maximum revenue received from a given item of the N products each day .
        // Amit has a sales record of N products for M days. Helo amit to find the highest revenue received each day.

        // Input :
        //The first line of the input consists of two space-separated integers- day(M) and item(N) representing the days and the products in the sales record.
        //The next M lines consist of N space separated integers representing the sales revenue from each product each day.

        // Output:
        //Print m space-separated integers representing the maximum revenue received each day .

        // Example Input:
        //3 4
        //101 123 234 344
        //143  282 499 493
        //283 493 202 304
        //Output:
        //344 499 493

        // Explanation:
        //The maximum revenue received on the first day is 344 ,
        // followed by a maximum revenue of 499 on the second day and a maximum revenue of 493 on the third day.

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();

        List<List<Integer>> sales = IntStream.range(0, m)
                .mapToObj(i -> Arrays.stream(sc.nextLine().split(" "))
                        .map(Integer::parseInt).toList())
                .toList();

        sales.stream().map(day -> day.stream().max(Integer::compareTo).get())
                .forEach(e -> System.out.print(e + " "));
    }
}
