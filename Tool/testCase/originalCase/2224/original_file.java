
import java.util.Date;

public class MyTimestamp {
    private final long t;
    public MyTimestamp() {
        t = new Date().getTime(); // <-- false-positive violation here
    }
    public long getTime() {
        return t;
    }
}
