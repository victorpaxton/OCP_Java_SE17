package C13.FunctionalStyleProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;

public class Consumers {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Otto");
        words.add("ADA");
        words.add("Alyla");
        words.add("Bob");
        words.add("HannaH");
        words.add("Java");
        System.out.println("List of words: " + words);

        Consumer<Double> formatter = d -> System.out.printf("Value: %.2f%n", d);
        formatter.accept(3.145);

        StringBuilder sb1 = new StringBuilder("Banana");
        Consumer<StringBuilder> resizeSB = s -> s.setLength(4);
        resizeSB.accept(sb1);
        System.out.println(sb1); // Bana

        Consumer<StringBuilder> reverseSB = s -> s.reverse();
        reverseSB.accept(sb1);
        System.out.println(sb1); // anaB

        Consumer<StringBuilder> printSB = s -> System.out.println("StringBuilder: " + s);
        printSB.accept(sb1);

        words.forEach(word -> System.out.println(word.toLowerCase()));
        // Using anonymous class
        words.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toLowerCase());
            }
        });

        words.forEach(word -> {
            if (word.length() % 2 == 0)
                System.out.println(word);
        });

        // Composing consumers
        resizeSB.andThen(reverseSB).andThen(printSB).accept(sb1);

        int k = 15;
        IntConsumer sqrt = i -> System.out.printf("Value: %.2f%n", Math.sqrt(i));
        IntConsumer sqr = i -> System.out.printf("%d%n", i*i);
        sqrt.andThen(sqr).accept(k); // 3.87
                                    // 225

        BiConsumer<String, Double> formatPrinter =
                (format, obj) -> System.out.printf(format, obj);
        formatPrinter.accept("Math.PI: %10.3f%n", Math.PI);

        // HashMap<String, Integer>:
        // (key, value) -> {}

        ObjIntConsumer<StringBuilder> resizeSb2 = (sb, len) -> sb.setLength(len);
        resizeSb2.accept(sb1, 2);
        System.out.println(sb1);
    }
}
