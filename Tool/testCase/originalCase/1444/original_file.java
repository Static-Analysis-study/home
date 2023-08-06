
class Foo {
    {
        // these evaluate to true and are allowed
        while (true & true) { }
        while ((true & true)) { }
        while (true && true) { }
        while ((true && true)) { }

        // these evaluate to false and should be flagged
        while (false & false) { } // line 10
        while ((false & false)) { }
        while (false && false) { }
        while ((false && false)) { }
        while (false & true) { }
        while ((false & true)) { }
        while (false && true) { }
        while ((false && true)) { }
        while (true & false) { }
        while ((true & false)) { }
        while (true && false) { }
        while ((true && false)) { }

        // do-while loops should always be flagged
        do {} while (false & false);
        do {} while ((false & false));
        do {} while (false && false);
        do {} while ((false && false));
        do {} while (true & false);
        do {} while ((true & false));
        do {} while (true && false);
        do {} while ((true && false));
        do {} while (false & true);
        do {} while ((false & true));
        do {} while (false && true);
        do {} while ((false && true));
        do {} while (true & true);
        do {} while ((true & true));
        do {} while (true && true);
        do {} while ((true && true));
    }
}
