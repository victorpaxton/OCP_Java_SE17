package C5.OOP;

class A {
    int max(int x, int y) {
        return (x > y) ? x : y;
    }
}

class B extends A {
    int max(int x, int y) {
        return super.max(y, x) - 10;
    }
}

class C extends B {
    int max(int x, int y) {
        return super.max(x + 10, y + 10);
    }
}

public class UserClass {
    public static void main(String[] args) {
        B b = new C();
        System.out.println(b.max(13, 29));
    }
}