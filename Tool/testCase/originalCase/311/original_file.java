
public class Foo {
    private int nonStaticPrivate = 1;                   //no violation cause non-final
    private final int nonStatic = nonStaticPrivate;     //no violation cause referenced name is non-static

    //private static final int staticFinal = nonStatic; //noncompliant: Non-static field 'nonStatic' cannot be referenced from a static context
    private static int staticNonFinal = 1;              //no violation cause non-final
    private final int nonStatic2 = staticNonFinal;      //violation because it could be static
}
        