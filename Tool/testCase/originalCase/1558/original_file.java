
public class Foo {
  @Override
  public String toString() {
    return new StringJoiner(",", Bar.class.getSimpleName().toString() + "[", "]");
  }
}
