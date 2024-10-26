package C13.FunctionalStyleProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Functions {
    public static void main(String[] args) {
        Function<Integer, Boolean> boolExpr = i -> 50 <= i && i < 100;
        System.out.println(boolExpr.apply(99));

        Function<Integer, Double> milesToKms = miles -> 1.6 * miles;
        System.out.printf("%dmi = %.2fkm%n", 24, milesToKms.apply(24));

        String[] strArray = {"One", "Two", "Three", "Four", "Five", "Six"};
        List<StringBuilder> sbList = listBuilder(strArray, s -> new StringBuilder(s));
        System.out.println(sbList);

        List<Integer> lenList = listBuilder(strArray, s -> s.length());
        System.out.println(lenList);

        // Composing functions
        Function<String, String> f = s -> s + "-One";
        Function<String, String> g = s -> s + "-Two";
        System.out.println(f.compose(g).apply("Three")); // Three-Two-One
        System.out.println(g.andThen(f).apply("Three")); // Three-Two-One
        System.out.println(f.apply(g.apply("Three")));  // Three-Two-One

        BiFunction<Double, Double, Double> areaOfRectangle =
                (length, width) -> length * width;
        System.out.printf("%.2f x %.2f = %.2f%n", 25.0, 4.0, areaOfRectangle.apply(25.0, 4.0));
    }

    public static <R, T> List<R> listBuilder(T[] strArray, Function<T, R> func) {
        List<R> result = new ArrayList<>();
        for (T str : strArray) {
            result.add(func.apply(str));
        }
        return result;
    }
}
