package app.arithmetic.model.matrix.type.square.sparse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.SquareMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

public class SparseMatrix extends SquareMatrix
{
	private BigDecimal [] d;
	private BigDecimal [] val;
	private int [] col;
	// rowsOfsets
	// colummnOfsets
	// private double sparsity;
	// this length is obfuscated and must be changed
	private int length;
	
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public SparseMatrix(Integer dimension, SortedSet<IndexedElement> elements)
	{
		super(dimension);
		this.initialize(elements);
	}

	public SparseMatrix(Integer dimension, BigDecimal [] diagonalElements, BigDecimal[] values, int [] columnIndices, int length)
	{
		super(dimension);
		this.d = diagonalElements;
		this.val = values;
		this.col = columnIndices;
		this.length = length;
	}

	private void initialize(SortedSet<IndexedElement> elements)
	{
		Integer rowIndex, columnIndex, previousRowIndex, counter;
		BigDecimal value;
		
		// FIXME : Can it be improved here ?
		// This is for the worst case situation in which all diagonal are null
		int initialMemoryAllocationSize = elements.size()*2 + super.dimension + 2;
		val = new BigDecimal[initialMemoryAllocationSize];
		col = new int[initialMemoryAllocationSize];
		d   = new BigDecimal[super.dimension];
		for (int i = 0; i < super.dimension; i++)
			d[i] = BigDecimal.ZERO;
			
		counter = 1;
		previousRowIndex = 0;
		val[0] = BigDecimal.ZERO;
		col[0] = -1;
		for (IndexedElement element : elements)
		{
			value = element.getValue();
			rowIndex    = element.getRowIndex();
			columnIndex = element.getColumnIndex();
			// check if we are moving to a new row
			if(rowIndex > previousRowIndex)
			{
				// check if the hop between rows is greater than 1
				if(rowIndex - previousRowIndex > 1)
				{
					// update val and col accordingly
					for(int i = previousRowIndex; i < rowIndex; i++)
					{
						val[counter] = BigDecimal.ZERO;
						col[counter] = -(i + 1);
						counter++;
					}
				}
				else
				{
					val[counter] = BigDecimal.ZERO;
					col[counter] = -(previousRowIndex + 2);
					counter++;
				}
				previousRowIndex = rowIndex;
			}
			// check if it's a diagonal element
			if(rowIndex.compareTo(columnIndex) == 0)
				d[rowIndex] = value;
			else
			{
				val[counter] = value;
				col[counter] = columnIndex + 1;
				counter++;
			}
		}
		// last element inserted was a non zero one, thus here 
		// the vectors are closed with final placeholders
		val[counter] = BigDecimal.ZERO;
		col[counter] = -(previousRowIndex + 2);
		length = counter;
	}

