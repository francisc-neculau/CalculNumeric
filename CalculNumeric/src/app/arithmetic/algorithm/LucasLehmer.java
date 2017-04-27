package app.arithmetic.algorithm;

import java.math.BigInteger;

public class LucasLehmer
{
	private static final BigInteger TWO  = new BigInteger("2");
	private static final BigInteger FOUR = new BigInteger("4");
	private SolovayStrassen ss;

	public LucasLehmer()
	{
		this.ss = new SolovayStrassen(64);
	}
	
	/**
	 * 
	 * @param Mn mersen number
	 * @param n mersen exponent
	 * @return
	 */
	public boolean isPrime(BigInteger Mn, int n)
	{
		if(!ss.isPrime(new BigInteger(Integer.valueOf(n).toString())))
			return false;

		BigInteger Sn = FOUR;
		
		for (int i = 2; i < n; i++)
		{
			Sn = Sn.pow(2).subtract(TWO);
			Sn = mersenMod(Sn, Mn, n);
		}
		
		if(Sn.equals(BigInteger.ZERO))
			return true;
		
		return false;
	}

	/**
	 * 
	 * @param Mn mersen number
	 * @param n mersen exponent
	 * @return
	 */
	public boolean isPrime(int n)
	{
		if(!ss.isPrime(new BigInteger(Integer.valueOf(n).toString())))
			return false;

		BigInteger Mn = TWO.pow(n).subtract(BigInteger.ONE);
		BigInteger Sn = FOUR;
		
		for (int i = 2; i < n; i++)
		{
			Sn = Sn.pow(2).subtract(TWO);
			Sn = mersenMod(Sn, Mn, n);
		}
		if(Sn.equals(BigInteger.ZERO))
			return true;
		
		return false;
	}
	
	/**
	 * 
	 * @param Mn mersen number
	 * @param n mersen exponent
	 * @return
	 */
	public boolean isPrimeSlow(BigInteger Mn, int n)
	{
		if(!ss.isPrime(new BigInteger(Integer.valueOf(n).toString())))
			return false;

		BigInteger Sn = FOUR;
		
		for (int i = 2; i < n; i++)
		{
			Sn = Sn.pow(2).subtract(TWO).mod(Mn);
		}
		
		if(Sn.equals(BigInteger.ZERO))
			return true;
		
		return false;
	}
	
	public BigInteger mersenMod(BigInteger x, BigInteger Mn, int n)
	{
		if(x.compareTo(Mn) != 1)
			return x;
		BigInteger x1, x0, result;
		x1 = x.shiftRight(n);
		x0 = x.and(Mn);
		result = x1.add(x0);
		if(result.compareTo(Mn) == 1)
		{
			x1 = result.shiftRight(n);
			x0 = result.and(Mn);
			return x1.add(x0);
		}
		else if(result.compareTo(Mn) == 0)
			return BigInteger.ZERO;
		else
			return result;
	}

}
