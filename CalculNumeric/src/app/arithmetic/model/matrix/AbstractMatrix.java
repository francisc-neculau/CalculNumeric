package app.arithmetic.model.matrix;

import java.math.MathContext;

import app.arithmetic.model.EpsilonPrecision;

public abstract class AbstractMatrix implements Matrix
{
	private Integer numberOfRows;
	private Integer numberOfColumns;
	private MathContext mathContext;
	
	public AbstractMatrix(Integer numberOfRows, Integer numberOfColumns)
	{
		this.numberOfRows    = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		this.mathContext     = EpsilonPrecision.getInstance().getMathContext();
	}
	
	public boolean canAdd(Matrix matrix)
	{
		boolean can = ( numberOfRows.equals(matrix.getNumberOfRows()) && numberOfColumns.equals(matrix.getNumberOfColumns()) );
		return can;
	}
	
	public boolean canSubtract(Matrix matrix)
	{
		return canAdd(matrix);
	}
	
	public boolean canMultiply(Matrix matrix)
	{
		boolean can = numberOfColumns.equals(matrix.getNumberOfRows());
		return can;
	}
	
	public Integer getNumberOfRows()
	{
		return numberOfRows;
	}

	public Integer getNumberOfColumns()
	{
		return numberOfColumns;
	}
	
	public MathContext getMathContext()
	{
		return mathContext;
	}
	
}
