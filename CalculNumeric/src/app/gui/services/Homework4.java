package app.gui.services;

import org.apache.log4j.BasicConfigurator;

import app.arithmetic.algorithm.GaussSeidel;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.sparse.SparseMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;
import app.arithmetic.util.SparseMatrixFileReader;

public class Homework4
{
	private static String[] results = new String[100];
	
	public static String[] getResults()
	{
		return results;
	}

	public static void execute()
	{
		BasicConfigurator.configure();
		long startTime, endTime;

		SparseMatrixFileReader m1, m2, m3, m4;
		SparseMatrix M1, M2, M3, M4;
		ColumnMatrix B1, B2, B3, B4;
		Matrix R1, R2, R3, R4;
		
		GaussSeidel gs = new GaussSeidel(100);

		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		m1 = new SparseMatrixFileReader("resources/homework4/m_rar_2017_1.txt");
		M1  = new SparseMatrix(m1.getDimension(), m1.getElements());
		B1  = new ColumnMatrix(m1.getDimension(), m1.getBelements());
		gs.solve(M1, B1);
		R1 = M1.multiply(gs.getXgs()).subtract(B1);
		results[0] = (gs.getXgs().toString());
		results[1] = ("Norm : " + R1.norm(NormType.UNIFORM).toPlainString());
		//
		//
		endTime = System.currentTimeMillis();
		results[2] = (new Double((endTime - startTime))/1000 + "s");
		//
		//
		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		m2 = new SparseMatrixFileReader("resources/homework4/m_rar_2017_2.txt");
		M2  = new SparseMatrix(m2.getDimension(), m2.getElements());
		B2  = new ColumnMatrix(m2.getDimension(), m2.getBelements());
		gs.solve(M2, B2);
		R2 = M2.multiply(gs.getXgs()).subtract(B2);
		results[3] = (gs.getXgs().toString());
		results[4] = ("Norm : " + R2.norm(NormType.UNIFORM).toPlainString());
		//
		//
		endTime = System.currentTimeMillis();
		results[5] = (new Double((endTime - startTime))/1000 + "s");
		//
		//
		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		m3 = new SparseMatrixFileReader("resources/homework4/m_rar_2017_3.txt");
		M3  = new SparseMatrix(m3.getDimension(), m3.getElements());
		B3  = new ColumnMatrix(m3.getDimension(), m3.getBelements());
		gs.solve(M3, B3);
		R3 = M3.multiply(gs.getXgs()).subtract(B3);
		results[6] = (gs.getXgs().toString());
		results[7] = ("Norm : " + R3.norm(NormType.UNIFORM).toPlainString());
		//
		//
		endTime = System.currentTimeMillis();
		results[8] = (new Double((endTime - startTime))/1000 + "s");
		//
		//
		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		m4 = new SparseMatrixFileReader("resources/homework4/m_rar_2017_4.txt");
		M4  = new SparseMatrix(m4.getDimension(), m4.getElements());
		B4  = new ColumnMatrix(m4.getDimension(), m4.getBelements());
		gs.solve(M4, B4);
		R4 = M4.multiply(gs.getXgs()).subtract(B4);
		results[9] = (gs.getXgs().toString());
		results[10] = ("Norm : " + R4.norm(NormType.UNIFORM).toPlainString());
		//
		//
		endTime = System.currentTimeMillis();
		results[11] = (new Double((endTime - startTime))/1000 + "s");
		//
		//
		
	}

}