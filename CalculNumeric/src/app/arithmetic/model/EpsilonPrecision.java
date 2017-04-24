package app.arithmetic.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class EpsilonPrecision
{
	private static int exponent = 18;
	private BigDecimal epsilon;
	private MathContext mc;
	
	private static final BigInteger TEN = new BigInteger("10");
	private static EpsilonPrecision instance;
	
	private EpsilonPrecision()
	{
		this.mc = new MathContext(exponent, RoundingMode.CEILING);
		this.epsilon = BigDecimal.ONE.divide(new BigDecimal(TEN.pow(exponent)), this.mc);
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
	
	public MathContext getMathContext()
	{
		return this.mc;
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(exponent);
	}
}
