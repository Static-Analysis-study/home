
package net.sourceforge.pmd.lang.java.rule.performance.stringtostring;

public final class Issue2080 {
    String value;

    public void foo() {
        A a = new A();
        value = a.value.toString(); // false positive
    }

    class A {
        B value;
    }

    class B { }
}
