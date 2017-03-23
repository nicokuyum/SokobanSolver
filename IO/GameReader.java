package IO;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameReader {
	
	private static Scanner in;

	public static int[][] open(String file) throws FileNotFoundException{

			in = new Scanner(new java.io.FileReader(file));
			
			int total = Integer.parseInt(in.nextLine());
			int[][] matrix = new int[total][total];
			
			for(int i = 0 ; i < total ; i++){		
				String line = in.next();
				char[] ar = line.toCharArray();
				for(int j = 0 ; j < total ; j++){
					matrix[i][j] = ar[j] - 48; //48 es el desfasaje en ASCII por haber convertido al string en charArray!
				}
			}
			
			printMatrix(matrix);
			return matrix;
			
	}
	
	private static void printMatrix(int[][] matrix){
		int l = matrix.length;
		for(int i = 0 ; i < l ; i++){
			for(int j = 0 ; j < l ; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("");
		}
	}
	

}

