package C12.CollectionArrayList;

import java.util.ArrayList;
import java.util.List;

public class IteratingArrayList {
    private static final List<String> languages = new ArrayList<>();

    static {
        // add elements to the ArrayList
        languages.add("java");
        languages.add("javascript");
        languages.add("swift");
        languages.add("python");
        languages.add("golang");
        languages.add("Pascal");
        System.out.println("ArrayList: " + languages);
    }

    public static void main(String[] args) {
        // for(;;) loop
        for (int i = 0; i < languages.size(); i++) {
            System.out.println(i + ": " + languages.get(i));
        }

        // for (:) loop
        for (String language : languages) {
            System.out.println(language);
        }
        // for (:) loop does not allow the list structure to be modified
        for (String language : languages) {
            if (language.length() <= 5) {
                languages.remove(language); // ConcurrentModificationException
            }
        }
        System.out.println(languages);
    }
}
