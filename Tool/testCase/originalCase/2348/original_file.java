
            public class Foo {
                {
                    boolean x;
                    x = (Float.NaN == y);
                    x = (Float.NaN != y);

                    x = (y == Float.NaN);
                    x = (y != Float.NaN);
                }
            }
            