
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.logstash.logback.argument.StructuredArguments;
import static net.logstash.logback.marker.Markers.*;
import static net.logstash.logback.argument.StructuredArguments.*;

public class Foo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);

    public void withMarkers() {
        // Add "foo:bar" to the JSON output
        LOGGER.info(append("foo", "bar"), "Some log message");
        // Add "foo:bar,name:value" to the JSON output
        LOGGER.info(append("foo", "bar").and(append("name", "value")), "Some log message");
    }

    public void withStructuredArguments() {
        // Add "foo:bar" to JSON output and "bar" to log message
        LOGGER.info("log message {}", value("foo", "bar"));
        // Add "foo:bar" to JSON output only
        LOGGER.info("log message", keyValue("foo", "bar"));
        // Add "foo:bar" to JSON output only
        LOGGER.info("log message", StructuredArguments.keyValue("foo", "bar"));
        // Add "foo:bar" to JSON output only, but one parameter
        LOGGER.info("log message: {}", "used parameter", keyValue("foo", "bar"));
        // Add "foo:bar" to JSON output only - too many arguments!
        LOGGER.info("log message", "unused parameter", keyValue("foo", "bar"));
        // Add "foo:bar" to JSON output only - too less arguments!
        LOGGER.info("log message {} {}", keyValue("foo", "bar"));
    }
}
        