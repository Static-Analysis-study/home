
package net.sourceforge.pmd.lang.java.rule.codestyle.unnecessaryfullyqualifiedname;

public class OuterTestClass {
	public static class TestClass{
        private final net.sourceforge.pmd.lang.java.rule.codestyle.unnecessaryfullyqualifiedname.TestClass test;

        public TestClass(net.sourceforge.pmd.lang.java.rule.codestyle.unnecessaryfullyqualifiedname.TestClass test){

            this.test = test;
        }
    }
}
        