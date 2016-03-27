import java.io.*;


public class Matrix {
	public int[][] vals;
	public int dim;
	
	public Matrix(int[][] vals) {
		this.vals = vals;
		this.dim = vals.length;
	}
	
	public static Matrix[] createMatrices(String filename, int dim) {
	
		String[] lines = new String[dim*dim*2]; //array tof hold the total expected lines
		
		try {
			FileReader input = new FileReader(filename);
			BufferedReader reader = new BufferedReader(input);
			for (int i = 0; i < lines.length; i++) {
				String line = reader.readLine();
				lines[i] = line;
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Matrix[] arrayOfMatrices = new Matrix[2];
		
		int counter = 0;
		for (int i = 0; i < 2; i++) {
			int[][] vals = new int[dim][dim];
			for (int j = 0; j < dim; j++) { 
				for (int k = 0; k < dim; k++) {
					vals[j][k] = Integer.parseInt(lines[counter]);
					counter++;
				}
			}
			arrayOfMatrices[i] = new Matrix(vals);
		}
		return arrayOfMatrices;
	}
	
	public void print() {
		//just to print diagonal
		for (int i = 0; i < this.dim; i++) {
			System.out.println(this.vals[i][i]);
		}

		//code for printing whole matrix, for testing

		for (int i = 0; i < this.dim; i++) {
			String line = "";
			for (int j = 0; j < this.dim; j++) {
				line += this.vals[i][j] + "\t";
		  }
				System.out.println(line);
		}

	}
}
