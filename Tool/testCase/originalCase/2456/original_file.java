
import java.io.FileInputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
class Buzz implements Serializable {
    private static final long serialVersionUID = 1L;
    // wrong modifiers for serialPersistentFields: not final, not private, not static
    ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("name", String.class) };

    private FileInputStream stream; // not serializable
    private String name;
}
