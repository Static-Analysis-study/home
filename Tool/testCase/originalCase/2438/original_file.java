
import java.io.Serializable;
public class Foo implements Serializable {
    private String foo;
    private String bar = foo;
}
        