package C16.Streams;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Student {
    private int id;
    private String name;
    private Double gpa;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getGpa() {
        return gpa;
    }

    Student(int id, String name, Double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
}

public class PracticeQuestion {

    public static void main(String[] args) {
        List<String> values = List.of("AA", "BBB", "C", "DD", "EEE");
        Map<Integer, List<String>> map = null;
        // (1) INSERT CODE HERE
        map = values.stream().filter(v -> v.length() == 1)
                        .collect(Collectors.groupingBy(String::length));

        map.forEach((i, s) -> System.out.println(i + " " + s));


        Student s1 = new Student(1, "Student 1", 9.6);
        Student s2 = new Student(2, "Student 2", 8.5);
        Student s3 = new Student(3, "Student 3", 9.6);
        Student s4 = new Student(4, "Student 4", 7.3);
        Student s5 = new Student(5, "Student 5", 4.6);
        Student s6 = new Student(1, "Student 1", 8.5);
        Student s7 = new Student(1, "Student 1", 10.0);
        Student s8 = new Student(4, "Student 4", 5.3);

        List<Student> students = List.of(s1, s2, s3, s4, s5, s6, s7, s8);

        // Return students list that contains only highest gpa of each student and sort by gpa descending.
        List<Student> res = students.stream().collect(
                Collectors.groupingBy(Student::getId,
                        Collectors.maxBy(Comparator.comparing(Student::getGpa)))
        ).values().stream()
                .flatMap(Optional::stream)
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .toList();

        System.out.println(res);

        // ----------------------------------------------------------------------------


        // ----------------------------------------------------------------

    }
}
