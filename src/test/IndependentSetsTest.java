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
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8}
        };
    }

    @Test(dataProvider = "check")
    public void testMain(int n) throws Exception {
        long start = System.currentTimeMillis();
        IndependentSets.main(n);
        long end = System.currentTimeMillis();

        System.out.println(end-start + "ms");
    }
}
