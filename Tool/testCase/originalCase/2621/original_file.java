
public class Foo {

    private int[] foo() {
        bar(new SomeInterface() {
            @Override
            public Object run() {
                baz();
                return null;
            }
        });

        return new int[0];
    }
}
    