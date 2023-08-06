
class Foo {
    void bar(boolean arg) {
        while (false & arg) { }
        while (false && arg) { }
        do { } while (false && arg);
    }
}
