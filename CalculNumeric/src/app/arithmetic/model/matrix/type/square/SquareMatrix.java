package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.AbstractMatrix;
import app.arithmetic.model.matrix.type.MutableMatrix;

public abstract class SquareMatrix extends AbstractMatrix implements MutableMatrix
{
	protected BigDecimal determinant;
	protected Integer dimension;
	protected boolean diagonallyRowDominant;
	protected boolean diagonallyColumnDominant;
	
	public SquareMatrix(Integer dimension)
	{
		super(dimension, dimension);
		this.dimension = dimension;
		this.determinant = null;
	}
	
	/**
	 * This method computes and returns the determinant
	 * of the current matrix.
	 */
	public abstract BigDecimal determinant();
	
	public boolean isDiagonallyRowDominant()
	{
		return diagonallyRowDominant;
	}
	
	public boolean isDiagonallyColumnDominant()
	{
		return diagonallyColumnDominant;
	}
	
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
