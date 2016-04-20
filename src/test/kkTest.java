import java.util.Random;

/**
 * Created by kbergin on 4/19/16.
 */
public class kkTest {
    //TODO: Test to run each variation
    //TODO: @BeforeTest to generate input file capability
    //TODO: test to run 50 random instances
    //TODO: @AfterTest to output results how we want for latex

    //TODO: Check distribution of random number generation for input files in JMP/R
    //TODO: When doing each algorithm, have it output residue at every iteration to graph and see how they improve,
    // to ensure that hill climbing/simulated annealing work how we expect

    private static long[] generateProblem(int n, long max) {
        Random rand = new Random();
        long[] problem = new long[n];
        for (int i = 0; i < n; i++) {
            problem[i] = RandomLong.nextLong(rand, max) + 1;
        }
        return problem;
    }

}