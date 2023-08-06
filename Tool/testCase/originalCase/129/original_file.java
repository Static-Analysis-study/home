
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
abstract class AllArgs {
    private String field;
    public int otherField;
}

@NoArgsConstructor
abstract class NoArgs {
    private String field;
    public int otherField;
}

@RequiredArgsConstructor
abstract class RequiredArgs {
    private String field;
    public int otherField;
}
