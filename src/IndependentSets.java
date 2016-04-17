import java.math.BigInteger;


/**
 * Created by kbergin on 4/16/16.
 */


public class IndependentSets {
    public static void main(int n) throws Exception {
        double nodes = findN(n);
        nodes = Math.ceil(nodes);
        System.out.println(nodes);

        BigInteger independentSets = findIndependentSets(nodes);

        System.out.println(independentSets);
    }
    public static double findN(int nodes){
        double y = Math.log(2);
        double x = Math.log(nodes);
        return ((2*y+x)/y);
    }
    public static BigInteger findIndependentSets(double n) {
        if(n<=1){
            return BigInteger.valueOf(1L);
        }
        else{
            return (findIndependentSets(n-1).pow(2).add(findIndependentSets(n-2).pow(4)));
        }
    }
}
