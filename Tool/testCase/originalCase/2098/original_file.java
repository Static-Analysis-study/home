
public class C {
  enum E {
      R;
      int bar;
      void bar() {}  // should report a warning in this line
  }
}
        