
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
class NoArgs1 { private NoArgs1(String a) {} } // no violation

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class NoArgs2 { private NoArgs2(String a) {} } // violation: only private ctors

@NoArgsConstructor(access = AccessLevel.PUBLIC)
class NoArgs3 { private NoArgs3(String a) {} } // no violation

@lombok.NoArgsConstructor
class NoArgs4 { private NoArgs4(String a) {} } // no violation

@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
class NoArgs5 { private NoArgs5(String a) {} } // violation: only private ctors

@lombok.NoArgsConstructor(access = lombok.AccessLevel.PUBLIC)
class NoArgs6 { private NoArgs6(String a) {} } // no violation


@RequiredArgsConstructor
class RequiredArgs1 { private RequiredArgs1(String a) {} } // no violation

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class RequiredArgs2 { private RequiredArgs2(String a) {} } // violation: only private ctors

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
class RequiredArgs3 { private RequiredArgs3(String a) {} } // no violation

@lombok.RequiredArgsConstructor
class RequiredArgs4 { private RequiredArgs4(String a) {} } // no violation

@lombok.RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
class RequiredArgs5 { private RequiredArgs5(String a) {} } // violation: only private ctors

@lombok.RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
class RequiredArgs6 { private RequiredArgs6(String a) {} } // no violation

@AllArgsConstructor
class AllArgs1 { private AllArgs1(String a) {} } // no violation

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class AllArgs2 { private AllArgs2(String a) {} } // violation: only private ctors

@AllArgsConstructor(access = AccessLevel.PUBLIC)
class AllArgs3 { private AllArgs3(String a) {} } // no violation

@lombok.AllArgsConstructor
class AllArgs4 { private AllArgs4(String a) {} } // no violation

@lombok.AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
class AllArgs5 { private AllArgs5(String a) {} } // violation: only private ctors

@lombok.AllArgsConstructor(access = lombok.AccessLevel.PUBLIC)
class AllArgs6 { private AllArgs6(String a) {} } // no violation

class OtherClass { private OtherClass(String a) {} } // violation: only private ctors
