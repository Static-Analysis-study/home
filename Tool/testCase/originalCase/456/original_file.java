
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ExampleImmutableField {

    @Mock
    private Foo foo;

    @InjectMocks
    private Bar bar;

    @MockBean
    private Baz Baz;

    @Autowired
    private Foo2 foo2;
}

class Foo {}
class Bar {}
class Baz {}
class Foo2 {}
        