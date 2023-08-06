
import javax.servlet.Filter;

public class FooFilter implements Filter {
    public FooFilter() { }

    // Adding two private filter to emulate the others
    private class BarFilter implements Filter {}

    private class OneTooMuchFilter implements Filter {}
}
        