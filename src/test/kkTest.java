import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;

public class kkTest {
    private final static int INPUT_SIZE = 100;
    private final static long MAX_INPUT_SIZE = 1000000000000L;
    private final static long[] INPUT = new long[INPUT_SIZE];

    @BeforeTest
    private static void generateA() {
        Random rand = new Random();
        for (int i = 0; i < INPUT_SIZE; i++) {
            INPUT[i] = RandomLong.nextLong(rand, MAX_INPUT_SIZE) + 1;
        }
    }

    @DataProvider(name = "LatexOutput")
    private Object[][] LatexOutput(){
        return new Object[][] {
                //max, n
                {25000, 50}
        };
    }

    @DataProvider(name = "generateGraphs")
    private Object[][] generateGraphs(){
        return new Object[][] {
                //maxIter
                //{100},
                //{1000},
                {25000}
                //{250000}
        };
    }

    @Test (dataProvider = "LatexOutput")
    private void makeLatexOutput(int maxIter, int n) {
        long[] KK = new long[n];
        long[] standardRR = new long[n];
        long[] standardHC = new long[n];
        long[] standardSA = new long[n];
        long[] PPRR = new long[n];
        long[] PPHC = new long[n];
        long[] PPSA = new long[n];

        for (int i = 0; i < n; i++) {
            //generate new input
            long[] A = generateA(100, 1000000000000L);

            //get KK only version
            long kkResidue = LocalSearchAlgorithms.kk(A);
            KK[i] = kkResidue;

            //Do Standard Representation
            Solution standard = new StandardSolution(A.length);

            //Random
            long rrResidue = LocalSearchAlgorithms.repeatedRandom(A, standard, maxIter);
            standardRR[i] = rrResidue;

            //Hill Climbing
            long hcResidue = LocalSearchAlgorithms.hillClimbing(A, standard, maxIter);
            standardHC[i] = hcResidue;

            //Simulated Annealing
            long saResidue = LocalSearchAlgorithms.simulatedAnnealing(A, standard, maxIter);
            standardSA[i] = saResidue;

            //Prepartition Representation
            Solution prepartition = new PrepartitionSolution(A.length);

            //Random
            rrResidue = LocalSearchAlgorithms.repeatedRandom(A, prepartition, maxIter);
            PPRR[i] = rrResidue;

            //Hill Climbing
            hcResidue = LocalSearchAlgorithms.hillClimbing(A, prepartition, maxIter);
            PPHC[i] = hcResidue;

            //Simulated Annealing
            saResidue = LocalSearchAlgorithms.simulatedAnnealing(A, prepartition, maxIter);
            PPSA[i] = saResidue;

        }
        outputLatex(KK, standardRR, standardHC, standardSA, PPRR, PPHC, PPSA);
    }

    @Test (dataProvider = "generateGraphs")
    private void runEachForGraphs(int maxIter) throws Exception {
        runRandom(maxIter);
        runHillClimbing(maxIter);
        runSimulatedAnneal(maxIter);
    }

    @Test
    private void testFile() throws Exception{
        long A[] = generateA(100, 1000000000000L);
        Writer writer;
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testInput.txt"), "utf-8"));

        for(int i = 0; i<A.length; i++) {
            writer.write(String.valueOf(A[i]));
            writer.write("\n");
        }

        writer.close();

        long residueKK = runKK(A);

        String[] args = new String[1];
        args[0] = "/Users/kbergin/CS124/testInput.txt";

        long residueFromFile = Main.main(args);
        Assert.assertEquals(residueKK, residueFromFile);
    }

    @Test
    public void runSimulatedAnnealrBest() throws Exception {
        long[] A = INPUT;
        int maxIter=25000;
        Solution prepartition = new PrepartitionSolution(A.length);
        long[] saResidue = LocalSearchAlgorithms.simulatedAnnealingArray(A, prepartition, maxIter);
        printToFile(saResidue, "PrepartitionSArBest", maxIter);
    }


    private long runKK(long[] A) throws Exception {
        return LocalSearchAlgorithms.kk(A);
    }


    private void runRandom(int maxIter) throws Exception {
        long[] A = INPUT;

        Solution standard = new StandardSolution(A.length);
        long[] rrResidue = LocalSearchAlgorithms.repeatedRandomArray(A, standard, maxIter);
        printToFile(rrResidue, "StandardRR", maxIter);

        Solution prepartition = new PrepartitionSolution(A.length);
        rrResidue = LocalSearchAlgorithms.repeatedRandomArray(A, prepartition, maxIter);
        printToFile(rrResidue, "PrepartitionRR", maxIter);
    }

    private void runHillClimbing(int maxIter) throws Exception {
        long[] A = INPUT;

        Solution standard = new StandardSolution(A.length);
        long[] hcResidue = LocalSearchAlgorithms.hillClimbingArray(A, standard, maxIter);
        printToFile(hcResidue, "StandardHC", maxIter);

        Solution prepartition = new PrepartitionSolution(A.length);
        hcResidue = LocalSearchAlgorithms.hillClimbingArray(A, prepartition, maxIter);
        printToFile(hcResidue, "PrepartitionHC", maxIter);

    }

    private void runSimulatedAnneal(int maxIter) throws Exception {
        long[] A = INPUT;

        Solution standard = new StandardSolution(A.length);
        long[] saResidue = LocalSearchAlgorithms.simulatedAnnealingArray(A, standard, maxIter);
        printToFile(saResidue, "StandardSA", maxIter);

        Solution prepartition = new PrepartitionSolution(A.length);
        saResidue = LocalSearchAlgorithms.simulatedAnnealingArray(A, prepartition, maxIter);
        printToFile(saResidue, "PrepartitionSA", maxIter);
    }

    private void outputLatex(long[] KK, long[] standardRR, long[] standardHC, long[] standardSA,
                             long[] PPRR, long[] PPHC, long[] PPSA){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));
            writer.write("\\begin{tabular}{|c|c|c|c|c|c|c|c|} \\hline\n" +
                    "& & \\multicolumn{3}{|c|}{\\textbf{Standard}} & \\multicolumn{3}{|c|}{\\textbf{Prepartition}} \\\\ \\hline\n" +
                    "\\textbf{Instance}\t&\\textbf{KK} &\\textbf{R} &\\textbf{HC} &\\textbf{SA} &\\textbf{R} &\\textbf{HC} &\\textbf{SA}\t\\\\ \\hline\n");
            for(int i = 0; i<KK.length; i++){
                writer.write(String.valueOf(i+1));
                writer.write(" & ");
                writer.write(String.valueOf(KK[i]));
                writer.write(" & ");
                writer.write(String.valueOf(standardRR[i]));
                writer.write(" & ");
                writer.write(String.valueOf(standardHC[i]));
                writer.write(" & ");
                writer.write(String.valueOf(standardSA[i]));
                writer.write(" & ");
                writer.write(String.valueOf(PPRR[i]));
                writer.write(" & ");
                writer.write(String.valueOf(PPHC[i]));
                writer.write(" & ");
                writer.write(String.valueOf(PPSA[i]));
                writer.write("\\\\ \\hline");
                writer.write("\n");
            }
            writer.write("\\end{tabular}");
        } catch (IOException ex) { // report
        }
        finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }

    }


    private void printToFile(long[] residues, String test, int maxIter) throws Exception {
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

    private static long[] generateA(int n, long max) {
        Random rand = new Random();
        long[] problem = new long[n];
        for (int i = 0; i < n; i++) {
            problem[i] = RandomLong.nextLong(rand, max) + 1;
        }
        return problem;
    }

}