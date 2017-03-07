package actn;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ReedSolomon
{

	private static final int MAX_ACCEPTED_ERRORS = 1;
	private static Logger logger = Logger.getLogger("ReedSolomon");

	static
	{
		BasicConfigurator.configure();
	}
	
	/*
	 *  encodable - m
	 *      prime - p
	 * components - k
	 * 
	 */
	public int [] encode(int m, int p, int k)
	{
		int n = k + 2 * MAX_ACCEPTED_ERRORS;

		int [] mVector = new int[(k-1)];
		int [] mFactorsVector = new int[(k-1)];
		
		int [] yVector = new int[n];
		
		{
			/*
			 * Here we compute the mFactorsVector used for Base Conversion
			 */
			int currentFactor = 1;
			mFactorsVector[0] = currentFactor;
			for(int counter = 1; counter < k-1; counter++)
			{
				currentFactor *= p;
				mFactorsVector[counter] = currentFactor;
			}
		}
		
		/*
		 * Here we compute the mVector Base Conversion
		 */
		int divider = m;
		for(int counter = (k-1) - 1; counter >= 0 ; counter--)
		{
			mVector[counter] = divider / mFactorsVector[counter];
			divider = divider % mFactorsVector[counter];
		}
		logger.info(displayVector(mVector, p));
		
		/*
		 * Here we compute the yVector
		 */
		{
			int polynomialResult;
			for(int counter = 0; counter < n; counter++)
			{
				polynomialResult = 0;
				for(int i = 0; i < k-1; i++)
				{
					polynomialResult += Math.pow(counter + 1, i + 1) *  mVector[i];
				}
				polynomialResult %= p;
				yVector[counter] = polynomialResult;
			}
		}
		logger.info(displayVector(yVector, p));
		return yVector;
	}
	
	public static String decode(String encoded)
	{
		String result = "";
		
		return result;
	}

	public BigInteger computeFreeCoeficient(List<BigInteger> A, BigInteger base)
	{
		BigInteger freeCoeficient      = BigInteger.ZERO;
		BigInteger intermidiateProduct = BigInteger.ONE;
		BigInteger product = BigInteger.ONE;
		for (int i = 0; i < A.size(); i++)
		{
			product = BigInteger.ONE;
			BigInteger i_ = new BigInteger(new Integer(i).toString());
			for (int j = 0; j < A.size(); j++)
			{
				if(j == i)
					continue;
				intermidiateProduct = new BigInteger(new Integer(j).toString());
				intermidiateProduct = intermidiateProduct.divide(intermidiateProduct.subtract(i_));
				product.multiply(intermidiateProduct);
			}
			freeCoeficient = freeCoeficient.add(product.multiply(A.get(i)));
		}
		return freeCoeficient.mod(base);
	}
	
	public String displayVector(int [] vector, int base)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for(int counter = vector.length - 1; counter >= 0; counter--)
		{
			sb.append(vector[counter]);
			if(counter != 0)
				sb.append(", ");
		}
		sb.append(")" + "b" + base);
		return sb.toString();
	}
	
}
