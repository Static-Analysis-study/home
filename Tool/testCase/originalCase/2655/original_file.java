
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.function.Consumer;

public class InvalidSl4jExceptionBug3560 {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidSl4jExceptionBug3560.class);

    public static Consumer<Throwable> build() {
        return e -> {
            if (e instanceof RuntimeException) {
                LOGGER.warn("Unexpected RuntimeException", e);
            }
        };
    }
}
        