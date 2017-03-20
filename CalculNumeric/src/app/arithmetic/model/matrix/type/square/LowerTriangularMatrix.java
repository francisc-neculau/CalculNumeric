package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.operation.Addable;
import app.arithmetic.operation.Multipliable;
import app.arithmetic.operation.Subtractable;

public class LowerTriangularMatrix extends SquareMatrix
{
	private BigDecimal[][] elements;

	public LowerTriangularMatrix(Integer dimension, BigDecimal[][] elements)
	{
		super(dimension);
		this.elements = new BigDecimal[dimension.intValue()][];
		for (int i = dimension.intValue() - 1; i >= 0 ; i--)
			this.elements[i] = elements[i];
		this.determinant = null;
	}

	public LowerTriangularMatrix(Integer dimension, Double[][] lowerTriangleElements)
	{
		super(dimension);
		this.elements = new BigDecimal[dimension][];
		for (int i = 0; i < dimension.intValue(); i++)
		{
			this.elements[i] = new BigDecimal[i + 1];
			for (int j = 0; j < i + 1; j++)
				this.elements[i][j] = new BigDecimal(lowerTriangleElements[i][j].toString());
		}
		this.determinant = null;
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
	{
		return null;
	}

	@Override
	public void determinant()
	{
		if(determinant != null)
			return;
		else
		{
			determinant = BigDecimal.ONE;
			for (int i = 0; i < elements.length; i++)
				determinant = determinant.add(elements[i][i]);
		}
	}

	@Override
	public Matrix transpose()
	{return null;}

	@Override
	public BigDecimal getIj(Integer rowIndex, Integer columnIndex)
	{
		if(rowIndex < columnIndex)
			return BigDecimal.ZERO;
		else
			return elements[rowIndex][columnIndex];
	}

	@Override
	public void setIj(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		elements[rowIndex][columnIndex] = value;
	}

}
