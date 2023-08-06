
            public class Foo {
                public void bar() {
                    for (int i = 0; i < 2; i++) {
                    }
                    for (int i = 0; i < 2; i++);
                    for (int i = 0; i < 2; i++) { // neg, nonempty
                        System.out.println(i);
                    }
                }
            }
            