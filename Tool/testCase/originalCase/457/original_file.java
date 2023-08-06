
import org.mockito.Captor;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Value;

public class ExampleImmutableField {

    @Value("${property.value}")
    private String bar2;

    @Captor
    private ArgumentCaptor<Object> baz2;
}
        