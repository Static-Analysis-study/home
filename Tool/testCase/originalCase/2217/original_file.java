
class Foo {
  public Foo() {
    privateInitOk(); // false positive
    privateInitBad1(); // warning expected here
    privateInitBad2(); // warning expected here
  }

  private void privateInitOk() {
    new Foo().publicMethod();
  }

  private void privateInitBad1() {
    publicMethod();
  }

  private void privateInitBad2() {
    this.publicMethod();
  }

  public void publicMethod() { }
}
