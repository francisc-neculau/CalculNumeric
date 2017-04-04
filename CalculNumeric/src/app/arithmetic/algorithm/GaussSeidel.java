package app.arithmetic.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

import app.arithmetic.model.EpsilonPrecision;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.type.MutableMatrix;
import app.arithmetic.model.matrix.type.square.sparse.SparseMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

public class GaussSeidel
{

	private MutableMatrix Xgs;
	private int maxNumberOfIterations;
	
	public GaussSeidel(int maxNumberOfIterations)
	{
		this.maxNumberOfIterations = maxNumberOfIterations;
	}
	
	public boolean solve(SparseMatrix A, ColumnMatrix B)
	{	
		int iterationNumber = 0, n;
		BigDecimal newXi, aii, aij, bi, deltaX, firstSum, secondSum;

		n = A.getDimension();

		Xgs = new ColumnMatrix(n);
		for (int i = 0; i < n; i++)
			Xgs.setEii(i, BigDecimal.ONE.add(new BigDecimal(i)));
		
		int [] rowOffsetsOfA = new int[n];
		for (int i = 0; i < A.getLength(); i++)
			if(A.getColumnIndicies()[i] < 0)
				rowOffsetsOfA[(-A.getColumnIndicies()[i]-1)] = i + 1;
		
		int offset = 0;
		do
		{
			iterationNumber++;
			
			for (int i = 0; i < n; i++)
			{
				firstSum  = BigDecimal.ZERO;
				secondSum = BigDecimal.ZERO;
				aii = A.getEii(i);
				bi  = B.getEii(i);
				
				offset = 0;
				for (int j = 0; j <= i - 1; j++)
				{
					// are there any values for A on row 'i' ?
					if(A.getColumnIndicies()[rowOffsetsOfA[i] + offset] < 0)
						break;
					// does A have a value on column 'j' for row 'i' ?
					if(A.getColumnIndicies()[rowOffsetsOfA[i] + offset] - 1 != j)
					{
						// jump j straight to the first column 'j' for which we have a value of A on row 'i'
						j = (A.getColumnIndicies()[rowOffsetsOfA[i] + offset] - 1 - 1 );
						continue;
					}
					aij = A.getValues()[rowOffsetsOfA[i] + offset];
					firstSum = firstSum.add(aij.multiply(Xgs.getEii(j)));
					offset++;
				}
				for (int j = i + 1; j < n; j++)
				{
					// are there any values for A on row 'i' ?
					if(A.getColumnIndicies()[rowOffsetsOfA[i] + offset] < 0)
						break;
					// does A have a value on column 'j' for row 'i' ?
					if(A.getColumnIndicies()[rowOffsetsOfA[i] + offset] - 1 != j)
					{
						// jump j straight to the first column 'j' for which we have a value of A on row 'i'
						j = (A.getColumnIndicies()[rowOffsetsOfA[i] + offset] - 1 - 1 );
						continue;
					}
					aij = A.getValues()[rowOffsetsOfA[i] + offset];
					secondSum = secondSum.add(aij.multiply(Xgs.getEii(j)));
					offset++;
				}
				newXi = bi.subtract(firstSum).subtract(secondSum).divide(aii, EpsilonPrecision.getInstance().getExponent(), RoundingMode.HALF_UP);
				deltaX = newXi.subtract(Xgs.getEii(i));//FIXME !
				Xgs.setEii(i, newXi);
			}
			
			if(iterationNumber == maxNumberOfIterations)
				break;
		} while(true);
		
		return true;
	}
	
	public Matrix getXgs()
	{
		return this.Xgs;
	}
}
