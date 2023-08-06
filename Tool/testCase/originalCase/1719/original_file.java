
public class InsufficientStringBufferDeclaration {
    public String case1_ok() {
        StringBuilder sb = new StringBuilder(2 + 2); // 4
        sb.append("aa");
        sb.append("bb");
        return sb.toString();
    }

    public String case2_insufficient() {
        StringBuilder sb = new StringBuilder(2 + 2); // line 10 - violation here, initial capacity 4
        sb.append("aa");
        sb.append("bb");
        sb.append('c');
        return sb.toString();
    }

    public String case3_unknown(String in) {
        StringBuilder sb = new StringBuilder(in.length()); // unknown
        sb.append("aa");
        sb.append("bb");
        sb.append('c');
        return sb.toString();
    }

    public String case4_unknown_calculation(String in) {
        StringBuilder sb = new StringBuilder(in.length() * 2); // unknown
        sb.append("aa");
        sb.append("bb");
        sb.append('c');
        return sb.toString();
    }

    public String case5_insufficient_setLength() {
        StringBuilder sb = new StringBuilder(5); // line 34 - violation here, initial capacity 5
        sb.append("xx");
        sb.setLength(2 + 2); // new length is 4
        sb.append("aa"); // appending 2 chars -> insufficient capacity
        return sb.toString();
    }

    public String case6_insufficient_setLength() {
        StringBuilder sb = new StringBuilder(5);
        sb.append("xx");
        sb.setLength(2 + 2 * 3); // line 44 - new length is 8, new capacity now 8  -> violation here
        sb.append("aa"); // appending 2 chars -> insufficient capacity
        return sb.toString();
    }

    public String case7_sufficient_ensureCapacity() {
        StringBuilder sb = new StringBuilder(5);
        sb.append("xx");
        sb.ensureCapacity(2 + 2 * 3); // length is still 2, new capacity now at least 8
        sb.append("aa"); // length is 4
        sb.append("bb"); // length is 6
        return sb.toString();
    }

    public String case8_insufficient_ensureCapacity() {
        StringBuilder sb = new StringBuilder(5);
        sb.append("xx");
        sb.ensureCapacity(2 + 2 * 3); // line 61 - length is still 2, new capacity now at least 8 -> violation here
        sb.append("aa"); // length is 4
        sb.append("bb"); // length is 6
        sb.append("cc"); // length is 8
        sb.append('d'); // length is now 9
        return sb.toString();
    }

    public String case9_sufficient_setLength() {
        StringBuilder sb = new StringBuilder(4);
        sb.append("xxxx");
        sb.setLength(0); // length is 0, capacity is still 4
        sb.append("aaaa");
        return sb.toString();
    }

    public String case10_unknown_setLength(String in) {
        StringBuilder sb = new StringBuilder(in.length()); // unknown
        sb.append("xxxx");
        sb.setLength(0); // length is 0, capacity is still unknown
        sb.append("aaaa");
        return sb.toString();
    }

    public String case11_unknown_ensureCapacity(String in) {
        StringBuilder sb = new StringBuilder(in.length()); // unknown
        sb.append("xxxx"); // length is 4
        sb.ensureCapacity(7); // line 88 - length is still 4, new capacity now at least 7 -> violation here
        sb.append("aaaa"); // length is 8
        return sb.toString();
    }
}
        