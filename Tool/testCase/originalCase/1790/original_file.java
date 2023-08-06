
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public void foo(Integer[] ints) {
        List<Integer> l = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            switch (lookup(ints[i])) {
            case 1: l.add(ints[i]); break; // line 10 - false positive
            case 2: l.addAll(getInts(i)); break;
            }
        }

        List<Integer> anotherList = new ArrayList<>();
        for (int i : ints) {
            switch (lookup(i)) {
            case 1: anotherList.add(i); break; // line 18 - false positive
            case 2: anotherList.addAll(getInts(i)); break;
            }
        }
    }

    int lookup(int a) {
        return a;
    }

    List<Integer> getInts(int a) {
        return Arrays.asList(a);
    }
}
