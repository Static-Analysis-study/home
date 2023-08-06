
import java.util.ArrayList;
import java.util.List;

public class Test {
    public void foo(Integer[] ints) {
        // could just use Arrays.asList(ints)
        List<Integer> l = new ArrayList<>(100);
        for (int i = 0; i < ints.length; i++) {
            l.add(ints[i]); // line 9, here is the violation
        }
        List<Integer> anotherList = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            anotherList.add(ints[i].toString()); // line 13 - false positive
        }
    }
}
        