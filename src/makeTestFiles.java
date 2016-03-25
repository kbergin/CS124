import java.io.Writer;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;

/**
 * Created by kbergin on 3/25/16.
 */
public class makeTestFiles {
    public static void main (String[] args) throws Exception {
        int dimension = Integer.parseInt(args[0]);
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));
            for(int i = 0; i<dimension*dimension; i++){
                writer.write("1");
                writer.write("\n");
                writer.write("0");
                writer.write("\n");
            }
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }

    }
}
