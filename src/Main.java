//TODO: function to read input file
//TODO: Sorting function
//TODO: ability to make A' list
//TODO: function to insert an element into the list in order
//TODO: function to run kk - returns residue of a solution
//TODO: probability function to do 50/50 coin flip - this could also use our random int function with bounds 1-2
//TODO: random int function - to choose i and j for random neighbor
//TODO: random long function - to generate input file
//TODO: function to randomly generate S/P solution - let's try to use some soft eng awesomeness to use this for both
//TODO: function to choose random neighbor - let's try to use some soft eng awesomeness to use this for both
//I think for this we want to create an Interface that can be implemented slightly differently for each version
//TODO: function for each algorithm
//TODO: cooling schedule function
//TODO: Output manipulation

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String usage = "Usage: ./kk <inputfile>";
        if (args.length != 1) {
            throw new Exception("Incorrect number of arguments. Usage is: \n" + usage);
        }

        int maxIter = 25000;

        String fileName = args[0];
        long[] testArray;
        try {
            testArray = generateFromFile(fileName);
        }
        catch (Exception e){
            throw new Exception("Input is not of expected length. Assignment specified 100 inputs");
        }
        long residue = kk.applyKK(testArray);
        System.out.println("Karmarkar Karp: " + residue);

        Solution standard = new StandardSolution(testArray.length);

        long rrSResidue = LocalSearchAlgorithms.repeatedRandom(testArray, standard, maxIter);
        System.out.println("Standard: Repeated Random: " + rrSResidue);

        long hcSResidue = LocalSearchAlgorithms.hillClimbing(testArray, standard, maxIter);
        System.out.println("Standard: Hill Climbing: " + hcSResidue);

        long saSResidue = LocalSearchAlgorithms.simulatedAnnealing(testArray, standard, maxIter);
        System.out.println("Standard: Simmulated Annealing: " + saSResidue);

        Solution prepartition = new PrepartitionSolution(testArray.length);

        long rrPResidue = LocalSearchAlgorithms.repeatedRandom(testArray, prepartition, maxIter);
        System.out.println("Prepartition: Repeated Random: " + rrPResidue);

        long hcPResidue = LocalSearchAlgorithms.hillClimbing(testArray, prepartition, maxIter);
        System.out.println("Prepartition: Hill Climbing: " + hcPResidue);

        long saPResidue = LocalSearchAlgorithms.simulatedAnnealing(testArray, prepartition, maxIter);
        System.out.println("Prepartition: Simmulated Annealing: " + saPResidue);
    }

    private static long[] generateFromFile(String filename) throws Exception {
        int numLines = 100;
        long[] A = new long[numLines];
        try {
            FileReader input = new FileReader(filename);
            BufferedReader reader = new BufferedReader(input);
            for (int i = 0; i < numLines; i++) {
                try {
                    String line = reader.readLine();
                    A[i] = Long.parseLong(line);
                } catch (Exception k) {
                    throw new Exception();
                }
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found! Make the path is either absolute or relative to the bin directory.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return A;
    }
}
