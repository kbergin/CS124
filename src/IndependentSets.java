import java.math.BigInteger;


/**
 * Created by kbergin on 4/16/16.
 */


public class IndependentSets {
    public static BigInteger main(int n) throws Exception {
        System.out.println(n);
        double nodes = findN(n);
        nodes = Math.ceil(nodes);
        System.out.println(nodes + " nodes");

        BigInteger independentSets = findIndependentSets(n);

        System.out.println(independentSets + " independent sets");
        return independentSets;
    }
    public static double findN(int nodes){
        return Math.pow(2.0, nodes-1)-1;
    }
    public static BigInteger findIndependentSets(int n) {
        if(n<=1){
            return BigInteger.valueOf(1L);
        }
        else{
            return (findIndependentSets(n-1).pow(2).add(findIndependentSets(n-2).pow(4)));
        }
    }
}
