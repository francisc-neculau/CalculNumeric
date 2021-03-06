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
		int iterationNumber = 0, n, offset = 0, rowOffsetOfA;
		BigDecimal newXi, aii, aij, bi, deltaX, newDeltaX, sum;

		deltaX    = BigDecimal.ZERO;
		newDeltaX = BigDecimal.ZERO;
		n = A.getDimension();

		Xgs = new ColumnMatrix(n);
		for (int i = 0; i < n; i++)
			Xgs.setEii(i, BigDecimal.ZERO);
		
		int [] rowOffsetsOfA = new int[n];
		for (int i = 0; i < A.getLength(); i++)
			if(A.getColumnIndicies()[i] < 0)
				rowOffsetsOfA[(-A.getColumnIndicies()[i]-1)] = i + 1;
		
		boolean converging = false;
		do
		{
			iterationNumber++;
			for (int i = 0; i < n; i++)
			{
				sum = BigDecimal.ZERO;
				aii = A.getEii(i);
				bi  = B.getEii(i);
				
				offset = 0;
				// We do not need to worry of the j==i case because 
				// A'columnIndices vector does not contain the diagonal elements
				for (int j = 0; j < n; j++)
				{
					rowOffsetOfA = rowOffsetsOfA[i] + offset;
					// are there any values for A on row 'i' ?
					if(A.getColumnIndicies()[rowOffsetOfA] < 0)
						break;
					// does A have a value on column 'j' for row 'i' ?
					if(A.getColumnIndicies()[rowOffsetOfA] - 1 != j)
					{
						// jump j straight to the first column 'j' for which we have a value of A on row 'i'
						j = (A.getColumnIndicies()[rowOffsetOfA] - 1 - 1 );
						continue;
					}
					aij = A.getValues()[rowOffsetOfA];
					sum = sum.add(aij.multiply(Xgs.getEii(j)));
					offset++;
				}
				newXi = bi.subtract(sum).divide(aii, EpsilonPrecision.getInstance().getExponent(), RoundingMode.HALF_UP);
				
				newDeltaX = newXi.subtract(Xgs.getEii(i)).abs();
				deltaX    = deltaX.compareTo(newDeltaX) == 1 ? deltaX : newDeltaX;
				if(deltaX.compareTo(EpsilonPrecision.getInstance().getEpsilon()) == -1)
					converging = true;
				
				Xgs.setEii(i, newXi);
			}
			deltaX = BigDecimal.ZERO;
			
		} while((iterationNumber != maxNumberOfIterations) && (!converging));
		
		return converging;
	}
	
	public Matrix getXgs()
	{
		return this.Xgs;
	}
}
