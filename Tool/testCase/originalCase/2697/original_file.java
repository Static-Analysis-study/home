
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.logstash.logback.argument.StructuredArguments;
import static net.logstash.logback.marker.Markers.*;

public class Foo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);

    public void bar(Object span, long duration) {
        new Runnable() {
            public void run() {
                LOGGER.info(
                      LogMarker.OBSERVABILITY.getMarker(),
                      "End span {} in {}ms",
                      kindMessage(span),
                      duration,
                      StructuredArguments.keyValue("traceId", "Text"));
            }
        };
    }

    public enum LogMarker {
        OBSERVABILITY {
            public LogstashMarker getMarker() {
                return append("foo", "bar");
            }
        };
        public abstract LogstashMarker getMarker();
    }

    private String kindMessage(Object span) {
        return String.valueOf(span);
    }
}
