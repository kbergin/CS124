/**
 * Created by kbergin and rmunchi on 3/22/16.
 */


public class StrassensR {
    //global n0 that we need to determine
    //private static int n0 = 8;

    public static void main (String[]args) throws Exception {
        if(args.length!=3){
            throw new Exception("Needs three integer arguments: \n" +
                    "(1) flag \n" +
                    "(2) dimension \n" +
                    "(3) input file with matrix entries");
        }

        //later make sure you change n0 to be a global number instead of this flag.
        final int n0 = Integer.parseInt(args[0]);
        final int dimension = Integer.parseInt(args[1]);
        final String inputFile = args[2];

        Matrix[] matrices = Matrix.createMatrices(inputFile, dimension);
        int[][] result = strassen(matrices[0].vals, matrices[1].vals, n0);

        Matrix resultMatrix = new Matrix(result);
        resultMatrix.print();
    }


    public static int[][] conventionalMM(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static int[][] strassen(int[][] A, int[][] B, final int n0) {
        int n = A.length;
        int m = nextPowerOfTwo(n);
        int[][] paddedA = new int[m][m];
        int[][] paddedB = new int[m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                paddedA[i][j] = A[i][j];
                paddedB[i][j] = B[i][j];
            }
        }

        int[][] paddedC = strassenRecursive(paddedA, paddedB, n0);
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = paddedC[i][j];
            }
        }
        return C;
    }

    private static int[][] strassenRecursive(int[][] A, int[][] B, final int n0) {
        int n = A.length;

        if (n <= n0) {
            return conventionalMM(A, B);
        } else {
            // initializing the new sub-matrices
            int newSize = n / 2;
            int[][] a11 = new int[newSize][newSize];
            int[][] a12 = new int[newSize][newSize];
            int[][] a21 = new int[newSize][newSize];
            int[][] a22 = new int[newSize][newSize];

            int[][] b11 = new int[newSize][newSize];
            int[][] b12 = new int[newSize][newSize];
            int[][] b21 = new int[newSize][newSize];
            int[][] b22 = new int[newSize][newSize];

            int[][] aResult;
            int[][] bResult;

            // dividing the matrices in 4 sub-matrices:
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    a11[i][j] = A[i][j]; // top left
                    a12[i][j] = A[i][j + newSize]; // top right
                    a21[i][j] = A[i + newSize][j]; // bottom left
                    a22[i][j] = A[i + newSize][j + newSize]; // bottom right

                    b11[i][j] = B[i][j]; // top left
                    b12[i][j] = B[i][j + newSize]; // top right
                    b21[i][j] = B[i + newSize][j]; // bottom left
                    b22[i][j] = B[i + newSize][j + newSize]; // bottom right
                }
            }

            // Calculating p1 to p7:

            // p1 = (a11+a22) * (b11+b22)
            aResult = matrixAddition(a11, a22);
            bResult = matrixAddition(b11, b22);
            int[][] p1 = strassenRecursive(aResult, bResult, n0);

            // p2 = (a21+a22) * (b11)
            aResult = matrixAddition(a21, a22); // a21 + a22
            int[][] p2 = strassenRecursive(aResult, b11, n0);

            // p3 = (a11) * (b12 - b22)
            bResult = matrixSubtraction(b12, b22); // b12 - b22
            int[][] p3 = strassenRecursive(a11, bResult, n0);

            // p4 = (a22) * (b21 - b11)
            bResult = matrixSubtraction(b21, b11); // b21 - b11
            int[][] p4 = strassenRecursive(a22, bResult, n0);

            // p5 = (a11+a12) * (b22)
            aResult = matrixAddition(a11, a12); // a11 + a12
            int[][] p5 = strassenRecursive(aResult, b22, n0);

            // p6 = (a21-a11) * (b11+b12)
            aResult = matrixSubtraction(a21, a11); // a21 - a11
            bResult = matrixAddition(b11, b12); // b11 + b12
            int[][] p6 = strassenRecursive(aResult, bResult, n0);

            // p7 = (a12-a22) * (b21+b22)
            aResult = matrixSubtraction(a12, a22); // a12 - a22
            bResult = matrixAddition(b21, b22); // b21 + b22
            int[][] p7 = strassenRecursive(aResult, bResult, n0);

            // calculating c21, c21, c11 c22:
            // c12 = p3 + p5
            int[][] c12 = matrixAddition(p3, p5);

            // c21 = p2 + p4
            int[][] c21 = matrixAddition(p2, p4);

            // c11 = p1 + p4 - p5 + p7
            aResult = matrixAddition(p1, p4); // p1 + p4
            bResult = matrixAddition(aResult, p7); // p1 + p4 + p7
            int[][] c11 = matrixSubtraction(bResult, p5);

            // c22 = p1 + p3 - p2 + p6
            aResult = matrixAddition(p1, p3); // p1 + p3
            bResult = matrixAddition(aResult, p6); // p1 + p3 + p6
            int[][] c22 = matrixSubtraction(bResult, p2);

            // Grouping the results obtained in a single matrix:
            int[][] C = new int[n][n];
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    C[i][j] = c11[i][j];
                    C[i][j + newSize] = c12[i][j];
                    C[i + newSize][j] = c21[i][j];
                    C[i + newSize][j + newSize] = c22[i][j];
                }
            }
            return C;
        }
    }

    private static int[][] matrixAddition(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private static int[][] matrixSubtraction(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static int nextPowerOfTwo(int n) {
        int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
        return (int) Math.pow(2, log2);
    }
}
