
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectInput;
class Foo implements Externalizable {
    private FileInputStream foo;
    void writeExternal(ObjectOutput out) throws IOException {}
    void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {}
}
