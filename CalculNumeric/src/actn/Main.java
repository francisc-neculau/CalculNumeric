package actn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main
{

	public static void main(String[] args)
	{
		/*
		 * Polynomial
		 */
		Polynomial tp, tp2, tmul;
		
		tp = new Polynomial(5, new int [] {1, 3, -6, 1, 1});
		System.out.println(tp); // ok
		
		tmul = tp.multiply(new BigInteger("-4"));
		System.out.println(tmul); // ok
		
		tp2 = new Polynomial(1, new int [] {1, 1});
		System.out.println(tp2);
		System.out.println(tp);
		tmul = tp.multiplyP2(tp2); // ok
		System.out.println(tmul);
		/*
		 * Test Interpolation
		 */
//		ReedSolomon rs = new ReedSolomon();
//		List<BigInteger> Z = new ArrayList<>();
//		Z.add(new BigInteger("9"));
//		Z.add(new BigInteger("2"));
//		Z.add(new BigInteger("6"));
//		Z.add(new BigInteger("5"));
//		Z.add(new BigInteger("8"));
//		
//		BigInteger [] a = new BigInteger [] {new BigInteger("9"), new BigInteger("9"), new BigInteger("9")}; 
//		A.add(2);
//		A.add(3);
//		A.add(4);
//		
//		System.out.println(rs.laGrangeInterpolation(Z, A));
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
//		BigInteger b1 = new BigInteger("99", 11);
//		BigInteger b2 = new BigInteger("8", 11);
//		BigInteger b3 = new BigInteger("11", 11);
//		System.out.println(b1.divide(b2).mod(b3).toString());
	}

}
