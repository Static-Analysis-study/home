
public class Foo {
    @Deprecated
    @SuppressWarnings("PMD.ImmutableField")
    private int x;

    public Foo() {
        x = 2;
    }
}
        