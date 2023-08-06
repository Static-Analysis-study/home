
class Main {
    final String outerString1 = "";
    final String outerString2 = "";

    public static void main(String[] args, String otherString) {
        final String innerString1 = "";
        final String innerString2 = "";

        String a = outerString1 + 114;
        String b = outerString2 + 514;
        String c = innerString1 + 1919;
        String d = innerString2 + 810;
        String e = otherString + 42; // should not be flagged, otherString is a method parameter. Not to be confused with otherString local var in otherMethod
    }

    void otherMethod() {
        final String otherString = "";
    }
}
        