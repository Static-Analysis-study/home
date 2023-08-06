
class Buzz implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private transient int someFoo;          // good, it's transient
    private static int otherFoo;            // also OK, it's static
    private java.io.FileInputStream stream; // bad - FileInputStream is not serializable

    public void setStream(FileInputStream stream) {
        this.stream = stream;
    }

    public int getSomeFoo() {
          return this.someFoo;
    }
}
