
            import java.io.InputStream;
            class X {
                void method(InputStream in) {
                    try (in) {
                    }
                }
            }
            