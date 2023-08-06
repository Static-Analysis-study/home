
package net.sourceforge.pmd.lang.java.rule.performance.stringtostring;
public class Foo {
    public void bar() {
        User user = new User();
        String s = user.getName().toString();
    }
}
