package app.gui;

import app.arithmetic.algorithm.CholeskyDecomposition;
import app.arithmetic.model.matrix.type.MutableMatrix;
import app.arithmetic.model.matrix.type.square.MirrorMatrix;

public class Main
{

	public static void main(String[] args)
	{
		CholeskyDecomposition cd = new CholeskyDecomposition();
		Double[][] elements = new Double[3][];

		elements[0] = new Double[1];
		elements[1] = new Double[2];
		elements[2] = new Double[3];
		
		elements[0][0] = 1.0;
		
		elements[1][0] = 2.5;
		elements[1][1] = 8.25;
		
		elements[2][0] = 3.0;
		elements[2][1] = 15.5;
		elements[2][2] = 43.0;

		MirrorMatrix A = new MirrorMatrix(3, elements);
		System.out.println(A);

		MutableMatrix[] result = cd.decompose(A);

		MutableMatrix L = result[0];
		MutableMatrix D = result[1];
		
		System.out.println(L);
		System.out.println(D);

		
//		BigDecimal[][] values = new BigDecimal[4][4];
//		values[0][0] = new BigDecimal("18"); values[0][1] = new BigDecimal("22"); values[0][2] = new BigDecimal("54");  values[0][3] = new BigDecimal("42");
//		                                     values[1][1] = new BigDecimal("70"); values[1][2] = new BigDecimal("86");  values[1][3] = new BigDecimal("62");
//		                                                                          values[2][2] = new BigDecimal("174"); values[2][3] = new BigDecimal("134");
//		                                                                                                                values[3][3] = new BigDecimal("106");
//        CholeskyMatrix A = new CholeskyMatrix(4, values, new EpsilonPrecision(9));
		
//		BigDecimal[][] values = new BigDecimal[3][3];
//		values[0][0] = new BigDecimal("25"); values[0][1] = new BigDecimal("15"); values[0][2] = new BigDecimal("-5");
//		                                     values[1][1] = new BigDecimal("18"); values[1][2] = new BigDecimal("0");
//		                                                                          values[2][2] = new BigDecimal("11");
//        CholeskyMatrix A = new CholeskyMatrix(3, values, new EpsilonPrecision(9));
		
//		BigDecimal[][] values = new BigDecimal[3][3];
//		values[0][0] = new BigDecimal("1"); values[0][1] = new BigDecimal("2.5");  values[0][2] = new BigDecimal("3");
//		                                    values[1][1] = new BigDecimal("8.25"); values[1][2] = new BigDecimal("15.5");
//		                                                                 /*6.25*/  values[2][2] = new BigDecimal("43");
//        CholeskyMatrix A = new CholeskyMatrix(3, values, new EpsilonPrecision(9));
		/*
		 *  Sa se calculeze, o descompunere LDLT(descompunerea/factorizarea Choleski) a matricii A (A = LDLT ), 
		 *  unde L este matrice inferior triunghiulara cu toate elementele de pe diagonala pricipala egale cu 1 iar
		 *  D este matrice diagonala;
		 */
//		A.decompose();
//		System.out.println(A.printA());
//		System.out.println(A.printD());
//		System.out.println(A.printL());
		/*
		 * Folosind aceasta descompunere, sa se calculeze determinantul matricii A 
		 * (det A = det L det D det LT ) ;
		 */
//		A.determinant();
//		System.out.println(A.getDet());
		
		/*
		 * Utilizand descompunerea Choleski calculata mai sus ¸si metodele substitutiei directe si inverse, 
		 * sa se calculeze xChol, o solutie aproximativa a sistemului Ax = b;
		 */
		
//		Matrix m = new DiagonalMatrix(2);
//		MutableMatrix mm = new DiagonalMatrix(2);

		/*
		 * La tema 1 atentie la POW ~!!!!!!!!!!!!
		 */
		
//		BigDecimal[][] v = new BigDecimal[2][];
//		
//		BigDecimal[] r1 = new BigDecimal[]{new BigDecimal("1")};
//		BigDecimal[] r2 = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("3")};
//		
//		v[0] = r1;
//		v[1] = r2;
//		
//		System.out.println("END");
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
