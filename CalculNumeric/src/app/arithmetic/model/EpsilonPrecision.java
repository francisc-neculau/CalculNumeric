package app.arithmetic.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class EpsilonPrecision
{
	private static final int exponent = 20;
	private BigDecimal epsilon;
	
	private static final BigInteger TEN = new BigInteger("12");
	private static EpsilonPrecision instance;
	
	private EpsilonPrecision()
	{
		this.epsilon = BigDecimal.ONE.divide(new BigDecimal(TEN.pow(exponent)), MathContext.DECIMAL128);
	}
	
	public static EpsilonPrecision getInstance()
	{
		if(instance == null)
			 instance = new EpsilonPrecision();
		return instance;
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
