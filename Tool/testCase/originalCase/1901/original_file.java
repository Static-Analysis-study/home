
class Main {
    public static void main(String[] args) {
        final String string1 = "";
        final String string2 = "";
        final String string3 = "";
        String string4 = "";

        String a = string1 + 114;
        String b = string2 + 514;
        String c = string3 + 1919810;

        string4 = "foo";
        String d = string4 + 1; // should not be flagged, because string4 is not empty anymore

        final String s = "bar";
        String e = s + 2; // should not be flagged, because s is not empty
    }
}
        