package app.arithmetic.util;

import java.math.BigInteger;
import java.util.Random;

public class RandomNumbers
{
	public static BigInteger nextBigInteger(Random random, BigInteger lowerBound, BigInteger upperBound)
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
	
	// returns a number a E [lowerBound, upperBound]
	public static int nextInteger(Random random, int lowerBound, int upperBound)
	{
		int randomNum = random.nextInt((upperBound - lowerBound)) + lowerBound;
	    return randomNum;
	}
}
