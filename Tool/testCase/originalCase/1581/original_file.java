
public class Foo {
    public void foo() {
        StringBuffer sb = new StringBuffer("any_string");
        final String nullStr = "";
        if (sb.toString().equals(nullStr)) { // PMD should report a warning here
            System.out.println("Buffer is empty");
        }

        // the preferred way
        if (sb.length() == 0) {
            System.out.println("Buffer is empty");
        }
    }
}
        