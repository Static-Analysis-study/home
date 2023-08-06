
import lombok.ToString;

@ToString
public class C {
    @ToString.Include
    private int a;  // Should not report a warning in this line
}
