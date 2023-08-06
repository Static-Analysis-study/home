
import java.util.ArrayList;
import java.util.List;

public class Bar {
    void foo() {
        Integer[] ints = new Integer[10];
        List l = new ArrayList(ints.length);
        for (int i = 0; i < ints.length; i++) {
            l.add(ints[i]);
        }
    }
}
        