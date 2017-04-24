package app.gui.services;

import java.math.BigDecimal;

import org.apache.log4j.BasicConfigurator;

import app.arithmetic.algorithm.HotellingBodewig;
import app.arithmetic.algorithm.LiAndLi;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.DenseSquareMatrix;
import app.arithmetic.model.matrix.type.square.SquareMatrix;

public class Homework5
{
	private static String[] results = new String[100];
	private static int MAX_NUMBER_OF_ITERATIONS = 1000;

	// 36 is max for HotellingBodewig | 30 for the other
	private static int dimension = 4;

	public static void setDimension(int dimension)
	{
		Homework5.dimension = dimension;
	}
	
	public static String[] getResults()
	{
		return results;
	}

	public static void execute()
	{
		BasicConfigurator.configure();
		long startTime, endTime;

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
		
		HotellingBodewig hotellingBodewig = new HotellingBodewig(MAX_NUMBER_OF_ITERATIONS);
		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		hotellingBodewig.execute((SquareMatrix)A);
		//
		//
		endTime = System.currentTimeMillis();
		results[0] = (new Double((endTime - startTime))/1000 + "s");
		//
		//
		results[1] = "converged   : " + hotellingBodewig.hasConverged();
		results[2] = "iteration # : " + hotellingBodewig.getIterationNumber();
		results[3] = "first norm  : " + A.multiply(hotellingBodewig.getInverseAproximation()).addDiagonal(BigDecimal.ONE.negate()).norm(NormType.MAXIMUM);
		results[4] = "inverse of A : \n" + hotellingBodewig.getInverseAproximation();
		
		
		
		LiAndLi liAndLi = new LiAndLi(MAX_NUMBER_OF_ITERATIONS);
		//
		//
		startTime = System.currentTimeMillis();
		//
		//
		liAndLi.execute((SquareMatrix)A);
		//
		//
		endTime = System.currentTimeMillis();
		results[5] = (new Double((endTime - startTime))/1000 + "s");
		//
		//
		results[6] = "converged   : " + liAndLi.hasConverged();
		results[7] = "iteration # : " + liAndLi.getIterationNumber();
		results[8] = "first norm  : " + A.multiply(liAndLi.getInverseAproximation()).addDiagonal(BigDecimal.ONE.negate()).norm(NormType.MAXIMUM);
		results[9] = "inverse of A : \n" + liAndLi.getInverseAproximation();
	}

}