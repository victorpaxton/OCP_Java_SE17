package C5.OOP;

public interface TestInterface {

    void abstractMethod();

    default void defaultMethod() {

    }

    static void staticMethod() {

    }

    private void privateMethod() {

    }

    private static void privateStaticMethod() {

    }
}
