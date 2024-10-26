package C13.FunctionalStyleProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Suppliers {

    public static void main(String[] args) {
        // The StringBuilder is not created until the get() method of the supplier is called
        Supplier<StringBuilder> createSB = () -> new StringBuilder("Howdy!");
        System.out.println(createSB.get());

        String str = "uppercase me!";
        Supplier<String> makeUpperCase = () -> str.toUpperCase();
        System.out.println(makeUpperCase.get()); // UPPERCASE ME!
        System.out.println(str); // "uppercase me!"

        Random numGen = new Random();
        IntSupplier intSupplier = () -> numGen.nextInt(100); // get a number between 0 (inclusive) and 100 (exclusive)
        System.out.println(intSupplier.getAsInt());

        List<Integer> intList = listBuilder(5, intSupplier);
        System.out.println(intList);
    }

    public static List<Integer> listBuilder(int length, IntSupplier intSupplier) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(intSupplier.getAsInt());
        }
        return result;
    }
}
