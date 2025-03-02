package C16.Streams;

import java.lang.reflect.Array;
import java.time.Year;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

public class P5IntermediateStreamOperation {

    public static void main(String[] args) {

//        1. Stream Mapping
        List<String> popCDTitiles = P1Introduction.CD.cdList
                .stream()
                .filter(P1Introduction.CD::isPop)
                .map(P1Introduction.CD::title)
                .toList();
        System.out.println(popCDTitiles);

// (2) Lazy Evaluation:
//        Intermediate operations are perform back-to-back on
//        each element in the stream. (single loop)
        List<P1Introduction.CD> cdList2 = P1Introduction.CD.cdList;
        List<String> popCDs2 = cdList2
                .stream() // Initial stream: Stream<CD>
                .filter(cd -> { // Intermediate operation: Stream<CD>
                    System.out.println("Filtering: " + cd // (3)
                            + (cd.isPop() ? " is pop CD." : " is not pop CD."));
                    return cd.isPop();
                })
                .map(cd -> { // Intermediate operation: Stream<String>
                    System.out.println("Mapping: " + cd.title()); // (4)
                    return cd.title();
                })
                .toList(); // Terminal operation: List<String>
        System.out.println("Pop music CDs: " + popCDs2);

//        Filtering: <Jaav, "Java Jive", 8, 2017, POP> is pop CD.
//        Mapping: Java Jive
//        Filtering: <Jaav, "Java Jam", 6, 2017, JAZZ> is not pop CD.
//                Filtering: <Funkies, "Lambda Dancing", 10, 2018, POP> is pop CD.
//        Mapping: Lambda Dancing
//        Filtering: <Genericos, "Keep on Erasing", 8, 2018, JAZZ> is not pop CD.
//                Filtering: <Genericos, "Hot Generics", 10, 2018, JAZZ> is not pop CD.
//                Pop music CDs: [Java Jive, Lambda Dancing]


//        (3) Short-circuit evaluation
//        .limit(): infinite stream -> finite stream


//      (4) Order of Intermediate Operations
        List<P1Introduction.CD> cdList = P1Introduction.CD.cdList;
// Map before skip.
        List<String> cdTitles1 = cdList
                .stream() // (1)
                .map(cd -> { // Map applied to all elements.
                    System.out.println("Mapping: " + cd.title());
                    return cd.title();
                })
                .skip(3) // Skip afterwards.
                .toList();
        System.out.println(cdTitles1);
        System.out.println();

// Skip before map preferable.
        List<String> cdTitles2 = cdList
                .stream() // (2)
                .skip(3) // Skip first.
                .map(cd -> { // Map not applied to the first 3 elements.
                    System.out.println("Mapping: " + cd.title());
                    return cd.title();
                })
                .toList();
        System.out.println(cdTitles2);

//        (5) Filtering Using a Predicate
        // Query 1: Find CDs whose titles are in the set of popular CD titles.
        Set<String> popularTitles = Set.of("Java Jive", "Java Jazz", "Java Jam");
        List<P1Introduction.CD> popularCDs1 =
                P1Introduction.CD.cdList
                        .stream()
                        .filter(cd -> popularTitles.contains(cd.title()))
                        .toList();

//        takeWhile, dropWhile with Ordered stream
        Stream.of(1, 3, 5, 7, 8, 9, 10)
                .takeWhile(n -> n % 2 != 0)
                .forEach(System.out::println);

        Stream.of(1, 3, 5, 7, 8, 9, 10)
                .dropWhile(n -> n % 2 != 0)
                .forEach(System.out::println);

//        takeWhile, dropWhile with Unordered stream
        Set<Integer> iSeq = Set.of(1, 9, 4, 3, 7); // (3)
        iSeq.stream()
                .takeWhile(n -> n % 2 != 0)
                .forEach(System.out::print);

        System.out.println("--------------------------------");
        iSeq.stream()
                .dropWhile(n -> n % 2 != 0)
                .forEach(System.out::print);

//        Selecting Distinct elements
        List<P1Introduction.CD> miscCDList = List.of(P1Introduction.CD.cd0,
                P1Introduction.CD.cd0,
                P1Introduction.CD.cd0,
                P1Introduction.CD.cd0);
        List<P1Introduction.CD> uniquePopCDs1 = miscCDList.stream()
                .filter(P1Introduction.CD::isPop)
                .distinct()
                .toList();

        // Query 3a: Create a list of jazz CDs, after skipping the first two CDs.
        List<P1Introduction.CD> jazzCDList =
                P1Introduction.CD.cdList
                        .stream()
                        .skip(2)
                        .filter(P1Introduction.CD::isJazz)
                        .toList();
        System.out.println(jazzCDList);

        // Query 3b: Create a list of jazz CDs, but skip the first two jazz CDs.
        List<P1Introduction.CD> jazzCDList2 =
                P1Introduction.CD.cdList
                        .stream()
                        .filter(P1Introduction.CD::isJazz)
                        .skip(2)
                        .toList();
        System.out.println("Query 3b: " + jazzCDList2);

        // Query 4: Create a list with the first 2 CDs that were released in 2018
        List<P1Introduction.CD> twoFirst2018CDs =
                P1Introduction.CD.cdList
                        .stream()
                        .filter(cd -> cd.year().equals(Year.of(2018)))
                        .limit(2)
                        .toList();
        System.out.println(twoFirst2018CDs);

        List<P1Introduction.CD> substream =
                P1Introduction.CD.cdList
                        .stream()
                        .skip(1)
                        .limit(3)
                        .toList();
        System.out.println(substream);

//        Examining Elements in a Stream
//        peek()
        System.out.println("map() before skip():");
        List<String> cdTitles11 = P1Introduction.CD.cdList
                .stream()
                .map(P1Introduction.CD::title)
                .peek(t -> System.out.println("After map: " + t))
                .skip(3)
                .peek(t -> System.out.println("After skip: " + t))
                .toList();
        System.out.println(cdTitles1);
        System.out.println();

        System.out.println("skip() before map():"); // Preferable.
        List<String> cdTitles22 = P1Introduction.CD.cdList
                .stream()
                .skip(3)
                .peek(cd -> System.out.println("After skip: " + cd))
                .map(P1Introduction.CD::title)
                .peek(t -> System.out.println("After map: " + t))
                .toList();
        System.out.println(cdTitles2);

//        Mapping - map()
        System.out.println("--------Mapping--------");
        P1Introduction.CD.cdList
                .stream()
                .map(P1Introduction.CD::year)
                .distinct()
                .map(Year::getValue)
                .forEach(System.out::println);

        // Query: Create a list of CD titles released in 2018.
        P1Introduction.CD.cdList
                .stream()
                .filter(cd -> cd.year().equals(Year.of(2018)))
                .map(P1Introduction.CD::title)
                .forEach(System.out::println);

//        Flattening Stream - flatMap()
        List<P1Introduction.CD> listOfCD =
                Stream.of(P1Introduction.CD.cdList, P1Introduction.CD.cdList2)
                        .flatMap(List::stream)
                        .distinct()
                        .toList();
        System.out.println("Flat stream: " + listOfCD.size());

        int[][] twoDimArray = {{2024, 2025}, {2025, 2024}};
        int[] intArray = Arrays.stream(twoDimArray)
                .flatMapToInt(Arrays::stream)
                .toArray();
        System.out.println(intArray.length);

//        Replacing Each Element of a Stream with Multiple Elements
//        .mapMulti()
        // One-to-one
        BiConsumer<P1Introduction.CD, Consumer<String>> bcA = (cd, consumer) -> { // (1)
            if (cd.genre() == P1Introduction.Genre.POP) { // (2)
                consumer.accept(String.format("%-15s: %s", cd.title(), // (3)
                        "*".repeat(cd.noOfTracks())));
            }
        };

        P1Introduction.CD.cdList
                .stream()
                .mapMulti(bcA)
                .forEach(System.out::println);

        // One-to-many
        List<P1Introduction.CD> cdList11 = List.of(P1Introduction.CD.cd0, P1Introduction.CD.cd1, P1Introduction.CD.cd1);
        List<P1Introduction.CD> cdList21 = List.of(P1Introduction.CD.cd0, P1Introduction.CD.cd1);

        BiConsumer<List<P1Introduction.CD>, Consumer<String>> bcB =
                (lst, consumer) -> {
                    for (P1Introduction.CD cd : lst) {
                        consumer.accept(cd.title());
                    }
                };

        Stream.of(cdList11, cdList21)
                .mapMulti(bcB)
                .distinct()
                .forEach(System.out::println);

        Stream.of(cdList11, cdList21)
                .mapMulti((List<P1Introduction.CD> lst, Consumer<String> consumer) -> {
                    for (P1Introduction.CD cd : lst) {
                        consumer.accept(cd.title());
                    }
                }).distinct().forEach(System.out::println);

//      Sorted Stream
        P1Introduction.CD[] cdArray = P1Introduction.CD.cdArray;
        System.out.println("(5) Only Jazz CDs, ordered by title:");

        Arrays.stream(cdArray)
                .filter(P1Introduction.CD::isJazz)
                .sorted(Comparator.comparing(P1Introduction.CD::title))
                .map(P1Introduction.CD::title)
                .toList();

        System.out.println("(6) No. of tracks >= 8, ordered by number of tracks:");
        Arrays.stream(cdArray)
                .filter(cd -> cd.noOfTracks() >= 8)
                .sorted(Comparator.comparing(P1Introduction.CD::noOfTracks))
                .toList();

//        Setting a Stream as Unordered
        P1Introduction.CD.cdList.stream()
                .unordered()
                .filter(P1Introduction.CD::isJazz)
                .limit(2)
                .map(P1Introduction.CD::title)
                .forEach(System.out::println);

//      Mapping stream
        int totalNumOfTracks = P1Introduction.CD.cdList
                .stream()
                .mapToInt(P1Introduction.CD::noOfTracks)
                .sum();

        int totalNumOfTracks2 = Stream.of(cdList11, cdList21)
                .mapMultiToInt((List<P1Introduction.CD> lst, IntConsumer consumer) -> {
                    consumer.accept(
                        lst.stream().mapToInt(P1Introduction.CD::noOfTracks).sum()
                    );
                }).sum();

        int totalNumOfTracks3 = Stream.of(cdList11, cdList21)
                        .flatMapToInt(lst -> lst.stream().mapToInt(P1Introduction.CD::noOfTracks))
                                .sum();

        System.out.println("totalNumOfTracks2: " + totalNumOfTracks2);
        System.out.println("totalNumOfTracks3: " + totalNumOfTracks3);

    }
}
