package actn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ReedSolomon
{

	private static final int MAX_ACCEPTED_ERRORS = 1;
	private static Logger logger = Logger.getLogger("ReedSolomon");

	static
	{
		BasicConfigurator.configure();
	}
	
	/*
	 *  encodable - m
	 *      prime - p
	 * components - k
	 * 
	 */
	public Polynomial encode(int m, BigInteger prime, int k)
	{
		logger.info("encoding - start");
		int n = k + 2 * MAX_ACCEPTED_ERRORS;
		int [] mVector = new int[k];
		int [] mFactorsVector = new int[(k-1)];
		logger.info("encoding - converting message representation into base " + prime);
		{
			/*
			 * Here we compute the mFactorsVector used for Base Conversion
			 * 0 - p^0, 1 - p^1 ... n - p^n
			 */
			int currentFactor = 1;
			mFactorsVector[0] = currentFactor;
			for(int counter = 1; counter < k-1; counter++)
			{
				currentFactor *= prime.intValue();
				mFactorsVector[counter] = currentFactor;
			}
		}
		
		/*
		 * Here we compute the mVector Base Conversion
		 */
		mVector[k-1] = 0;
		int divider = m;
		int j = 0;
		for(int i = (k-1) - 1; i >= 0 ; i--)
		{
			mVector[j++] = divider / mFactorsVector[i];
			divider = divider % mFactorsVector[i];
		}
		
		Polynomial Px = new Polynomial(mVector);
		logger.info("encoding - message : " + m + " in base " + prime + " : " + Px.toVectorString());
		
		logger.info("encoding - polynomial computation");
		Polynomial Py;
		List<BigInteger> coeficients = new ArrayList<>();
		coeficients.add(BigInteger.ZERO);
		for(int i = 1; i <= n; i++)
			coeficients.add(Px.P(new BigInteger(new Integer(i).toString())));
		Py = new Polynomial(coeficients);
		Py = Py.modCoefficients(prime);
		
		logger.info("encoding - polynomial computation result : " + Py.toVectorString());
		
		logger.info("encoding - end");
		return Py;
	}
	
	public Polynomial decode(Polynomial Z, BigInteger prime, int k)
	{
		logger.info("decode - computing set A");
		BigInteger [] indicies = new BigInteger [Z.getDegree() - 2];
		{
			int index = 0;
			for (int i = 1; i < Z.getDegree(); i++)
			{
				index = 0;
				for (int j = 1; j < Z.getDegree(); j++)
				{
					if(i == j)
						continue;
					indicies[index] = new BigInteger(new Integer(j).toString());
					index += 1;
				}
				if(computeFreeCoeficient(Z, indicies, prime).compareTo(BigInteger.ZERO) == 0)
					break; // FIXME : If no fc exists, what to do ?
			}
		}
		BigInteger [] A = new BigInteger[k];
		for (int i = 0; i < k; i++)
			A[i] = indicies[i];
		logger.info("decode - computing set A finished : " + A);
		Polynomial decoded;
		logger.info("decode - LaGrange Interpolation");
		decoded = fastLaGrangeInterpolation(Z, A, prime);
		return decoded;
	}

	public Polynomial laGrangeInterpolation(Polynomial z, BigInteger [] indicies, BigInteger prime)
	{
		Polynomial sumationPolynomial = Polynomial.X0_0;
		Polynomial productPolynomial  = Polynomial.X0_1;
		
		Polynomial intermidiateProductPolynomial = null;

		for (int i = 0; i < indicies.length; i++)
		{
			productPolynomial = Polynomial.X0_1;
			for (int j = 0; j < indicies.length; j++)
			{
				if(i == j)
					continue;
				intermidiateProductPolynomial = Polynomial.X1_0;
				intermidiateProductPolynomial = intermidiateProductPolynomial.subtract(indicies[j]);
				intermidiateProductPolynomial = intermidiateProductPolynomial.multiply(
							indicies[i].subtract(indicies[j]).modInverse(prime)
						);
				productPolynomial = productPolynomial.multiplyP2(intermidiateProductPolynomial);
			}
			sumationPolynomial = sumationPolynomial.add(productPolynomial.multiply(z.getCoeficientAt(indicies[i])));
		}
		Polynomial result = sumationPolynomial.modCoefficients(prime);
		return result;
	}
	
	public Polynomial fastLaGrangeInterpolation(Polynomial z, BigInteger [] A, BigInteger prime)
	{
		BigInteger [] products = computeProducts(A, prime);
		BigInteger productInverse = products[1];
		BigInteger product = products[0];
		
		Polynomial sumationPolynomial = Polynomial.X0_0;
		Polynomial productPolynomial  = Polynomial.X0_1;
		Polynomial intermidiateProductPolynomial = Polynomial.X1_0;
		BigInteger iMinusJProduct;
		for (int i = 0; i < A.length; i++)
		{
			productPolynomial = Polynomial.X0_1;
			iMinusJProduct = BigInteger.ONE;
			for (int j = 0; j < A.length; j++)
			{
				if(i == j)
					continue;
				intermidiateProductPolynomial = Polynomial.X1_0;
				intermidiateProductPolynomial = intermidiateProductPolynomial.subtract(A[j]);
				productPolynomial = productPolynomial.multiplyP2(intermidiateProductPolynomial);
				iMinusJProduct    = iMinusJProduct.multiply(A[i].subtract(A[j]));
			}
			productPolynomial = productPolynomial.multiply(productInverse.multiply(product.divide(iMinusJProduct)).mod(prime));
			sumationPolynomial = sumationPolynomial.add(productPolynomial.multiply(z.getCoeficientAt(A[i])));
		}

		Polynomial result = sumationPolynomial.modCoefficients(prime);
		return result;
	}
	
	/*
	 * z - should be the polynomial  
	 */
	public BigInteger computeFreeCoeficient(Polynomial z, BigInteger [] indicies, BigInteger prime)
	{
		BigInteger [] products = computeProducts(indicies, prime);
		BigInteger productInverse = products[1];
		BigInteger product = products[0];
		
		BigInteger jMinusIProduct, jProduct;
		BigInteger sumation = BigInteger.ZERO;
		for (int i = 0; i < indicies.length; i++)
		{
			jMinusIProduct = BigInteger.ONE;
			jProduct = BigInteger.ONE;
			for (int j = 0; j < indicies.length; j++)
			{
				if(i == j)
					continue;
				jProduct = jProduct.multiply(indicies[j]);
				jMinusIProduct = jMinusIProduct.multiply(indicies[i].subtract(indicies[j]));
			}
			sumation = sumation.add(z.getCoeficientAt(indicies[i]).multiply(
						jProduct.multiply(
								productInverse.multiply(product.divide(jMinusIProduct)))
						)
					);
		}
		return sumation.mod(prime);
	}
	
	private BigInteger [] computeProducts(BigInteger [] indicies, BigInteger prime)
	{
		BigInteger productInverse = new BigInteger("1");
		BigInteger product = new BigInteger("1");
		{
			/*
			 * get all unique (i-j) mod p values
			 */
			Set<BigInteger> s = new HashSet<>();
			{
				BigInteger iMinusJ = BigInteger.ZERO;
				for (int i = 0; i < indicies.length; i++)
				{
					for (int j = 0; j < indicies.length; j++)
					{
						if(i == j)
							continue;
						iMinusJ = indicies[i].subtract(indicies[j]);
						if(iMinusJ.compareTo(BigInteger.ZERO) < 0)
							iMinusJ = iMinusJ.mod(prime);
						s.add(iMinusJ);
						// System.out.print(iMinusJ + ", ");
					}
				}
				// System.out.println();
			}
			/*
			 * compute 1/(i-j) for all i,j in indicies
			 */
			for (BigInteger iMinusJ : s)
				product = product.multiply(iMinusJ);
			productInverse = productInverse.multiply(product.modInverse(prime));
		}
		BigInteger [] products = new BigInteger [] {product, productInverse};
		return products;
	}
	
	public String displayVector(int [] vector, int base)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for(int counter = vector.length - 1; counter >= 0; counter--)
		{
			sb.append(vector[counter]);
			if(counter != 0)
				sb.append(", ");
		}
		sb.append(")" + "b" + base);
		return sb.toString();
	}
	
}
