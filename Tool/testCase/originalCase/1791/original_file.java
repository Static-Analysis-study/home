
import java.util.ArrayList;
import java.util.List;

class Test {
    public void convert(Object value) {
        short[] array = (short[]) value;
        List<Short> arrayList = new ArrayList<>(array.length);
        for (short v : array) {
            arrayList.add(v); // line 9 - false positive
        }

        List<Short> arrayList2 = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            arrayList2.add(array[i]); // line 14 - false positive
        }
    }
}
