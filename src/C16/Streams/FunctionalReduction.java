package C16.Streams;

import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;

public class FunctionalReduction {

    public static void main(String[] args) {
//        2-argument reduce() method
        System.out.println("(1) Find total number of tracks (loop-based version):");
        int sum = 0;
        for (P1Introduction.CD cd : P1Introduction.CD.cdList) {
            sum += cd.noOfTracks();
        }
        System.out.println("Total number of tracks: " + sum);

        System.out.println("(2) Find total number of tracks (stream-based version):");
        int sum2 = P1Introduction.CD.cdList
                .stream()
                .mapToInt(P1Introduction.CD::noOfTracks)
                .sum();
        System.out.println("Total number of tracks, sum2 =  " + sum2);

        int sum3 = P1Introduction.CD.cdList
                .stream()
                .mapToInt(P1Introduction.CD::noOfTracks)
                .reduce(0, Integer::sum);

//        1-argument reduce method
        OptionalInt optSumTrack0 = P1Introduction.CD.cdList
                .stream()
                .mapToInt(P1Introduction.CD::noOfTracks)
                .reduce(Integer::sum);

        OptionalInt optSumTrack1 = P1Introduction.CD.cdList
                .stream()
                .mapToInt(P1Introduction.CD::noOfTracks)
                .reduce((s, noOfTracks) -> {
                    int newSum = s + noOfTracks;
                    System.out.printf("Accumulator: sum=%2d, noOfTracks=%2d, newSum=%2d%n", s, noOfTracks, newSum);
                    return newSum;
                });

//        3-argument reduce method (figure page 963)
//        The single-argument and two-argument reduce() methods accept a binary operator
//as the accumulator whose arguments and result are of the same type
//        The combiner is not executed if the reduce() method is
//applied to a sequential stream
        Integer sumTracks5 = P1Introduction.CD.cdList
                .parallelStream()
                .reduce(Integer.valueOf(0),
                        (s, cd) -> s + cd.noOfTracks(),
                        (s1, s2) -> s1 + s2);



    }
}
