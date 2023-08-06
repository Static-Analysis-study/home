
import java.io.FileInputStream;
import java.io.InputStream;
import net.sourceforge.pmd.lang.java.rule.performance.stringtostring.User;

class B {
    public void foo() {
        String s = new User().getName().toString(); // detected because class User is on the auxclasspath
        s = getString().toString(); // detected
        s = getData(new FileInputStream()).toString(); // detected because of argument (sub) type
        s = getData(new Integer(4), new Integer(5)).toString(); // detected because of unique args count
    }
    public String getString() {
        return "exampleStr";
    }
    public String getData(InputStream is) {
        return "argsResolutionIssueExample";
    }
    public int getData(String s) {
        return 0;
    }
    public String getData(Number a, Number b) {
        return "uniqueArgsCountExample";
    }
}
        