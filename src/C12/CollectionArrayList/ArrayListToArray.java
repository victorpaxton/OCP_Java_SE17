package C12.CollectionArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListToArray {
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
        // Object[] toArray()
        Object[] strLanguages = languages.toArray();
        for (Object lang : strLanguages) {
            String strLang = (String) lang;
            System.out.println(strLang.length());
        }

        // <T> T[] toArray(T[] a)
        String[] strLanguages2 = languages.toArray(new String[1]);
        System.out.println(Arrays.toString(strLanguages2)); // [java, javascript, swift, python, golang, Pascal]

        String[] strLanguages3 = languages.toArray(new String[8]);
        System.out.println(Arrays.toString(strLanguages3));
    }
}
