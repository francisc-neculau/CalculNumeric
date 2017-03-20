package app.arithmetic.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EpsilonPrecision
{
	private int        exponent;
	private BigDecimal epsilon;
	
	private static final BigInteger TEN = new BigInteger("10");
	
	public EpsilonPrecision(int exponent)
	{
		this.exponent = exponent;
		this.epsilon = BigDecimal.ONE.divide(new BigDecimal(TEN.pow(exponent)));
	}
	
	public BigDecimal getEpsilon()
	{
		return epsilon;
	}
	
	public int getExponent()
	{
		return exponent;
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(exponent);
	}
}
