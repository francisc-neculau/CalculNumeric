package app.arithmetic.model.matrix.type.square.sparse;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IndexedElement implements Comparable<IndexedElement>
{
	private BigDecimal value;
	private int rowIndex;
	private int columnIndex;
	
	public IndexedElement(BigDecimal value, int rowIndex, int columnIndex)
	{
		this.value = value;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	public IndexedElement(BigInteger value, int rowIndex, int columnIndex)
	{
		this.value = new BigDecimal(value);
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	public BigDecimal getValue()
	{
		return value;
	}
	public void setValue(BigDecimal value)
	{
		this.value = value;
	}
	public int getRowIndex()
	{
		return rowIndex;
	}
	public void setRowIndex(int rowIndex)
	{
		this.rowIndex = rowIndex;
	}
	public int getColumnIndex()
	{
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex)
	{
		this.columnIndex = columnIndex;
	}
	
	public IndexedElement transpose()
	{
		return new IndexedElement(value, columnIndex, rowIndex);
	}

	@Override
	public int compareTo(IndexedElement o)
	{
		if(this.rowIndex < o.rowIndex)
			return -1;
		else if(this.rowIndex > o.rowIndex)
			return 1;
		else
		{
			// row indices are the same,
			// thus we compare the column ones
			if(this.columnIndex < o.columnIndex)
				return -1;
			else if(this.columnIndex > o.columnIndex)
				return 1;
		}
		// Row and column indices are the same.
		// We should compare now the values but,
		// we will return equality and let the 
		// user decide whether to discard, sum etc.
		// the values
		return 0;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this) 
			return true;
		if(!(o instanceof IndexedElement))
		    return false;
		IndexedElement element = (IndexedElement) o;
		return element.value.equals(value) && element.rowIndex == rowIndex && element.columnIndex == columnIndex;
	}
	
	@Override
	public int hashCode()
	{
	    int result = 17;
	    result = 19 * result + value.hashCode();
	    result = 23 * result + rowIndex;
	    result = 31 * result + columnIndex;
	    return result;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("value : " + value + "\n");
		sb.append("row    : " + rowIndex + "\n");
		sb.append("column : " + columnIndex + "\n");
		return sb.toString();
	}

}
