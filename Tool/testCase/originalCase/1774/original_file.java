
public class Foo {
    public void bar1() {
        for (int i = 0; i < 10; i++) {
            a[i] += b[i];
        }
    }
    public void bar2() {
        int i = 0;
        for (i = 0; i < 10; i++) {
            a[i] += b[i];
        }
    }
}
        