package C15.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Collections {
    public static void main(String[] args) {
        Collection<String> collectionOfNames = new ArrayList<String>();
        java.util.Collections.addAll(collectionOfNames,  "Alice", "Bob", "Tom","Dick","Harriet");
        System.out.println(collectionOfNames);

        // Using an explicit iterator
        Iterator<String> iterator = collectionOfNames.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name.toUpperCase());
        }
        System.out.println();

        // Using forEachRemaining of Iterator interface
        iterator = collectionOfNames.iterator();
        iterator.forEachRemaining(name -> System.out.println(name.toUpperCase()));
        System.out.println();

        // Using for (:)
        for (String name : collectionOfNames) {
            System.out.println(name.toUpperCase());
        }
        System.out.println();

        // Using forEach
        collectionOfNames.forEach(name -> System.out.println(name.toUpperCase()));
        System.out.println();

        // Filtering using explicit iterator
        iterator = collectionOfNames.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.length() == 3) {
                iterator.remove();
            }
        }
        System.out.println(collectionOfNames);

        // Filtering using removeIf
        collectionOfNames.removeIf(name -> name.length() == 3);
        System.out.println(collectionOfNames);

        collectionOfNames.stream().forEach(name -> System.out.println(name.toUpperCase()));
    }
}
