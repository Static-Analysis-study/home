
import java.util.ArrayList;
import java.util.List;

public class Foo {
    public final int BAR = (42);

    // these empty arrays could theoretically be shared and therefore be static
    private final Object[] argsObjs1 = new Object[0];
    private final Object[] argsObjs2 = new Object[(0)];

    // not flagging anonymous class instantiation
    private final StringBuffer mFilter = new StringBuffer(new CharSequence() {
        @Override public char charAt(int index) { return 'A'; }
        @Override public int length() { return 1; }
        @Override public CharSequence subSequence(int start, int end) { return this; }
        @Override public String toString() { return "Foo"; }
    });

    // not flagging any instantiation in order to avoid false positives
    // especially for lists one could still require a separate list for each instance...
    public final List<String> mList = new ArrayList<>();

    // not flagging instantiation of boxed types - would be a different rule
    // this is to keep this rule simple
    public final Integer DefaultInit = new Integer(27);
    private final String mString = new String("Foo");

    // not flagging array creation. Same reasoning as for lists:
    // one could still require a separate array for each instance...
    private final int[] p = new int[42];
}
        