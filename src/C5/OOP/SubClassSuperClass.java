package C5.OOP;

// Classes
class Foo {
    public int i;

    public static int k;

    public static void f() { /* ... */ }

    public static void g() { /* ... */ }
}

class Bar extends Foo {

    public int i;
    public int j;

    public static int k;

    public static void g() {
        System.out.println(k);
        /* ... */ }
}

public class SubClassSuperClass {
    public static void main(String[] args) {
        Foo a = new Bar();
        /*
        a cannot access i because it is private to Foo
        a connot access j because a is a reference of type Foo
        a can call f() (inherited from Foo)
        a can call g() (overidden in Bar)
         */
        Bar b = new Bar();
        /*
        b cannot access i because it is private to Foo
        b can access j
        b can access f()
        b can access g()
         */
// (1) INSERT STATEMENT HERE
    }
}
