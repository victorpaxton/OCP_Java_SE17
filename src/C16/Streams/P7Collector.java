package C16.Streams;

import java.time.Year;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P7Collector {

    public enum Genre {POP, JAZZ, OTHER}

    public record CD(String artist, String title, int noOfTracks, Year year, Genre genre)
            implements Comparable<CD> {

        // Additional get methods:
        public boolean isPop() { return this.genre == Genre.POP; }
        public boolean isJazz() { return this.genre == Genre.JAZZ; }
        public boolean isOther() { return this.genre == Genre.OTHER; }

        // Provide own implementation of the toString() method.
        @Override
        public String toString() {
            return String.format("<%s, \"%s\", %d, %s, %s>",
                    this.artist, this.title, this.noOfTracks, this.year, this.genre);
        }

        /** Compare by artist, by title, by number of tracks, by year, and by genre. */
        @Override
        public int compareTo(CD other) {
            return Comparator.comparing(CD::artist)
                    .thenComparing(CD::title)
                    .thenComparing(CD::noOfTracks)
                    .thenComparing(CD::year)
                    .thenComparing(CD::genre)
                    .compare(this, other);
        }

        // Some ready-made CDs.
        public static final CD cd0
                = new CD("Jaav", "Java Jive", 8, Year.of(2017), Genre.POP);
        public static final CD cd1
                = new CD("Jaav", "Java Jam", 6, Year.of(2017), Genre.JAZZ);
        public static final CD cd2
                = new CD("Funkies", "Lambda Dancing", 10, Year.of(2018), Genre.POP);
        public static final CD cd3
                = new CD("Genericos", "Keep on Erasing", 8, Year.of(2018), Genre.JAZZ);
        public static final CD cd4
                = new CD("Genericos", "Hot Generics", 10, Year.of(2018), Genre.JAZZ);

        // A fixed-size list of CDs.
        public static final List<CD> cdList = List.of(cd4, cd0, cd1, cd2, cd3);
        public static final List<CD> cdList2 = List.of(cd3, cd4);

        // An array of CDs.
        public static final CD[] cdArray = {cd0, cd1, cd2, cd3, cd4};
    }

    public static void main(String[] args) {

        Map<CD, Year> mapCDToYear =
                CD.cdList.stream()
                        .collect(Collectors.toMap(Function.identity(), CD::year));

        Map<String, Year> mapTitleToYear = 
                CD.cdList.stream()
                        .collect(Collectors.toMap(CD::title, CD::year));
//        Duplicate key -> IllegalStateException

        Map<Year, String> mapYearToTitle =
                CD.cdList.stream()
                        .collect(Collectors.toMap(CD::year, CD::title, (t1, t2) -> t1 + ", " + t2));

        Map<Year, String> mapYearToOneTitle =
                CD.cdList.stream()
                        .collect(Collectors.toMap(CD::year, CD::title, (t1, t2) -> t2));

        TreeMap<Year, String> mapYearToLongestTitle =
                CD.cdList.stream()
                        .collect(Collectors.toMap(CD::year, CD::title,
                                BinaryOperator.maxBy(Comparator.naturalOrder()),
                                TreeMap::new));

//        ConcurrentMap
//        Using a concurrent map avoids merging of maps during parallel execution, as a
//single map is created that is used concurrently to accumulate the results from the
//execution of each substream
//        The concurrent map is unordered
        ConcurrentMap<Year, String> concMapYearToTitles =
                CD.cdList.parallelStream()
                        .collect(Collectors.toConcurrentMap(CD::year, CD::title, (t1, t2) -> t1 + ", " + t2));


//        Joining
        String concatTitles1 = CD.cdList.stream()
                .map(CD::title)
                .collect(Collectors.joining());

        String concatTitles2 = CD.cdList.stream()
                .map(CD::title)
                .collect(Collectors.joining(", "));

        String concatTitles3 = CD.cdList.stream()
                .map(CD::title)
                .collect(Collectors.joining(", ", "[", "]")); // (3) Delimiter, Prefix, Suffix
//[Java Jive, Java Jam, Lambda Dancing, Keep on Erasing, Hot Generics]

//        Grouping
        // Query: Group by number of tracks.

//         but only the groupingBy() method call at (3) can guarantee that the
//entries will be sorted in a TreeMap<Integer, List<CD>> according to the natural order
//for the Integer keys.

        Map<Integer, List<CD>> map11 =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(CD::noOfTracks));

        TreeMap<Integer, List<CD>> map33 = CD.cdList.stream()
                .collect(Collectors.groupingBy(CD::noOfTracks, // (3)
                        TreeMap::new,
                        Collectors.toList()));

        Map<Integer, Set<CD>> map22 =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(CD::noOfTracks, Collectors.toSet()));

        Map<Integer, Long> map55 = CD.cdList.stream()
                .collect(Collectors.groupingBy(CD::noOfTracks, Collectors.counting()));
//{6=1, 8=2, 10=2}

