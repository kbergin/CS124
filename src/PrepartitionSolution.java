import java.util.Random;

public class PrepartitionSolution implements Solution {
    private int[] P;
    private int n;

    public PrepartitionSolution(int n) {
        int[] P = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int num = rand.nextInt(n);
            P[i] = num;
        }
        this.P = P;
        this.n = n;
    }

    public PrepartitionSolution(int[] P) {
        this.P = P;
        this.n = P.length;
    }

    public int[] getArray() {
        return this.P;
    }

    public Solution findNeighbor(boolean random) {
        int[] neighbor = new int[this.n];
        for (int k = 0; k < this.n; k++) {
            neighbor[k] = this.P[k];
        }
        Random rand = new Random();
        int i = rand.nextInt(this.n);
        int j;
        do {
            j = rand.nextInt(this.n);
        } while (j == neighbor[i]);
        neighbor[i] = j;
        return new PrepartitionSolution(neighbor);
    }

    public long residue(long[] A) {
        long[] AA = new long[this.n];
        for (int i = 0; i < this.n; i++) {
            AA[i] = 0;
        }
        for (int i = 0; i < this.n; i++) {
            AA[this.P[i]] += A[i];
        }
        return kk.applyKK(AA);
    }
}
