package C13.FunctionalStyleProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Predicates {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Otto");
        words.add("ADA");
        words.add("Alyla");
        words.add("Bob");
        words.add("HannaH");
        words.add("Java");
        System.out.println("List of words: " + words);

        // ------------ Remove all palindromes --------------------
        Predicate<String> isPalindrome = str -> new StringBuilder(str).reverse().toString().equalsIgnoreCase(str);
//        words.removeIf(isPalindrome);
//        words.removeIf(str -> new StringBuilder(str).reverse().toString().equalsIgnoreCase(str));
        System.out.println(words);

        // Using anonymous class
//        words.removeIf(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return new StringBuilder(s).reverse().toString().equalsIgnoreCase(s);
//            }
//        });

        // Remove all words with even length
        Predicate<String> isEvenLength = str -> str.length() % 2 == 0;
//        words.removeIf(str -> str.length() % 2 == 0);

        // Remove all words starting with 'A'
        Predicate<String> startWithA = str -> str.startsWith("A");
//        words.removeIf(str -> str.startsWith("A"));

        // Composing predicates
        // negate (!) - and - or - not - isEqual

        // A string that is not a palindrome
        Predicate<String> isNotPalindrome = isPalindrome.negate();

        // A string with even length or starting with an "A", and is not a palindrome
        // (x.or(y)).and(z)
        Predicate<String> composedPredicate = isEvenLength.or(startWithA).and(isNotPalindrome);

        // A string with even length, or it starts with an "A" and is not a palindrome
        Predicate<String> composedPredicate2 = isEvenLength.or(startWithA.and(isNotPalindrome));
        words.removeIf(composedPredicate2);
        System.out.println(words);

        // a || b && !c -> a || (b && (!c))

        Predicate<String> isEqualToTarget = Predicate.isEqual("Ada");
        System.out.println(isEqualToTarget.test("Ada")); // true
        System.out.println(isEqualToTarget.test("Adda")); // false

        System.out.println(Predicate.isEqual(null).test(null)); // true

        // BiPredicate
        BiPredicate<String, List<String>> isMember =
                (element, list) -> list.contains(element);
        System.out.println(isMember.test("ADA", words)); // true
        System.out.println(isMember.test("Ada", words)); // false

        // determine if a file name has an extension from a specified set of file extensions
        BiPredicate<String, Set<String>> selector =
                (filename, extensions) -> extensions.contains(filename.substring(filename.lastIndexOf('.')));

        // filterList(List<String> fileNames, Set<String> fileExtensions, BiPredicate<String, Set<String>> selector);
    }
}
