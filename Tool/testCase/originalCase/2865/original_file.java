
            class X {
                void method() {
                    try (ClientResponse ignored = execute(() -> target.request(mediaTypes).delete(), DELETE, new ExpectedResponse(status, required))) {
                    }
                }
            }
            