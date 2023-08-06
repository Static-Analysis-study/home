
class AvoidArrayLoops {
  void copy_a_to_b(int[] a, int[] b) {
        int[] c = new int[10];
        // this will not trigger the rule
        for (int i = 0; i < 10; i++) {
            b[i] = a[c[i]];
        }
  }
}
