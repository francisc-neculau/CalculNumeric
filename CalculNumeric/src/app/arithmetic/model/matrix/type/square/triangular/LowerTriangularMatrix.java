package app.arithmetic.model.matrix.type.square.triangular;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.MutableMatrix;
import app.arithmetic.model.matrix.type.square.SquareMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

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
	public Matrix add(Matrix matrix)
	{return null;}

	@Override
	public Matrix subtract(Matrix matrix)
	{return null;}

	@Override
	public Matrix multiply(Matrix matrix)
	{
		MutableMatrix Y = null;
		
		if(matrix instanceof ColumnMatrix)
		{
			BigDecimal aij, xj, Saijxj;
			Y = new ColumnMatrix(super.dimension);
			for (int i = 0; i < super.dimension; i++)
			{
				Saijxj = BigDecimal.ZERO;
				for (int j = 0; j < super.dimension; j++)
				{
					aij = this.getEij(i, j);
					xj  = matrix.getEii(j);
					Saijxj = Saijxj.add(aij.multiply(xj));
				}
				Y.setEii(i, Saijxj);
			}
		}
		// else
		// unsuported matrix type
		//
			
		return Y;
	}

	@Override
	public Matrix solve(Matrix B)
	{
		return null;
	}

	@Override
	public BigDecimal determinant()
	{
		if(determinant == null)
		{
			determinant = BigDecimal.ONE;
			for (int i = 0; i < elements.length; i++)
				determinant = determinant.add(elements[i][i]);
		}
		return determinant;
	}

	@Override
	public BigDecimal norm(NormType normType)
	{
		return null;
	}
	
	@Override
	public Matrix transpose()
	{return null;}

	@Override
	public Matrix transposeSolve(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getEij(Integer rowIndex, Integer columnIndex)
	{
		if(rowIndex < columnIndex)
			return BigDecimal.ZERO;
		else
			return elements[rowIndex][columnIndex];
	}

	@Override
	public void setEij(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		elements[rowIndex][columnIndex] = value;
	}

	@Override
	public double[][] doubleValue()
	{
		double[][] elements = new double[super.dimension][super.dimension];
		for (int i = 0; i < elements.length; i++)
			for (int j = 0; j < elements.length; j++)
				elements[i][j] = this.getEij(i, j).doubleValue();
		return elements;
	}

}
