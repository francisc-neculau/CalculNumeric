package app.gui;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main
{

	public static void main(String[] args)
	{
		//Tema1<DoubleNumber> tema = new Tema1<DoubleNumber>();
		//tema.start2();
		BigDecimal b1 = new BigDecimal("3");
		BigDecimal b2 = new BigDecimal("5");
		System.out.println(b1.divide(b2));
	}

}

//class Tema1<T extends GenericNumber<?>>
//{
//	public void start()
//	{
//		Double machinePrecisionNumber;
//		Double denominator, numerator; // numerator/denominator
//		denominator = 1.0;
//		numerator = 1.0;
//		Double step = 10.0;
//		
//		Double precisionUnit = 1.0;
//		
//		do
//		{
//			denominator = step * denominator;
//			machinePrecisionNumber = numerator / denominator;
//			if((precisionUnit + machinePrecisionNumber) == precisionUnit) break;
//		} while(true);
//
//		System.out.println(machinePrecisionNumber);
//		
//		/* ********************************** */
//		
//		Double x, y, z;
//		x = 1.0;
//		y = machinePrecisionNumber;
//		z = machinePrecisionNumber;
//		
//		Double firstSum, secondSum;
//		firstSum = (( x + y ) + z);
//		secondSum = ( x + ( y + z ));
//
//		System.out.println("( x + y ) + z = " + firstSum);
//		System.out.println("x + ( y + z ) = " + secondSum);
//		if(firstSum.compareTo(secondSum) != 0)
//			System.out.println("neasociativa");
//		else 
//			System.out.println("asociativa");
//		
//		
//		Double firstProduct, secondProduct;
//		firstProduct = (( x * y ) * z);
//		secondProduct = ( x * ( y * z ));
//		
//		System.out.println("( x * y ) * z = " + firstProduct);
//		System.out.println("x * ( y * z ) = " + secondProduct);
//		if(firstProduct.compareTo(secondProduct) != 0)
//			System.out.println("neasociativa");
//		else 
//			System.out.println("asociativa");
//	}
//
//	public void start2(GenericNumber<Number> machinePrecisionNumber, GenericNumber<Number> precisionUnit, GenericNumber<Number> denominator, GenericNumber<Number> numerator, GenericNumber<Number> step)
//	{
////		Double machinePrecisionNumber;
////		Double denominator, numerator; // numerator/denominator
////		denominator = 1.0;
////		numerator = 1.0;
////		Double step = 10.0;
////		
////		Double precisionUnit = 1.0;
//		
//		do
//		{
//			denominator = step.multiply(denominator);
//			machinePrecisionNumber = numerator.divide(denominator);
//			if((precisionUnit.add(machinePrecisionNumber)).compareTo(precisionUnit)) break;
//		} while(true);
//
//		System.out.println(machinePrecisionNumber);
//		
//		/* ********************************** */
//	}
//}
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
//
////		BigInteger [] A = new BigInteger [] {new BigInteger("1"), new BigInteger("3"), new BigInteger("4")};
////		List<BigInteger> c = new ArrayList<>();
////		c.add(new BigInteger(new String("0")));
////		c.add(new BigInteger(new String("9")));
////		c.add(new BigInteger(new String("2")));
////		c.add(new BigInteger(new String("6")));
////		c.add(new BigInteger(new String("5")));
////		c.add(new BigInteger(new String("8")));
////		Polynomial interpolationResult = rs.fastLaGrangeInterpolation(new Polynomial(c), A, prime);
////		System.out.println(new Polynomial(c));
////		System.out.println("freeCoeficient : " + rs.computeFreeCoeficient(new Polynomial(c), 
////				new BigInteger [] {
////						new BigInteger(new String("1")) ,
////						new BigInteger(new String("3")) ,
////						new BigInteger(new String("4"))
////						}, prime));
////		System.out.println("LaGrange : " + interpolationResult);
//
//		Polynomial M = rs.decode(Z, prime, k);
//		System.out.println("decoded : " + M.toVectorString());