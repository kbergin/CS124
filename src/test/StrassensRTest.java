import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by kbergin on 3/25/16.
 */
public class StrassensRTest {

    private final static File TEST_DIR = new File("/Users/kbergin/CS124/StrassensResults/");
    private File testResults;
    private int i = 1;

    private FileWriter writer = null;
    private BufferedWriter w = null;

    @BeforeTest
    public void prepOutputFile() throws IOException {
        testResults = File.createTempFile("testResults", ".txt", TEST_DIR);

        try {
            writer = new FileWriter(testResults);
            w = new BufferedWriter(writer);
            w.write("Test & MatrixDimension & CrossoverPoint(n0) & Time(ms) & Algorithm\n");
        } catch (IOException ex) {
            // report
        }
    }

    @DataProvider(name = "initialTiming")
    public Object[][] initialTimingTests(){
        return new Object[][] {
                //dimension, n0
                //Conventional
                /*{"8", "8"},
                {"16", "16"},
                {"32", "32"},
                {"64", "64"},
                {"128", "128"},
                {"256", "256"},
                {"512", "512"},
                {"1024", "1024"},*/

                //Strassens
                {"8", "1"}
                /*{"16", "1"},
                {"32", "1"},
                {"64", "1"},
                {"128", "1"},
                {"256", "1"},
                {"512", "1"},
                {"1024", "1"},

                //n/4 Crossover
                {"8", "2"},
                {"16", "4"},
                {"32", "8"},
                {"64", "16"},
                {"128", "32"},
                {"256", "64"},
                {"512", "128"},
                {"1024", "256"},

                //n/2 Crossover
                {"8", "4"},
                {"16", "8"},
                {"32", "16"},
                {"64", "32"},
                {"128", "64"},
                {"256", "128"},
                {"512", "256"},
                {"1024", "512"},

                //3n/4 Crossover
                {"8", "6"},
                {"16", "12"},
                {"32", "24"},
                {"64", "48"},
                {"128", "96"},
                {"256", "192"},
                {"512", "384"},
                {"1024", "768"}*/
        };
    }

    @DataProvider(name = "findn0")
    public Object[][] findn0(){
        return new Object[][] {
                //dimension, n0
                {"1024", "256"},
                {"1024", "128"},
                {"1024", "130"},
                {"1024", "120"},
                {"1024", "64"},
        };
    }

    @DataProvider(name = "findn0odd")
    public Object[][] findn0odd(){
        return new Object[][] {
                //dimension, n0
                //{"17", "0"},
                //{"17", "17"},
                /*{"129", "0"},
                {"129", "129"},
                {"257", "0"},
                {"257", "100"},*/
                {"8", "0"}
        };
    }

    @Test (dataProvider = "findn0")
    public void testMain(String dimension, String n0) throws Exception {
        //makes test file for this dimension and n0 value and sends to Strassens
        makeTestFiles.main(Integer.parseInt(dimension));

        String[] inputs = new String[3];
        inputs[0] = n0;
        inputs[1] =  dimension;
        inputs[2] = "/Users/kbergin/CS124/output.txt";

        long start = System.currentTimeMillis();
        strassen.main(inputs);
        long end = System.currentTimeMillis();

        String alg = findAlg(Integer.parseInt(n0), Integer.parseInt(dimension));

        try {
            w.append(i + " & " + dimension + " & " + n0 + " & " + (end - start)+ " & " + alg + "\n");
        } catch (IOException ex) {
            // report
        }

        System.out.println("Total time for n0 = " + n0 + " and matrix dimension = " + dimension + ": " + (end - start) + "ms");

        i++;
    }

    @AfterTest
    private void postTest() throws IOException {
        w.close();
        writer.close();
    }

    private String findAlg(Integer n0, Integer dimension){
        String alg;

        Integer temp=dimension-n0;

        if(temp==0){
            alg = "Conventional";
        }
        else if(n0==0){
            alg = "Strassens";
        }
        else{
            alg = "Mix";
        }

        return alg;
    }
}