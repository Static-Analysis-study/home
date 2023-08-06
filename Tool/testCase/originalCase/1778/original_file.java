
class AvoidArrayLoops {
    void sample() {
        int[] a = new int[10];
        int[] b = new int[10];
        final int c = 6;
        for (int i = 0; i < 10; i++) {
            b[i] = a[i + c];  // should report a warning at this line
            // b[i] = a[i + 6];  // This line can be detected
        }
        for (int i = 0; i < 10; i++) {
            b[i + c] = a[i];  // should report a warning at this line
        }
        for (int i = 0; i < 10; i++) {
            b[i + c] = a[i + c];  // should report a warning at this line
        }
    }
}
