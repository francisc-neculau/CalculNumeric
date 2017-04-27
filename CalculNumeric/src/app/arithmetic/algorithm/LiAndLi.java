package app.arithmetic.algorithm;

import java.math.BigDecimal;

import app.arithmetic.model.EpsilonPrecision;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.SquareMatrix;

public class LiAndLi
{

	private Matrix v;
	private int maxNumberOfIterations;
	private int iterationNumber;
	private boolean converged;
	private static BigDecimal THREE = new BigDecimal("3");
	private static BigDecimal TEN_POW_TEN = (new BigDecimal("10")).pow(10);
	private static BigDecimal ONE_QUARTER = new BigDecimal("0.25");

	public LiAndLi(int maxNumberOfIterations)
	{
		this.maxNumberOfIterations = maxNumberOfIterations;
		this.iterationNumber = 0;
	}

	public void execute(SquareMatrix A)
	{
		BigDecimal deltaNorm, divisor = A.norm(NormType.MAX_ABSOLUTE_ROW_SUM).multiply(A.norm(NormType.MAX_ABSOLUTE_COLUMN_SUM));
		Matrix avPrevious, negativeA, vPrevious;
		
		negativeA = A.multiply(BigDecimal.ONE.negate());
		vPrevious = A.transposeMultiply(BigDecimal.ONE.divide(divisor, EpsilonPrecision.getInstance().getMathContext()));
		v = vPrevious;
		
		Matrix firstParenthesis, secondParenthesis;
		
		iterationNumber = 0;
		do
		{
			// ********** Step 1
			vPrevious = v;
			// ********** Step 2
			// Li and Li first version
			avPrevious = A.multiply(vPrevious);
			v = vPrevious.multiply(avPrevious.multiply(avPrevious).addDiagonal(THREE).subtract(avPrevious.multiply(THREE))); 

			// Li and Li second version
//			avPrevious = negativeA.multiply(vPrevious);
//			firstParenthesis  = avPrevious.addDiagonal(BigDecimal.ONE);
//			secondParenthesis = avPrevious.addDiagonal(THREE).multiply(avPrevious.addDiagonal(THREE));
//			v = vPrevious.multiply(
//					firstParenthesis.multiply(secondParenthesis).multiply(ONE_QUARTER).addDiagonal(BigDecimal.ONE)
//					); 

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
