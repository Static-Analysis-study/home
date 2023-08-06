
class TestArrayIsStoredDirectly {
    private final boolean[] a;
    private final foo b;

    private TestArrayIsStoredDirectly(boolean[] a) {
        this.a = null;
        this.a = a.clone(); // no violation, it doesn't matter what the state of this.a is
        this.b = new TestArrayIsStoredDirectly(a); // no violation
        this.b = new TestArrayIsStoredDirectly(this.a); // false positive violation here
    }
}
