package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.operation.Addable;
import app.arithmetic.operation.Multipliable;
import app.arithmetic.operation.Subtractable;

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
	{return null;}

	@Override
	public Matrix transpose()
	{return null;}

	@Override
	public Addable add(Addable number)
	{return null;}

	@Override
	public Subtractable subtract(Subtractable number)
	{return null;}

	@Override
	public Multipliable multiply(Multipliable number)
	{return null;}

	@Override
	public void determinant()
	{
		if(super.determinant.equals(BigDecimal.ZERO))
		{
			determinant = BigDecimal.ONE;
			for (int i = 0; i < super.dimension; i++)
				determinant = determinant.multiply(elements[i]);
		}
	}
	
	@Override
	public BigDecimal getIj(Integer rowIndex, Integer columnIndex)
	{
		if(rowIndex == columnIndex)
			return this.getIi(rowIndex);
		return BigDecimal.ZERO;
	}
	
	@Override
	public BigDecimal getIi(Integer index)
	{
		return elements[index];
	}
	
	@Override
	public void setIj(Integer i, Integer columnIndex, BigDecimal value)
	{
		// should throw unsupported operation ?
	}

	@Override
	public void setIi(Integer index, BigDecimal value)
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
				sb.append(String.format("%20.10f", this.getIj(i, j)));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
