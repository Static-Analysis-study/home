
import org.slf4j.Logger;
public class Foo {
    public void aTest() {
        Logger logger = null;

        if (true) {
            final String logMessage = "A message formatted with three parameters: {}, {}, {}";
        }

        if (true) {
            final String logMessage = "A message formatted with only one parameter: {}";
            final Object param = null;
            logger.trace(logMessage, param);
        }
    }
}
        