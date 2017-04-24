package app.arithmetic.algorithm;

import java.math.BigDecimal;

import app.arithmetic.model.EpsilonPrecision;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.SquareMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

public class SteepestDescent
{
	public SteepestDescent()
	{}
	
	public boolean solve(SquareMatrix A, ColumnMatrix B, ColumnMatrix preconditioner)
	{
		Matrix ri, xi, xiPlus1;
		BigDecimal alpha, norm;

		xi = preconditioner;

		boolean hasConverged = false;
		while(!hasConverged)
		{
			ri      = B.subtract(A.multiply(xi));
			alpha   = ri.transposeMultiplyToNumber(ri)
					  	.divide(
					  ri.transposeMultiplyToNumber(A.multiply(ri))
					 	);
			xiPlus1 = xi.add(ri.multiply(alpha));
			norm    = xiPlus1.subtract(xi).norm(NormType.UNIFORM).abs();
			if(norm.compareTo(EpsilonPrecision.getInstance().getEpsilon()) == -1)
				hasConverged = true;
		}
		return hasConverged;
	}
}
