package app.arithmetic.model.matrix.type;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.Matrix;

public interface MutableMatrix extends Matrix
{

	/**
	 * This method will modify the element
	 * from the row i and column j
	 */
	public abstract void setEij(Integer rowIndex, Integer columnIndex, BigDecimal value);
	
	/**
	 * This method will modify the element
	 * from the row i and column i
	 */
	public abstract void setEii(Integer index, BigDecimal value);
}
