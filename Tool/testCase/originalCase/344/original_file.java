
package net.sourceforge.pmd.example.beans;
import java.io.Serializable;
public final class Button implements Serializable {
    private String label;
    private final String constant;

    public Button(String constant) {
        this.constant = constant;
    }

    public String getConstant() { return constant; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}
