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
        String inputFile = args[2];

        Matrix[] matrices = Matrix.createMatrices(inputFile, dimension);
    }

    private static Matrix strassens(Matrix A, Matrix B){
        if (A.dim != B.dim) throw new RuntimeException("Illegal matrix dimensions.");

        int d = A.dim;
        int[][] matrixC = new int[d][d];

        /*
        * matrix A:
        * A (0,0) | B (0,1)
        * C (1,0) | D (1,1)
        *
        * matrix B:
        * E (0,0) | F (0,1)
        * G (1,0) | H (1,1)
        * */

        int P1 = A[0][0] * (B[0][1] - B[1][1]); // A(F-H)
        int P2 = (A[0][0] + A[0][1]) * B[1][1]; // (A+B)H
        int P3 = (A[1][0] + A[1][1]) * B[0][0]; // (C+D)E
        int P4 = A[1][1] * (B[1][0] - B[0][0]); // D(G-E)
        int P5 = (A[0][0] + A[1][1]) * (B[0][0] + B[1][1]); // (A+D)(E+H)
        int P6 = (A[0][1] - A[1][1]) * (B[1][0] + B[1][1]); // (B-D)(G+H)
        int P7 = (A[0][0] - A[1][0]) * (B[0][0] + B[0][1]); // (A-C)(E+F)

        matrixC[0][0] = P5 + P4 - P2 + P6;
        matrixC[0][1] = P1 + P2;
        matrixC[1][0] = P3 + P4;
        matrixC[1][1] = P1 + P5 - P3 - P7;

        Matrix C = new Matrix(matrixC);
        return C;
    }

    public static Matrix matrixMultiplication(Matrix A, Matrix B) {

        if (A.dim != B.dim) throw new RuntimeException("Illegal matrix dimensions.");

        int d = A.dim;
        int[][] product = new int[d][d];

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                for (int k = 0; k < d; k++) {
                    product[i][j] += A.vals[i][k] * B.vals[k][j];
                }
            }
        }

        Matrix C = new Matrix(product);
        return C;
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
