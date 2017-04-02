package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

import app.arithmetic.model.matrix.type.square.triangular.LowerTriangularMatrix;

public class MirrorMatrix extends LowerTriangularMatrix
{

	public MirrorMatrix(Integer dimension, BigDecimal[][] elements)
	{
		super(dimension, elements);
	}
	
	public MirrorMatrix(Integer dimension, Double[][] elements)
	{
		super(dimension, elements);
	}

	@Override
	public BigDecimal getEij(Integer rowIndex, Integer columnIndex)
	{
		if(rowIndex < columnIndex)
			return super.getEij(columnIndex, rowIndex);
		return super.getEij(rowIndex, columnIndex);
	}
	
	@Override
	public void setEij(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		if(rowIndex < columnIndex)
			super.setEij(columnIndex, rowIndex, value);
		super.setEij(rowIndex, columnIndex, value);
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
}
