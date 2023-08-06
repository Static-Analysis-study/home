
import java.util.Arrays;
import java.util.Collection;

class Foo {
    public Foo(String ... names) {
        setNames(Arrays.asList(names));
    }

    public void setNames(Collection<String> names) { }
}
