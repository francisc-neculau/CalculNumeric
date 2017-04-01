package app.gui.services;

import java.util.Arrays;
import java.util.Random;

public class Homework1
{
	private static String[] results = new String[100];
	
	public static String[] getResults()
	{
		return results;
	}

	public static void execute()
	{
		Double denominator, numerator, step, precisionUnit, machinePrecisionNumber;	
		step = 10.0;
		precisionUnit = 1.0;
		denominator = 1.0;
		numerator = 1.0;
		machinePrecisionNumber = numerator / denominator;

		do
		{
			if((precisionUnit + machinePrecisionNumber) == precisionUnit) break;
			denominator = step * denominator;
			machinePrecisionNumber = numerator / denominator;
		} while(true);
		
		results[0] = "machine precision number is "+machinePrecisionNumber.toString();
		/* ********************************** */
		
		Double x, y, z;
		x = 1.0;
		y = machinePrecisionNumber;
		z = machinePrecisionNumber;
		
		Double firstSum, secondSum;
		firstSum = (( x + y ) + z);
		secondSum = ( x + ( y + z ));

//		System.out.println("( x + y ) + z = " + firstSum);
//		System.out.println("x + ( y + z ) = " + secondSum);
//		if(firstSum.compareTo(secondSum) != 0)
//			System.out.println("neasociativa");
//		else 
//			System.out.println("asociativa");
		
		results[1] = "( "+x+" + "+y+" )"+" + "+z+" = "+x+" + "+"("+y + " + "+z+") -> "+(firstSum.compareTo(secondSum) == 0);
		/* ********************************** */
		
		x = 0.0000000001;
		y = machinePrecisionNumber;
		z = 10.0;
		
		Double firstProduct, secondProduct;
		firstProduct = (( x * y ) * z);
		secondProduct = ( x * ( y * z ));
		
//		System.out.println("( x * y ) * z = " + firstProduct);
//		System.out.println("x * ( y * z ) = " + secondProduct);
//		if(firstProduct.compareTo(secondProduct) != 0)
//			System.out.println("neasociativa");
//		else 
//			System.out.println("asociativa");
		
		results[2] = "( "+x+" * "+y+" )"+" * "+z+" = "+x+" * "+"("+y + " * "+z+") -> "+(firstProduct.compareTo(secondProduct) == 0);
		/* ********************************** */
		// Preparing random generation :
		Random random = new Random();
		
		Double vExact;
		
		Double P1, P2, P3, P4, P5, P6;
		Double yx2, yx4, yx6, yx8, yx10, yx12;
		Double c1, c2, c3, c4, c5, c6;
		Double error1, error2, error3, error4, error5, error6;

		c1 = 0.16666666666666666666666666666667;
		c2 = 0.00833333333333333333333333333333;
		c3 = 1.984126984126984126984126984127e-4;
		c4 = 2.7557319223985890652557319223986e-6;
		c5 = 2.5052108385441718775052108385442e-8;
		c6 = 1.6059043836821614599392377170155e-10;
		
		int ps1 = 0,ps2 = 0,ps3 = 0,ps4 = 0,ps5 = 0,ps6 = 0, collision = 0;
		double pc1 = 0,pc2 = 0,pc3 = 0,pc4 = 0,pc5 = 0,pc6 = 0;
		double[] errors = new double [6];
		double rating;
		for (int i = 0; i < 10000; i++)
		{
			x = Math.PI * (random.nextDouble() - 0.5 );
			// Divide by x to save 6 multiplications of Pi * x
			vExact = Math.sin(x) / x;
			
			yx2  =   x * x;
			yx4  = yx2 * yx2;
			yx6  = yx4 * yx2;
			yx8  = yx4 * yx4;
			yx10 = yx6 * yx4;
			yx12 = yx6 * yx6;

			P1 = (1 - c1 * yx2 + c2 * yx4);
			P2 = (P1 - c3 * yx6);  // P2 < P1
			P3 = (P2 + c4 * yx8);  // P3 > P2
			P5 = (P3 - c5 * yx10); // P5 < P3
			P6 = (P5 + c6 * yx12); // P6 > P5
			P4 = (1 - 0.166 * yx2 + yx4 * (0.00833 - c3 * yx2 + c4 * yx4)); // P4 < P3
			
			error1 = Math.abs(P1 - vExact);
			errors [0] = error1;
			error2 = Math.abs(P2 - vExact);
			errors [1] = error2;
			error3 = Math.abs(P3 - vExact);
			errors [2] = error3;
			error4 = Math.abs(P4 - vExact);
			errors [3] = error4;
			error5 = Math.abs(P5 - vExact);
			errors [4] = error5;
			error6 = Math.abs(P6 - vExact);
			errors [5] = error6;
			Arrays.sort(errors);
			
			// a b b b c d
			if(errors[1] == errors[2] && errors[2] == errors[3])
			{
				collision++;
				continue; // This is a heavy collision
			}

			rating = getRating(error1, errors);
			ps1 += rating > 1 ? 1 : 0;
			pc1 += rating;
			
			rating = getRating(error2, errors);
			ps2 += rating > 1 ? 1 : 0;
			pc2 += rating;
			
			rating = getRating(error3, errors);
			ps3 += rating > 1 ? 1 : 0;
			pc3 += rating;
			
			rating = getRating(error4, errors);
			ps4 += rating > 1 ? 1 : 0;
			pc4 += rating;
			
			rating = getRating(error5, errors);
			ps5 += rating > 1 ? 1 : 0;
			pc5 += rating;
			
			rating = getRating(error6, errors);
			ps6 += rating > 1 ? 1 : 0;
			pc6 += rating;

		}
		
		/* ********************************** */

		long timeStart, timeEnd;
		Long t1 = 0l, t2 = 0l, t3 = 0l, t4 = 0l, t5 = 0l, t6 = 0l;
		for (int i = 0; i < 200000; i++)
		{
			x = Math.PI * (random.nextDouble() - 0.5 );
			// Divide by x to save 6 multiplications of Pi * x
			vExact = Math.sin(x) / x;
			
			yx2 = x * x;yx4 = yx2 * yx2;yx6 = yx4 * yx2;yx8 = yx4 * yx4;yx10 = yx6 * yx4;yx12 = yx6 * yx6;
			
			timeStart = System.currentTimeMillis();
			P1 = (1 - c1 * yx2 + c2 * yx4);
			timeEnd = System.currentTimeMillis();
			t1 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P2 = (P1 - c3 * yx6);  // P2 < P1
			timeEnd = System.currentTimeMillis();
			t2 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P3 = (P2 + c4 * yx8);  // P3 > P2
			timeEnd = System.currentTimeMillis();
			t3 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P5 = (P3 - c5 * yx10); // P5 < P3
			timeEnd = System.currentTimeMillis();
			t5 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P6 = (P5 + c6 * yx12); // P6 > P5
			timeEnd = System.currentTimeMillis();
			t6 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P4 = (1 - 0.166 * yx2 + yx4 * (0.00833 - c3 * yx2 + c4 * yx4)); // P4 < P3
			timeEnd = System.currentTimeMillis();
			t4 += (timeEnd - timeStart);
		}
		
		/* ********************************** */
		
		Long t11 = 0l, t22 = 0l, t33 = 0l, t44 = 0l, t55 = 0l, t66 = 0l;
		for (int i = 0; i < 200000; i++)
		{
			x = Math.PI * (random.nextDouble() - 0.5 );
			
			timeStart = System.currentTimeMillis();
			P1 = (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5));
			timeEnd = System.currentTimeMillis();
			t11 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P2 = (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5) - c3 * Math.pow(x, 7));
			timeEnd = System.currentTimeMillis();
			t22 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P3 = (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5) - c3 * Math.pow(x, 7) + c4 * Math.pow(x, 9));
			timeEnd = System.currentTimeMillis();
			t33 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P5 = (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5) - c3 * Math.pow(x, 7) + c4 * Math.pow(x, 9) - c5 * Math.pow(x, 11));
			timeEnd = System.currentTimeMillis();
			t55 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P6 = (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5) - c3 * Math.pow(x, 7) + c4 * Math.pow(x, 9) - c5 * Math.pow(x, 11) + c6 * Math.pow(x, 13));
			timeEnd = System.currentTimeMillis();
			t66 += (timeEnd - timeStart);
			
			timeStart = System.currentTimeMillis();
			P4 = (x - 0.166 * Math.pow(x, 3) + 0.00833 * Math.pow(x, 5) - c3 * Math.pow(x, 7) + c4 * Math.pow(x, 9));
			timeEnd = System.currentTimeMillis();
			t44 += (timeEnd - timeStart);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("top3 #of hits - overall Rating - OptimizedTime - LazyTime" + "\n");
		sb.append("P6 " + String.format("%6d", ps6) + " - " + String.format("%10.2f", pc6) + " - " + t6 + " - " + t66 + "\n");
		sb.append("P5 " + String.format("%6d", ps5) + " - " + String.format("%10.2f", pc5) + " - " + t5 + " - " + t55 + "\n");
		sb.append("P3 " + String.format("%6d", ps3) + " - " + String.format("%10.2f", pc3) + " - " + t3 + " - " + t33 + "\n");
		sb.append("P1 " + String.format("%6d", ps1) + " - " + String.format("%10.2f", pc1) + " - " + t1 + " - " + t11 + "\n");
		sb.append("P4 " + String.format("%6d", ps4) + " - " + String.format("%10.2f", pc4) + " - " + t4 + " - " + t44 + "\n");
		sb.append("P2 " + String.format("%6d", ps2) + " - " + String.format("%10.2f", pc2) + " - " + t2 + " - " + t22 + "\n");
		sb.append("heavy collisions - " + collision);
		
		results[3] = sb.toString();
		/* ********************************** */

	}
	
	public static double getRating(double error, double [] errors)
	{
		double pc;
		if(error < errors[3])
			pc = error == errors[0] ? 9 : (error == errors[1] ? 7 : 3);
		else
			pc = error == errors[4] ? 0.1 : (error == errors[5] ? 0.01 : 0.001);
		return pc;
	}
}

/*
 * Backtesting
 */
//System.out.println("P1 + " + P1 * x);
//System.out.println("P1 - " + (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5)));
//System.out.println("P2 + " + P2 * x);
//System.out.println("P2 - " + (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5) - c3 * Math.pow(x, 7)));                                    
//System.out.println("P3 + " + P3 * x);
//System.out.println("P3 - " + (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5) - c3 * Math.pow(x, 7) + c4 * Math.pow(x, 9)));
//System.out.println("P4 + " + P4 * x);
//System.out.println("P4 - " + (x - 0.166 * yx2 + yx4 * (0.00833 - c3 * yx2 + c4 * yx4)));
//System.out.println("P5 + " + P5 * x);
//System.out.println("P5 - " + (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5) - c3 * Math.pow(x, 7) + c4 * Math.pow(x, 9) - c5 * Math.pow(x, 11)));
//System.out.println("P6 + " + P6 * x);
//System.out.println("P6 - " + (x - c1 * Math.pow(x, 3) + c2 * Math.pow(x, 5) - c3 * Math.pow(x, 7) + c4 * Math.pow(x, 9) - c5 * Math.pow(x, 11) + c6 * Math.pow(x, 13)));
//System.out.println("\n\n-------------\n\n");