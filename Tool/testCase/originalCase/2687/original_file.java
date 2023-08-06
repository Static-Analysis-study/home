
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

public class InvalidLogMessageFormatTest {
    private static final Logger logger = LoggerFactory.getLogger("MyLogger");
    private static final Marker marker = BasicMarkerFactory.getMarker("MARKER");

    public static void main(String[] args) {
        logger.warn(marker, "foo {}", "flibble", "moo", "blah", "blah"); // wrong number of arguments
        logger.warn(marker, "foo"); // correct: marker and no arguments
        logger.warn(marker, "foo", new Exception()); // correct: marker and one exception parameter
        logger.warn(marker, "foo {}", "bar"); // correct: marker and one argument

        final var otherMarker = MarkerFactory.getMarker("OTHER_MARKER");
        // we can't statically determine the type of the "otherMarker" variable, so we assume it is not a string and ignore it
        logger.warn(otherMarker, "foo");

        final var message = "foo {} {}";
        logger.warn(message, "a", "b"); // correct: first var is the message with expects two parameters
    }
}
        