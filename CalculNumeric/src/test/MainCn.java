package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainCn
{

	public static void main(String[] args)
	{		
		try
		{
			BufferedReader frA = new BufferedReader(new FileReader(new File("")));
			String line;
			while((line = frA.readLine()) != null)
			{
				
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
////		Homework1.execute();
////
////		System.out.println(Homework1.getResults()[0]);
////		System.out.println(Homework1.getResults()[1]);
////		System.out.println(Homework1.getResults()[2]);
////		System.out.println(Homework1.getResults()[3]);
////		if(new Integer(1) == 1)
////			return;
//
//		Integer matricesDimension = 4;
//		
////		Double[][] elements = new Double[matricesDimension][];
////
////		elements[0] = new Double[1];
////		elements[1] = new Double[2];
////		elements[2] = new Double[3];
////		
////		elements[0][0] = 1.0;
////		
////		elements[1][0] = 2.5;
////		elements[1][1] = 8.25;
////		
////		elements[2][0] = 3.0;
////		elements[2][1] = 15.5;
////		elements[2][2] = 43.0;
//
//		BigDecimal[][] elements = new BigDecimal[4][4];
//		
//		elements[0][0] = new BigDecimal("18");
//		
//		elements[1][0] = new BigDecimal("22");
//		elements[1][1] = new BigDecimal("70");
//
//		elements[2][0] = new BigDecimal("54");
//		elements[2][1] = new BigDecimal("86");
//		elements[2][2] = new BigDecimal("174");
//		
//		elements[3][0] = new BigDecimal("42");
//		elements[3][1] = new BigDecimal("62");
//		elements[3][2] = new BigDecimal("134"); 
//		elements[3][3] = new BigDecimal("106");
//		
//		
//		MirrorMatrix A = new MirrorMatrix(matricesDimension, elements);
//		Matrix B = new ColumnMatrix(matricesDimension, new Double[]{2.0, 9.0, 26.0, 4.0});
//
//		System.out.println("A:\n" + A);
//		System.out.println("B:\n" + B);
//
//		/* ***
//		 * I
//		 * ***
//		 *  Sa se calculeze, o descompunere LDLT(descompunerea/factorizarea Choleski) a matricii A (A = LDLT ), 
//		 *  unde L este matrice inferior triunghiulara cu toate elementele de pe diagonala pricipala egale cu 1 iar
//		 *  D este matrice diagonala;
//		 */
//		System.out.println("\nI" + "\n--------------------");
//		CholeskyDecomposition cd = new CholeskyDecomposition();
//		MutableMatrix[] result = cd.decompose(A);
//		
//		StrictLowerTriangularMatrix L = (StrictLowerTriangularMatrix)result[0];
//		DiagonalMatrix D = (DiagonalMatrix)result[1];
//		
//		System.out.println("L:\n" + L);
//		System.out.println("D:\n" + D);
//
//		/* ***
//		 * II
//		 * ***
//		 * Folosind aceasta descompunere, sa se calculeze determinantul matricii A 
//		 * (det A = det L det D det LT ) ;
//		 */
//		System.out.println("\nII" + "\n--------------------");
//		BigDecimal determinantA = D.determinant();//L.determinant().multiply(D.determinant());
//		System.out.println("det(A)="+determinantA);
//		
//		/* ***
//		 * III
//		 * ***
//		 * Utilizand descompunerea Choleski calculata mai sus ¸si metodele substitutiei directe si inverse, 
//		 * sa se calculeze xChol, o solutie aproximativa a sistemului Ax = b;
//		 */
//		System.out.println("\nIII" + "\n--------------------");
//		Matrix xCholesky = L.transposeSolve(D.solve(L.solve(B)));//L.transposeSolve(D.solve(L.solve(B)));
//		System.out.println("X Cholesky:\n"+xCholesky);
//		
//		/* ***
//		 * IV
//		 * ***
//		 * 	Folosindu-se una din bibliotecile mentionate in pagina laboratorului, sa 
//		 * se calculeze si sa se afiseze o descompunere LU a matricii A si solutia
//		 * sistemului Ax = b;
//		 */
//		System.out.println("\nIV" + "\n--------------------");
//		System.out.println("LU decomposition");
//		Jama.Matrix jmA, jmB;
//		jmA = new Jama.Matrix(A.doubleValue());
//		jmB = new Jama.Matrix(B.doubleValue());
//		LUDecomposition luDecomposition = new LUDecomposition(jmA);
//		System.out.println("L :");
//		luDecomposition.getL().print(20, 9);
//		System.out.println("U :");
//		luDecomposition.getU().print(20, 9);
//		System.out.println("L * U * X = B");
//		System.out.println("X : ");
//		Jama.Matrix xJama = luDecomposition.solve(jmB);
//		xJama.print(20, 9);
//
//		/* ***
//		 * V
//		 * ***
//		 * Sa se verifice solutia calculata prin afisarea normei:
//		 */
//		System.out.println("\nV" + "\n--------------------");
//		System.out.println("A*xChol=\n"+A.multiply(xCholesky));
//		System.out.println("A*xChol - B=\n"+A.multiply(xCholesky).subtract(B));
//		BigDecimal norm = A.multiply(xCholesky).subtract(B).norm(NormType.EUCLIDEAN);
//		System.out.println("norm=" + norm);
	}

}
