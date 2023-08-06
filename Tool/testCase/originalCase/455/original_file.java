
public class ExampleImmutableField {

    private String str; // false negative here, could be final

    public ExampleImmutableField(String strLocal, boolean flag) { 
        if (flag){
            this.str = strLocal;
        } else {
            this.str = strLocal+"123";
        }
    }
}
        