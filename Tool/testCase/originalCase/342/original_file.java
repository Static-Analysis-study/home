
package net.sourceforge.pmd.example.beans;
import java.io.Serializable;
public final class Button implements Serializable {
    private String label;
    private boolean active;
    private Boolean enabled;
    private transient String notImportant;
    private final String constant = "Foo";

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Boolean isEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public String getConstant() { return constant; }
}
