package test;

import java.math.BigDecimal;

import org.apache.log4j.BasicConfigurator;

import app.arithmetic.algorithm.HotellingBodewig;
import app.arithmetic.algorithm.LiAndLi;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.DenseSquareMatrix;
import app.arithmetic.model.matrix.type.square.SquareMatrix;

public class MainCn
{

	public static void main(String[] args)
	{
		BasicConfigurator.configure();
		long startTime, endTime;

		
		int dimension = 40; // 36 is max for HotellingBodewig | 30 for the other
		Double[][] elements = new Double[dimension][dimension];
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				elements[i][j] = 0.0;
		for (int i = 0; i < dimension - 1; i++)
		{
			elements[i][i] = 1.0;
			elements[i][i + 1] = 2.0;
		}
		elements[dimension - 1][dimension - 1] = 1.0;

		Matrix A = new DenseSquareMatrix(dimension, elements);		
		
// 		System.out.println(A.addDiagonal(BigDecimal.ONE).addDiagonal(BigDecimal.ONE).multiply(BigDecimal.ONE.negate()));
		
		HotellingBodewig hotellingBodewig = new HotellingBodewig(1000);
		hotellingBodewig.execute((SquareMatrix)A);
		System.out.println("converged   : " + hotellingBodewig.hasConverged());
		System.out.println("iteration # : " + hotellingBodewig.getIterationNumber());
		System.out.println("first norm  : " + A.multiply(hotellingBodewig.getInverseAproximation()).addDiagonal(BigDecimal.ONE.negate()).norm(NormType.MAXIMUM));
		System.out.println("inverse of A : \n" + hotellingBodewig.getInverseAproximation());
		
		LiAndLi liAndLi = new LiAndLi(1000);
		liAndLi.execute((SquareMatrix)A);
		System.out.println("converged   : " + liAndLi.hasConverged());
		System.out.println("iteration # : " + liAndLi.getIterationNumber());
		System.out.println("first norm  : " + A.multiply(liAndLi.getInverseAproximation()).addDiagonal(BigDecimal.ONE.negate()).norm(NormType.MAXIMUM));
		System.out.println("inverse of A : \n" + liAndLi.getInverseAproximation());
		
		
//		SparseMatrixReader reader = new SparseMatrixReader("resources/homework6/m_rar_sim_2017.txt");
//		RandomMatrix random = new RandomMatrix(System.currentTimeMillis());
//		
//		Matrix randomMatrix, fileMatrix;
//		
//		randomMatrix = random.nextSymmetricSparseMatrix(500, 0.015);
//		fileMatrix   = new SparseMatrix(reader.getDimension(), reader.getElements());
//		
//		PowerIteration powerIteration = new PowerIteration(500);
//		
//		powerIteration.execute((SquareMatrix)fileMatrix);
//		System.out.println(powerIteration.getEigenValue());
//		
//		powerIteration.execute((SquareMatrix)randomMatrix);
//		System.out.println(powerIteration.getEigenValue());
		
		
		
		
		
//		//SparseMatrixFileReader reader = new SparseMatrixFileReader("resources/homework4/bicgstab.txt");
//		SparseMatrixFileReader reader = new SparseMatrixFileReader("resources/homework4/m_rar_2017_3.txt");
//		
//		SparseMatrix A = new SparseMatrix(reader.getDimension(), reader.getElements());
//		ColumnMatrix B = new ColumnMatrix(reader.getDimension(), reader.getBelements());
//		
//		//
//		//
//		startTime = System.currentTimeMillis();
//		//
//		//
//		BiConjugateGradientStabilized bicgstab = new BiConjugateGradientStabilized();
//		bicgstab.solve(A, B);
//		System.out.println(bicgstab.getX());
//		//
//		//
//		endTime = System.currentTimeMillis();
//		System.out.println("BiConjugateGradientStabilized - time : " + new Double((endTime - startTime))/1000 + "s");
//		//
//		//
//		//
//		//
//		startTime = System.currentTimeMillis();
//		//
//		//
//		GaussSeidel gs = new GaussSeidel(100);
//		gs.solve(A, B);
//		System.out.println(gs.getXgs());
//		//
//		//
//		endTime = System.currentTimeMillis();
//		System.out.println("GaussSeidel - time : " + new Double((endTime - startTime))/1000 + "s");
//		//
//		//
		
		
		
		
		
		
		
//		long startTime, endTime;
//
//		SparseMatrixFileReader m1, m2, m3, m4;
//		SparseMatrix M1, M2, M3, M4;
//		ColumnMatrix B1, B2, B3, B4;
//		Matrix R1, R2, R3, R4;
//		
//		m1 = new SparseMatrixFileReader("resources/homework4/m_rar_2017_1.txt");
//		m2 = new SparseMatrixFileReader("resources/homework4/m_rar_2017_2.txt");
//		m3 = new SparseMatrixFileReader("resources/homework4/m_rar_2017_3.txt");
//		m4 = new SparseMatrixFileReader("resources/homework4/m_rar_2017_4.txt");
//		//
//		//
//		startTime = System.currentTimeMillis();
//		//
//		//
//		M1  = new SparseMatrix(m1.getDimension(), m1.getElements());
//		B1  = new ColumnMatrix(m1.getDimension(), m1.getBelements());
//		
//		M2  = new SparseMatrix(m2.getDimension(), m2.getElements());
//		B2  = new ColumnMatrix(m2.getDimension(), m2.getBelements());
//		
//		M3  = new SparseMatrix(m3.getDimension(), m3.getElements());
//		B3  = new ColumnMatrix(m3.getDimension(), m3.getBelements());
//		
//		M4  = new SparseMatrix(m4.getDimension(), m4.getElements());
//		B4  = new ColumnMatrix(m4.getDimension(), m4.getBelements());
//		
//		GaussSeidel gs = new GaussSeidel(100);
//		
//		gs.solve(M1, B1);
//		R1 = M1.multiply(gs.getXgs()).subtract(B1);
//		System.out.println(gs.getXgs());
//		System.out.println("Norm : " + R1.norm(NormType.UNIFORM).toPlainString());
//		
//		gs.solve(M2, B2);
//		R2 = M2.multiply(gs.getXgs()).subtract(B2);
//		System.out.println(gs.getXgs());
//		System.out.println("Norm : " + R2.norm(NormType.UNIFORM).toPlainString());
//		
//		gs.solve(M3, B3);
//		R3 = M3.multiply(gs.getXgs()).subtract(B3);
//		System.out.println(gs.getXgs());
//		System.out.println("Norm : " + R3.norm(NormType.UNIFORM).toPlainString());
//		
//		gs.solve(M4, B4);
//		R4 = M4.multiply(gs.getXgs()).subtract(B4);
//		System.out.println(gs.getXgs());
//		System.out.println("Norm : " + R4.norm(NormType.UNIFORM).toPlainString());
//		//
//		//
//		endTime = System.currentTimeMillis();
//		System.out.println("Program ended - time : " + new Double((endTime - startTime))/1000 + "s");
//		//
//		//
//		SparseMatrixFileReader file = new SparseMatrixFileReader("resources/homework4/test.txt");
//		SparseMatrix A = new SparseMatrix(file.getDimension(), file.getElements());
//		ColumnMatrix B = new ColumnMatrix(file.getDimension(), file.getBelements());
//		GaussSeidel gs = new GaussSeidel(1);
//		gs.solve(A, B);
//		System.out.println(gs.getXgs());
		
//		BasicConfigurator.configure();
//		long startTime, endTime;
//
//		SparseMatrixFileReader a, aorib, aplusb, b;
//		Matrix A, Ab, B, Bb, AoriB, AoriBb, AplusB, AplusBb, b2017;
//		
//		a      = new SparseMatrixFileReader("resources/homework3/a.txt");
//		aorib  = new SparseMatrixFileReader("resources/homework3/aorib.txt");
//		aplusb = new SparseMatrixFileReader("resources/homework3/aplusb.txt");
//		b      = new SparseMatrixFileReader("resources/homework3/b.txt");
//		//
//		//
//		startTime = System.currentTimeMillis();
//		//
//		//
//		A  = new SparseMatrix(a.getDimension(), a.getElements());
//		Ab = new ColumnMatrix(a.getDimension(), a.getBelements());
//		
//		B  = new SparseMatrix(b.getDimension(), b.getElements());
//		Bb = new ColumnMatrix(b.getDimension(), b.getBelements());
//		
//		AoriB  = new SparseMatrix(aorib.getDimension(), aorib.getElements());
//		AoriBb = new ColumnMatrix(aorib.getDimension(), aorib.getBelements());
//		
//		AplusB  = new SparseMatrix(aplusb.getDimension(), aplusb.getElements());
//		AplusBb = new ColumnMatrix(aplusb.getDimension(), aplusb.getBelements());
//		
//		{
//			BigDecimal [] elements = new BigDecimal[2017];
//			for (int i = 0; i < 2017; i++)
//				elements[i] = new BigDecimal(2017 - i);
//			b2017 = new ColumnMatrix(2017, elements);
//		}
//		System.out.println("A * (2017,2016..2,1) (computed) = Ab(file) \n - " + A.multiply(b2017).equals(Ab));
//		System.out.println("B * (2017,2016..2,1) (computed) = Bb(file) \n - " + B.multiply(b2017).equals(Bb));
//		System.out.println("A * B (computed) = A * B (file) \n - " + A.multiply(B).equals(AoriB));
//		System.out.println("(A * B) * b (computed) = (A * B) * b (file) \n - " + A.multiply(B).multiply(b2017).equals(AoriBb));
//		System.out.println("A + B (computed) = A + B(file) \n - " + A.add(B).equals(AplusB));
//		System.out.println("(A + B) * (2017,2016..2,1) (computed) = (A + B) * (2017,2016..2,1) (file) \n - " + A.add(B).multiply(b2017).equals(AplusBb));
//		//
//		//
//		endTime = System.currentTimeMillis();
//		System.out.println("Program ended - time : " + new Double((endTime - startTime))/1000 + "s");
//		//
//		//
//		SparseMatrixFileReader fileA = new SparseMatrixFileReader("resources/homework3/testA.txt");
//		SparseMatrixFileReader fileB = new SparseMatrixFileReader("resources/homework3/testB.txt");
//		Matrix testA = new SparseMatrix(fileA.getDimension(), fileA.getElements());
//		Matrix testB = new SparseMatrix(fileB.getDimension(), fileB.getElements());
////		System.out.println(testA);
////		System.out.println(testB);
//		System.out.println("-------");
//		System.out.println(testA);
//		System.out.println("-------");
////		System.out.println(testA.equals(testB));
//		System.out.println("B*A\n"+testA.multiply(testB));
//		//System.out.println("B*A\n"+testB.multiply(testA));
//		//System.out.println();
//		//System.out.println((testA.multiply(testB)).equals(testB.multiply(testA)));
	}
	
}
