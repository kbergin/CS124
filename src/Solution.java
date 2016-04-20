public interface Solution {
    Solution getNeighbor(boolean random);
    long residue(long[] A);
    void print();
    int[] getArray();
}
