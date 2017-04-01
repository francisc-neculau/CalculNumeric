package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.EpsilonPrecision;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.MutableMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

/**
 * This class models matrices of the following form :
 * 	
 *  a 0 0
 * 	0 b 0
 * 	0 0 c
 * 
 */
public class DiagonalMatrix extends SquareMatrix
{
	private BigDecimal[] elements;
	
	public DiagonalMatrix(Integer dimension)
	{
		super(dimension);
		this.elements = new BigDecimal[dimension];
	}

	public DiagonalMatrix(Integer dimension, BigDecimal[] elements)
	{
		super(dimension);
		this.elements = elements;
	}

	@Override
	public Matrix solve(Matrix B)
	{
		MutableMatrix X = new ColumnMatrix(super.dimension);

		BigDecimal xi, bi, aii;
		X.setEii(0, B.getEii(0).divide(this.getEii(0), EpsilonPrecision.getInstance().getExponent(), BigDecimal.ROUND_HALF_UP));
		for(int i = 1; i < super.dimension; i++)
		{
			aii = this.getEii(i);
			bi  = B.getEii(i);
			xi  = bi.divide(aii, EpsilonPrecision.getInstance().getExponent(), BigDecimal.ROUND_HALF_UP);
			X.setEii(i, xi);
		}

		return X;
	}

	@Override
	public Matrix transpose()
	{return null;}

	@Override
	public Matrix transposeSolve(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix add(Matrix matrix)
	{return null;}

	@Override
	public Matrix subtract(Matrix matrix)
	{return null;}

	@Override
	public Matrix multiply(Matrix matrix)
	{return null;}

	@Override
	public BigDecimal determinant()
	{
		if(super.determinant == null)
		{
			super.determinant = BigDecimal.ONE;
			for (int i = 0; i < super.dimension; i++)
				super.determinant = super.determinant.multiply(this.elements[i]);
		}
		return super.determinant;
	}
	
	@Override
	public BigDecimal norm(NormType normType)
	{
		return null;
	}
	
	@Override
	public BigDecimal getEij(Integer rowIndex, Integer columnIndex)
	{
		if(rowIndex == columnIndex)
			return this.getEii(rowIndex);
		return BigDecimal.ZERO;
	}
	
	@Override
	public BigDecimal getEii(Integer index)
	{
		return elements[index];
	}
	
	@Override
	public void setEij(Integer i, Integer columnIndex, BigDecimal value)
	{
		// should throw unsupported operation ?
	}

	@Override
	public void setEii(Integer index, BigDecimal value)
	{
		elements[index] = value;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				sb.append(String.format("%20.10f", this.getEij(i, j)));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public double[][] doubleValue()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
}
