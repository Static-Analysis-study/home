
public class Foo {
    public Foo() {
        intermediatePrivateMethod();
        shorterChain();
        differentChain();
    }

    private void intermediatePrivateMethod() {
        otherMethod1();
    }

    private final void otherMethod1() {
        otherMethod2();
    }

    private final void otherMethod2() {
        overridableMethod();
    }

    private void shorterChain() {
        overridableMethod();
    }

    void overridableMethod();

    private void differentChain() {
        otherOverridableMethod();
    }

    void otherOverridableMethod();
}
