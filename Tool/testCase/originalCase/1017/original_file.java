
class Test{
    public static void main(String[] args){
        int a = 0;
        int i = 0;
        while (a < 50) {
            if (i >= 30) {
                a = i + 1; // unused
                break;
            }
            i++; // used by itself
        }
    }
}
        