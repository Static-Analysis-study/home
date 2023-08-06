
package net.sourceforge.pmd.example.beans;
import java.io.Serializable;
public final class Button implements Serializable {
    private String label;
    private boolean active;
    private transient String notImportant;
    private final String constant = "Foo";

    public String getLabel() { return label; }
}
