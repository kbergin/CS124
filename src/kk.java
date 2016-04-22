public class kk {

    public static long applyKK(long[] A) {
        MaxHeap h = new MaxHeap();
        for (long i : A) {
            h.insert(i);
        }
        while (h.size() > 1) {
            long n1 = h.deleteMax();
            long n2 = h.deleteMax();
            long diff = Math.abs(n1 - n2);
            h.insert(diff);
        }
        return h.deleteMax();
    }
}
