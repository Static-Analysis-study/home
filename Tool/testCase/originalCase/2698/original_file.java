
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Foo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);
    public void bar() {
        String msg = "Got kafka msg, headers=%s, body=%s".formatted(headers, new String(body, UTF_8));
        LOGGER.info(msg);
        return msg;
    }
}
