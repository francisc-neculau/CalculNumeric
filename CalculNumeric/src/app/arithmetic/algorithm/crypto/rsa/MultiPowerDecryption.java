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
		BigInteger phi, d, x, pp;
		
		pp = p.pow(2);
		
		phi = pp.subtract(p).multiply(q.subtract(BigInteger.ONE));
		d = e.modInverse(phi);

		// TCR
		// Hensel’s lifting
		BigInteger b0, b1, x0, x1, E, yp2;
		x0 = y.mod(p).modPow(d.mod(p.subtract(BigInteger.ONE)), p);
		yp2 = y.mod(pp);
		E = yp2.subtract(x0.modPow(e, pp)).mod(pp);
		x1 = E.divide(p).multiply(e.multiply(x0.modPow(e.subtract(BigInteger.ONE), p)).modInverse(p)).mod(p);
		/* xp^2 */
		b0 = x0.add(p.multiply(x1));
		/* xq */
		b1 = y.mod(q).modPow(d.mod(q.subtract(BigInteger.ONE)), q);
		
		// Garner
		BigInteger alfa1, v0, v1, m0, m1;
		m0 = pp;
		m1 = q;

		alfa1 = m0.modInverse(m1);
		
		v0 = b0;
		v1 = (b1.subtract(b0.mod(m1)).multiply(alfa1)).mod(m1);
		
		x = v1.multiply(m0).add(v0);
	
		return x;
	}

}
