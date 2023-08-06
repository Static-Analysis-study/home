
            import net.sourceforge.pmd.lang.java.rule.bestpractices.switchstmtsshouldhavedefault.SimpleEnum;

            public class Foo {
                void bar(SimpleEnum x) {
                    switch (x) {
                    case FOO -> System.out.println("it is on");
                    case BAR -> System.out.println("it is off");
                    case BZAZ -> System.out.println("it is bzaz");
                    default -> System.out.println("it is neither on nor off - should not happen? maybe null?");
                    }
                }
            }
            