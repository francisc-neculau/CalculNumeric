package app.arithmetic.model.matrix;

import java.math.BigDecimal;

public interface Matrix /*extends Addable, Subtractable, Multipliable*/
{

	public abstract Matrix add(Matrix B);

	public abstract Matrix subtract(Matrix B);

	public abstract Matrix multiply(Matrix B);

	public abstract BigDecimal norm(NormType normType);
	
	/**
	 * This method is used for computing the
	 * solution of the system A*X = B
	 * A is this, X and B are the other matrices
	 */
	public abstract Matrix solve(Matrix B);

	/**
	 * This method is used for computing the
	 * solution of the system A.transpose()*X = B
	 * A is this, X and B are the other matrices
	 * Calling this method, instead of A.transpose()
	 * will save memory.
	 */
	public abstract Matrix transposeSolve(Matrix B);
	
	/**
	 * This method computes the transpose
	 * of the current matrix.
	 */
	public abstract Matrix transpose();

	/**
	 * This method will return the element
	 * from the row i and column j
	 */
	public abstract BigDecimal getEij(Integer rowIndex, Integer columnIndex);
	
	/**
	 * This method will return the element
	 * from the row i and column i
	 */
	public abstract BigDecimal getEii(Integer index);
	
	/**
	 * This method returns the number of rows
	 */
	public abstract Integer getNumberOfRows();

	/**
	 * This method returns the number of columns
	 */
	public abstract Integer getNumberOfColumns();
	
	public abstract double[][] doubleValue();
}
