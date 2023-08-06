
            import org.slf4j.Logger;
            import org.slf4j.LoggerFactory;

            public class Foo {

                private static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);

                public void okStr() {
                    String message = "Test"; // No exception
                    LOGGER.info(message.toString());
                }
                public void okStrVar() {
                    var message = "Test"; // No exception
                    LOGGER.info(message.toString());
                }
                public void okSb() {
                    StringBuilder message = new StringBuilder("Test"); // No exception
                    LOGGER.info(message.toString());
                }
                public void exceptionOnThisOne() {
                    var message = new StringBuilder("Test"); // Throws IndexOutOfBoundsException
                    LOGGER.info(message.toString());
                }
            }
            