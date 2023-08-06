
class AvoidArrayLoopsSamples {
    public static void sample1() {
        final int[] a = new int[10];
        final int[] b = new int[10];
        for (int i = 0; i < 10; i++) { // line 5 - no violation (false positive)
            final int c = i;           // it's final but not a constant during the loop
            b[i] = a[i + c];
        }
    }
    public static void sample2() {
        final int[] a = new int[10];
        final int[] b = new int[10];
        for (int i = 0; i < 10; i++) { // line 13 - no violation (false positive)
            int c = i;                 // not final, not a constant (literal)
            b[i] = a[i + c];
        }
    }
    public static void sample3() {
        final int[] a = new int[10];
        final int[] b = new int[10];
        final int c = 6;
        for (int i = 0; i < 10; i++) { // line 22 - violation
            b[i] = a[i + c];           // using c, which is a final and a constant
        }
    }
    public static void sample4() {
        final int[] a = new int[10];
        final int[] b = new int[10];
        for (int i = 0; i < 10; i++) { // line 29 - violation
            final int c = 6;           // using c, which is a final and a constant
            b[i] = a[i + c];
        }
    }
    private static final int OFFSET = 5;
    public static void sample5() {
        final int[] a = new int[10];
        final int[] b = new int[10];
        for (int i = 0; i < 10; i++) { // line 38 - violation
            b[i] = a[i + OFFSET];      // using OFFSET, a constant field
        }
    }
}
