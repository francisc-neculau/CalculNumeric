package app.arithmetic.model.matrix.type.vector;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.AbstractMatrix;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.MutableMatrix;

public class ColumnMatrix extends AbstractMatrix implements MutableMatrix
{
	private BigDecimal[] elements;
	
	public ColumnMatrix(Integer numberOfRows)
	{
		super(numberOfRows, 1);
		this.elements = new BigDecimal[numberOfRows];
	}
	
	public ColumnMatrix(Integer numberOfRows, Double [] elements)
	{
		this(numberOfRows);
		for (int i = 0; i < elements.length; i++)
			this.elements[i] = new BigDecimal(elements[i]);
	}

	@Override
	public Matrix add(Matrix matrix)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix subtract(Matrix B)
	{
		MutableMatrix X = null;
		if(B instanceof ColumnMatrix && ((ColumnMatrix)B).getNumberOfColumns() == this.getNumberOfColumns())
		{
			X = new ColumnMatrix(this.getNumberOfRows());
			for (int i = 0; i < this.getNumberOfRows(); i++)
				X.setEii(i, this.getEii(i).subtract(B.getEii(i)));
		}
		//
		// Unsupported operation
		//
		return X;
	}

	@Override
	public Matrix multiply(Matrix matrix)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal norm(NormType normType)
	{
		// Default Euclidean
		BigDecimal zi, Szi;
		Double norm;
		Szi = BigDecimal.ZERO;
		for (int i = 0; i < this.getNumberOfRows(); i++)
		{
			zi = this.getEii(i);
			Szi.add(zi.pow(2));
		}
		norm = Math.sqrt(Szi.doubleValue());
		return new BigDecimal(norm);
	}

	@Override
	public Matrix solve(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix transposeSolve(Matrix B)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix transpose()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getEij(Integer rowIndex, Integer columnIndex)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getEii(Integer index)
	{
		return this.elements[index];
	}

	@Override
	public double[][] doubleValue()
	{
		double[][] elements = new double[getNumberOfRows()][1];
		for (int i = 0; i < elements.length; i++)
			elements[i][0] = this.getEii(i).doubleValue();
		return elements;
	}

	@Override
	public void setEij(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEii(Integer index, BigDecimal value)
	{
		this.elements[index] = value;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < super.getNumberOfRows(); i++)
		{
			sb.append(String.format("%40.20f", this.getEii(i)));
			sb.append("\n");
		}
		return sb.toString();
	}

}
