
            public class Foo {
                public void bar() {
                    int i = 0;
                    do {
                    } while (i < 3);
                    do; while (i < 3);
                    do { // neg, nonempty
                        System.out.println(i);
                    } while (i < 3);
                }
            }
            