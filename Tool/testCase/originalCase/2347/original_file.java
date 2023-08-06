
            public class Foo {

                {
                    boolean x;
                    x = (Double.NaN == y);
                    x = (Double.NaN != y);

                    x = (y == Double.NaN);
                    x = (y != Double.NaN);
                }
            }
            