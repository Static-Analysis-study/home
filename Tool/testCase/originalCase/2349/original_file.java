
            public class Foo {

                {
                    boolean x;
                    x = (y <= Double.NaN);
                    x = (y >= Double.NaN);
                    x = (y > Double.NaN);
                    x = (y < Double.NaN);

                    x = (Double.NaN <= y);
                    x = (Double.NaN >= y);
                    x = (Double.NaN > y);
                    x = (Double.NaN < y);
                }
            }
            