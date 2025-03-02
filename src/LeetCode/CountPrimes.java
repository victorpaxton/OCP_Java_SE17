package LeetCode;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class CountPrimes {

    // 204 - Medium
    // Given an integer n, return the number of prime numbers that are strictly less than n.
    // -> Sieve of Eratosthenes

    public static int countPrimes(int n) {
        if (n <= 1) return 0;

        Boolean[] flag = new Boolean[n];
        Arrays.fill(flag, null);
        flag[0] = false;
        flag[1] = false;

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (flag[i] == null) {
                flag[i] = true;
                count++;
                for (int j = 2; j * i < n; j++) {
                    flag[j * i] = false;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }
}
