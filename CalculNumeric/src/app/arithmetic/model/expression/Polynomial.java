package actn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Polynomial
{
	private List<BigInteger> coefficients; // 0 - a0, 1 - a1, ... n - an
	
	public static final Polynomial X0_1;
	public static final Polynomial X0_0; 
	public static final Polynomial X1_0;
	static
	{
		X0_1 = new Polynomial(new int [] {1});
		X0_0 = new Polynomial(new int [] {0});
		X1_0 = new Polynomial(new int [] {1, 0});
	}
	
	public Polynomial(List<BigInteger> coefficients)
	{
		this.coefficients = coefficients; // FIXME : Should discard 0 high consecutive coefficients and emit a warning with logger
	}
	
	/*
	 * Coefficients are specified clockwise order ( from biggest to smallest )
	 */
	public Polynomial(int [] coefficients)
	{
//		this.degree = degree;
		this.coefficients = new ArrayList<>(coefficients.length);
		for (int i = coefficients.length - 1; i >= 0; i--)
			this.coefficients.add(new BigInteger(String.valueOf(coefficients[i])));
	}

	/*
	 * Horner's Scheme
	 * 
	 * intermediate result = a_n-1 + a_n * x
	 * https://ro.wikipedia.org/wiki/Schema_Horner
	 */
	public BigInteger P(BigInteger x)
	{
		BigInteger result = coefficients.get(coefficients.size() - 1);
		for(int counter = coefficients.size() - 1; counter > 0; counter--)
		{
			if(coefficients.get(counter) == null)
				coefficients.set(counter, BigInteger.ZERO);
			result = x.multiply(result).add(coefficients.get(counter - 1));
		}
		if(coefficients.get(0) != null)
			result.add(coefficients.get(0));
		return result;
	}
	
	public BigInteger getCoeficientAt(Integer index)
	{
		if(coefficients.get(index) != null)
			return coefficients.get(index);
		else
			return BigInteger.ZERO;
	}
	
	public BigInteger getCoeficientAt(BigInteger index)
	{
		return getCoeficientAt(index.intValue());
	}
	
	public Polynomial setCoefficient(BigInteger index, BigInteger value)
	{
		Polynomial result;
		List<BigInteger> coefficients = new ArrayList<>(this.coefficients);
		coefficients.set(index.intValue(), value);
		result = new Polynomial(coefficients);
		return result;
	}
	
	
	public Polynomial add(Polynomial p)
	{
		Polynomial result = null;
		
		List<BigInteger> big, small;
		if(p.getCoeficients().size() > this.coefficients.size())
		{
			big = p.getCoeficients();
			small = this.coefficients;
		}
		else
		{
			big = this.coefficients;
			small = p.getCoeficients();
		}
		
		List<BigInteger> coeficients = new ArrayList<>(big);
		
		for(int index = 0; index < small.size(); index++)
			coeficients.set(index, small.get(index).add(big.get(index)));
		
		result = new Polynomial(coeficients);
		
		return result;
	}

	public Polynomial subtract(Polynomial p)
	{
		Polynomial result = this.add(p.negate());
		return result;
	}
	
	public Polynomial subtract(BigInteger number)
	{
		List<BigInteger> coeficients = new ArrayList<>();
		Polynomial result = null;

		coeficients.addAll(this.coefficients);
		coeficients.set(0, coeficients.get(0).subtract(number));
		result = new Polynomial(coeficients);

		return result;
	}

	public Polynomial subtract(Integer number)
	{
		return subtract(new BigInteger(number.toString()));
	}
	
	public Polynomial modCoefficients(BigInteger number)
	{
		Polynomial result = null;
		List<BigInteger> coeficients = new ArrayList<>();

		for (int i = 0; i < this.coefficients.size(); i++)
			coeficients.add(this.coefficients.get(i).mod(number));
		result = new Polynomial(coeficients);

		return result;
	}
	
	public Polynomial modInverseCoeficients(BigInteger number)
	{
		Polynomial result = null;
		List<BigInteger> coeficients = new ArrayList<>();

		for (int i = 0; i < this.coefficients.size(); i++)
			coeficients.add(this.coefficients.get(i).modInverse(number));

		result = new Polynomial(coeficients);

		return result;
	}
	
	public Polynomial multiply(Polynomial p)
	{
		Polynomial result = null;
		
		return result;
	}
	
	public Polynomial multiply(BigInteger number)
	{
		List<BigInteger> coeficients = new ArrayList<>(this.coefficients.size());
		Polynomial result;
		for (int j = 0; j < this.coefficients.size(); j++)
			coeficients.add(this.coefficients.get(j).multiply(number));
		result = new Polynomial(coeficients);
		return result;
	}

	public Polynomial multiplyP2(Polynomial p2)
	{
		Polynomial result = null;
		List<BigInteger> newCoeficients = new ArrayList<>();
		
		BigInteger carry = BigInteger.ZERO;
		BigInteger b_0 = p2.getCoeficients().get(0);
		BigInteger b_1 = p2.getCoeficients().get(1);
		BigInteger a_n;

		for (int i = 0; i < coefficients.size(); i++)
		{
			a_n = coefficients.get(i);
			newCoeficients.add(b_0.multiply(a_n).add(carry));
			carry = b_1.multiply(a_n);
		}
		newCoeficients.add(carry);
		result = new Polynomial(newCoeficients); 
		return result;
	}

	public Integer getDegree()
	{
		return coefficients.size();
	}

	public List<BigInteger> getCoeficients()
	{
		return coefficients;
	}

	public Polynomial negate()
	{
		Polynomial result = null;
		List<BigInteger> coeficients = new ArrayList<>();
		for (BigInteger bigInteger : this.coefficients)
			coeficients.add(bigInteger.negate());
		result = new Polynomial(coeficients);
		return result;
	}
	
	public String toVectorString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("( ");
		for (int i = coefficients.size() - 1; i > 1; i--)
		{
			sb.append(coefficients.get(i).toString()).append(", ");
		}
		sb.append(coefficients.get(1).toString());
		sb.append(" | ");
		sb.append(coefficients.get(0).toString());
		sb.append(" )");
		return sb.toString();
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = coefficients.size() - 1; i >= 1; i--)
		{
			if(coefficients.get(i).compareTo(BigInteger.ZERO) == 0)
				continue;
			else if(coefficients.get(i).compareTo(BigInteger.ZERO) > 0)
				sb.append(" + ").append(coefficients.get(i));
			else
				sb.append(" - ").append(coefficients.get(i).negate());
			sb.append("*").append("x^").append(i);
		}
		
		if(coefficients.get(0).compareTo(BigInteger.ZERO) >= 0)
			sb.append(" + ").append(coefficients.get(0));
		else
			sb.append(" - ").append(coefficients.get(0).negate());

		// trim the + if found
		if(coefficients.get(coefficients.size() - 1).compareTo(BigInteger.ZERO) > 0)
			return sb.toString().substring(2);
		else
			return sb.toString();
	}
}
