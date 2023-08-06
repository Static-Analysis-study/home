
public class TestArrayIsStoredDirectly {
    public double energy(int x) {
        return 0.0;
    }

    // if energy[] is replace by energy2[], then no ArrayIsStoredDirectly warning
    private void f(double[] energy) {
        energy[0] = energy(1);
    }
}
