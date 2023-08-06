
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
public class Foo<T> implements Serializable {
    private List<String> names = new ArrayList<>();
    private AbstractList<String> anotherList;
    private T someData;
    private Object canBeAnything;
}
