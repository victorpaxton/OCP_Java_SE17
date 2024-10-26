package C13.FunctionalStyleProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExpVSAnonymousClass {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Otto");
        words.add("ADA");
        words.add("Alyla");
        words.add("Bob");
        words.add("HannaH");
        words.add("Java");
        System.out.println("List of words: " + words);

        System.out.println("------------ Using anonymous classes ----------------");
        List<String> palindromesA = filterList(words,
                new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.equals(new StringBuilder(s).reverse().toString());
                    }
                });
        System.out.println(palindromesA);

        System.out.println("------------ Using Lambda Expression ----------------");
        List<String> palindromesB = filterList(words,
                str -> str.equals(new StringBuilder(str).reverse().toString()));
        System.out.println(palindromesB);


    }

    private static <E> List<E> filterList(List<E> words, Predicate<E> predicate) {
        List<E> result = new ArrayList<>();
        for (E word : words) {
            if (predicate.test(word)) {
                result.add(word);
            }
        }
        return result;
    }
}
