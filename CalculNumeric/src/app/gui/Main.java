package app.gui;

import app.calculus.DoubleNumber;
import app.calculus.GenericNumber;

public class Main
{

	public static void main(String[] args)
	{
		Tema1<DoubleNumber> tema = new Tema1<DoubleNumber>();
		//tema.start2();
	}

}

class Tema1<T extends GenericNumber<?>>
{
	public void start()
	{
		Double machinePrecisionNumber;
		Double denominator, numerator; // numerator/denominator
		denominator = 1.0;
		numerator = 1.0;
		Double step = 10.0;
		
		Double precisionUnit = 1.0;
		
		do
		{
			denominator = step * denominator;
			machinePrecisionNumber = numerator / denominator;
			if((precisionUnit + machinePrecisionNumber) == precisionUnit) break;
		} while(true);

		System.out.println(machinePrecisionNumber);
		
		/* ********************************** */
		
		Double x, y, z;
		x = 1.0;
		y = machinePrecisionNumber;
		z = machinePrecisionNumber;
		
		Double firstSum, secondSum;
		firstSum = (( x + y ) + z);
		secondSum = ( x + ( y + z ));

		System.out.println("( x + y ) + z = " + firstSum);
		System.out.println("x + ( y + z ) = " + secondSum);
		if(firstSum.compareTo(secondSum) != 0)
			System.out.println("neasociativa");
		else 
			System.out.println("asociativa");
		
		
		Double firstProduct, secondProduct;
		firstProduct = (( x * y ) * z);
		secondProduct = ( x * ( y * z ));
		
		System.out.println("( x * y ) * z = " + firstProduct);
		System.out.println("x * ( y * z ) = " + secondProduct);
		if(firstProduct.compareTo(secondProduct) != 0)
			System.out.println("neasociativa");
		else 
			System.out.println("asociativa");
	}

	public void start2(GenericNumber<Number> machinePrecisionNumber, GenericNumber<Number> precisionUnit, GenericNumber<Number> denominator, GenericNumber<Number> numerator, GenericNumber<Number> step)
	{
//		Double machinePrecisionNumber;
//		Double denominator, numerator; // numerator/denominator
//		denominator = 1.0;
//		numerator = 1.0;
//		Double step = 10.0;
//		
//		Double precisionUnit = 1.0;
		
		do
		{
			denominator = step.multiply(denominator);
			machinePrecisionNumber = numerator.divide(denominator);
			if((precisionUnit.add(machinePrecisionNumber)).compareTo(precisionUnit)) break;
		} while(true);

		System.out.println(machinePrecisionNumber);
		
		/* ********************************** */
	}
}