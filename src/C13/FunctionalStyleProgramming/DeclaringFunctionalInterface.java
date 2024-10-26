package C13.FunctionalStyleProgramming;

import java.util.function.Predicate;

public class DeclaringFunctionalInterface {

    // A functional interface has exactly one abstract method.

    @FunctionalInterface
    interface StrPredicate {
        boolean test(String str);   // abstract method
    }

    @FunctionalInterface
    interface NewStrPredicate {
        boolean test(String str);       // abstract method

        default void msg(String str) {  // default method
            System.out.println(str);
        }

        static void info() {            // static method
            System.out.println("Testing!");
        }
    }

    interface Funky {
        @Override int hashCode();           // From Object class
        @Override boolean equals(Object obj);   // From Object class
        @Override String toString();        // From Object class
        boolean doTheFunk(Object obj);      // abstract method
    }

    // Java SE 17 provides general-purpose functional interfaces
//    @FunctionalInterface
//    interface Predicate<T> {
//        boolean test(T element);
//        // ...
//    }


    public static <T> boolean testPredicate(T object, Predicate<T> predicate) {
        return predicate.test(object);
    }

    // Class implementation of Predicate<String>
    static class PredicateTest implements Predicate<String> {

        @Override
        public boolean test(String s) {
            return s.startsWith("A");
        }
    }

    // Anonymous class implementation of Predicate<String>
    static Predicate<String> testLength = new Predicate<>() {
        public boolean test(String s) {
            return s.length() < 4;
        }
    };

    // Client code
    public static void main(String[] args) {
        System.out.println(testPredicate("Anna", new PredicateTest())); // true
        System.out.println(testPredicate("Anna", testLength)); // false
    }
}
