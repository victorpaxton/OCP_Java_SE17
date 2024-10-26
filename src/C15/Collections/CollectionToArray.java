package C15.Collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.IntFunction;

public class CollectionToArray {
    public static void main(String[] args) {

        Collection<String> strSet = new HashSet<>();
        strSet.add("2021"); strSet.add("2022"); strSet.add("2023");
        int n = strSet.size();

        Object[] objects = strSet.toArray(); // (1)
// String[] string = strSet.toArray(); // (2) Compile-time error!
        System.out.println(Arrays.toString(objects));

        Object[] objArray = strSet.toArray(new Object[0]); // (3)
        System.out.println("Array size: " + objArray.length);               // 3
        System.out.println("Array type: " + objArray.getClass().getName()); // [Ljava.lang.Object;
        System.out.println("Actual element type: " +                        // java.lang.String
                objArray[0].getClass().getName());

        String[] strArray1 = new String[0];
        String[] strArray2 = strSet.toArray(strArray1); // (4)
        System.out.println("strArray1 == strArray2: " + (strArray1 == strArray2));
        System.out.println(Arrays.toString(strArray1));
        System.out.println(Arrays.toString(strArray2));

        String[] strArray3 = new String[n];
        String[] strArray4 = strSet.toArray(strArray3); // (5)
        System.out.println("strArray3 == strArray4: " + (strArray3 == strArray4));
        System.out.println(Arrays.toString(strArray3));
        System.out.println(Arrays.toString(strArray4));

        IntFunction<String[]> createStrArray = nn -> new String[nn]; // (7)
        String[] strArray5 = strSet.toArray(createStrArray); // (8a)
        String[] strArray6 = strSet.toArray(String[]::new); // (8b)
        String[] strArray7 = strSet.toArray(createStrArray.apply(0)); // (8c)
        System.out.println("strArray5: " + Arrays.toString(strArray5));
        System.out.println("strArray6: " + Arrays.toString(strArray6));
        System.out.println("strArray7: " + Arrays.toString(strArray7));
    }
}
