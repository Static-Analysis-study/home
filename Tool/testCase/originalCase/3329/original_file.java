
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
class MyTests {
    @Test
    void firstCheck() {}

    @Nested
    class Reactor {
        @Test
        void secondCheck() {}
    }
}