	@Override
	public void setEij(Integer rowIndex, Integer columnIndex, BigDecimal value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Matrix add(Matrix B)
	{
		if(!(B instanceof SparseMatrix) || !this.canAdd(B))
			throw new UnsupportedOperationException();
		
		SparseMatrix result, sparseB = (SparseMatrix)B;
		
		BigDecimal value;
		int columnIndex;
		// FIXME : Here I think the size is too big !
		int [] columnIndices = new int[this.dimension * 20 + sparseB.dimension * 2];
		BigDecimal [] diagonalElements = new BigDecimal[this.dimension];
		BigDecimal [] values           = new BigDecimal[this.dimension * 20 + sparseB.dimension * 2];
		
		for (int i = 0; i < this.dimension; i++)
			diagonalElements[i] = this.d[i].add(sparseB.d[i], this.getMathContext());
		
		int counter = 0, k1 = 0, k2 = 0, rowIndex1 = -1, rowIndex2 = -1;
		while(k1 != this.length || k2 != sparseB.length)
		{
			if(k1 == this.length)
			{
				values[counter]          = sparseB.val[k2];
				columnIndices[counter++] = sparseB.col[k2++];
				continue;
			}
			if(k2 == sparseB.length)
			{
				values[counter]          = this.val[k1];
				columnIndices[counter++] = this.col[k1++];
				continue;
			}
			rowIndex1 =    this.col[k1] < 0 ?    this.col[k1] : rowIndex1;
			rowIndex2 = sparseB.col[k2] < 0 ? sparseB.col[k2] : rowIndex2;
			
			// rows have reached each other
			if(this.col[k1] < 0 && sparseB.col[k2] < 0)
			{
				value = BigDecimal.ZERO;
				if(this.col[k1] < sparseB.col[k2])
				{
					columnIndex = this.col[k2];
					k2++;
				}
				else
				{
					if(this.col[k1] > sparseB.col[k2])
					{
						columnIndex = this.col[k1];
						k1++;
					}
					else
					{
						columnIndex = this.col[k1];
						k1++;
						k2++;
					}
				}
				values[counter]          = value;
				columnIndices[counter++] = columnIndex;
				continue;
			}
			
			if(rowIndex1 == rowIndex2)
			{
				if(this.col[k1] > sparseB.col[k2])
				{
					value       = sparseB.val[k2];
					columnIndex = sparseB.col[k2++];
				}
				else
				{
					if(this.col[k1] < sparseB.col[k2])
					{
						value       = this.val[k1];
						columnIndex = this.col[k1++];
					}
					else
					{
						value       = this.val[k1].add(sparseB.val[k2], this.getMathContext());
						columnIndex = this.col[k1];
						k1++;
						k2++;
					}
				}
			}
			else if(-rowIndex1 >= -rowIndex2)
			{
				value       = sparseB.val[k2];
				columnIndex = sparseB.col[k2++];
			}
			else
			{
				value       = this.val[k1];
				columnIndex = this.col[k1++];
			}
			if(value.compareTo(BigDecimal.ZERO) == 0)
				continue;
			values[counter]          = value;
			columnIndices[counter++] = columnIndex;
		}
		values[counter]        = BigDecimal.ZERO;
		columnIndices[counter] = -(super.dimension + 1);
		
		result = new SparseMatrix(super.dimension, diagonalElements, values, columnIndices, counter);
		return result;
	}

	@Override
	public Matrix subtract(Matrix B)
	{return null;}

	@Override
	public Matrix multiply(Matrix B)
	{
		Matrix result = null;
		if(B instanceof ColumnMatrix)
		{
			int rowIndex = 0;
			BigDecimal [] elements = new BigDecimal[super.dimension];
			for (int k = 0; (-super.dimension - 1) != this.col[k]; k++)
			{
				if(this.col[k] < 0)
				{
					rowIndex = - this.col[k] - 1;
					elements[rowIndex] = BigDecimal.ZERO;
					elements[rowIndex] = elements[rowIndex].add(this.d[rowIndex].multiply(B.getEii(rowIndex), this.getMathContext()), this.getMathContext());
					continue;
				}
				elements[rowIndex] = elements[rowIndex].add(B.getEii(col[k] - 1).multiply(val[k], this.getMathContext()), this.getMathContext());
			}
			result = new ColumnMatrix(super.dimension, elements);
		}
		else if(B instanceof SparseMatrix)
		{
			SparseMatrix sparseB = (SparseMatrix)B;
			
			// compute in advance the diagonal products
			BigDecimal [] diagonalProducts = new BigDecimal[super.dimension];
			for (int i = 0; i < super.dimension; i++)
				diagonalProducts[i] = this.d[i].multiply(sparseB.d[i]);

			// compute in advance the row offsets
			int [] rowOffsetA = new int[super.dimension];
			for (int i = 0; i < this.length; i++)
				if(this.col[i] < 0)
					rowOffsetA[(-this.col[i]-1)] = i + 1;
			
			// compute in advance the column offsets
			List<Map<Integer, Integer>> columnOffsetB = new ArrayList<>();
			int rowCounter = 0;
			for (int i = 0; i < super.dimension; i++)
				columnOffsetB.add(new HashMap<Integer, Integer>());
			for (int i = 0; i < sparseB.length; i++)
			{
				if(sparseB.col[i] < 0)
				{
					rowCounter = (-sparseB.col[i]-1);
					continue;
				}
				columnOffsetB.get(sparseB.col[i] - 1).put(rowCounter, i);
			}
			
			BigDecimal c_ij, a_ik, b_kj;
			SortedSet<IndexedElement> elements = new TreeSet<>();
			int rowIndexOfB = 0;
			// for each row 'i' of A
			for (int i = 0; i < super.dimension; i++)
			{
				// for each column 'j' of B
				for (int j = 0; j < super.dimension; j++)
				{
					if(i == j)
						c_ij = diagonalProducts[i];
					else if(columnOffsetB.get(j).containsKey(i))
						c_ij = sparseB.val[columnOffsetB.get(j).get(i)].multiply(this.d[i], this.getMathContext());
					else
						c_ij = BigDecimal.ZERO;
					for (int k = 0; k < super.dimension; k++)
					{
						a_ik = this.val[rowOffsetA[i] + k];
						// do we have any element left in row 'i' to multiply ?
						if(a_ik.compareTo(BigDecimal.ZERO) == 0)
							break;
						rowIndexOfB = this.col[rowOffsetA[i] + k] - 1;
						// is there ,for column 'j' of B, an element on row 'k' ?
						if(columnOffsetB.get(j).containsKey(rowIndexOfB))
							b_kj = sparseB.val[columnOffsetB.get(j).get(rowIndexOfB)];
						// was 'j' equal to 'k' ?
						else if(rowIndexOfB == j)
							b_kj = sparseB.d[rowIndexOfB];
						else
							continue;
						c_ij = c_ij.add(a_ik.multiply(b_kj, this.getMathContext()), this.getMathContext());
						
					}
					if(c_ij.compareTo(BigDecimal.ZERO) != 0)
						elements.add(new IndexedElement(c_ij, i, j));
				}
			}
			result = new SparseMatrix(super.dimension, elements);
		}
		else
			throw new UnsupportedOperationException();
		return result;
	}

	@Override
	public BigDecimal norm(NormType normType)
	{return null;}

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
		return this.d[index];
	}
	
