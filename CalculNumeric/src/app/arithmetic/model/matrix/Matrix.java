package app.arithmetic.model.matrix;

import java.math.BigDecimal;

import app.arithmetic.operation.Addable;
import app.arithmetic.operation.Multipliable;
import app.arithmetic.operation.Subtractable;

public interface Matrix extends Addable, Subtractable, Multipliable
{
	
	/**
	 * This method is used for computing the
	 * solution of the system A*X = B
	 * A is this, X and B are the other matrices
	 */
	public abstract Matrix solve(Matrix B);

	/**
	 * This method computes the transpose
	 * of the current matrix.
	 */
	public abstract Matrix transpose();

	/**
	 * This method will return the element
	 * from the row i and column j
	 */
	public abstract BigDecimal getIj(Integer rowIndex, Integer columnIndex);
	
	/**
	 * This method will return the element
	 * from the row i and column i
	 */
	public abstract BigDecimal getIi(Integer index);
	
	/**
	 * This method returns the number of rows
	 */
	public abstract Integer getNumberOfRows();

	/**
	 * This method returns the number of columns
	 */
	public abstract Integer getNumberOfColumns();
}
