package app.arithmetic.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import app.arithmetic.model.matrix.type.square.SquareMatrix;
import app.arithmetic.model.matrix.type.square.sparse.IndexedElement;
import app.arithmetic.model.matrix.type.square.sparse.SparseMatrix;

public class RandomMatrix
{
	private Random random;
	private long seed;

	public RandomMatrix(long seed)
	{
		this.seed = seed;
		this.random = new Random(seed);
	}
	
	public SquareMatrix nextSymmetricSparseMatrix(int dimension, double sparsity)
	{
		SortedSet<IndexedElement> elements;
		SquareMatrix matrix;
		IndexedElement element;
		
		BigInteger value;
		BigInteger lowerBound = BigInteger.ONE;
		BigInteger upperBound = new BigInteger(new Integer(dimension).toString());

		int maxNumberOfElements = (int)((double)(dimension * dimension) * sparsity);
		int rowIndex, columnIndex;//24.458
		
		elements = new TreeSet<>();
		// injecting diagonal elements
		for (int i = 0; i < dimension; i++)
			elements.add(new IndexedElement(RandomNumbers.nextBigInteger(random, lowerBound, upperBound), i, i));
		
		do
		{
			random.setSeed(seed + System.currentTimeMillis());
			rowIndex    = RandomNumbers.nextInteger(random, 0, dimension);
			columnIndex = RandomNumbers.nextInteger(random, 0, dimension);
			if(rowIndex == columnIndex)
				continue;
			value   = RandomNumbers.nextBigInteger(random, lowerBound, upperBound);
			element = new IndexedElement(value, rowIndex, columnIndex);
			if(!elements.contains(element))
			{
				elements.add(element);
				elements.add(element.transpose());
			}
		} while(elements.size() < maxNumberOfElements);
		
		matrix = new SparseMatrix(dimension, elements);

		return matrix;
	}
}
