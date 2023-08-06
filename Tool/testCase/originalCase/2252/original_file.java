
public final class TestPrivateClassWithFactory implements GenericTest {
  private final boolean shouldFail;

  private TestPrivateClassWithFactory(TestStep step) {
    this.shouldFail = step.shouldFail;
  }

  public void run() {
    if (shouldFail) {
      throw new AssertionError("fail");
    }
  }

  public static final class TestStep {
    private boolean shouldFail = false;

    public TestStep shouldFail() {
      this.shouldFail = true;
      return this;
    }

    public GenericTest newTest() {
      return new TestPrivateClassWithFactory(this);
    }
  }
}
