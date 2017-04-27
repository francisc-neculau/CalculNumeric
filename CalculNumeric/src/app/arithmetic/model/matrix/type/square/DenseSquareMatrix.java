package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;

public class DenseSquareMatrix extends SquareMatrix
{
	private BigDecimal[][] elements;

	public DenseSquareMatrix(Integer dimension)
	{
		super(dimension);
		this.elements = new BigDecimal[dimension][dimension];
	}

	public DenseSquareMatrix(Integer dimension, BigDecimal [][] elements)
	{
		this(dimension);
		this.elements = elements;
	}
	
	public DenseSquareMatrix(Integer dimension, Double [][] elements)
	{
		this(dimension);
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				this.elements[i][j] = new BigDecimal(elements[i][j]);
	}
	
	@Override
	public void setEij(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		elements[rowIndex][columnIndex] = value;
	}

	@Override
	public Matrix add(Matrix matrix)
	{
		BigDecimal[][] elements = new BigDecimal[super.dimension][super.dimension];
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				elements[i][j] = this.elements[i][j].add(matrix.getEij(i, j), super.getMathContext());
		Matrix result = new DenseSquareMatrix(super.dimension, elements);
		return result;
	}

	@Override
	public Matrix addDiagonal(BigDecimal number)
	{
		// WARNING !
		// The elements must be copied one by one
		// using clone is going to just copy the reference
		BigDecimal[][] elements = new BigDecimal[super.dimension][super.dimension];
		for (int i = 0; i < elements.length; i++)
			for (int j = 0; j < elements.length; j++)
				elements[i][j] = new BigDecimal(this.elements[i][j].toString());
		
		for (int i = 0; i < dimension; i++)
			elements[i][i] = elements[i][i].add(number, super.getMathContext());
		Matrix result = new DenseSquareMatrix(super.dimension, elements);
		return result;
	}
	
	@Override
	public Matrix subtract(Matrix matrix)
	{
		BigDecimal[][] elements = new BigDecimal[super.dimension][super.dimension];
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				elements[i][j] = this.elements[i][j].subtract(matrix.getEij(i, j), super.getMathContext());
		Matrix result = new DenseSquareMatrix(super.dimension, elements);
		return result;
	}

	@Override
	public Matrix multiply(Matrix matrix)
	{
		Matrix result = null;
		if(matrix instanceof SquareMatrix)
		{
			BigDecimal[][] elements = new BigDecimal[super.dimension][super.dimension];
			// for each column of matrix
			for (int i = 0; i < dimension; i++)
				// for each row of this
				for (int j = 0; j < dimension; j++)
				{
					elements[i][j] = BigDecimal.ZERO;
					// product their elements
					for (int k = 0; k < dimension; k++)
						elements[i][j] = elements[i][j].add(this.elements[i][k].multiply(matrix.getEij(k, j)));
					elements[i][j] = new BigDecimal(elements[i][j].doubleValue());
					//elements[i][j] = elements[i][j].setScale(super.getMathContext().getPrecision(), super.getMathContext().getRoundingMode());
				}
			result = new DenseSquareMatrix(super.dimension, elements);
		}
//		else if(matrix instanceof ColumnMatrix)
//		{
//			BigDecimal[] elements = new BigDecimal[super.dimension];
//			for (int i = 0; i < dimension; i++)
//			{
//				elements[i] = BigDecimal.ZERO;
//				for (int j = 0; j < dimension; j++)
//					elements[i] = elements[i].add(this.elements[i][j].multiply(matrix.getEij(j, j), super.getMathContext()));
//			}
//			result = new ColumnMatrix(super.dimension, elements);
//		}
		else
			throw new UnsupportedOperationException();
		return result;
	}

	@Override
	public Matrix multiply(BigDecimal number)
	{
		BigDecimal[][] elements = new BigDecimal[super.dimension][super.dimension];
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
			{
				elements[i][j] = this.elements[i][j].multiply(number, super.getMathContext());
				elements[i][j] = new BigDecimal(elements[i][j].doubleValue());
				//elements[i][j] = elements[i][j].setScale(super.getMathContext().getPrecision(), super.getMathContext().getRoundingMode());
			}
		Matrix result = new DenseSquareMatrix(super.dimension, elements);
		return result;
	}

	@Override
	public Matrix transposeMultiply(BigDecimal number)
	{
		BigDecimal[][] elements = new BigDecimal[super.dimension][super.dimension];
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
			{
				elements[j][i] = this.elements[i][j].multiply(number, super.getMathContext());
				elements[j][i] = new BigDecimal(elements[j][i].doubleValue());
				//elements[j][i] = elements[j][i].setScale(super.getMathContext().getPrecision(), super.getMathContext().getRoundingMode());
			}
		Matrix result = new DenseSquareMatrix(super.dimension, elements);
		return result;
	}
	
	@Override
	public BigDecimal norm(NormType normType)
	{
		BigDecimal norm = null;
		switch(normType)
		{
			case MAXIMUM :
				{
					norm = BigDecimal.ZERO;
					for (int i = 0; i < elements.length; i++)
						for(int j = 0; j < elements.length; j++)
							if(norm.compareTo(this.elements[i][j].abs()) == -1)
								norm = this.elements[i][j].abs();
				}
				break;
			case MAX_ABSOLUTE_ROW_SUM :
				{
					BigDecimal sum;
					norm = BigDecimal.ZERO;
					for (int i = 0; i < elements.length; i++)
					{
						sum = BigDecimal.ZERO;
						for (int j = 0; j < elements.length; j++)
							sum = sum.add(this.elements[i][j], super.getMathContext());
						if(sum.compareTo(norm) == 1)
							norm = sum;
					}
				}
				break;
			case MAX_ABSOLUTE_COLUMN_SUM :
				{
					BigDecimal sum;
					norm = BigDecimal.ZERO;
					for (int i = 0; i < elements.length; i++)
					{
						sum = BigDecimal.ZERO;
						for (int j = 0; j < elements.length; j++)
							sum = sum.add(this.elements[j][i], super.getMathContext());
						if(sum.compareTo(norm) == 1)
							norm = sum;
					}
				}
				break;
			default:
				throw new UnsupportedOperationException();
		}
		norm = norm.round(super.getMathContext());
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
	{
		return this.elements[rowIndex][columnIndex];
	}

	@Override
	public double[][] doubleValue()
	{
		double[][] elements = new double[super.dimension][super.dimension];

		for (int i = 0; i < super.dimension; i++)
			for (int j = 0; j < super.dimension; j++)
				elements[i][j] = this.elements[i][j].doubleValue();

		return elements;
	}

	@Override
	public BigDecimal determinant()
	{return null;}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
				sb.append(String.format("%20." + super.getMathContext().getPrecision() + "f", this.getEij(i, j)));
			sb.append("\n");
		}
		return sb.toString();
	}

}
