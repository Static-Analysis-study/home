
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
class Foo {
    @ParameterizedTest
    @ValueSource(ints = {1})
    void testBar(int i) { }
}
