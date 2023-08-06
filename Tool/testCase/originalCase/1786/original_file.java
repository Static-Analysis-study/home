
import java.util.ArrayList;
import java.util.List;

public class Test {
    public void foo(Integer[] ints) {
        // could just use Arrays.asList(ints)
        List l = new ArrayList(10);
        for (int i = 0; i < ints.length; i++) {
            l.add(ints[i]);
        }

        List l2 = new ArrayList(10);
        for (int i = 0; i < ints.length; i++) {
            l2.add(ints[i].toString()); // won't trigger the rule
        }
    }
}
        