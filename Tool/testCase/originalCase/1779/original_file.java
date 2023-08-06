
class Scratch {
    public static void main(String[] args) {
        int[] ints;
        ints = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(Arrays.toString(ints)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // shift left
        ints = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int i = 0; i < ints.length - 1; i++) {
            ints[i] = ints[i + 1];
        }
        System.out.println(Arrays.toString(ints)); // [2, 3, 4, 5, 6, 7, 8, 9, 10, 10]
        ints = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.arraycopy(ints, 1, ints, 0, ints.length - 1);
        System.out.println(Arrays.toString(ints)); // [2, 3, 4, 5, 6, 7, 8, 9, 10, 10]

        // shift right
        ints = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int i = ints.length - 1; i > 0; i--) {
            ints[i] = ints[i - 1];
        }
        System.out.println(Arrays.toString(ints)); // [1, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        ints = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.arraycopy(ints, 0, ints, 1, ints.length - 1);
        System.out.println(Arrays.toString(ints)); // [1, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
