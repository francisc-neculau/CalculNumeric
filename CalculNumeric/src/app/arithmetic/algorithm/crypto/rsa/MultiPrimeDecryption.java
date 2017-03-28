package app.arithmetic.algorithm.crypto.rsa;

import java.math.BigInteger;
import java.util.Random;

public class MultiPrimeDecryption
{
	/*
Implement multi-prime RSA decryption (i.e., computing y^d mod n, for
n = p · q · r, where p, q, and r are distinct 512-bit primes) using the Chinese
remainder theorem algorithm discussed in class. Perform time comparisons
between this modular exponentiation algorithm and the regular modular
exponentiation algorithm (the one that is implemented in your large integers
library).
	 */
	
	public BigInteger decrypt(BigInteger p, BigInteger q, BigInteger r, BigInteger e, BigInteger y)
	{
		BigInteger phi, d, x;
		
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE).multiply(r.subtract(BigInteger.ONE)));
		d = e.modInverse(phi);
		
		// Garner + TCR

		BigInteger b0, b1, b2;
		b0 = y.mod(p).modPow(d.mod(p.subtract(BigInteger.ONE)), p);
		b1 = y.mod(q).modPow(d.mod(q.subtract(BigInteger.ONE)), q);
		b2 = y.mod(r).modPow(d.mod(r.subtract(BigInteger.ONE)), r);

		BigInteger alfa1, alfa2, v0, v1, v2, m0, m1, m2;
		m0 = p;
		m1 = q;
		m2 = r;

		alfa1 = m0.modInverse(m1);
		alfa2 = (m0.multiply(m1)).modInverse(m2);
		
		v0 = b0;
		v1 = (b1.subtract(b0.mod(m1)).multiply(alfa1)).mod(m1);
		v2 = (b2.subtract((v0.add(v1.multiply(m0))).mod(m2)).multiply(alfa2)).mod(m2);
		
		x = (v2.multiply(m1).add(v1)).multiply(m0).add(v0);
	
		return x;
	}
	
	public static void main(String[] args)
	{
//		BigInteger TWO = new BigInteger("2");
//		
//		Random rnd = new Random();
//		
//		BigInteger p = BigInteger.probablePrime(512, rnd);
//		BigInteger q = BigInteger.probablePrime(512, rnd);
//		BigInteger r = BigInteger.probablePrime(512, rnd);
//		
//		BigInteger n = p.multiply(q.multiply(r));
//		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE).multiply(r.subtract(BigInteger.ONE)));
//		
//		BigInteger e = new BigInteger((rnd.nextInt() * i + 10 * i).toString()); // gcd(e, phi) = 1 !
//		BigInteger d = e.modInverse(phi);     // d = e^-1; (d * e) mod phi = 1
//		
//		System.out.println("public-key  : " + "(" + e + ", " + n + ")");
//		System.out.println("private-key : " + "(" + d + ", " + n + ")");
//				
//		System.out.println();
//		System.out.println("text : " + x);
//		System.out.println("crypto-text : " + x.modPow(e, n));
		Integer numberOfIterations = 100;
		MultiPrimeDecryption mprmd;
		MultiPowerDecryption mpowd;
		Long timeStart, timeEnd, totalTime11 = 0l, totalTime12 = 0l, totalTime2 = 0l, totalTime3 = 0l;
		boolean ok = false;
		for (int i = 0; i < numberOfIterations; i++)
		{
			Random rnd = new Random();
			
			BigInteger p = BigInteger.probablePrime(512, rnd);
			BigInteger pp = p.pow(2);
			BigInteger q = BigInteger.probablePrime(512, rnd);
			BigInteger r = BigInteger.probablePrime(512, rnd);
			
			BigInteger n = p.multiply(q.multiply(r));
			BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE).multiply(r.subtract(BigInteger.ONE)));
			
			BigInteger e = null;
			BigInteger d = null;
			while(!ok)
			{
				try
				{
					e = BigInteger.probablePrime(8, rnd); // gcd(e, phi) = 1 !
					d = e.modInverse(phi);                // d = e^-1; (d * e) mod phi = 1
					ok = true;
				}
				catch(Exception e1)
				{
					System.out.println("Prime not invertible, try again");
				}
			}
			ok = false;

			BigInteger x = new BigInteger(new Integer(113 + rnd.nextInt() * i + 10 * i).toString());
			BigInteger y = x.pow(e.intValue()).mod(n);

			// Library Function :
			timeStart = System.nanoTime();
			System.out.println("plain-text  : " + y.modPow(d, n));
			timeEnd = System.nanoTime();
			totalTime11 += (timeEnd - timeStart);
			
			// TCR & Garner
			mprmd = new MultiPrimeDecryption();
			timeStart = System.nanoTime();
			System.out.println("(TCR & Garner) plain-text : " + mprmd.decrypt(p, q, r, e, y));
			timeEnd = System.nanoTime();
			totalTime2 += (timeEnd - timeStart);
			
			phi = pp.subtract(p).multiply(q.subtract(BigInteger.ONE));
			n   = pp.multiply(q);
			d = e.modInverse(phi);
			y = x.pow(e.intValue()).mod(n);
			
			// Library Function :
			timeStart = System.nanoTime();
			System.out.println("plain-text  : " + y.modPow(d, n));
			timeEnd = System.nanoTime();
			totalTime12 += (timeEnd - timeStart);
			
			// TCR & Hensel’s lifting & Garner
			mpowd = new MultiPowerDecryption();
			timeStart = System.nanoTime();
			System.out.println("(TCR & Hensel’s lifting & Garner) plain-text : " + mpowd.decrypt(p, q, e, y));
			timeEnd = System.nanoTime();
			totalTime3 += (timeEnd - timeStart);
		}
		System.out.println("Ab - Library");
		System.out.println("Ac - Library");
		System.out.println("B - TCR & Garner");
		System.out.println("C - TCR & Hensel’s lifting & Garner");
		System.out.println("Total time Ab : " + totalTime11);
		System.out.println("Total time Ac : " + totalTime12);
		System.out.println("Total time B : " + totalTime2);
		System.out.println("Total time C : " + totalTime3);
//		System.out.println("Avearage time Ab : " + new Double(totalTime11)/new Double(numberOfIterations));
//		System.out.println("Avearage time Ac : " + new Double(totalTime12)/new Double(numberOfIterations));
//		System.out.println("Avearage time B  : " + new Double(totalTime2)/new Double(numberOfIterations));
//		System.out.println("Avearage time C  : " + new Double(totalTime3)/new Double(numberOfIterations));
		System.out.println("Time Improvement Ab/B : " + new Double(totalTime11)/new Double(totalTime2));
		System.out.println("Time Improvement Ac/C : " + new Double(totalTime12)/new Double(totalTime3));
		//System.out.println("Time Improvement C/B  : " + new Double(totalTime3)/new Double(totalTime2));
	}

}
