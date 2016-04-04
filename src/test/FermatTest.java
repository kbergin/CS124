import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by kbergin on 4/3/16.
 */
public class FermatTest {
    @DataProvider(name = "findWitness")
    public Object[][] findWitness(){
        return new Object[][]{
                {636127},
                {294409}
        };
    }

    @Test(dataProvider = "findWitness")
    public void testMain(int n) throws Exception {
        long start = System.currentTimeMillis();
        FermatWitness.main(n);
        long end = System.currentTimeMillis();

        System.out.println(end-start + "ms");
    }

    @Test
    public void testRSA() throws Exception {
        long start = System.currentTimeMillis();
        RSA.runRSA("Give me an A");
        long end = System.currentTimeMillis();

        System.out.println(end-start + "ms");
    }

}
