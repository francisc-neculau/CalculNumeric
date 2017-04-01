package app.gui.services;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;

import Jama.LUDecomposition;
import app.arithmetic.algorithm.CholeskyDecomposition;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.MutableMatrix;
import app.arithmetic.model.matrix.type.square.DiagonalMatrix;
import app.arithmetic.model.matrix.type.square.MirrorMatrix;
import app.arithmetic.model.matrix.type.square.StrictLowerTriangularMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

public class Homework2
{
	private static String[] results = new String[100];
	private static String stringMatrices = null;
	
	public static void setStringMatrices(String stringMatrices)
	{
		Homework2.stringMatrices = stringMatrices;
	}

	public static String[] getResults()
	{
		return results;
	}
	
	public static void execute()
	{
		StringBuilder resultStringBuilder = new StringBuilder();
		BigDecimal[][] elements   = null;
		Double[] elementsB = null;
		Integer matricesDimension = null;

		if(stringMatrices == null)
		{
			matricesDimension = 4;
			
	//		Double[][] elements = new Double[matricesDimension][];
	//
	//		elements[0] = new Double[1];
	//		elements[1] = new Double[2];
	//		elements[2] = new Double[3];
	//		
	//		elements[0][0] = 1.0;
	//		
	//		elements[1][0] = 2.5;
	//		elements[1][1] = 8.25;
	//		
	//		elements[2][0] = 3.0;
	//		elements[2][1] = 15.5;
	//		elements[2][2] = 43.0;
	
			elements = new BigDecimal[4][4];
			
			elements[0][0] = new BigDecimal("18");
			
			elements[1][0] = new BigDecimal("22");
			elements[1][1] = new BigDecimal("70");
	
			elements[2][0] = new BigDecimal("54");
			elements[2][1] = new BigDecimal("86");
			elements[2][2] = new BigDecimal("174");
			
			elements[3][0] = new BigDecimal("42");
			elements[3][1] = new BigDecimal("62");
			elements[3][2] = new BigDecimal("134"); 
			elements[3][3] = new BigDecimal("106");
			
			elementsB = new Double[]{2.0, 9.0, 26.0, 4.0};
		}
		else
		{
			String[] lines = stringMatrices.split("\n");
			matricesDimension = new Integer(lines[0].trim());
			elements = new BigDecimal[matricesDimension][matricesDimension];
			elementsB = new Double[matricesDimension];
			for (int i = 0; i < matricesDimension; i++)
			{
				String[] numbers = lines[2 + i].split(", ");
				for (int j = 0; j < numbers.length - 1; j++)
				{
					elements[i][j] = new BigDecimal(numbers[j].trim());
				}
			}
			String[] numbersB = lines[3 + matricesDimension].split(", ");
			for (int i = 0; i < numbersB.length - 1; i++)
			{
				elementsB[i] = new Double(numbersB[i].trim());
			}
		}
		
		MirrorMatrix A = new MirrorMatrix(matricesDimension, elements);
		Matrix B = new ColumnMatrix(matricesDimension, elementsB);

		resultStringBuilder.append("A:\n" + A);
		resultStringBuilder.append("B:\n" + B);

		/* ***
		 * I
		 * ***
		 *  Sa se calculeze, o descompunere LDLT(descompunerea/factorizarea Choleski) a matricii A (A = LDLT ), 
		 *  unde L este matrice inferior triunghiulara cu toate elementele de pe diagonala pricipala egale cu 1 iar
		 *  D este matrice diagonala;
		 */
		resultStringBuilder.append("\nI" + "\n--------------------\n");
		CholeskyDecomposition cd = new CholeskyDecomposition();
		MutableMatrix[] result = cd.decompose(A);
		
		StrictLowerTriangularMatrix L = (StrictLowerTriangularMatrix)result[0];
		DiagonalMatrix D = (DiagonalMatrix)result[1];
		
		resultStringBuilder.append("L:\n" + L);
		resultStringBuilder.append("D:\n" + D);

		/* ***
		 * II
		 * ***
		 * Folosind aceasta descompunere, sa se calculeze determinantul matricii A 
		 * (det A = det L det D det LT ) ;
		 */
		resultStringBuilder.append("\nII" + "\n--------------------\n");
		BigDecimal determinantA = D.determinant();//L.determinant().multiply(D.determinant());
		resultStringBuilder.append("det(A)="+determinantA.toString().substring(0, 30));
		
		/* ***
		 * III
		 * ***
		 * Utilizand descompunerea Choleski calculata mai sus ¸si metodele substitutiei directe si inverse, 
		 * sa se calculeze xChol, o solutie aproximativa a sistemului Ax = b;
		 */
		resultStringBuilder.append("\nIII" + "\n--------------------\n");
		Matrix xCholesky = L.transposeSolve(D.solve(L.solve(B)));//L.transposeSolve(D.solve(L.solve(B)));
		resultStringBuilder.append("X Cholesky:\n"+xCholesky);
		
		/* ***
		 * IV
		 * ***
		 * 	Folosindu-se una din bibliotecile mentionate in pagina laboratorului, sa 
		 * se calculeze si sa se afiseze o descompunere LU a matricii A si solutia
		 * sistemului Ax = b;
		 */
		try
		{
			resultStringBuilder.append("\nIV" + "\n--------------------\n");
			resultStringBuilder.append("LU decomposition\n");
			Jama.Matrix jmA, jmB;
			jmA = new Jama.Matrix(A.doubleValue());
			jmB = new Jama.Matrix(B.doubleValue());
			LUDecomposition luDecomposition = new LUDecomposition(jmA);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintWriter baosPw = new PrintWriter(baos);
			
			baosPw.append("L :\n");
			luDecomposition.getL().print(baosPw, 20, 9);
			
			baosPw.append("U :\n");
			luDecomposition.getU().print(baosPw, 20, 9);
			
			baosPw.append("L * U * X = B\n");
			baosPw.append("X : \n");
			Jama.Matrix xJama = luDecomposition.solve(jmB);
			xJama.print(baosPw, 20, 9);
			
			baosPw.flush();
			resultStringBuilder.append(baos.toString("UTF-8"));
			baosPw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		/* ***
		 * V
		 * ***
		 * Sa se verifice solutia calculata prin afisarea normei:
		 */
		resultStringBuilder.append("\nV" + "\n--------------------\n");
		resultStringBuilder.append("A*xChol=\n"+A.multiply(xCholesky));
		resultStringBuilder.append("A*xChol - B=\n"+A.multiply(xCholesky).subtract(B));
		BigDecimal norm = A.multiply(xCholesky).subtract(B).norm(NormType.EUCLIDEAN);
		resultStringBuilder.append("norm=" + norm);
		
		results[0] = resultStringBuilder.toString();
	}
}
