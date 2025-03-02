package C5.OOP;


// Exceptions
class InvalidHoursException extends Exception {
}

class NegativeHoursException extends InvalidHoursException {
}

class ZeroHoursException extends InvalidHoursException {
}

abstract class Light {
    protected String lightType = "Generic Light"; // (1) Instance field

    protected double energyCost(int noOfHours) // (2) Instance method
            throws InvalidHoursException {
        System.out.print(">> Light.energyCost(int): ");
        if (noOfHours < 0)
            throw new NegativeHoursException();
        double cost = 00.20 * noOfHours;
        System.out.println("Energy cost for " + lightType + ": " + cost);
        return cost;
    }

    public Light makeInstance() { // (3) Instance method
        System.out.print(">> Light.makeInstance(): ");
        return new Light();
    }

    public void showSign() { // (4) Instance method
        System.out.print(">> Light.showSign(): ");
        System.out.println("Let there be light!");
    }

    protected static void printLightType() { // (5) Static method
        System.out.print(">> Static Light.printLightType(): ");
        System.out.println("Generic Light");
    }

    private void privateMethod() {
        System.out.println("Private method");
    }

    void defaultAccessMethod() {
        System.out.println("Default access method");
    }

    public static void staticMethod() {
        System.out.println("Static method");
    }

    public abstract void abstractMethod();

    public void overloadAbstractMethod(String methodName) {
        System.out.println(methodName);
    };
}

//______________________________________________________________________________
abstract class TubeLight extends Light {
    public static String lightType = "Tube Light"; // (6) Hiding field at (1).

    @Override
    public double energyCost(final int noOfHours) // (7) Overriding instance
            throws ZeroHoursException { // method at (2).
        System.out.print(">> TubeLight.energyCost(int): ");
        if (noOfHours == 0)
            throw new ZeroHoursException();
        double cost = 00.10 * noOfHours;
        System.out.println("Energy cost for " + lightType + ": " + cost);
        return cost;
    }

    public double energyCost() { // (8) Overloading method at (7).
        System.out.print(">> TubeLight.energyCost(): ");
        double flatrate = 20.00;
        System.out.println("Energy cost for " + lightType + ": " + flatrate);
        return flatrate;
    }

    public static int energyCost(String str) {
        return str.length();
    }

    @Override
    public TubeLight makeInstance() { // (9) Overriding instance method at (3).
        System.out.print(">> TubeLight.makeInstance(): ");
        return new TubeLight();
    }

    public static void printLightType() { // (10) Hiding static method at (5).
        System.out.print(">> Static TubeLight.printLightType(): ");
        System.out.println(lightType);
    }

    @Override
    void defaultAccessMethod() {
        System.out.println("The overriding of default access method");
    }

    public static void staticMethod() {
        System.out.println("Static method");
    }

    @Override
    public void abstractMethod() {

    }

    @Override
    public abstract void overloadAbstractMethod(String methodName);
}
//______________________________________________________________________________

class NeonLight extends TubeLight {
    // ...
    public void demonstrate() // (11)
            throws InvalidHoursException {
        super.showSign(); // (12) Invokes method at (4)
        super.energyCost(50); // (13) Invokes method at (7)
        super.energyCost(); // (14) Invokes method at (8)
        ((Light) this).energyCost(50); // (15) Invokes method at (7)
        System.out.println(super.lightType); // (16) Accesses field at (6)
        System.out.println(((Light) this).lightType); // (17) Accesses field at (1)
        super.printLightType(); // (18) Invokes method at (10)
        ((Light) this).printLightType(); // (19) Invokes method at (5)
    }
}

public class Overriding {
    public static void main(String[] args) // (11)
            throws InvalidHoursException {
        TubeLight tubeLight = new TubeLight(); // (12)
        Light light1 = tubeLight; // (13) Aliases.
        Light light2 = new Light(); // (14)
        System.out.println("Invoke overridden instance method:");
        tubeLight.energyCost(50); // (15) Invokes method at (7).
        light1.energyCost(50); // (16) Invokes method at (7).
        light2.energyCost(50); // (17) Invokes method at (2).
        System.out.println(
                "\nInvoke overridden instance method with covariant return:");
        System.out.println(
                light2.makeInstance().getClass()); // (18) Invokes method at (3).
        System.out.println(
                tubeLight.makeInstance().getClass()); // (19) Invokes method at (9).
        System.out.println("\nAccess hidden field:");
        System.out.println(tubeLight.lightType); // (20) Accesses field at (6).
        System.out.println(light1.lightType); // (21) Accesses field at (1).
        System.out.println(light2.lightType); // (22) Accesses field at (1).
        System.out.println("\nInvoke hidden static method:");
        tubeLight.printLightType(); // (23) Invokes method at (10).
        light1.printLightType(); // (24) Invokes method at (5).
        light2.printLightType(); // (25) Invokes method at (5).
        System.out.println("\nInvoke overloaded method:");
        tubeLight.energyCost(); // (26) Invokes method at (8).

//        ----------------------------------------------------------------------------
        NeonLight neonRef = new NeonLight();
        neonRef.demonstrate();
    }
}
