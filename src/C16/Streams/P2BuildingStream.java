package C16.Streams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class P2BuildingStream {

    public static void main(String[] args) {

//        Generate
        IntSupplier supplier = () -> (int) (Math.random() * 6.0) + 1;
        IntStream diceStream = IntStream.generate(supplier);
        diceStream.limit(5)
                .forEach(i -> System.out.println(i + ""));

//        Interate
        IntUnaryOperator uop = n -> n + 2;
        IntStream oddNums = IntStream.iterate(1, uop);
        oddNums.limit(5)
                .forEach(i -> System.out.println(i + " "));

        Stream.iterate("ba", b -> b + "na")
                .limit(5)
                .forEach(System.out::println);

//        Concat stream: unordered
        Set<String> strSet1 // (1)
                = Set.of("All", " objects", " are", " equal");
        Set<String> strSet2 // (2)
                = Set.of(" but", " some", " are", " more", " equal", " than", " others.");
        Stream<String> unorderedStream1 = strSet1.stream(); // (3)
        Stream<String> unorderedStream2 = strSet2.stream(); // (4)
        Stream.concat(unorderedStream1, unorderedStream2) // (5)
                .forEachOrdered(System.out::print); // (6)
// objectsAll equal are some are others. than equal more but

//        Concat stream: ordered
        Stream<String> orderedStream1 = Stream.of("All", " objects", // (1)
                " are", " equal");
        Stream<String> orderedStream2 = Stream.of(" but", " some", " are", " more", // (2)
                " equal", " than", " others.");
        Stream.concat(orderedStream1, orderedStream2) // (3)
                .forEachOrdered(System.out::print); // (4)
// All objects are equal but some are more equal than others.

        Stream<String> pStream1 = strSet1.stream().parallel(); // (1)
        System.out.println("pStream1 is parallel: " + pStream1.isParallel()); // (2) true
        Stream<String> rStream = Stream.concat(pStream1, strSet2.stream()); // (3)
        System.out.println("rStream is parallel: " + pStream1.isParallel()); // (4) true
        rStream.forEachOrdered(System.out::print); // (5)
// objectsAll equal are some are others. than equal more but

//        If the
//data source is modified before the terminal operation is initiated, the changes will
//be reflected in the stream.
        List<P1Introduction.CD> listOfCDS = new ArrayList<>(List.of(P1Introduction.CD.cd0, P1Introduction.CD.cd1)); // (1)
        Stream<P1Introduction.CD> cdStream = listOfCDS.stream(); // (2)
        listOfCDS.add(P1Introduction.CD.cd2); // (3)
        System.out.println(cdStream.count()); // (4) 3
// System.out.println(cdStream.count()); // (5) IllegalStateException


//        Map -> unordered sequential stream
        Map<Integer, String> dataMap = new HashMap<>(); // (1)
        dataMap.put(1, "en");
        dataMap.put(2, "to");
        dataMap.put(3, "tre");
        dataMap.put(4, "fire");

        long numOfEntries = dataMap.entrySet()
                .stream()
                .count();

//        Stream from Arrays
        Stream<P1Introduction.CD> cdStream1 = Arrays.stream(P1Introduction.CD.cdArray, 1, 4);
        long noOfElements = cdStream1.count(); // 3

        IntStream.range(0, P1Introduction.CD.cdArray.length)
                .forEach(i -> System.out.println(P1Introduction.CD.cdArray[P1Introduction.CD.cdArray.length - 1 - i]));

        int divisor = 5;
        int start = 2000, end = 5000;
        long count = IntStream.rangeClosed(start, end)
                .filter(num -> (num % divisor) == 0)
                .count();

        int first = 10, len = 8;
        int[] array = IntStream.range(first, first + len).toArray();
        System.out.println(Arrays.toString(array));

        IntStream.rangeClosed(1, 10)
                .forEach(i ->
                        IntStream.rangeClosed(1, 10)
                                .forEach(j -> System.out.printf("%2d * %2d = %2d%n", i, j, i * j))
                );

        String strSource = "bananana";
        IntStream iStream = strSource.chars();
        iStream.forEach(i -> System.out.println(i + " "));

//      IntStream iStream = CharSequence.chars()
        strSource.chars().mapToObj(i -> (char)i)
                .forEach(System.out::print);

//        Stream<String> lStream = inputLines.lines();
        String inputLines = "Wise men learn from their mistakes.\n" // (1)
                + "But wiser men learn from the mistakes of others.\n"
                + "And fools just carry on.";

        Stream<String> lStream = inputLines.lines();
        lStream.filter(l -> l.contains("mistakes")).forEach(System.out::println);

//      Stream<String> lStream = bReader.lines()
        try (FileReader fReader = new FileReader("CD_Data.txt"); // (1)
             BufferedReader bReader = new BufferedReader(fReader); // (2)
             Stream<String> lStream1 = bReader.lines() ) { // (3)
            System.out.println("Number of lines: " + lStream1.count()); // (4) 13
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//      Stream<String> lStream = Files.lines(path)
        Path path = Paths.get("CD_Data.txt"); // (1)
        try (Stream<String> lStream2 = Files.lines(path)) { // (2)
            System.out.println("Number of lines: " + lStream2.count()); // (3) 13
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }
}