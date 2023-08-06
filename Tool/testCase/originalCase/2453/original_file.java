
import java.io.FileInputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
class Buzz implements Serializable {
    public static final ObjectStreamField[] FIELDS = {
        new ObjectStreamField("name", String.class),
        new ObjectStreamField("stream", FileInputStream.class),
     };

    private static final long serialVersionUID = 1L;
    private static final ObjectStreamField[] serialPersistentFields = FIELDS;

    private FileInputStream stream; // not serializable
    private String name;
}
