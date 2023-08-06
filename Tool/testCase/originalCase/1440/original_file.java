
class Foo {
    {
        while (false | false) { }
        while ((false | false)) { }
        while (false || false) { }
        while ((false || false)) { }
    }
}
        