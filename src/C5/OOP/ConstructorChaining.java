package C5.OOP;

class Light {
    // Fields:
    private int noOfWatts;
    private boolean indicator;
    private String location;

    // Constructors:
    Light() { // (1) No-argument constructor
        this(0, false);
        System.out.println("Returning from no-argument constructor no. 1.");
    }

    Light(int watt, boolean ind) { // (2)
        this(watt, ind, "X");
        System.out.println("Returning from constructor no. 2.");
    }

    Light(int noOfWatts, boolean indicator, String location) { // (3)
        super();
        this.noOfWatts = noOfWatts;
        this.indicator = indicator;
        this.location = location;
        System.out.println("Returning from constructor no. 3.");
    }
}

class TubeLight extends Light {
    // Instance variables:
    private int tubeLength;
    private int colorNo;
    // Constructors:
    private TubeLight(int tubeLength, int colorNo) { // (5)
        this(tubeLength, colorNo, 100, true, "Unknown");
        System.out.println(
                "Returning from constructor no. 1 in class TubeLight");
    }
    TubeLight(int tubeLength, int colorNo, int noOfWatts,
              boolean indicator, String location) { // (6)
        super(noOfWatts, indicator, location); // (7)
        this.tubeLength = tubeLength;
        this.colorNo = colorNo;
        System.out.println(
                "Returning from constructor no. 2 in class TubeLight");
    }
}
//

class NeonLight extends TubeLight {

    private String sign;

    NeonLight() {
//        super(10, 10);
        super(10, 2, 100, true, "Roof-top"); // (2) Cannot be commented out.
        sign = "All will be a revealed!";
    }
}


public class ConstructorChaining {

    public static void main(String[] args) { // (4)
        System.out.println("Creating Light object no. 1.");
        Light light1 = new Light(); // (5)
        System.out.println("Creating Light object no. 2.");
        Light light2 = new Light(250, true); // (6)
        System.out.println("Creating Light object no. 3.");
        Light light3 = new Light(250, true, "attic"); // (7)

        System.out.println("Creating a TubeLight object.");
        TubeLight tubeLightRef = new TubeLight(20, 5); // (8)


    }
}
