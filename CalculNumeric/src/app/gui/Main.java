package app.gui;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import app.arithmetic.algorithm.CholeskyDecomposition;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.MutableMatrix;
import app.arithmetic.model.matrix.type.square.DiagonalMatrix;
import app.arithmetic.model.matrix.type.square.MirrorMatrix;
import app.arithmetic.model.matrix.type.square.StrictLowerTriangularMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

public class Main
{

	public static void main(String[] args)
	{
//		BigDecimal a,b;
//
//		a = new BigDecimal("21384");
//		b = new BigDecimal("43.111111120888888888");
//		
//		System.out.println(a.divide(b, 9, RoundingMode.HALF_DOWN));
//		
//		if(true)
//			return;

		Integer matricesDimension = 4;
		
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

		BigDecimal[][] elements = new BigDecimal[4][4];
		
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
		
		
		MirrorMatrix A = new MirrorMatrix(matricesDimension, elements);
		Matrix B = new ColumnMatrix(matricesDimension, new Double[]{2.0, 9.0, 26.0, 4.0});

		System.out.println("A:\n" + A);
		System.out.println("B:\n" + B);

		/* ***
		 * I
		 * ***
		 *  Sa se calculeze, o descompunere LDLT(descompunerea/factorizarea Choleski) a matricii A (A = LDLT ), 
		 *  unde L este matrice inferior triunghiulara cu toate elementele de pe diagonala pricipala egale cu 1 iar
		 *  D este matrice diagonala;
		 */
		System.out.println("\nI" + "\n--------------------");
		CholeskyDecomposition cd = new CholeskyDecomposition();
		MutableMatrix[] result = cd.decompose(A);
		
		StrictLowerTriangularMatrix L = (StrictLowerTriangularMatrix)result[0];
		DiagonalMatrix D = (DiagonalMatrix)result[1];
		
		System.out.println("L:\n" + L);
		System.out.println("D:\n" + D);

		/* ***
		 * II
		 * ***
		 * Folosind aceasta descompunere, sa se calculeze determinantul matricii A 
		 * (det A = det L det D det LT ) ;
		 */
		System.out.println("\nII" + "\n--------------------");
		BigDecimal determinantA = L.determinant().multiply(D.determinant());
		System.out.println("det(A)="+determinantA);
		
		/* ***
		 * III
		 * ***
		 * Utilizand descompunerea Choleski calculata mai sus ¸si metodele substitutiei directe si inverse, 
		 * sa se calculeze xChol, o solutie aproximativa a sistemului Ax = b;
		 */
		System.out.println("\nIII" + "\n--------------------");
		Matrix xCholesky = L.transposeSolve(D.solve(L.solve(B)));//L.transposeSolve(D.solve(L.solve(B)));
		System.out.println("X Cholesky:\n"+xCholesky);
		
		/* ***
		 * IV
		 * ***
		 * 	Folosindu-se una din bibliotecile mentionate in pagina laboratorului, sa 
		 * se calculeze si sa se afiseze o descompunere LU a matricii A si solutia
		 * sistemului Ax = b;
		 */
		System.out.println("\nIV" + "\n--------------------");
//		LUDecomposition luDecomposition = new LUDecomposition(new Jama.Matrix(A.toDoubleVector()));
//		System.out.println(luDecomposition.getL());
//		System.out.println(luDecomposition.getU());
//		Jama.Matrix xJama = luDecomposition.getL().solve(luDecomposition.getL().solve(new Jama.Matrix(B.toDoubleVector())));
//		System.out.println(xJama);
//		
		/* ***
		 * V
		 * ***
		 * Sa se verifice solutia calculata prin afisarea normei:
		 */
		System.out.println("\nV" + "\n--------------------");
		System.out.println("A*xChol=\n"+A.multiply(xCholesky));
		System.out.println("A*xChol - B=\n"+A.multiply(xCholesky).subtract(B));
		BigDecimal norm = A.multiply(xCholesky).subtract(B).norm(NormType.EUCLIDEAN);
		System.out.println("norm=" + norm);
	}

}
