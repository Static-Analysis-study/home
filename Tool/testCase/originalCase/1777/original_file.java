
import java.util.Random;
class AvoidArrayLoops {
    void bar1(int[][] target, int[] source) {
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                target[i][j] = source[i * 10 + j];
            }
        }
    }
    void bar2(int[][] target, int[] source) {
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                int sourceIndex = new Random().nextInt(source.length);
                target[i][j] = source[sourceIndex];
            }
        }
    }
}
