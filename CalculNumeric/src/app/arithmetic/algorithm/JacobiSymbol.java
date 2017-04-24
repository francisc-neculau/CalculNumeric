package app.arithmetic.algorithm;

import java.math.BigInteger;

public class JacobiSymbol
{
	private static final BigInteger TWO   = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");
	private static final BigInteger FOUR  = new BigInteger("4");
	private static final BigInteger FIVE  = new BigInteger("5");
	private static final BigInteger SEVEN = new BigInteger("7");
	private static final BigInteger EIGHT = new BigInteger("8");
	
	public JacobiSymbol()
	{
		
	}

	/**
	 * an odd integer n ≥ 3, and an integer a, 0 ≤ a < n
	 * @param a
	 * @param n
	 * @return Jacobi symbol (and Legendre symbol if n is prime)
	 */
	public Integer compute(BigInteger a,BigInteger n)
	{
		if(a.compareTo(BigInteger.ZERO) == 0)
			return 0;
		if(a.compareTo(BigInteger.ONE) == 0)
			return 1;
		
		BigInteger nModEight, a1 = a;
		int e = 0, s = 1; // WARNING : s in the algorithm is not set a priori like here
		while(a1.mod(TWO).compareTo(BigInteger.ZERO) == 0)
		{
			a1 = a1.divide(TWO);
			e++;
		}

		if((e % 2) == 0)
			s = 1;
		else
		{
			nModEight = n.mod(EIGHT);
			if(nModEight.compareTo(BigInteger.ONE) == 0 || nModEight.compareTo(SEVEN) == 0)
				s = 1;
			if(nModEight.compareTo(THREE) == 0 || nModEight.compareTo(FIVE) == 0)
				s = -1;
		}
		if(n.mod(FOUR).compareTo(THREE) == 0 && a1.mod(FOUR).compareTo(THREE) == 0)
			s = -s;
		if(a1.compareTo(BigInteger.ONE) == 0)
			return s;
		else
		{
			return s * compute(n.mod(a1), a1);
		}
	}
}
