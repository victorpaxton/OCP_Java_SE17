package C13.FunctionalStyleProgramming;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class LambdaExpression {

    StringBuilder seq;

    LambdaExpression(String str) {
        this.seq = new StringBuilder(str);
    }

    public Predicate<String> getPredicate() {
        return str -> {
            this.seq.append(str);
            return str.length() > 5;
        };
    }

    public static void main(String[] args) {
        LambdaExpression obj = new LambdaExpression("Hello ");
        Predicate<String> p = obj.getPredicate();
        System.out.println(p.test("World!"));

        // Lambda parameters

        // Declared-type parameters
        // (Integer a, String y) -> {};

        // Inferred-type parameters
        // (a, b) -> {};

        // var-type inferred parameters
        // (var a, var b) -> {};

        Predicate<Integer> p1 = i -> i % 2 == 0;    // Integer -> boolean
        IntPredicate p2 = i -> i % 2 == 0;          // int -> boolean

        boolean result1 = p1.test(2021); // false
        boolean result2 = p2.test(2020);  // true

    }
}
