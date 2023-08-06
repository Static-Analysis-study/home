
import java.util.Collection;
import java.util.Set;

class Foo1 {
  final boolean tag = true;
  public Foo1() {
    bar(tag);  // should report a warning at this line
  }
  public void bar(boolean b) {}
}

class Foo2 {
  public Foo2() {
    bar(true);  // should report a warning at this line
  }
  public void bar(boolean b) {}
}

class Foo3 {
  public Foo3(String arg) {
    bar(arg); // should report a warning at this line
  }
  public void bar(String s) {}
}

class Foo4 {
  public Foo4(String[] arg) {
    bar(arg); // should report a warning at this line
  }
  public void bar(String[] s) {}
}

class Foo5 {
  public Foo5(int[] arg) {
    bar(arg); // should report a warning at this line
  }
  public void bar(int[] i) {}
}

class Foo6 {
  public Foo6(int[] arg) {
    bar(arg); // should report a warning at this line
  }
  public void bar(int i[]) {} // note, the different array notation!
}

class Foo7 {
  public Foo7(String[] arg) {
    bar(arg); // should report a warning at this line
  }
  public void bar(String s[]) {} // note, the different array notation!
}

class Foo8 {
  public Foo8(String[] arg) {
    bar(arg); // should report a warning at this line
  }
  public void bar(String... s) {} // vararg
}

class Foo9<E> {
  public Foo9(Set<E> arg) {
    bar(arg); // should report a warning at this line
  }
  public void bar(Collection<E> s) {} // base type
}
