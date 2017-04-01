package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;

public class SparseSquareMatrix extends SquareMatrix
{

	public SparseSquareMatrix(Integer dimension)
	{
		super(dimension);
	}

	@Override
	public void setEij(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Matrix add(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix subtract(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix multiply(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal norm(NormType normType)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix solve(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix transposeSolve(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix transpose()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getEij(Integer rowIndex, Integer columnIndex)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal determinant()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[][] doubleValue()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
