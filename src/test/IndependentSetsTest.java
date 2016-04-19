import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by kbergin on 4/3/16.
 */
public class IndependentSetsTest {
    @DataProvider(name = "check")
    public Object[][] findWitness(){
        return new Object[][]{
                {2, 2},
                {3, 5},
                {4, 41},
                {5, 2306},
                {6, 8143397},
                {7, 0},
                {8, 0}
        };
    }

    @Test(dataProvider = "check")
    public void testMain(int n, int x) throws Exception {
        long start = System.currentTimeMillis();
        BigInteger sets = IndependentSets.main(n);
        long end = System.currentTimeMillis();

        if(n<=6) {
            BigInteger xBig = BigInteger.valueOf(x);
            Assert.assertEquals(sets, xBig);
        }
        System.out.println(end-start + "ms");
    }
}
