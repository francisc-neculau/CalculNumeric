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
		this.determinant = null;
	}
	
	/**
	 * This method computes the determinant
	 * of the current matrix.
	 */
	public abstract BigDecimal determinant();
	
	@Override
	public BigDecimal getEii(Integer index)
	{
		return this.getEij(index, index);
	}
	
	@Override
	public void setEii(Integer index, BigDecimal value)
	{
		this.setEij(index, index, value);
	}
	
	public Integer getDimension()
	{
		return dimension;
	}
}