//        Multilevel Grouping
//        The stream pipeline below creates a classification map in which the CDs are first
//grouped by the number of tracks in a CD at (1), and then grouped by the musical
//genre of a CD at (2)
        Map<Integer, Map<Genre, List<CD>>> twoLevelGrp =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(CD::noOfTracks,
                                Collectors.groupingBy(CD::genre)));

        Map<Integer, Map<Genre, Long>> twoLevelGrp2 =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(CD::noOfTracks,
                                Collectors.groupingBy(CD::genre, Collectors.counting())));

//        If the collector returned by the Collectors.groupingBy() method is used in a parallel
//stream, the partial maps created during execution are merged to create the final
//map—as in the case of the Collectors.toMap() method (p. 983). Merging maps can
//carry a performance penalty.

//        a concurrent collector—that is, a collector that uses a single concurrent
//map to perform the reduction. The entries in such a map are unordered
        ConcurrentMap<Integer, Long> map66 = CD.cdList
                .parallelStream()
                .collect(Collectors.groupingByConcurrent(CD::noOfTracks,
                        Collectors.counting()));
//{6=1, 8=2, 10=2}

//        Partitioning
//        the partitioningBy() method can only create, at most, two partitions
//from the input elements.
        // Query: Partition by whether it is a pop music CD.
        Map<Boolean, List<CD>> map1 = CD.cdList.stream()
                .collect(Collectors.partitioningBy(CD::isPop));

        Map<Boolean, Long> map3 = CD.cdList.stream()
                .collect(Collectors.partitioningBy(CD::isPop, Collectors.counting()));

//        Multilevel partitioning
        Map<Boolean, Map<Year, List<CD>>> map2 =
                CD.cdList.stream()
                        .collect(Collectors.partitioningBy(CD::isPop,
                                Collectors.groupingBy(CD::year)));

//        Filtering downstream from grouping
        Map<Integer, List<CD>> grpByNoOfTracksFilterByPopCD =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(CD::noOfTracks,
                                Collectors.filtering(CD::isPop, Collectors.toList())));
//{6=[],
//8=[<Jaav, "Java Jive", 8, 2017, POP>],
//10=[<Funkies, "Lambda Dancing", 10, 2018, POP>]}

        Map<Integer, List<CD>> grpByNoOfTracksFilterByPopCD2 =
                CD.cdList.stream()
                        .filter(CD::isPop)
                        .collect(Collectors.groupingBy(CD::noOfTracks));
//        {8=[<Jaav, "Java Jive", 8, 2017, POP>],
//10=[<Funkies, "Lambda Dancing", 10, 2018, POP>]}

//        There are no surprises with partitioning, regardless of whether filtering is done
//before or after the partitioning, as partitioning always results in a map with two
//entries: one for the Boolean.TRUE key and one for the Boolean.FALSE key.

//      Partitions CDs released in 2018 according to whether a CD is a pop music
//CD or not.
        Map<Boolean, List<CD>> partbyPopCDsFilterByYear =
                CD.cdList.stream()
                        .collect(Collectors.partitioningBy(CD::isPop,
                                Collectors.filtering(cd -> cd.year.equals(Year.of(2018)), Collectors.toList())));

        // Filtering before partitioning.
        Map<Boolean, List<CD>> filterByYearPartbyPopCDs = CD.cdList.stream() // (2)
                .filter(cd -> cd.year().equals(Year.of(2018)))
                .collect(Collectors.partitioningBy(CD::isPop, Collectors.toList()));

//        Mapping Adapter for Downstream collectors
        Map<Year, Set<String>> titlesByYearInSet =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(
                                CD::year,               // Mapper
                                Collectors.mapping(CD::title, Collectors.toSet())   // Downstream collector
                        ));
// {2017=[Java Jive, Java Jam],
// 2018=[Hot Generics, Lambda Dancing, Keep on Erasing]}

        Map<Year, String> joinTitlesByYear =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(
                                CD::year,
                                Collectors.mapping(CD::title, Collectors.joining(", "))
                        ));

        Map<Year, Long> totalNumOfTracksByYear =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(
                                CD::year,
//                                Collectors.counting()
//                                Collectors.counting()   -> to count num of CDs in each year, not count total num of tracks
                                Collectors.summingLong(CD::noOfTracks))
                        );
        
//        Flat Mapping Adapter for Downstream Collectors
        // Given lists of CDs:
        List<CD> cdListA = List.of(CD.cd0, CD.cd1);
        List<CD> cdListB = List.of(CD.cd0, CD.cd1, CD.cd1);

        // Find unique CD titles in the given lists:
        Set<String> uniqueTitles = Stream.of(cdListA, cdListB)
                .flatMap(list -> list.stream().map(CD::title))
                .collect(Collectors.toSet());

        Set<String> uniqueTitles2 = Stream.of(cdListA, cdListB)
                .collect(Collectors.flatMapping(list -> list.stream().map(CD::title),
                        Collectors.toSet()));


    }
}
