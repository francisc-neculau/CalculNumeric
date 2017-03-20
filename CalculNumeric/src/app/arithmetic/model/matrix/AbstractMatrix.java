package app.arithmetic.model.matrix;

public abstract class AbstractMatrix implements Matrix
{
	private Integer numberOfRows;
	private Integer numberOfColumns;

	public AbstractMatrix(Integer numberOfRows, Integer numberOfColumns)
	{
		this.numberOfRows    = numberOfRows;
		this.numberOfColumns = numberOfColumns;
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
	
}
