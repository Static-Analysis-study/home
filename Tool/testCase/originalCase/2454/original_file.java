
import java.io.FileInputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
class Buzz implements Serializable {
    public static final ObjectStreamField[] NO_FIELDS = new ObjectStreamField[0];

    private static final long serialVersionUID = 1L;
    private static final ObjectStreamField[] serialPersistentFields = NO_FIELDS;

    private FileInputStream stream; // not serializable, but that's ok, because it is not among the persistent fields
    private String name;
}
