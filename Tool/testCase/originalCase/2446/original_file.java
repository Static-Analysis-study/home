
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectStreamException;
class Foo implements Serializable {
    private FileInputStream foo; // no violation, because writeObject/readObject is present
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {}
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {}
    private void readObjectNoData() throws ObjectStreamException {}
}
