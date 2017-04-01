package app.arithmetic.algorithm.crypto.rsa;

import java.math.BigInteger;

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

}
