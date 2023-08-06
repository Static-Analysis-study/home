
            public class Foo {
                String inefficientConcatenation() {
                    String result = "";
                    for (int i = 0; i < 10; i++) {
                        // warning: this concatenation will create one new StringBuilder per iteration
                        result += getStringFromSomeWhere(i);
                    }
                    return result;
                }

                String efficientConcatenation() {
                    // better would be to use one StringBuilder for the entire loop
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < 10; i++) {
                        result.append(getStringFromSomeWhere(i));
                    }
                    return result.toString();
                }

                String getStringFromSomeWhere(int i) {
                    return "a" + i;
                }
            }
        