package actn;

import java.math.BigInteger;
import java.util.List;

public class Polynomial
{
	public static final BigInteger UNIT = new BigInteger("1", 10);
	public static final BigInteger ZERO = new BigInteger("0", 10);
	
	private Integer base;
	private Integer grade;
	private List<BigInteger> coeficients;
	private BigInteger freeCoeficient;

	public Polynomial(Integer base, Integer grade, List<BigInteger> coeficients)
	{
		this(base, grade, coeficients, Polynomial.ZERO);
	}
	
	public Polynomial(Integer base, Integer grade, List<BigInteger> coeficients, BigInteger freeCoeficient)
	{
		this.base  = base;
		this.grade = grade;
		this.coeficients = coeficients;
		this.freeCoeficient = freeCoeficient;
	}

	/*
	 * Horner's Scheme
	 * 
	 * intermediate result = a_n-1 + a_n * x
	 * https://ro.wikipedia.org/wiki/Schema_Horner
	 */
	public BigInteger P(BigInteger x)
	{
		BigInteger result = coeficients.get(grade - 1);
		for(int counter = grade - 1; counter >= 1; counter--)
			result = x.multiply(result).add(coeficients.get(counter - 1));
		result = result.multiply(x); // + a_0
		return result;
	}
	
	public void interpolation()
	{
		// Lagrange()
		// Brute()
	}
	
	public Polynomial add(Polynomial p)
	{
		Polynomial result = null;
		
		return result;
	}

	public Polynomial subtract(Polynomial p)
	{
		Polynomial result = null;
		
		return result;
	}
	
	public Polynomial multiply(Polynomial p)
	{
		Polynomial result = null;
		
		return result;
	}
	
	public Integer getBase()
	{
		return base;
	}

	public Integer getGrade()
	{
		return grade;
	}

	public List<BigInteger> getCoeficients()
	{
		return coeficients;
	}
	
	public BigInteger getFreeCoeficient()
	{
		return freeCoeficient;
	}

	public String toVectorString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (BigInteger bigInteger : coeficients)
		{
			sb.append(bigInteger.toString());
			sb.append(", ");
		}
		sb.append(")" + "b" + base);
		return sb.toString();
	}
	
}
