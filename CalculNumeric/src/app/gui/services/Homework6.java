package app.gui.services;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import org.apache.log4j.BasicConfigurator;

import Jama.SingularValueDecomposition;
import app.arithmetic.algorithm.PowerIteration;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.type.square.SquareMatrix;
import app.arithmetic.model.matrix.type.square.sparse.SparseMatrix;
import app.arithmetic.util.RandomMatrix;
import app.arithmetic.util.RandomNumbers;
import app.arithmetic.util.SparseMatrixReader;

public class Homework6
{
	private static String[] results = new String[100];

	private static int    randomSparseMatrixDimension = 500;
	private static double randomSparseMatrixSparsity  = 0.015;
	
	private static int numberOfRows    = 5;    // n
	private static int numberOfColumns = 7; // p
	private static int s = 5;

	public static String[] getResults()
	{
		return results;
	}

	public static void setValues(int numberOfRows, int numberOfColumns, int s, double randomSparseMatrixSparsity, int randomSparseMatrixDimension)
	{
		Homework6.numberOfRows = numberOfRows;
		Homework6.numberOfColumns = numberOfColumns;
		Homework6.s = s;
		Homework6.randomSparseMatrixSparsity = randomSparseMatrixSparsity;
		Homework6.randomSparseMatrixDimension = randomSparseMatrixDimension;
	}
	
	public static void execute()
	{
		BasicConfigurator.configure();
		long startTime, endTime;

		
		
		
		Random r = new Random(System.currentTimeMillis());
		BigInteger lowerBound = BigInteger.ONE;
		BigInteger upperBound = new BigInteger(new Integer(numberOfColumns).toString());
		
		double[][] elements = new double[numberOfRows][numberOfColumns];
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				elements[i][j] = new BigDecimal(RandomNumbers.nextBigInteger(r, lowerBound, upperBound)).doubleValue();
		
		Jama.Matrix A = new Jama.Matrix(elements);
		SingularValueDecomposition svd = new SingularValueDecomposition(A);

		double [] eigenvalues = svd.getSingularValues();
		
		results[0] = "";
		for (int i = 0; i < eigenvalues.length - 1; i++)
			results[0] = results[0] + eigenvalues[i] + "\n";

		results[1] = "rank  : " + svd.rank();
		results[2] = "k2(A) : " + svd.cond();
		results[3] = "norm  : " + svd.norm2();
		
		Jama.Matrix As, ui, vi; 
		
		ui = svd.getU().getMatrix(0, numberOfRows - 1, 0, 0);
		vi = svd.getV().getMatrix(0, numberOfColumns - 1, 0, 0);
		As = ui.times(vi.transpose()).times(eigenvalues[4]);

		for (int i = 1; i < s; i++) // max is the number of rows
		{
			ui = svd.getU().getMatrix(0, numberOfRows - 1, i, i);
			vi = svd.getV().getMatrix(0, numberOfColumns - 1, i, i);
			As = As.plus(ui.times(vi.transpose()).times(eigenvalues[4 - i]));
		}
		results[4] = "";
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintWriter baosPw = new PrintWriter(baos);
			As.print(baosPw, 20, 9);
			baosPw.flush();
			results[4] =(baos.toString("UTF-8"));
			baosPw.close();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		results[5] = "norm  : " + As.minus(A).norm2();
		
		
		
		
		SparseMatrixReader reader = new SparseMatrixReader("resources/homework6/m_rar_sim_2017.txt");
		RandomMatrix random = new RandomMatrix(System.currentTimeMillis());
		
		Matrix randomMatrix, fileMatrix;
		
		randomMatrix = random.nextSymmetricSparseMatrix(randomSparseMatrixDimension, randomSparseMatrixSparsity);
		fileMatrix   = new SparseMatrix(reader.getDimension(), reader.getElements());
		
		PowerIteration powerIteration = new PowerIteration(randomSparseMatrixDimension);
		
		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		powerIteration.execute((SquareMatrix)fileMatrix);
		//
		//
		endTime = System.currentTimeMillis();
		results[6] = powerIteration.getEigenValue().toPlainString();
		results[7] = powerIteration.getEigenVector().toString();
		results[8] = " time : " + (new Double((endTime - startTime))/1000 + "s");
		//
		//
		
		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		powerIteration.execute((SquareMatrix)randomMatrix);
		//
		//
		endTime = System.currentTimeMillis();
		results[9] = randomMatrix.toString();
		results[10] = powerIteration.getEigenValue().toPlainString();
		results[11] = powerIteration.getEigenVector().toString();
		results[12] = " time : " + (new Double((endTime - startTime))/1000 + "s");
		//
		//
	}

}
