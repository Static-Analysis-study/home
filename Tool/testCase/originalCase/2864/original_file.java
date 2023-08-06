
            class X {
                void method() {
                    try (ClientResponse response = execute(() -> target.request(mediaTypes).delete(), DELETE, new ExpectedResponse(status, required))) {
                        // was false positive
                        // EmptyTryBlock was fixed to ignore empty try-with-resources.
                        // This new rule will by default report also empty try-with-resource blocks,
                        // if the resource name is not "ignored", see next test case.
                    }
                }
            }
            