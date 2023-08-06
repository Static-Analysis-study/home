
import org.mockito.Spy;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

public class MyTest {
   @Mock
   private Object mock;

   @MockBean
   private Object bean;

   @Spy
   private Object spy;

   @InjectMocks
   private Object target;

   void test() {
      target.methodToTest();
   }
}
        