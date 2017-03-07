package actn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main
{

	public static void main(String[] args)
	{
		ReedSolomon rs = new ReedSolomon();
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
		
		List<BigInteger> A = new ArrayList<>();
		
		//A.add(new BigInteger(new String("9")));
		A.add(new BigInteger(new String("6")));
		A.add(new BigInteger(new String("5")));
		A.add(new BigInteger(new String("8")));
		
		System.out.println(rs.computeFreeCoeficient(A, new BigInteger("11")).toString());
		
		BigInteger b1 = new BigInteger("99", 11);
		BigInteger b2 = new BigInteger("8", 11);
		BigInteger b3 = new BigInteger("11", 11);
		System.out.println(b1.divide(b2).mod(b3).toString());
	}

}
