
import java.util.Set;

public record Record(Set<String> stringSet) {

    public boolean hasMoreThanOneItem() {
        return this.stringSet.size() == 0;
    }
}        