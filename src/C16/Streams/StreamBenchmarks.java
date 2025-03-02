package C16.Streams;

import java.util.stream.LongStream;

public class StreamBenchmarks {

    public static long seqSumRangeClosed(long n) { // (1)
        return LongStream.rangeClosed(1L, n).sum();
    }
    public static long paraSumRangeClosed(long n) { // (2)
        return LongStream.rangeClosed(1L, n).parallel().sum();
    }
    public static long seqSumIterate(long n) { // (3)
        return LongStream.iterate(1L, i -> i + 1).limit(n).sum();
    }
    public static long paraSumIterate(long n) { // (4)
        return LongStream.iterate(1L, i -> i + 1).limit(n).parallel().sum();
    }

    public static long iterSumLoop(long n) { // (5)
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }


}
