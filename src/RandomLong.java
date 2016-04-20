import java.util.Random;

/**
 * Created by kbergin on 4/20/16.
 */
public class RandomLong {
    protected static long nextLong(Random rng, long n) {
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }
}
