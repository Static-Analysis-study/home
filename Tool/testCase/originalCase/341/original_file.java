
package org.example.beans;
public class MyBean {        // <-- bean is not serializable, missing "implements Serializable"
    private String label;    // <-- missing setter for property "label"

    public String getLabel() {
        return label;
    }
}
        