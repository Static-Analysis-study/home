
            import net.sourceforge.pmd.lang.java.rule.bestpractices.switchstmtsshouldhavedefault.SimpleEnum;

            public class Foo {

                void bar(SimpleEnum x) {
                    switch (x) {
                    case BZAZ:
                        int y = 8;
                        break;
                    case FOO:
                        break;
                    default:
                        break;
                    }
                }
            }
            