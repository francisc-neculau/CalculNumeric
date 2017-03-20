package app.arithmetic.model.matrix;

import java.math.BigDecimal;
import java.math.BigInteger;

import app.arithmetic.operation.Addable;
import app.arithmetic.operation.Multipliable;
import app.arithmetic.operation.Subtractable;

public abstract class Matrix implements Addable, Subtractable, Multipliable
{
	private BigInteger numberOfRows;
	private BigInteger numberOfColumns;
	
	public Matrix(BigInteger numberOfRows, BigInteger numberOfColumns)
	{
		this.numberOfRows    = numberOfRows;
		this.numberOfColumns = numberOfColumns;
	}
	
	/**
	 * This method is used for computing the
	 * solution of the system A*X = B
	 * A is this, X and B are the other matrices
	 */
	public abstract Matrix solve(Matrix B);

	/**
	 * This method computes the determinant
	 * of the current matrix.
	 */
	public abstract BigDecimal determinant();

	/**
	 * This method computes the transpose
	 * of the current matrix.
	 */
	public abstract Matrix transpose();

	/**
	 * This method will return the element
	 * from the row i and column j
	 * @param matrix
	 * @return
	 */
	public abstract BigDecimal ij(BigInteger rowIndex, BigInteger columnIndex);
	
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
	
	public BigInteger getNumberOfRows()
	{
		return numberOfRows;
	}
	public void setNumberOfRows(BigInteger numberOfRows)
	{
		this.numberOfRows = numberOfRows;
	}
	public BigInteger getNumberOfColumns()
	{
		return numberOfColumns;
	}
	public void setNumberOfColumns(BigInteger numberOfColumns)
	{
		this.numberOfColumns = numberOfColumns;
	}

}
