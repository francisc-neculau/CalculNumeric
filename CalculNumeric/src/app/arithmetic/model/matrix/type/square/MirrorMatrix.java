package app.arithmetic.model.matrix.type.square;

import java.math.BigDecimal;

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
	public BigDecimal getIj(Integer rowIndex, Integer columnIndex)
	{
		if(rowIndex < columnIndex)
			return super.getIj(columnIndex, rowIndex);
		return super.getIj(rowIndex, columnIndex);
	}
	
	@Override
	public void setIj(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		if(rowIndex < columnIndex)
			super.setIj(columnIndex, rowIndex, value);
		super.setIj(rowIndex, columnIndex, value);
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
