
class foo {
    private final Object[] lock = new Object[0];
    private final Object[] lock2 = new Object[0];
    private final Object[] o = new Object[0];


    void init() {
        synchronized (lock) {
            // do init stuff
        }
    }

    public void close() {
        synchronized (this.lock2) {
           // do close stuff
        }
    }

    void foo() {
        System.out.println(o);
    }
}
