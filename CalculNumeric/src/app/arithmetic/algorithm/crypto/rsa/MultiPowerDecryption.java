package app.arithmetic.algorithm.crypto.rsa;

import java.math.BigInteger;

public class MultiPowerDecryption
{
	/*
Implement multi-power RSA decryption (i.e., computing y^d mod n, for
n = p^2 · q, where p and q are distinct 512-bit primes) using the Chinese
remainder theorem algorithm and Hensel’s lifting lemma discussed in class.
Perform time comparisons between this modular exponentiation algorithm
and the regular modular exponentiation algorithm (the one that is implemented
in your large integers library)
	 */
	public BigInteger decrypt(BigInteger p, BigInteger q, BigInteger e, BigInteger y)
	{
		BigInteger n, phi, d, x;
		
		n = p.pow(2).multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
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
		Long timeStart, timeEnd, totalTime1, totalTime2;

		BigInteger TWO = new BigInteger("2");
		
		BigInteger p = new BigInteger(TWO.pow(3217).subtract(BigInteger.ONE).toString());
		BigInteger q = new BigInteger(TWO.pow(4253).subtract(BigInteger.ONE).toString());
		
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger n = p.pow(2).multiply(q);
		BigInteger e = new BigInteger("137");
		BigInteger d = e.modInverse(phi);
		
		System.out.println("public-key  : " + "(" + e + ", " + n + ")");
		System.out.println("private-key : " + "(" + d + ", " + n + ")");
				
		System.out.println();

		BigInteger x = new BigInteger("20");
		System.out.println("text : " + x);
		BigInteger y = x.pow(e.intValue()).mod(n);
		
		System.out.println("crypto-text : " + x.modPow(e, n));
		timeStart = System.currentTimeMillis();
		System.out.println("plain-text  : " + y.modPow(d, n));
		timeEnd = System.currentTimeMillis();
		totalTime1 = timeEnd - timeStart;
		
		MultiPowerDecryption mpd = new MultiPowerDecryption();
		timeStart = System.currentTimeMillis();
		System.out.println("(TCR & Garner) plain-text : " + mpd.decrypt(p, q, e, y));
		timeEnd = System.currentTimeMillis();
		totalTime2 = timeEnd - timeStart;
		
		System.out.println("Time Improvement " + totalTime1/totalTime2);
		
	}
}
