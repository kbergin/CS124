import java.util.Random;

public class LocalSearchAlgorithms {

    public static long kk(long[] A) {
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

    public static long repeatedRandom(long[] A, Solution s, int max) {
        long r = s.residue(A);
        for (int i = 0; i < max; i++) {
            Solution ss = s.getNeighbor(true);
            long rr = ss.residue(A);
            if (rr < r) {
                r = rr;
                s = ss;
            }
        }
        return r;
    }

    public static long hillClimbing(long[] A, Solution s, int max) {
        long r = s.residue(A);
        for (int i = 0; i < max; i++) {
            Solution ss = s.getNeighbor(false);
            long rr = ss.residue(A);
            if (rr < r) {
                r = rr;
                s = ss;
            }
        }
        return r;
    }

    private static double T(int i) {
        return (Math.pow(10, 10)*Math.pow(0.8, Math.floor(i/300)));
    }

    public static long simulatedAnnealing(long[] A, Solution s, int max) {
        long r = s.residue(A);
        long rBest = r;
        for (int i = 0; i < max; i++) {
            Solution ss = s.getNeighbor(false);
            long rr = ss.residue(A);
            if (rr < r) {
                r = rr;
                s = ss;
            } else {
                Random rand = new Random();
                double p = rand.nextDouble();
                double prob = Math.exp(-(rr-r)/T(i));
                if (p < prob) {
                    r = rr;
                    s = ss;
                }
            }
            if (r < rBest) {
                rBest = r;
            }
        }
        return rBest;
    }

 //Functions for returning all 25000 iterations for graphing
    public static long[] repeatedRandomArray(long[] A, Solution s, int max) {
        long[] residues = new long[max];
        long r = s.residue(A);
        for (int i = 0; i < max; i++) {
            Solution ss = s.getNeighbor(true);
            long rr = ss.residue(A);
            if (rr < r) {
                r = rr;
                s = ss;
            }
            residues[i] = r;
        }
        return residues;
    }

    public static long[] hillClimbingArray(long[] A, Solution s, int max) {
        long[] residues = new long[max];

        long r = s.residue(A);
        for (int i = 0; i < max; i++) {
            Solution ss = s.getNeighbor(false);
            long rr = ss.residue(A);
            if (rr < r) {
                r = rr;
                s = ss;
            }

            residues[i] = r;
        }
        return residues;
    }

    public static long[] simulatedAnnealingArray(long[] A, Solution s, int max) {
        long[] residues = new long[max];

        long r = s.residue(A);
        long rDoublePrime = r;
        for (int i = 0; i < max; i++) {
            Solution sPrime = s.getNeighbor(false);
            long rPrime = sPrime.residue(A);
            if (rPrime < r) {
                r = rPrime;
                s = sPrime;
            } else {
                Random rand = new Random();
                double p = rand.nextDouble();
                double prob = Math.exp(-(rPrime-r)/T(i));
                if (p < prob) {
                    r = rPrime;
                    s = sPrime;
                }
            }
            if (r < rDoublePrime) {
                rDoublePrime = r;
            }
            residues[i] = rDoublePrime;
        }
        return residues;
    }

}
