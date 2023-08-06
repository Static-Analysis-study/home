
import java.io.FileInputStream;
import java.io.Serializable;
public class Foo implements Serializable {
    private transient FileInputStream foo;
}
        