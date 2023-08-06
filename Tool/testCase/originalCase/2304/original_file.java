
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class SampleTest extends Object {
    @ParameterizedTest
    @ValueSource(ints = {1})
    void doesTest(final int num) {
        Assertions.assertTrue(num > 0);
    }
}
