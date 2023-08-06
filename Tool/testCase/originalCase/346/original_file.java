
package net.sourceforge.pmd.example.beans;
import java.io.Serializable;
public final class Button implements Serializable {
    private boolean active;

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
