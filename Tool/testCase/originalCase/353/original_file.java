
import lombok.Getter;
public class Foo {
    @Getter(lazy=true) private final String cached = expensive();

    private String expensive() { return "expensive calculation that should be cached."; }
}
        