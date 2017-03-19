package app.arithmetic.algorithm;

import app.arithmetic.model.matrix.DiagonalMatrix;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.SquareMatrix;

public class CholeskyDecomposition
{
	public Matrix [] decompose(SquareMatrix A)
	{
		Matrix [] ldl = new Matrix[3];
		Matrix L, D, Lstar;
		
		D = new DiagonalMatrix(A.getDimension());
		L = new SquareMatrix(A.getDimension());
		
		int n = A.getNumberOfColumns().intValue();
		
		return ldl;
	}
}
