
public class MyClass {
    private int positive; // can be final

    public MyClass(int value) {
        if (value > 0) {
            positive = value;
        } else {
            positive = 1;
        }
    }
}
        