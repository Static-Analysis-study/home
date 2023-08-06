
public class Scratch {
    private Long member = 0L;

    public Scratch(final Long v) {
        setMember(v);
    }

    public final void setMember(final Long v) {
        this.member = v;
    }

    // Renaming this or marking it as final prevents the
    // 'ConstructorCallsOverridableMethod' rule from being broken.
    public void setMember(final String n) {
        setMember(Long.valueOf(n));
    }
}
