package app.arithmetic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import app.arithmetic.model.matrix.type.square.sparse.IndexedElement;

public class SparseMatrixFileReader
{
	private Logger logger;

	private String filePath;
	//private List<String> cachedFileLines;
	
	private BigDecimal[] bElements;
	private SortedSet<IndexedElement> cachedElements;
	private int cachedDimension;

	public SparseMatrixFileReader(String filePath)
	{
		this.logger = Logger.getLogger(this.getClass().getName());
		this.filePath = filePath;
		//this.cachedFileLines = new ArrayList<>();
		this.cachedElements = new TreeSet<>();
		this.cachedDimension = 0;
	}
	
	public BigDecimal[] getBelements()
	{
		return this.bElements;
	}
	
	public SortedSet<IndexedElement> getElements()
	{
		if(this.cachedElements.isEmpty())
			processFile();
		return this.cachedElements;
	}

	public int getDimension()
	{
		if(this.cachedDimension == 0)
			processFile();
		return this.cachedDimension;
	}
	
	/*
	 * File Format :
	 * 
	 * dimension
	 * (space)
	 * b vector
	 * (space)
	 * sparse matrix elements
	 */
	private void processFile()
	{
		BufferedReader reader = null;
		String line = null;
		try
		{
			reader = new BufferedReader(new FileReader(new File(filePath)));
			logger.info("File is open and ready to be read");
		} catch (FileNotFoundException e)
		{
			logger.error(e);
		}
		//
		//
		//
		//
		long startTime, endTime;
		logger.info("File processing started");
		startTime = System.currentTimeMillis();
		//
		//
		//
		//
		try
		{
			/*
			 * Step I - Read dimension
			 */
			line = reader.readLine();
			cachedDimension = Integer.valueOf(line.trim());
			
			/*
			 * Step II - Read ColumnMatrix
			 */
			reader.readLine();

			bElements = new BigDecimal[cachedDimension];
			for (int i = 0; i < cachedDimension; i++)
			{
				line = reader.readLine();
				bElements[i] = new BigDecimal(line.trim());
			}
			
			/*
			 * Step III - Read SparseMatrix elements
			 */
			reader.readLine();
			
			int rowIndex, columnIndex;
			BigDecimal value;
			IndexedElement element = null;
			int firstComma = 0, lastComma = 0;
			while((line = reader.readLine()) != null)
			{
				firstComma = line.indexOf(',');
				lastComma  = line.lastIndexOf(',');
				
				value = new BigDecimal(line.substring(0, firstComma).trim());
				rowIndex    = Integer.valueOf(line.substring(firstComma + 1, lastComma).trim());
				columnIndex = Integer.valueOf(line.substring(lastComma + 1).trim());
				
				element = new IndexedElement(value, rowIndex, columnIndex);

				if(columnIndex == rowIndex && value.compareTo(BigDecimal.ZERO) == 0)
					logger.warn("Diagonal element " + rowIndex + " - " + columnIndex + "is 0 !");
				if(cachedElements.contains(element))
					logger.error("Duplicate element found : \n" + element);
				else
					if(!cachedElements.add(element))
						logger.error("Element : \n" + element + " could not be stored");
			}
		} catch (IOException e)
		{
			logger.error("Error reading line", e);
		}
		//
		//
		//
		//
		endTime = System.currentTimeMillis();
		logger.info("File processing ended - time : " + new Double((endTime - startTime))/1000 + "s");
		//
		//
		//
		//
		try
		{
			reader.close();
			logger.info("File is closed");
		} catch (IOException e)
		{
			logger.error(e);
		}
	}

	public void printElements()
	{
		Iterator<IndexedElement> iterator = getElements().iterator();
		while(iterator.hasNext())
		{
			IndexedElement indexedElement = (IndexedElement) iterator.next();
			System.out.println(indexedElement.getRowIndex() + "   " + indexedElement.getColumnIndex());
		}
	}
}
