package app.arithmetic.algorithm;

import java.math.BigInteger;
import java.util.Random;

public class SolovayStrassen
{
	int numberOfIterations;
	
	public SolovayStrassen(int numberOfIterations)
	{
		this.numberOfIterations = numberOfIterations;
	}
	
	public boolean isPrime(BigInteger number)
	{
		BigInteger a, r, s, number_1, number_2, number_1_2;
		number_1   = number.subtract(BigInteger.ONE);
		number_2   = number.subtract(BigInteger.ONE).subtract(BigInteger.ONE);
		number_1_2 = number.subtract(BigInteger.ONE).divide((BigInteger.ONE.add(BigInteger.ONE)));

		for (int i = 0; i < numberOfIterations; i++)
		{
			a = generateRandom(new Random(System.nanoTime()), number_2);
			r = a.modPow(number_1_2, number);
			if(!r.equals(BigInteger.ONE) && !r.equals(number_1))
				return false;
			
			s = jacobiSymbol(a, number);
			
			if(!r.mod(number).equals(s.mod(number)))
				return false;
		}
		return true;
	}
	
	private BigInteger jacobiSymbol(BigInteger a, BigInteger n)
	{
		return null;
	}
	
	private BigInteger generateRandom(Random random,BigInteger upperBound)
	{
		int nlen = upperBound.bitLength();
		BigInteger nm1 = upperBound.subtract(BigInteger.ONE);
		BigInteger r, s;
		do {
		    s = new BigInteger(nlen + 100, random);
		    r = s.mod(upperBound);
		} while (s.subtract(r).add(nm1).bitLength() >= nlen + 100);
		
		return r;
	}
}
