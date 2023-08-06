
public final class Button {
    public String getLabel() { return "foo"; }
    public void setLabel(String label) { /* ignored */ }

    public String getSize() { return "1"; }
    public void setSize(int size) { /* ignored */ }

    public String getName() { return "bar"; } // read-only property
}
