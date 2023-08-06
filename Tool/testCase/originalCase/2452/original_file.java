
import java.io.FileInputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
class Buzz implements Serializable {
    public static final ObjectStreamField[] FIELDS = { new ObjectStreamField("name", String.class) };

    private static final long serialVersionUID = 1L;
    private static final ObjectStreamField[] serialPersistentFields = FIELDS;

    private FileInputStream stream; // not serializable, but that's ok, because it is not among the persistent fields
    private String name;
}
