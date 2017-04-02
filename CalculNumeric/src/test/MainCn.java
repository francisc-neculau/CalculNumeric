package test;

import java.math.BigDecimal;

import org.apache.log4j.BasicConfigurator;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.type.square.sparse.SparseMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;
import app.arithmetic.util.SparseMatrixFileReader;

public class MainCn
{

	public static void main(String[] args)
	{
		BasicConfigurator.configure();
		long startTime, endTime;

		SparseMatrixFileReader a, aorib, aplusb, b;
		Matrix A, Ab, B, Bb, AoriB, AoriBb, AplusB, AplusBb, b2017;
		
		a      = new SparseMatrixFileReader("resources/homework3/a.txt");
		aorib  = new SparseMatrixFileReader("resources/homework3/aorib.txt");
		aplusb = new SparseMatrixFileReader("resources/homework3/aplusb.txt");
		b      = new SparseMatrixFileReader("resources/homework3/b.txt");
		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		A  = new SparseMatrix(a.getDimension(), a.getElements());
		Ab = new ColumnMatrix(a.getDimension(), a.getBelements());
		
		B  = new SparseMatrix(b.getDimension(), b.getElements());
		Bb = new ColumnMatrix(b.getDimension(), b.getBelements());
		
		AoriB  = new SparseMatrix(aorib.getDimension(), aorib.getElements());
		AoriBb = new ColumnMatrix(aorib.getDimension(), aorib.getBelements());
		
		AplusB  = new SparseMatrix(aplusb.getDimension(), aplusb.getElements());
		AplusBb = new ColumnMatrix(aplusb.getDimension(), aplusb.getBelements());
		
		{
			BigDecimal [] elements = new BigDecimal[2017];
			for (int i = 0; i < 2017; i++)
				elements[i] = new BigDecimal(2017 - i);
			b2017 = new ColumnMatrix(2017, elements);
		}
		System.out.println("A * (2017,2016..2,1) (computed) = Ab(file) - " + A.multiply(b2017).equals(Ab));
		System.out.println("B * (2017,2016..2,1) (computed) = Bb(file) - " + B.multiply(b2017).equals(Bb));
		System.out.println("A + B (computed) = A + B(file) - " + A.add(B).equals(AplusB));
		System.out.println("(A + B) * (2017,2016..2,1) (computed) = (A + B) * (2017,2016..2,1) (file) - " + A.add(B).multiply(b2017).equals(AplusBb));
		//
		//
		endTime = System.currentTimeMillis();
		System.out.println("Program ended - time : " + new Double((endTime - startTime))/1000 + "s");
		//
		//
//		SparseMatrixFileReader fileA = new SparseMatrixFileReader("resources/homework3/testA.txt");
//		SparseMatrixFileReader fileB = new SparseMatrixFileReader("resources/homework3/testB.txt");
//		Matrix testA = new SparseMatrix(fileA.getDimension(), fileA.getElements());
//		Matrix testB = new SparseMatrix(fileB.getDimension(), fileB.getElements());
//		System.out.println(testA);
//		System.out.println(testB);
//		System.out.println("-------");
//		System.out.println(testA.equals(testB));
//		System.out.println(testA.add(testB));
	}
	
}
