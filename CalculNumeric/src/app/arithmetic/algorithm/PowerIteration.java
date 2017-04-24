package app.arithmetic.algorithm;

import java.math.BigDecimal;

import app.arithmetic.model.EpsilonPrecision;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.SquareMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

// also known as the PowerMethod
public class PowerIteration
{
	private int maxNumberOfIterations;
	private BigDecimal eigenValue;
	private Matrix eigenVector;

	public PowerIteration(int maxNumberOfIterations)
	{
		this.maxNumberOfIterations = maxNumberOfIterations;
	}
	
	public void execute(SquareMatrix A)
	{
		BigDecimal norm, upperBound;
		Matrix w;

		upperBound = EpsilonPrecision.getInstance().getEpsilon().multiply(new BigDecimal(A.getDimension().toString()));
		
		// build the v vector
		Double [] elements = new Double[A.getDimension()];
		for (int i = 0; i < A.getDimension(); i++)
			elements[i] = 1.0;
		eigenVector = new ColumnMatrix(A.getDimension(), elements);
		eigenVector = eigenVector.multiply(BigDecimal.ONE.divide(eigenVector.norm(NormType.EUCLIDEAN), EpsilonPrecision.getInstance().getMathContext()));
		
		w = A.multiply(eigenVector);
		eigenValue = w.transposeMultiplyToNumber(eigenVector);
		
		int iterationCounter = 0;
		do
		{
			// ********** Step 1
			eigenVector = w.multiply(BigDecimal.ONE.divide(w.norm(NormType.EUCLIDEAN), EpsilonPrecision.getInstance().getMathContext()));
			// ********** Step 2
			w = A.multiply(eigenVector);
			// ********** Step 3
			eigenValue = w.transposeMultiplyToNumber(eigenVector);
			// ********** Step 4
			iterationCounter++;
			// ********** norm
			norm = w.subtract(eigenVector.multiply(eigenValue)).norm(NormType.EUCLIDEAN);
		} while((norm.compareTo(upperBound) == 1) && (iterationCounter < maxNumberOfIterations));

	}
	
	public BigDecimal getEigenValue()
	{
		return eigenValue;
	}
	
	public Matrix getEigenVector()
	{
		return eigenVector;
	}
}
