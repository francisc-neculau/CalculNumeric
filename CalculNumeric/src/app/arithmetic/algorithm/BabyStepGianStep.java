package app.arithmetic.algorithm;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class BabyStepGianStep
{
	
	public BabyStepGianStep()
	{
		
	}
	
	/*
	 * computes x = log(alpha, beta) mod m
	 * 
	 * n is the order of the group
	 * 
	 */
	public BigInteger compute(BigInteger alpha, BigInteger beta, BigInteger m)
	{
		Map<BigInteger, Integer> table = new HashMap<>();
		BigInteger x = null;

		/* ************* Step 1. */
		int n = (int) Math.sqrt(m.intValue()) + 1; // FIXME : it should be the upper part ! and also this will break for large m..
		
		/* ************* Step 2. */
		BigInteger alphaJ = BigInteger.ONE;
		for (int j = 1; j < n; j++)
		{
			alphaJ  = alphaJ.multiply(alpha).mod(m);
			table.put(alphaJ, j);
		}

		/* ************* Step 3. */
		BigInteger lambda = beta;
		BigInteger inverseAlpha = alpha.modInverse(m).modPow(new BigInteger(Integer.valueOf(n).toString()), m);
		
		/* ************* Step 4. */
		for (int i = 0; i < n - 1; i++)
		{
			if(table.containsKey(lambda))
			{
				x = new BigInteger(Integer.valueOf(table.get(lambda) + i * n).toString()); 
				break;
			}
			else
				lambda = lambda.multiply(inverseAlpha).mod(m);
		}
		
		return x;
	}
	
}
