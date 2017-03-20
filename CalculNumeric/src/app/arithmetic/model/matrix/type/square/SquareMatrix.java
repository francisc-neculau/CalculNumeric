package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.AbstractMatrix;
import app.arithmetic.model.matrix.type.MutableMatrix;

public abstract class SquareMatrix extends AbstractMatrix implements MutableMatrix
{
	protected BigDecimal determinant;
	protected Integer dimension;

	public SquareMatrix(Integer dimension)
	{
		super(dimension, dimension);
		this.dimension = dimension;
		this.determinant = BigDecimal.ZERO;
	}
	
	@Override
	public BigDecimal getIi(Integer index)
	{
		return this.getIj(index, index);
	}
	
	@Override
	public void setIi(Integer index, BigDecimal value)
	{
		this.setIj(index, index, value);
	}
	
	/**
	 * This method computes the determinant
	 * of the current matrix.
	 */
	public abstract void determinant();
	
	public BigDecimal getDeterminant()
	{
		return determinant;
	}
	
	public Integer getDimension()
	{
		return dimension;
	}
}
