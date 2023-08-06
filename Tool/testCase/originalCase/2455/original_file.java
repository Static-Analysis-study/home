
import java.io.FileInputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
class Buzz implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("name", String.class),
        new ObjectStreamField("stream", FileInputStream.class),
    };

    private FileInputStream stream; // not serializable
    private String name;
}
