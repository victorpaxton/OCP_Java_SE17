package C15.Collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sets {
    public static void main(String[] args) {

        // A set created by Set.of() cannot be modified
        Set<String> set = Set.of("Tom", "Dick", "Harriet");
//        set.add("Harry");   // UnsupportedOperationException
//        set.remove(2);   // UnsupportedOperationException

        System.out.println(set);
        System.out.println(set instanceof HashSet);

        // The Set.of() method does not allow null elements
//        Set<String> coinSet = Set.of("dime", "nickel", null);   // NullPointerException

        String[] strArray = {"Tom", "Dick", "Harriet"};
        // Modifying the original array does not reflect in the set created by he Set.of() method.
        strArray[0] = "Jerry";
        Set<String> strSet = Set.of(strArray); // (1) Set of String
        Set<String[]> strArraySet = Set.<String[]>of(strArray); // (2) Set of String[]
        System.out.println(Arrays.toString(strArray));
        System.out.println(strSet); // [Harriet, Dick, Tom]
        System.out.println(strArraySet); // [[Ljava.lang.String;@3b22cdd0]

        // Modifying the original set does not reflect in the copy of the set
        Set<String> fab4 = new HashSet<>();
        fab4.add("John"); fab4.add("Paul"); fab4.add("George"); fab4.add("Ringo");
        System.out.println(fab4); // [George, John, Ringo, Paul]
        Set<String> fabAlways = Set.copyOf(fab4); // (1)
        fab4.remove("John"); fab4.remove("George"); // Modify original set
        // The list created by the Set.copyOf() method cannot be modified
//        fabAlways.remove("Ringo");  // UnsupportedOperationException
        System.out.println(fab4); // [Ringo, Paul]
        System.out.println(fabAlways); // [John, Paul, Ringo, George]

    }
}
