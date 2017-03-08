package actn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Polynomial
{
	
	/*private Integer base;*/
	private int degree;
	private List<BigInteger> coeficients; // 0 - a0, 1 - a1, ... n - an
	
	public static final Polynomial X0_1;
	public static final Polynomial X0_0; 
	public static final Polynomial X1_0;
	static
	{
		List<BigInteger> coeficients;
		coeficients= new ArrayList<>();
		coeficients.add(BigInteger.ZERO);
		coeficients.add(BigInteger.ONE);
		X0_1 = new Polynomial(/*10, */0, coeficients);
		
		coeficients.clear();
		coeficients.add(BigInteger.ONE);
		X0_0 = new Polynomial(/*10, */0, coeficients);
		
		coeficients.clear();
		coeficients.add(BigInteger.ONE);
		coeficients.add(BigInteger.ZERO);
		X1_0 = new Polynomial(/*10, */1, coeficients);
	}
	
	public Polynomial(/*Integer base, */int degree, List<BigInteger> coeficients)
	{
		/*this.base  = base;*/
		this.degree = degree;
		this.coeficients = coeficients;
	}
	
	public Polynomial(/*Integer base, */int degree, int [] coeficients)
	{
		/*this.base  = base;*/
		this.degree = degree;
		this.coeficients = new ArrayList<>(coeficients.length);
		for (int i = coeficients.length - 1; i >= 0; i--)
			this.coeficients.add(new BigInteger(String.valueOf(coeficients[i])));
	}

	/*
	 * Horner's Scheme
	 * 
	 * intermediate result = a_n-1 + a_n * x
	 * https://ro.wikipedia.org/wiki/Schema_Horner
	 */
	public BigInteger P(BigInteger x)
	{
		BigInteger result = coeficients.get(degree - 1);
		for(int counter = degree - 1; counter >= 0; counter--)
		{
			if(coeficients.get(counter) == null)
				coeficients.set(counter, BigInteger.ZERO);
			result = x.multiply(result).add(coeficients.get(counter - 1));
		}
		result = result.multiply(x); // + a_0
		if(coeficients.get(0) != null)
			result.add(coeficients.get(0));
		return result;
	}
	
	public BigInteger getCoeficientAt(Integer index)
	{
		if(coeficients.get(index) != null)
			return coeficients.get(index);
		else
			return BigInteger.ZERO;
	}
	
	public BigInteger getCoeficientAt(BigInteger index)
	{
		return getCoeficientAt(index.intValue());
	}
	
	public Polynomial add(Polynomial p)
	{
		Polynomial result = null;
		List<BigInteger> coeficients = new ArrayList<>();
		
		int difference = this.coeficients.size() - p.getCoeficients().size();
		if(difference > 0)
		{
			for (int i = 0; i < this.coeficients.size() - difference; i++)
			{
				
			}
		}
		else
		{
			difference = difference * -1;
			
		}
		return result;
	}

	public Polynomial subtract(Polynomial p)
	{
		Polynomial result = null;
		
		return result;
	}
	
	public Polynomial subtract(BigInteger b)
	{
		Polynomial result = null;
		
		return result;
	}

	public Polynomial subtract(Integer b)
	{
		Polynomial result = null;
		
		return result;
	}
	
	public Polynomial divide(BigInteger b)
	{
		return null;
	}
	
	public Polynomial multiply(Polynomial p)
	{
		Polynomial result = null;
		
		return result;
	}
	
	public Polynomial multiply(BigInteger number)
	{
		List<BigInteger> coeficients = new ArrayList<>(this.coeficients.size());
		Polynomial result;
		for (int j = 0; j < this.coeficients.size(); j++)
			coeficients.add(this.coeficients.get(j).multiply(number));
		result = new Polynomial(this.degree, coeficients);
		return result;
	}

	public Polynomial multiplyP2(Polynomial p2)
	{
		Polynomial result = null;
		List<BigInteger> newCoeficients = new ArrayList<>();
		Integer newDegree = this.degree + 1;
		
		BigInteger carry = BigInteger.ZERO;
		BigInteger b_0 = p2.getCoeficients().get(0);
		BigInteger b_1 = p2.getCoeficients().get(1);
		BigInteger a_n;
		/*BigInteger base = new BigInteger(this.base.toString());*/
		for (int i = 0; i < coeficients.size(); i++)
		{
			a_n = coeficients.get(i);
			newCoeficients.add(b_0.multiply(a_n).add(carry)/*.mod(base)*/); // FIXME : Why ?
			carry = b_1.multiply(a_n);
		}
		newCoeficients.add(carry);
		result = new Polynomial(/*this.base, */newDegree, newCoeficients); 
		return result;
	}
	
/*	public Integer getBase()
	{
		return base;
	}*/

	public Integer getDegree()
	{
		return degree;
	}

	public List<BigInteger> getCoeficients()
	{
		return coeficients;
	}

	public String toVectorString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (BigInteger bigInteger : coeficients)
			sb.append(bigInteger.toString()).append(", ");
		sb.append(")" + "b"/* + base*/);
		return sb.toString();
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = coeficients.size() - 1; i >= 1; i--)
		{
			if(coeficients.get(i).compareTo(BigInteger.ZERO) == 0)
				continue;
			else if(coeficients.get(i).compareTo(BigInteger.ZERO) > 0)
				sb.append(" + ").append(coeficients.get(i));
			else
				sb.append(" - ").append(coeficients.get(i).negate());
			sb.append("*").append("x^").append(i);
		}
		if(coeficients.get(0).compareTo(BigInteger.ZERO) != 0)
			sb.append(" + ").append(coeficients.get(0));
		if(coeficients.get(coeficients.size() - 1).compareTo(BigInteger.ZERO) > 0)
			return sb.toString().substring(2);
		else
			return sb.toString();
	}
}
