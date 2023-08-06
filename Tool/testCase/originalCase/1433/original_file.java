
class Foo {
    {
        do { } while (true | true);
        do { } while (true || true);
        do { } while ((true | true));
        do { } while ((true || true));
    }
}
        