
            public class EmptyTryBlock2 {
                public void foo() {
                    try {
                        int x = 0;
                    } finally { // warn
                    }
                }
            }
            