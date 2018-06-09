import java.util.Arrays;

import exception.InvalidMatrixInputException;

public class Matrix {
	/**
	 * Diese Methode soll eine Matrixmultiplikation
	 * durchführen und das Ergebnis zurück geben.
	 * Hinweis: Prüfen Sie am Anfang, ob eine Multiplikation der Matrizen überhaupt möglich ist. Legen
	 * Sie nach der Prüfungs die Ergebnismatrize an und implementieren den Algorithmus.
	 * @param matrix1
	 * @param matrix2
	 * @return
	 */
	public int[][] mult(int[][] matrix1, int[][] matrix2) throws InvalidMatrixInputException {
		/*
		 * Bedingung um Matrizen Multiplikation möglich ist:
		 * Bei einer Matrizenmultiplikation muss die Spaltenzahl der ersten Matrix gleich der Zeilenzahl der zweiten Matrix sein.
		 */
		if(matrix1[0].length != matrix2.length){
			 throw new InvalidMatrixInputException();
		}
		if(this.hasAnomaly(matrix1) || this.hasAnomaly(matrix2)){
			 throw new InvalidMatrixInputException();
		}
		/*
		 * Die Ergebnismatrix hat dann die Zeilenzahl der ersten und die Spaltenzahl der zweiten Matrix.
		 */
		int[][] ergMatrix = new int[matrix1.length][matrix2[0].length];
		/*
		 * ausrechnen der felder über eine felder definition
		 */
		 for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix2[0].length; j++) { 
				int tmp = 0;
				for (int j2 = 0; j2 < matrix1[0].length; j2++) {
					tmp +=  matrix1[i][j2] * matrix2[j2][j];
				}
				ergMatrix[i][j] = tmp;
			}
		}
		return ergMatrix;
	}
	
	//Sucht nach Anomalitäten im Input welche meine Funktion unbrauchbar machen
	public boolean hasAnomaly(int[][] testArr){
		//leere Arrays in dimension 1 werden nicht gebraucht
		if(testArr.length == 0) {
			return true;
		}
		//Unterschiede in der Länge sind verboten
		int testLength = testArr[0].length;
		for (int i = 0; i < testArr.length; i++) {
			if(testArr[i].length != testLength) {
				return true;
			}
		}
		return false; 
	}
	
	public static void runTests() throws InvalidMatrixInputException {
		Matrix matrix = new Matrix();
		int[][] mat1  = {{1,2,3},{1,2,3}};
		int[][] mat2  = {{1,2},{1,2},{1,2},{1,2}};
		
		try {
			matrix.mult(mat1, mat2);
		} catch (InvalidMatrixInputException e) {
			System.out.println("Exception wurde bei falschem Input geworfen: Zuviele Zeilen bei der zweiten Matrix");
		}
		
		int[][] mat3  = {{1,2,3,4},{1,2,3,4}};
		int[][] mat4  = {{1,2},{1,2},{1,2}};
		
		try {
			matrix.mult(mat3, mat4);
		} catch (InvalidMatrixInputException e) {
			System.out.println("Exception wurde bei falschem Input geworfen: Zuviele Spalten bei der ersten Matrix");
		}
		
		int[][] mat5  = {{1,2,3},{1,2,3}};
		int[][] mat6  = {{1,2},{1,2},{1,2,3,4,5}};
		
		try {
			matrix.mult(mat5, mat6);
		} catch (InvalidMatrixInputException e) {
			System.out.println("Exception wurde bei falschem Input geworfen: Anomaly in der Element Anzahl");
		}
		
		int[][] mat10  = {{1,2,3},{1,2,3}};
		int[][] mat11  = {{1,2},{1,2},{1,2}};
		int[][] expectedResult  = {{6,12},{6,12}};
	
		int equalArrays = Arrays.deepToString(expectedResult).compareTo(Arrays.deepToString(matrix.mult(mat10, mat11)));
		String test = (equalArrays == 0) ? "Resultat korrekt" : "Resultat inkorrekt";
		System.out.println(test);
		/**
		 * Aus irgendeinem Grund ist bei Java die string representation meines Ergebnisses gleich mit dem erwarteten aber ein direkter vergleich läuft schief.
		 */
		String anomaly = (expectedResult == matrix.mult(mat10, mat11)) ? "Resultat korrekt" : "Resultat inkorrekt"; 
		System.out.println(anomaly);
	}
}
