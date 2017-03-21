package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.MutableMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

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
	public Matrix add(Matrix matrix)
	{return null;}

	@Override
	public Matrix subtract(Matrix matrix)
	{return null;}

	@Override
	public Matrix multiply(Matrix matrix)
	{return null;}

	@Override
	public Matrix solve(Matrix B)
	{
		// Direct substitution method
		MutableMatrix X = new ColumnMatrix(super.dimension);

		BigDecimal xi, bi, Saijxj, aii;

		X.setEii(0, B.getEii(0).divide(this.diagonalElement));
		
		aii = this.diagonalElement;
		for(int i = 1; i < super.dimension; i++)
		{
			Saijxj = BigDecimal.ZERO;
			for (int j = 0; j <= i - 1; j++)
			{
				Saijxj = Saijxj.add(this.getEij(i, j).multiply(X.getEii(j)));
			}
			bi = B.getEii(i);
			xi = bi.subtract(Saijxj).divide(aii);
			X.setEii(i, xi);
		}
		
		return X;
	}

	@Override
	public BigDecimal norm(NormType normType)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix transposeSolve(Matrix B)
	{
		// Inverse substitution method
		MutableMatrix X = new ColumnMatrix(super.dimension);

		BigDecimal xi, bi, Saijxj, aii;

		X.setEii(super.dimension - 1, B.getEii(super.dimension - 1).divide(this.diagonalElement));
		
		aii = this.diagonalElement;
		for(int i = super.dimension - 2; i >= 0; i--)
		{
			Saijxj = BigDecimal.ZERO;
			for (int j = i + 1; j <= super.dimension - 1; j++)
			{
				Saijxj = Saijxj.add(this.getEij(j, i).multiply(X.getEii(j)));
			}
			bi = B.getEii(i);
			xi = bi.subtract(Saijxj).divide(aii);
			X.setEii(i, xi);
		}
		
		return X;
	}
	
	@Override
	public BigDecimal determinant()
	{
		if(super.determinant == null)
			super.determinant = diagonalElement.equals(BigDecimal.ONE) ? BigDecimal.ONE : diagonalElement.pow(dimension.intValue());
		return super.determinant;
	}

	@Override
	public Matrix transpose()
	{return null;}

	@Override
	public BigDecimal getEij(Integer rowIndex, Integer columnIndex)
	{
		if(rowIndex == columnIndex)
			return diagonalElement;
		if(rowIndex < columnIndex)
			return BigDecimal.ZERO;

		rowIndex = rowIndex == 0 ? 0 : rowIndex - 1;

		return this.elements[rowIndex][columnIndex];
			
	}

	@Override
	public void setEij(Integer rowIndex, Integer columnIndex, BigDecimal value)
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
				sb.append(String.format("%20.10f", this.getEij(i, j)));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public double[][] toDoubleVector()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
