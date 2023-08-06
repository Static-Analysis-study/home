
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DemoNestedTests {
    @Nested
    class SomeTest {
        @Test
        void isCorrect() {
            assertTrue(42 == 42);
        }
    }
}
