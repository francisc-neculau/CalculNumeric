package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.operation.Addable;
import app.arithmetic.operation.Multipliable;
import app.arithmetic.operation.Subtractable;

/**
 * This class models matrices of the following form :
 * 	
 *  x 0 0
 * 	a x 0
 * 	b c x
 * 
 */
public class StrictLowerTriangularMatrix extends SquareMatrix
{
	private BigDecimal[][] elements;
	private BigDecimal diagonalElement;
	
	public StrictLowerTriangularMatrix(Integer dimension, BigDecimal diagonalElement)
	{
		super(dimension);
		this.elements = new BigDecimal[dimension.intValue() - 1][];
		for (int i = 0; i < dimension.intValue() - 1 ; i++)
			this.elements[i] = new BigDecimal[i + 1];
		this.diagonalElement = diagonalElement;
	}
	
	public StrictLowerTriangularMatrix(Integer dimension, BigDecimal diagonalElement, BigDecimal[][] elements)
	{
		super(dimension);
		for (int i = 0; i < dimension.intValue() - 1 ; i++)
			this.elements[i] = elements[i];
		this.diagonalElement = diagonalElement;
	}
	
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
	public Matrix solve(Matrix B)
	{return null;}

	@Override
	public void determinant()
	{
		super.determinant = diagonalElement.pow(dimension.intValue());
	}

	@Override
	public Matrix transpose()
	{return null;}

	@Override
	public BigDecimal getIj(Integer rowIndex, Integer columnIndex)
	{
		if(rowIndex == columnIndex)
			return diagonalElement;
		if(rowIndex < columnIndex)
			return BigDecimal.ZERO;

		rowIndex = rowIndex == 0 ? 0 : rowIndex - 1;

		return this.elements[rowIndex][columnIndex];
			
	}

	@Override
	public void setIj(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		if(rowIndex == columnIndex || rowIndex < columnIndex)
			throw new ArrayIndexOutOfBoundsException("Nu-i bine boss");
		
		rowIndex = rowIndex == 0 ? 0 : rowIndex - 1;
		
		this.elements[rowIndex][columnIndex] = value;
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
