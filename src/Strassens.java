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
}