	@Override
	public BigDecimal determinant()
	{return null;}

	@Override
	public double[][] doubleValue()
	{return null;}
	
	public BigDecimal [] getDiagonalElements()
	{
		return this.d;
	}

	public BigDecimal[] getValues()
	{
		return this.val;
	}
	
	public int [] getColumnIndicies()
	{
		return this.col;
	}

	public int getLength()
	{
		return this.length;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this) 
			return true;
		if(!(o instanceof SparseMatrix))
		    return false;
		SparseMatrix matrix = (SparseMatrix) o;
		if(super.dimension.compareTo(matrix.dimension) != 0)
			return false;
		for (int i = 0; i < super.dimension; i++)
			if(this.d[i].compareTo(matrix.d[i]) != 0)
				return false;
		int index = 0;
		while(this.col[index] != -(dimension + 1))
			if((this.col[index] != matrix.col[index]) || (this.val[index].compareTo(matrix.val[index++]) != 0))
				return false;
		return true;
	}
	
	@Override
	public int hashCode()
	{
	    int result = 17;
	    result = 19 * result + val.hashCode();
	    result = 23 * result + col.hashCode();
	    result = 31 * result + d.hashCode();
	    return result;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("d=(");
		for (int i = 0; i < d.length; i++)
			sb.append(d[i]).append(", ");
		sb.append(")\n");
		
		sb.append("val=(");
		for (int i = 0; i < val.length; i++)
		{
			if(val[i] == null)
				break;
			sb.append(val[i].doubleValue()).append(", ");
		}
		sb.append(")\n");
		
		sb.append("col=(");
		for (int i = 0; i < col.length; i++)
		{
			sb.append(col[i]).append(", ");
			if(col[i] == -(super.dimension + 1))
				break;
		}
		sb.append(")");

		return sb.toString();
	}

}
