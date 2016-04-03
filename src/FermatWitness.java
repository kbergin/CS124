/**
 * Created by kbergin on 4/3/16.
 */

import java.math.*;

public class FermatWitness {
    public static void main(int n) throws Exception {
        Integer witness = findWitness(BigInteger.valueOf(n));
        System.out.println(witness +" is a witness that proves " + n + " is composite.");
    }
    public static Integer findWitness(BigInteger n){
        int x = n.intValue()-1;
        BigInteger witness;
        for(int i = 1; i<n.intValue(); i++){
            BigInteger k = BigInteger.valueOf(i);
            witness=k.modPow(BigInteger.valueOf(x),n);
            if(witness.intValue()!=1){
                return i;
            }
        }
        return 0;
    }
}
