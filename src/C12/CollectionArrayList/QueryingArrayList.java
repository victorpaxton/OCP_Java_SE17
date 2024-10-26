package C12.CollectionArrayList;

import java.util.ArrayList;
import java.util.List;

public class QueryingArrayList {

    private static final List<String> languages = new ArrayList<>();

    static {
        // add elements to the ArrayList
        languages.add("java");
        languages.add("javascript");
        languages.add("swift");
        languages.add("python");
        languages.add("golang");
        languages.add("Pascal");
        languages.add(null);
        languages.add("golang");
        System.out.println("ArrayList: " + languages);
    }

    public static void main(String[] args) {
        System.out.println(languages.size()); // 8
        System.out.println(languages.isEmpty()); // false
        System.out.println(languages.get(1)); // javascript
        System.out.println(languages.contains("golang")); // true
        System.out.println(languages.indexOf("golang")); // 4
        System.out.println(languages.lastIndexOf("golang")); // 7
        System.out.println(languages.indexOf("c++")); // -1

        // List<E> subList(int fromIndex, int toIndex)
        List<String> view = languages.subList(1, 5);
        System.out.println(view); // [javascript, swift, python, golang]
        // *** Any changes in the view are reflected in the underlying list, and vice versa
        languages.set(1, "JAVASCRIPT");
        System.out.println(view); // [JAVASCRIPT, swift, python, golang]
        view.set(1, "SWIFT");
        System.out.println(languages); // [java, JAVASCRIPT, SWIFT, python, golang, Pascal, null, golang]

        List<String> comparedList = new ArrayList<>();
        comparedList.add("JAVASCRIPT");
        comparedList.add("SWIFT");
        comparedList.add("python");
        comparedList.add("golang");
        System.out.println(view.equals(comparedList)); // true
    }
}
