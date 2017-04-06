package app.arithmetic.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class EpsilonPrecision
{
	private static int exponent = 12;
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
	
	// FIXME : This is not good !
	public void setExponent(int exponenet)
	{
		EpsilonPrecision.exponent = exponenet;
		this.epsilon = BigDecimal.ONE.divide(new BigDecimal(TEN.pow(exponent)), MathContext.DECIMAL128);
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
