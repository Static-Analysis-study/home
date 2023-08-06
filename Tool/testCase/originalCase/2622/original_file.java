
public class Foo {

    private int[] foo() {
        bar(() -> {
            baz();
            return null;
        });

        return new int[0];
    }
}
    