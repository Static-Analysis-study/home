
class Foo {
    public Foo() throws Throwable {
        this.equals(new String());
        this.clone();
        this.finalize();

        equals(new String());
        clone();
        finalize();

        // #1718 - super calls should be ignored
        super.equals(new String());
        super.clone();
        super.finalize();
    }

    public boolean equals(Object o) { return true; }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void finalize() throws Throwable {
        super.finalize();
    }
}
