package C16.Streams;

import java.time.Year;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

enum Genre {POP, JAZZ, OTHER}

record CD(String artist, String title, int noOfTracks, Year year, Genre genre)
        implements Comparable<CD> {

    // Additional get methods:
    public boolean isPop() {
        return this.genre == Genre.POP;
    }

    public boolean isJazz() {
        return this.genre == Genre.JAZZ;
    }

    public boolean isOther() {
        return this.genre == Genre.OTHER;
    }

    // Provide own implementation of the toString() method.
    @Override
    public String toString() {
        return String.format("<%s, \"%s\", %d, %s, %s>",
                this.artist, this.title, this.noOfTracks, this.year, this.genre);
    }

    /**
     * Compare by artist, by title, by number of tracks, by year, and by genre.
     */
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
    public static final List<CD> cdList = List.of(cd0, cd1, cd2, cd3, cd4);
    public static final List<CD> cdList2 = List.of(cd3, cd4);

    // An array of CDs.
    public static final CD[] cdArray = {cd0, cd1, cd2, cd3, cd4};
}

// Radio station with a playlist.
class RadioPlaylist { // (1)
    private String radioStationName;
    private List<CD> Playlist;

    public RadioPlaylist(String radioStationName, List<CD> cdList) {
        this.radioStationName = radioStationName;
        this.Playlist = cdList;
    }

    public String getRadioStationName() {
        return this.radioStationName;
    }

    public List<CD> getPlaylist() {
        return this.Playlist;
    }
}

public class CollectorsFlatMapping {

    public static void main(String[] args) {
// Some lists of CDs: (2)
        List<CD> cdList1 = List.of(CD.cd0, CD.cd1, CD.cd1, CD.cd2);
        List<CD> cdList2 = List.of(CD.cd0, CD.cd0, CD.cd3);
        List<CD> cdList3 = List.of(CD.cd0, CD.cd4);

        // Some radio playlists: (3)
        RadioPlaylist pl1 = new RadioPlaylist("Radio JVM", cdList1);
        RadioPlaylist pl2 = new RadioPlaylist("Radio JRE", cdList2);
        RadioPlaylist pl3 = new RadioPlaylist("Radio JAR", cdList3);

        // List of radio playlists:
        List<RadioPlaylist> radioPlaylists = List.of(pl1, pl2, pl3);

        // Map of radio station names and set of CD titles they played:
        Map<String, Set<String>> map = radioPlaylists.stream()
                .collect(Collectors.groupingBy(RadioPlaylist::getRadioStationName,
                        Collectors.flatMapping(list -> list.getPlaylist().stream().map(CD::title), Collectors.toSet())));

// Finishing Adapter for Downstream Collectors -  collectingAndThen()
        Map<Year, Integer> maxTracksByYear =
                CD.cdList.stream().collect(Collectors.groupingBy(
                        CD::year,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(CD::noOfTracks)),
                                optCD -> optCD.map(CD::noOfTracks).orElse(0))
                ));

        // Average tracks by genre
        Map<Genre, String> avgTracksByGenre =
                CD.cdList.stream().collect(
                        Collectors.groupingBy(CD::genre,
                                Collectors.collectingAndThen(
                                        Collectors.averagingDouble(CD::noOfTracks),
                                        avg -> String.format("%.2f", avg)
                                )
                ));

//        Downstream Collectors for Functional Reduction
        // Count numbers of jazz music CDs.
        Long noOfJazzCDs = CD.cdList.stream().filter(CD::isJazz).count();

        // Count number of CDs in each Genre group
        Map<Genre, Long> grpByGenre =
                CD.cdList.stream().collect(
                        Collectors.groupingBy(CD::genre,
                                Collectors.counting())
                );

        // Group CDs by musical genre, and max CD in each group.
        Comparator<CD> cmp = Comparator.naturalOrder();
        Map<Genre, Optional<CD>> maxByGenre =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(CD::genre,
                                Collectors.maxBy(cmp)));

//        Sum tracks
        Integer sumTracks = CD.cdList.stream().mapToInt(CD::noOfTracks).sum();

//        Sum tracks each genre
        Map<Genre, Integer> sumTracksByGenre = CD.cdList.stream()
                .collect(Collectors.groupingBy(CD::genre,
                        Collectors.summingInt(CD::noOfTracks)));

//        Average no of tracks
        Double avgTracks = CD.cdList.stream()
                .mapToInt(CD::noOfTracks).average().orElse(0);

        // Average no of tracks by genre
        Map<Genre, Double> avgNoOfTracksByGenre = CD.cdList.stream()
                .collect(Collectors.groupingBy(CD::genre,
                        Collectors.averagingDouble(CD::noOfTracks)));

//        Statistics
        Map<Genre, IntSummaryStatistics> statsByGenre =
                CD.cdList.stream().collect(
                        Collectors.groupingBy(CD::genre,
                                Collectors.summarizingInt(CD::noOfTracks))
                );


//        Reducing
        // Comparator to compare CDs by title.
        Comparator<CD> cmpByTitle = Comparator.comparing(CD::title); // (1)
// Comparator to compare strings by their length.
        Comparator<String> byLength = Comparator.comparing(String::length); // (2)

        Optional<String> longestTitle1 = CD.cdList.stream()
                .map(CD::title)
                .reduce(BinaryOperator.maxBy(byLength));

        Map<Year, Optional<CD>> cdWithMaxTitleByYear =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(CD::year,
                                Collectors.maxBy(cmpByTitle)));

        Map<Year, String> longestTitleByYear =
                CD.cdList.stream()
                        .collect(Collectors.groupingBy(CD::year,
                                Collectors.reducing("", CD::title, BinaryOperator.maxBy(byLength))));

        Map<Year, Optional<String>> longestTitleByYear2 = CD.cdList.stream()
                .collect(Collectors.groupingBy(
                        CD::year,
                        Collectors.mapping(CD::title, // (7) Downstream collector
                                Collectors.maxBy(byLength))
                ));
        System.out.println(longestTitleByYear2);
// {2017=Optional[Java Jive], 2018=Optional[Keep on Erasing]}
        System.out.println(longestTitleByYear2.get(Year.of(2018))
                .orElse("No title.")); // Keep on Erasing


    }
}
