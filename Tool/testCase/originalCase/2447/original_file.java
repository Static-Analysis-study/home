
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.ObjectStreamException;
class Foo implements Serializable {
    private FileInputStream foo; // no violation, because writeReplace/readResolve is present
    private Object writeReplace() throws ObjectStreamException {}
    private Object readResolve() throws ObjectStreamException {}
}
