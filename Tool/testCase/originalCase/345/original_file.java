
package net.sourceforge.pmd.example.beans;
import java.io.Serializable;
public final class Button implements Serializable {
    private String label;

    public int getLabel() { return 42; }
    public void setLabel(String label) { this.label = label; }
}
