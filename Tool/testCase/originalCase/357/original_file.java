
public final class Button {
    private String[] labels;
    public String getLabels() { return labels[0]; }
    public void setLabels(String[] labels) { this.labels = labels; }
    public String getLabels(int i) { return labels[i]; }
    public void setLabels(int i, String label) { this.labels[i] = label; }

    private String[] names;
    public String[] getNames() { return names; }
    public void setNames(String[] names) { this.names = names; }
    public int getNames(int i) { return i; } // wrong result type
    public void setNames(int i, int name) { /* ... */ } // wrong type
}
