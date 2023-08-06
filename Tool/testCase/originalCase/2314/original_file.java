
import org.junit.jupiter.api.Test;
class SampleTest {
    @Test
    void usesNestedHelperClass() { }

    private static class TestHelper {} // matches the default testClassPattern

    private static class Other {}
}
