package app.arithmetic.algorithm;

import java.math.BigInteger;
import java.util.Random;

public class SolovayStrassen
{
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger TEN = new BigInteger("10");
	private JacobiSymbol jacobiSymbol;
	int numberOfIterations;

	public SolovayStrassen(int numberOfIterations)
	{
		this.numberOfIterations = numberOfIterations;
		this.jacobiSymbol = new JacobiSymbol();
	}
	
	public boolean isPrime(BigInteger n)
	{
		if(n.mod(TEN).intValue() % 2 == 0)
			return false;
		BigInteger a, r, s, nMinusOne, nMinusTwo, nMinusOneDivideByTwo;
		nMinusOne = n.subtract(BigInteger.ONE);
		nMinusTwo = n.subtract(TWO);
		nMinusOneDivideByTwo = n.subtract(BigInteger.ONE).divide(TWO);

		for (int i = 0; i < numberOfIterations; i++)
		{
			a = generateRandom(new Random(System.nanoTime()), TWO, nMinusTwo);
			if(a.compareTo(new BigInteger("2")) == -1 || a.compareTo(nMinusTwo) == 1)
				System.out.println(a);
			r = a.modPow(nMinusOneDivideByTwo, n);
			if(!r.equals(BigInteger.ONE) && !r.equals(nMinusOne))
				return false;

			s = new BigInteger(jacobiSymbol.compute(a, n).toString());

			if(!r.mod(n).equals(s.mod(n)))
				return false;
		}
		return true;
	}

	private BigInteger generateRandom(Random random, BigInteger lowerBound, BigInteger upperBound)
	{
		int nlen = upperBound.bitLength();
		BigInteger nm1 = upperBound.subtract(BigInteger.ONE);
		BigInteger r, s;
		do {
		    s = new BigInteger(nlen + 100, random);
		    r = s.mod(upperBound);
		} while (s.subtract(r).add(nm1).bitLength() >= nlen + 100);
		if(r.subtract(lowerBound).compareTo(BigInteger.ZERO) != 1)
			r = r.add(lowerBound);
		return r;
	}
}
