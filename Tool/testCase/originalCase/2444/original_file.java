
import java.util.List;
public abstract class List<A> implements Iterable<A> {
    public static <A> F<List<A>, Boolean> isEmpty() {
        return null;
    }
}
class F<X, Y> {}
        