
public class Test {
  public static void main( String[] args ) {
    double[] foo = new double[100];
    double bar = 0.0;
    int[] exps = new int[10];

    for (int i = 0; i < exps.length; i++) {
      double value = Math.random();
      foo[i] = Math.exp(value);
      bar += foo[i];
    }
  }
}
