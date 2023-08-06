
            public class Foo {

                {
                    boolean x;
                    x = (y <= Float.NaN);
                    x = (y >= Float.NaN);
                    x = (y > Float.NaN);
                    x = (y < Float.NaN);

                    x = (Float.NaN <= y);
                    x = (Float.NaN >= y);
                    x = (Float.NaN > y);
                    x = (Float.NaN < y);
                }
            }
            