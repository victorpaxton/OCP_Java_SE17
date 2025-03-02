package C5.OOP;

import jdk.incubator.vector.VectorOperators;

public abstract class TestInterfaceImpl implements TestInterface{
    @Override
    public void abstractMethod() {

    }

    abstract static void abstractStaticMethod() {}


    public static void main(String[] args) {
        TestInterface.staticMethod();
    }
}
