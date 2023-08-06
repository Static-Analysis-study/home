
            import java.util.List;

            public class Foo {

                public void bar(List<Integer> list) {
                    for (int i : list) {
                    }
                    for (int i : list) ;
                    for (int i : list)  { // neg, nonempty
                        System.out.println(i);
                    }
                }
            }
            