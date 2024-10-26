package C15.Collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class IterationHashSetAndLinkedHashSet {

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        set1.add("breakfast"); set1.add("lunch"); set1.add("dinner");
        // Serving meals from a HashSet, order can vary
        for (String meal : set1) {
            System.out.println(meal);
        }

        Set<String> set2 = new LinkedHashSet<>();
        set2.add("breakfast"); set2.add("lunch"); set2.add("dinner");
        // Serving meals from a LinkedHashSet, always insertion order
        for (String meal : set2) {
            System.out.println(meal);   // breakfast, lunch, dinner
        }

        
    }
}
