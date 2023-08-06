
package net.sourceforge.pmd.lang.java.rule.performance.stringtostring;

public class User {

    public String getName() {
        String s;
        s = toString();
        s = this.toString();
        s = super.toString();
        s = convert(toString());
        s = convert(this.toString());
        s = convert(super.toString());
        return s;
    }

    private String convert(String s) {
        return s.toLowerCase(Locale.ROOT);
    }
}
