import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by kbergin on 3/21/16.
 */
public class Strassens {
    public static void main (String[]args) throws Exception {
        if(args.length!=3){
            throw new Exception("Needs three integer arguments: \n" +
                    "(1) flag \n" +
                    "(2) dimension \n" +
                    "(3) input file with matrix entries");
        }

        int dimension = Integer.parseInt(args[1]);
        File inputFile = new File(args[2]);

        int[] matrixA = buildMatrix(inputFile, dimension, true);
        int[] matrixB = buildMatrix(inputFile, dimension, false);
    }

    private static int[] buildMatrix(File inputFile, int dimension, boolean first) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        int[] matrix = new int[dimension*dimension];
        int i = (first) ? 0 : dimension;
        String line;
        int j = i;
        while( (line = br.readLine())!= null && j<i+dimension ) {
            matrix[i] = Integer.parseInt(line);
            j++;
        }
        return matrix;
    }


    private static int[] strassens(int[] A, int[] B){
        int[] matrixC = new int[4];

        /*
        * matrix A:
        * A (0) | B (1)
        * C (2) | D (3)
        *
        * matrix B:
        * E (0) | F (1)
        * G (2) | H (3)
        * */

        int P1 = A[0] * (B[1] - B[3]); // A(F-H)
        int P2 = (A[0] + A[1]) * B[3]; // (A+B)H
        int P3 = (A[2] + A[3]) * B[0]; // (C+D)E
        int P4 = A[3] * (B[2] - B[0]); // D(G-E)
        int P5 = (A[0] + A[3]) * (B[0] + B[3]); // (A+D)(E+H)
        int P6 = (A[1] - A[3]) * (B[2] + B[3]); // (B-D)(G+H)
        int P7 = (A[0] - A[2]) * (B[0] + B[1]); // (A-C)(E+F)

        matrixC[0] = P5 + P4 - P2 + P6;
        matrixC[1] = P1 + P2;
        matrixC[2] = P3 + P4;
        matrixC[3] = P1 + P5 - P3 - P7;

        return matrixC;
    }


    //Conventional matrix multiplication hard coded for 2x2 -- need to expand to handle any size matrix
    public static int[] matrixMultiplication(int[] x, int[] y) {
        int[] result = new int[4];
        result[0] = ((x[0] * y[0]) + (x[1] * y[2]));
        result[1] = ((x[0] * y[1]) + (x[1] * y[3]));
        result[2] = ((x[2] * y[0]) + (x[3] * y[2]));
        result[3] = ((x[2] * y[1]) + (x[3] * y[3]));
        return result;
    }
    
    /*
    * Next I think we need ...
    *  - a function to divide the matrices into two sub matrices
    *  - a function to pad the matrix with zeros
    *  - a recursion to actually run strassens
    *  - to add the normal matrix multiplication that can work for any size matrix
    *  - a function to print only the diagonal of the final matrix like they ask
    * */
}
