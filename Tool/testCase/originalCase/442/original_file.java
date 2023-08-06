
public class MyClass {
    private int positive = 1; // cannot be final, is modified in ctor

    public MyClass(int value) {
        // if negative keep default
        if (value > 0) {
            positive = value;
        }
    }
}
        