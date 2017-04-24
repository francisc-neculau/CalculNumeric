package app.arithmetic.model.matrix.type.vector;

import java.math.BigDecimal;

import app.arithmetic.model.EpsilonPrecision;
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
	
	public ColumnMatrix(Integer numberOfRows, BigDecimal [] elements)
	{
		this(numberOfRows);
		this.elements = elements; // FIXME : Copy of it ?
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
		MutableMatrix result = null;
		if(matrix instanceof ColumnMatrix && ((ColumnMatrix)matrix).getNumberOfColumns() == this.getNumberOfColumns())
		{
			result = new ColumnMatrix(this.getNumberOfRows());
			for (int i = 0; i < this.getNumberOfRows(); i++)
				result.setEii(i, this.getEii(i).add(matrix.getEii(i), super.getMathContext()));
		}
		else
			throw new UnsupportedOperationException();
		//
		// Unsupported operation
		//
		return result;
	}

	@Override
	public Matrix subtract(Matrix matrix)
	{
		MutableMatrix result = null;
		if(matrix instanceof ColumnMatrix && ((ColumnMatrix)matrix).getNumberOfColumns() == this.getNumberOfColumns())
		{
			result = new ColumnMatrix(this.getNumberOfRows());
			for (int i = 0; i < this.getNumberOfRows(); i++)
				result.setEii(i, this.getEii(i).subtract(matrix.getEii(i), super.getMathContext()));
		}
		else
			throw new UnsupportedOperationException();
		//
		// Unsupported operation
		//
		return result;
	}

	@Override
	public Matrix multiply(Matrix matrix)
	{return null;}

	@Override
	public Matrix multiply(BigDecimal number)
	{
		BigDecimal[] elements = new BigDecimal[this.elements.length];
		for (int i = 0; i < elements.length; i++)
			elements[i] = this.elements[i].multiply(number, super.getMathContext());
		Matrix result = new ColumnMatrix(elements.length, elements);
		return result;
	}
	
	@Override
	public BigDecimal transposeMultiplyToNumber(Matrix B)
	{
		// so we compute the transpose of current times the column matrix B
		BigDecimal sum = BigDecimal.ZERO;
		for (int i = 0; i < elements.length; i++)
			sum = sum.add(this.getEii(i).multiply(B.getEii(i), super.getMathContext()), super.getMathContext());
		return sum;
	}
	
	@Override
	public BigDecimal norm(NormType normType)
	{
		BigDecimal norm = null;
		switch(normType)
		{
			case EUCLIDEAN :
				BigDecimal zi, Szi;
				Szi = BigDecimal.ZERO;
				for (int i = 0; i < this.getNumberOfRows(); i++)
				{
					zi  = this.getEii(i);
					Szi = Szi.add(new BigDecimal(zi.doubleValue() * zi.doubleValue()), this.getMathContext());
				}
				norm = new BigDecimal(Math.sqrt(Szi.doubleValue()), this.getMathContext());
				break;
			case UNIFORM :
			case MAXIMUM :
					norm = this.getEii(0).abs();
					for (int i = 1; i < this.elements.length; i++)
						if(norm.compareTo(this.elements[i].abs()) == -1)
							norm = this.elements[i].abs();
				break;
			default:
				break;
		}
		return norm;
	}

	@Override
	public Matrix solve(Matrix B)
	{return null;}

	@Override
	public Matrix transposeSolve(Matrix B)
	{return null;}

	@Override
	public Matrix transpose()
	{return null;}

	@Override
	public BigDecimal getEij(Integer rowIndex, Integer columnIndex)
	{return null;}

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
	{}

	@Override
	public void setEii(Integer index, BigDecimal value)
	{
		this.elements[index] = value;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this) 
			return true;
		if(!(obj instanceof ColumnMatrix))
		    return false;
		ColumnMatrix matrix = (ColumnMatrix) obj;
		if(this.elements.length != matrix.elements.length)
			return false;
		for (int i = 0; i < this.elements.length; i++)
			if(this.elements[i].compareTo(matrix.elements[i]) != 0)
				return false;
		return true;
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
