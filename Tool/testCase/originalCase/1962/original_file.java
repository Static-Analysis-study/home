
import org.Annotation;
// import java.util.Vector; Note: explicitly not importing... unresolved type
@Annotation
public class C {
    void x() {
        Vector v = new Vector();  // should report a warning in this line, but no warnings
    }
}
