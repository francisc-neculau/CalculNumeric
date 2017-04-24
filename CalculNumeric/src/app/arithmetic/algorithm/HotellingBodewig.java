package app.arithmetic.algorithm;

import java.math.BigDecimal;

import app.arithmetic.model.EpsilonPrecision;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.SquareMatrix;

public class HotellingBodewig
{

	private Matrix v;
	private int maxNumberOfIterations;
	private int iterationNumber;
	private boolean converged;
	private static BigDecimal TWO = new BigDecimal("2");
	private static BigDecimal TEN_POW_TEN = (new BigDecimal("10")).pow(10);
	
	public HotellingBodewig(int maxNumberOfIterations)
	{
		this.maxNumberOfIterations = maxNumberOfIterations;
		this.iterationNumber = 0;
	}

	public void execute(SquareMatrix A)
	{
		BigDecimal deltaNorm, divisor = A.norm(NormType.MAX_ABSOLUTE_ROW_SUM).multiply(A.norm(NormType.MAX_ABSOLUTE_COLUMN_SUM));
		Matrix vPrevious;
		
		vPrevious = A.transposeMultiply(BigDecimal.ONE.divide(divisor, EpsilonPrecision.getInstance().getMathContext()));
		v = vPrevious;

		iterationNumber = 0;
		do
		{
			// ********** Step 1
			vPrevious = v;
			// ********** Step 2
			v = vPrevious.multiply(TWO).subtract(vPrevious.multiply(A).multiply(vPrevious));
			// ********** norm
			deltaNorm = v.subtract(vPrevious).norm(NormType.MAXIMUM);
			
			if(deltaNorm.compareTo(EpsilonPrecision.getInstance().getEpsilon()) == -1)
				break;
//			if(deltaNorm.compareTo(TEN_POW_TEN) == 1)
//				break;  
			
			iterationNumber++;
		} while (iterationNumber < maxNumberOfIterations);
		
		if(deltaNorm.compareTo(EpsilonPrecision.getInstance().getEpsilon()) == -1)
			converged = true;
		else
			converged = false;
	}
	
	public SquareMatrix getInverseAproximation()
	{
		return (SquareMatrix)this.v;
	}
	
	public int getIterationNumber()
	{
		return iterationNumber;
	}
	
	public boolean hasConverged()
	{
		return this.converged;
	}
}
