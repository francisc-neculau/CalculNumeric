package test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

import app.arithmetic.algorithm.BabyStepGianStep;
import app.arithmetic.algorithm.SolovayStrassen;
import app.arithmetic.util.RandomNumbers;

public class MainActn
{
	private static final BigInteger TWO = new BigInteger("2");

	public static void main(String[] args)
	{
		
		
		/*
		 * I. Generate a prime p = 2q + 1 where q is also prime.
		 */
		SolovayStrassen solovayStrassen = new SolovayStrassen(64);
		
		int numberOfBits = 16;
		
		BigInteger p = BigInteger.probablePrime(numberOfBits, new Random(System.currentTimeMillis()));
		BigInteger q = p.subtract(BigInteger.ONE).divide(TWO);
		
		boolean found = false;
		while(!found)
		{
			if(solovayStrassen.isPrime(q))
				found = true;
			else
				p = BigInteger.probablePrime(numberOfBits, new Random(System.currentTimeMillis()));
				q = p.subtract(BigInteger.ONE).divide(TWO);
		}
		System.out.println("p = " + p.toString());
		System.out.println("q = " + q.toString());
		
		
		/*
		 * II. Generate alpha a primitive root modulo p.
		 */
		/*
		 * Because p is prime => Euler's Function phi(p) = p - 1
		 * which means we have p - 1 multiplicative inverses
		 * in Zp* ( |Zp*| = phi(p) = p - 1 )
		 * 
		 * We have in Zp* a number of phi(phi(p)) primitive 
		 * roots modulo p.
		 * 
		 * Because p = 2q + 1 with p,q primes => phi(p) = 2q
		 * phi(phi(p)) = phi(2)*phi(q)
		 * phi(phi(p)) = (2 - 1) * ( q - 1 )
		 * phi(phi(p)) = q - 1
		 * 
		 * Thus, for our particular case, Zp* has q -1 
		 * primitive roots modulo p.
		 * 
		 * To check weather alpha is a primitive root modulo p
		 * we must check, for our particular case, if :
		 * 
		 * 	1. alpha^(phi(p)/2) mod p != 1 
		 *  2. alpha^(phi(p)/q) mod p != 1
		 * 
		 * Where phi(p)/2 = q, and phi(p)/q = 2.
		 * 
		 */
		
		BigInteger phiP = p.subtract(BigInteger.ONE);
		
		System.out.println("The chances to pick randomly a primitive root is " + new BigDecimal(q.subtract(BigInteger.ONE)).divide(new BigDecimal(phiP), 4, RoundingMode.HALF_DOWN));
		
		BigInteger alpha = RandomNumbers.nextBigInteger(new Random(System.currentTimeMillis()), TWO, phiP);
		
		found = false;
		while(!found)
		{
			if(!alpha.modPow(q, p).equals(BigInteger.ONE) && !alpha.modPow(TWO, p).equals(BigInteger.ONE))
				found = true;
			else
				alpha = RandomNumbers.nextBigInteger(new Random(System.currentTimeMillis()), TWO, phiP);
		}
		System.out.println("Primite root alpha is : " + alpha);
		
		/*
		 * III. Use BabyStepGiantStep algorithm.
		 */
		BabyStepGianStep shanks = new BabyStepGianStep();
		
		BigInteger beta = RandomNumbers.nextBigInteger(new Random(System.currentTimeMillis()), TWO, q.subtract(BigInteger.ONE));
		System.out.println("Beta is chosen to be : " + beta);
		System.out.println("The discreete logarithm log base alpha from beta mod p is " + shanks.compute(alpha, beta, p));
		
		
		
		/*
		 * IV. Use Pohlig-Hellman algorithm
		 */
		System.out.println(BigInteger.probablePrime(1024, new Random(System.currentTimeMillis())));
		
		
		
		
		
		/*
		 * Test Jacobi Symbol 
		 */
		
//		BigInteger a;
//		BigInteger n;
//		
//		JacobiSymbol jacobiSymbol = new JacobiSymbol();
//
//		a = new BigInteger("13");
//		n = new BigInteger("23");
//		System.out.println("Jacobi Symbol for " + a + " and " + n + "is" + jacobiSymbol.compute(a, n));
//		
//		a = new BigInteger("107");
//		n = new BigInteger("23");
//		System.out.println("Jacobi Symbol for " + a + " and " + n + "is" + jacobiSymbol.compute(a, n));
//		
//		a = new BigInteger("22");
//		n = new BigInteger("22");
//		System.out.println("Jacobi Symbol for " + a + " and " + n + "is" + jacobiSymbol.compute(a, n));
//		
//		for (int i = 1; i < 179424691; i++)
//		{
//			a = new BigInteger(Integer.valueOf(i).toString());
//			n = new BigInteger("179424691");
//			System.out.println("Jacobi Symbol for " + a + " and " + n + "is" + jacobiSymbol.compute(a, n));			
//		}

		
		/*
		 * Test mersenMod
		 */
//		LucasLehmer lucasLehmer = new LucasLehmer();
//		BigInteger x  = new BigInteger("61");
//		BigInteger Mn = new BigInteger("31");
//		int n  = 5;
//		
//		System.out.println(lucasLehmer.mersenMod(x, Mn, n));
//		System.out.println(lucasLehmer.isPrime(Mn, n));
//		System.out.println(lucasLehmer.isPrime(n));
		
//		int a, b, m, i;
//		int result;
//
//		a = 194;
//		i = 5;
//		b = (int) (Math.pow(2, i) - 1);
//		m = (int) (Math.pow(2, i));
//
//		result = (a & b) + (a >> i);
//		System.out.println(a + " & "  + b + " : " + (a & b));
//		System.out.println(a + " >> " + i + " : " + (a >> i));
//		System.out.println(result);
//		System.out.println(((194 >> 5) + (194 & 31)));

//		long startTime, endTime;
//		
//		int n  = 1279; // 1279
//		BigInteger Mn = (new BigInteger("2")).pow(n).subtract(BigInteger.ONE);
//
//		SolovayStrassen solovayStrassen = new SolovayStrassen(64);
//		LucasLehmer lucasLehmer = new LucasLehmer();
//		//
//		//
//		startTime = System.currentTimeMillis();
//		System.out.println(Mn + " is prime ? \n" + solovayStrassen.isPrime(Mn));
//		endTime = System.currentTimeMillis();
//		System.out.println("time : " + new Double((endTime - startTime))/1000 + "s");
//		//
//		//
//		startTime = System.currentTimeMillis();
//		System.out.println(Mn + " is prime ? \n" + lucasLehmer.isPrimeSlow(Mn, n));
//		endTime = System.currentTimeMillis();
//		System.out.println("time : " + new Double((endTime - startTime))/1000 + "s");
//		//
//		//
//		startTime = System.currentTimeMillis();
//		System.out.println(Mn + " is prime ? \n" + lucasLehmer.isPrime(Mn, n));
//		endTime = System.currentTimeMillis();
//		System.out.println("time : " + new Double((endTime - startTime))/1000 + "s");
//		//
//		//
		
		
		
		
		
		
		/*
		 * Polynomial
		 */
//		Polynomial tp, tp2, tp3, tmul;
//		
//		tp = new Polynomial(new int [] {1, 0, 1, 3, -6, 1, 1});
//		tp2 = new Polynomial(new int [] {999, 999});
//		tp3 = new Polynomial(new int [] {1, 1, 1});
//		
//		System.out.println("Simple toString : " + tp); // ok
//		System.out.println("Simple toString : " + tp2); // ok
//		System.out.println("Simple toString : " + tp3); // ok
//		System.out.println("Multiply by number -4 : " + tp.multiply(new BigInteger("-4"))); // ok
//		System.out.println("Multiply by polynomial degree 2 : " + tp.multiplyP2(tp2)); // ok
//		System.out.println("Subtract by number 19 : " + tp.subtract(19)); // ok
//		System.out.println("Add polynomials : " + tp.add(tp3)); // ok
//		System.out.println("Subtract polynomials : " + tp.subtract(tp2)); // ok
		
//		int k = 3;
//		int s = 1;
//		//(n = 5) 
//		int p = 11;
//		
//		int m = 29;
//		
//		rs.encode(m, p, k);
//		
//		List<BigInteger> coeficients = new ArrayList<>();
//		coeficients.add(new BigInteger("2", 10));
//		coeficients.add(new BigInteger("-6", 10));
//		coeficients.add(new BigInteger("2", 10));
//		Polynomial polynomial = new Polynomial(10, 3, coeficients);
//		
//		BigInteger p3 = polynomial.P(new BigInteger("3", 10));
//		System.out.println(p3.toString());
//		
//		List<BigInteger> A = new ArrayList<>();
//		
//		//A.add(new BigInteger(new String("9")));
//		A.add(new BigInteger(new String("6")));
//		A.add(new BigInteger(new String("5")));
//		A.add(new BigInteger(new String("8")));
//		
//		System.out.println(rs.computeFreeCoeficient(A, new BigInteger("11")).toString());
//		
//		BigInteger b1 = new BigInteger("-13");
//		BigInteger b2 = new BigInteger("-3");
//		BigInteger b3 = new BigInteger("11");
//		System.out.println(b2.modInverse(b3));
//		System.out.println(b1.multiply(b2.modInverse(b3)));
//		System.out.println(b1.multiply(b2.modInverse(b3)).mod(b3));
		
//		Integer n = 4;
//		Integer overallProduct = 1;
//		Integer lineProduct = 1;
//		BigInteger prime = new BigInteger("11");
//		BigInteger inv;
//		
////		BigInteger fullLineProduct = new BigInteger(new Integer(1*2*3*4*5*6*7*8).toString());
////		System.out.println(fullLineProduct.modInverse(prime));
////		System.out.println(new Integer(1*2*3*4*5*6*7*8));
//
//		int skip = 2;
//		for (int i = 1; i <= n; i++)
//		{
//			if(i == skip)
//			{
//				System.out.println();
//				continue;
//			}
//			lineProduct = 1;
//			for (int j = 1; j <= n; j++)
//			{
//				if(j == i)
//					continue;
//				if(j == skip)
//				{
//					System.out.print("    ");
//					continue;
//				}
//				lineProduct *= (i-j);
//				overallProduct *= (i-j);
//
//				inv = (new BigInteger(new Integer((i-j)).toString())).modInverse(prime);
//				if(i-j < 0)
//					System.out.print(" " + (i-j + 11) + " " + inv + ", ");
//				else
//					System.out.print(" " + (i-j) + " " + inv + ", ");
//			}
//			System.out.print(" - " + lineProduct + " - " + (new BigInteger(lineProduct.toString())).modInverse(prime));
//			System.out.println();
//		}
//		
//
//
		
		
		
		/*
		 * Test Interpolation
		 */
		
//		ReedSolomon rs = new ReedSolomon();
//		BigInteger prime = new BigInteger("11");
//		int message = 29;
//		int k = 3;
//
//		Polynomial Y = rs.encode(message, prime, k);
//		System.out.println("encoded : " + Y.toVectorString());
//		
//		Polynomial Z = Y.setCoefficient(new BigInteger("4"), new BigInteger("7987"));
//		System.out.println("corrupt : " + Z.toVectorString());

//		BigInteger [] A = new BigInteger [] {new BigInteger("1"), new BigInteger("3"), new BigInteger("4")};
//		List<BigInteger> c = new ArrayList<>();
//		c.add(new BigInteger(new String("0")));
//		c.add(new BigInteger(new String("9")));
//		c.add(new BigInteger(new String("2")));
//		c.add(new BigInteger(new String("6")));
//		c.add(new BigInteger(new String("5")));
//		c.add(new BigInteger(new String("8")));
//		Polynomial interpolationResult = rs.fastLaGrangeInterpolation(new Polynomial(c), A, prime);
//		System.out.println(new Polynomial(c));
//		System.out.println("freeCoeficient : " + rs.computeFreeCoeficient(new Polynomial(c), 
//				new BigInteger [] {
//						new BigInteger(new String("1")) ,
//						new BigInteger(new String("3")) ,
//						new BigInteger(new String("4"))
//						}, prime));
//		System.out.println("LaGrange : " + interpolationResult);

//		Polynomial M = rs.decode(Z, prime, k);
//		System.out.println("decoded : " + M.toVectorString());
		
		
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
//		Integer numberOfIterations = 100;
//		MultiPrimeDecryption mprmd;
//		MultiPowerDecryption mpowd;
//		Long timeStart, timeEnd, totalTime11 = 0l, totalTime12 = 0l, totalTime2 = 0l, totalTime3 = 0l;
//		boolean ok = false;
//		for (int i = 0; i < numberOfIterations; i++)
//		{
//			Random rnd = new Random();
//			
//			BigInteger p = BigInteger.probablePrime(1024, rnd);
//			BigInteger pp = p.pow(2);
//			BigInteger q = BigInteger.probablePrime(1024, rnd);
//			BigInteger r = BigInteger.probablePrime(1024, rnd);
//			
//			BigInteger n = p.multiply(q.multiply(r));
//			BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE).multiply(r.subtract(BigInteger.ONE)));
//			
//			BigInteger e = null;
//			BigInteger d = null;
//			while(!ok)
//			{
//				try
//				{
//					e = BigInteger.probablePrime(8, rnd); // gcd(e, phi) = 1 !
//					d = e.modInverse(phi);                // d = e^-1; (d * e) mod phi = 1
//					ok = true;
//				}
//				catch(Exception e1)
//				{
//					System.out.println("Prime not invertible, try again");
//				}
//			}
//			ok = false;
//
//			BigInteger x = new BigInteger(new Integer(113 + rnd.nextInt() * i + 10 * i).toString());
//			BigInteger y = x.pow(e.intValue()).mod(n);
//
//			// Library Function :
//			timeStart = System.nanoTime();
//			n = p.multiply(q.multiply(r));
//			phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE).multiply(r.subtract(BigInteger.ONE)));
//			y.modPow(d, n);
//			//System.out.println("plain-text  : " + y.modPow(d, n));
//			timeEnd = System.nanoTime();
//			totalTime11 += (timeEnd - timeStart);
//			
//			// TCR & Garner
//			mprmd = new MultiPrimeDecryption();
//			timeStart = System.nanoTime();
//			mprmd.decrypt(p, q, r, e, y);
//			//System.out.println("(TCR & Garner) plain-text : " + mprmd.decrypt(p, q, r, e, y));
//			timeEnd = System.nanoTime();
//			totalTime2 += (timeEnd - timeStart);
//			
//			phi = pp.subtract(p).multiply(q.subtract(BigInteger.ONE));
//			n   = pp.multiply(q);
//			d = e.modInverse(phi);
//			y = x.pow(e.intValue()).mod(n);
//			
//			// Library Function :
//			timeStart = System.nanoTime();
//			phi = pp.subtract(p).multiply(q.subtract(BigInteger.ONE));
//			n   = pp.multiply(q);
//			y.modPow(d, n);
//			//System.out.println("plain-text  : " + y.modPow(d, n));
//			timeEnd = System.nanoTime();
//			totalTime12 += (timeEnd - timeStart);
//			
//			// TCR & Hensel’s lifting & Garner
//			mpowd = new MultiPowerDecryption();
//			timeStart = System.nanoTime();
//			mpowd.decrypt(p, q, e, y);
//			//System.out.println("(TCR & Hensel’s lifting & Garner) plain-text : " + mpowd.decrypt(p, q, e, y));
//			timeEnd = System.nanoTime();
//			totalTime3 += (timeEnd - timeStart);
//		}
//		System.out.println("Ab - Library");
//		System.out.println("Ac - Library");
//		System.out.println("B - TCR & Garner");
//		System.out.println("C - TCR & Hensel’s lifting & Garner");
//		System.out.println("Total time Ab : " + totalTime11);
//		System.out.println("Total time Ac : " + totalTime12);
//		System.out.println("Total time B : " + totalTime2);
//		System.out.println("Total time C : " + totalTime3);
////		System.out.println("Avearage time Ab : " + new Double(totalTime11)/new Double(numberOfIterations));
////		System.out.println("Avearage time Ac : " + new Double(totalTime12)/new Double(numberOfIterations));
////		System.out.println("Avearage time B  : " + new Double(totalTime2)/new Double(numberOfIterations));
////		System.out.println("Avearage time C  : " + new Double(totalTime3)/new Double(numberOfIterations));
//		System.out.println("Time Improvement Ab/B : " + new Double(totalTime11)/new Double(totalTime2));
//		System.out.println("Time Improvement Ac/C : " + new Double(totalTime12)/new Double(totalTime3));
//		//System.out.println("Time Improvement C/B  : " + new Double(totalTime3)/new Double(totalTime2));
	}
	
}
