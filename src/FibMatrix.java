
/**
 * Created by rmunshi on 2/7/16.
 *
 * In this class, a matrix is represented
 * as a single dimensional array for simplicity.
 */
public class FibMatrix {
    public static void main (String[]args) {
        System.out.println("Running Matrix Solution");
        //integer overflow at i > 46
        long start = System.currentTimeMillis(); for (int i = 0; i <= 46; i ++) {
            int answer = fibMatrix(i);
            System.out.println("The " + i + "th Fibonacci number is :" + answer);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time to reach integer overflow: " + (end - start) );
        //until ~ 1 min
        long moduloStart = System.currentTimeMillis(); for (int i = 0; i < 95000000; i ++) {
            fibMatrix(i);
        }
        long moduloEnd = System.currentTimeMillis();
        System.out.println("Total Matrix Modulo execution time: " + (moduloEnd - moduloStart));
    }
    public static int fibMatrix(int n) {
        int[] constant = {1,1,1,0}; //standard matrix
        return matrixPower(constant, n)[1]; //return bottom (or second) value of the final 2x
    }


//matrix exponentiation
    public static int[] matrixPower(int[] constant, int n) { //identity matrix
        int[] result = {1, 0, 0, 1};
        while (n >= 1) { //keep halving the n as the matrix keeps getting squared if (n % 2 != 0) {
        result = matrixMultiplication(result, constant);
        }
        constant = matrixMultiplication(constant, constant);
        n = n/2;
        return result;
    }
//matrix multiplication by definition
    public static int[] matrixMultiplication(int[] x, int[] y) {
        int[] result = new int[4];
        result [0] = ((x[0] * y[0]) + (x[1] * y[2])) % 65536;
        result [1] = ((x[0] * y[1]) + (x[1] * y[3])) % 65536;
        result [2] = ((x[2] * y[0]) + (x[3] * y[2])) % 65536;
        result [3] = ((x[2] * y[1]) + (x[3] * y[3])) % 65536;
        return result;
        }
}

