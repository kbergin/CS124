import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;

/**
 * Created by kbergin on 4/19/16.
 */
public class kkTest {
    private final static int INPUT_SIZE = 100;
    private final static long MAX_INPUT_SIZE = 1000000000000L;
    private final static long[] INPUT = new long[INPUT_SIZE];

    //TODO: @AfterTest to output results how we want for latex
    //TODO: Write a test to test the input file functionality

    @BeforeTest
    private static void generateProblem() {
        Random rand = new Random();
        for (int i = 0; i < INPUT_SIZE; i++) {
            INPUT[i] = RandomLong.nextLong(rand, MAX_INPUT_SIZE) + 1;
        }
    }

    @DataProvider(name = "runAllAlgs")
    public Object[][] runAllAlgs(){
        return new Object[][] {
                //max, n
                //{10, 1},
                {25000, 50}
        };
    }

    @DataProvider(name = "LatexOutput")
    public Object[][] LatexOutput(){
        return new Object[][] {
                //max, n
                {25000, 50}
        };
    }

    @DataProvider(name = "generateGraphs")
    public Object[][] generateGraphs(){
        return new Object[][] {
                //maxIter
                //{100},
                //{1000},
                {25000}
                //{250000}
        };
    }

    @Test (dataProvider = "runAllAlgs")
    private void runAll(int maxIter, int n) {
        for (int i = 0; i < n; i++) {
            long[] A = generateProblem(n, maxIter);

            long start = System.nanoTime();
            long kkResidue = LocalSearchAlgorithms.kk(A);
            long elapsed = System.nanoTime() - start;
            double seconds = (double)elapsed / 1000000000.0;
            System.out.println("\nKK: " + kkResidue + "\tTime: " + seconds);

            Solution standard = new StandardSolution(A.length);
            System.out.println("\nStandard Representation:");
            start = System.nanoTime();
            long rrResidue = LocalSearchAlgorithms.repeatedRandom(A, standard, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Repeated Random: " + rrResidue + "\tTime: " + seconds);
            start = System.nanoTime();
            long hcResidue = LocalSearchAlgorithms.hillClimbing(A, standard, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Hill Climbing: " + hcResidue + "\tTime: " + seconds);
            start = System.nanoTime();
            long saResidue = LocalSearchAlgorithms.simulatedAnnealing(A, standard, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Simulated Annealing: " + saResidue + "\tTime: " + seconds);

            Solution prepartition = new PrepartitionSolution(A.length);
            System.out.println("\nPrepartition Representation:");
            start = System.nanoTime();
            rrResidue = LocalSearchAlgorithms.repeatedRandom(A, prepartition, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Repeated Random: " + rrResidue + "\tTime: " + seconds);
            start = System.nanoTime();
            hcResidue = LocalSearchAlgorithms.hillClimbing(A, prepartition, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Hill Climbing: " + hcResidue + "\tTime: " + seconds);
            start = System.nanoTime();
            saResidue = LocalSearchAlgorithms.simulatedAnnealing(A, prepartition, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Simulated Annealing: " + saResidue + "\tTime: " + seconds);
        }
    }

    @Test (dataProvider = "LatexOutput")
    private void makeLatexOutput(int maxIter, int n) {
        //
        for (int i = 0; i < n; i++) {
            long[] A = generateProblem(n, maxIter);

            long start = System.nanoTime();
            long kkResidue = LocalSearchAlgorithms.kk(A);
            long elapsed = System.nanoTime() - start;
            double seconds = (double)elapsed / 1000000000.0;
            System.out.println("\nKK: " + kkResidue + "\tTime: " + seconds);

            Solution standard = new StandardSolution(A.length);
            System.out.println("\nStandard Representation:");
            start = System.nanoTime();
            long rrResidue = LocalSearchAlgorithms.repeatedRandom(A, standard, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Repeated Random: " + rrResidue + "\tTime: " + seconds);
            start = System.nanoTime();
            long hcResidue = LocalSearchAlgorithms.hillClimbing(A, standard, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Hill Climbing: " + hcResidue + "\tTime: " + seconds);
            start = System.nanoTime();
            long saResidue = LocalSearchAlgorithms.simulatedAnnealing(A, standard, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Simulated Annealing: " + saResidue + "\tTime: " + seconds);

            Solution prepartition = new PrepartitionSolution(A.length);
            System.out.println("\nPrepartition Representation:");
            start = System.nanoTime();
            rrResidue = LocalSearchAlgorithms.repeatedRandom(A, prepartition, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Repeated Random: " + rrResidue + "\tTime: " + seconds);
            start = System.nanoTime();
            hcResidue = LocalSearchAlgorithms.hillClimbing(A, prepartition, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Hill Climbing: " + hcResidue + "\tTime: " + seconds);
            start = System.nanoTime();
            saResidue = LocalSearchAlgorithms.simulatedAnnealing(A, prepartition, maxIter);
            elapsed = System.nanoTime() - start;
            seconds = (double)elapsed / 1000000000.0;
            System.out.println("Simulated Annealing: " + saResidue + "\tTime: " + seconds);
        }
    }


    @Test (dataProvider = "generateGraphs")
    private void runEachForGraphs(int maxIter) throws Exception {
        runRandom(maxIter);
        runHillClimbing(maxIter);
        runSimulatedAnneal(maxIter);
    }

    public void runRandom(int maxIter) throws Exception {
        long[] A = INPUT;

        Solution standard = new StandardSolution(A.length);
        long[] rrResidue = LocalSearchAlgorithms.repeatedRandomArray(A, standard, maxIter);
        printToFile(rrResidue, "StandardRR", maxIter);

        Solution prepartition = new PrepartitionSolution(A.length);
        rrResidue = LocalSearchAlgorithms.repeatedRandomArray(A, prepartition, maxIter);
        printToFile(rrResidue, "PrepartitionRR", maxIter);
    }

    public void runHillClimbing(int maxIter) throws Exception {
        long[] A = INPUT;

        Solution standard = new StandardSolution(A.length);
        long[] hcResidue = LocalSearchAlgorithms.hillClimbingArray(A, standard, maxIter);
        printToFile(hcResidue, "StandardHC", maxIter);

        Solution prepartition = new PrepartitionSolution(A.length);
        hcResidue = LocalSearchAlgorithms.hillClimbingArray(A, prepartition, maxIter);
        printToFile(hcResidue, "PrepartitionHC", maxIter);

    }

    public void runSimulatedAnneal(int maxIter) throws Exception {
        long[] A = INPUT;

        Solution standard = new StandardSolution(A.length);
        long[] saResidue = LocalSearchAlgorithms.simulatedAnnealingArray(A, standard, maxIter);
        printToFile(saResidue, "StandardSA", maxIter);

        Solution prepartition = new PrepartitionSolution(A.length);
        saResidue = LocalSearchAlgorithms.simulatedAnnealingArray(A, prepartition, maxIter);
        printToFile(saResidue, "PrepartitionSA", maxIter);
    }

    @Test
    public void runSimulatedAnnealrBest() throws Exception {
        long[] A = INPUT;
        int maxIter=25000;
        Solution prepartition = new PrepartitionSolution(A.length);
        long[] saResidue = LocalSearchAlgorithms.simulatedAnnealingArray(A, prepartition, maxIter);
        printToFile(saResidue, "PrepartitionSArBest", maxIter);
    }

    public void printToFile(long[] residues, String test, int maxIter) throws Exception {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output_"+test+"_"+maxIter+".txt"), "utf-8"));
            writer.write("Iteration, Residue\n");
            for(int i = 0; i<residues.length; i++){
                writer.write(String.valueOf(i));
                writer.write(", ");
                writer.write(String.valueOf(residues[i]));
                writer.write("\n");
            }
        } catch (IOException ex) { // report
        }
        finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }

    private static long[] generateProblem(int n, long max) {
        Random rand = new Random();
        long[] problem = new long[n];
        for (int i = 0; i < n; i++) {
            problem[i] = RandomLong.nextLong(rand, max) + 1;
        }
        return problem;
    }

}