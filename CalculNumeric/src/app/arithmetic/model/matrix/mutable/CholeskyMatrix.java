package app.arithmetic.model.matrix.mutable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import app.arithmetic.model.EpsilonPrecision;

public class CholeskyMatrix
{
	private BigDecimal [][] A;
	private BigDecimal [] D;
	private EpsilonPrecision precision;

	private String padding = "18";
	
	private int dimension;

	public CholeskyMatrix(int dimension, BigDecimal [][] values, EpsilonPrecision precision)
	{
		this.dimension = dimension;
		this.A = values;
		this.precision = precision;
		this.D = new BigDecimal[dimension];
	}

	public int getDimension()
	{
		return dimension;
	}
	
	public void decompose()
	{
		BigDecimal dp, dk, aip, app, lip, lpk, lik;
		BigDecimal Sdl  = BigDecimal.ZERO;
		BigDecimal Sdll = BigDecimal.ZERO;

		for (int p = 0; p < dimension; p++)
		{
			app = A[p][p];
			
			// Special case
			if(p==0)
				D[p] = app;
			//             

			Sdl  = BigDecimal.ZERO;
			for (int k = 0; k <= p-1; k++)
			{
				lpk = A[p][k];
				dk = D[k];
				Sdl = Sdl.add(dk.multiply(lpk.pow(2))); /* formula */
			}
			dp = app.subtract(Sdl); /* formula */
			D[p] = dp;
			
			for (int i = p + 1; i < dimension; i++)
			{
				aip = A[p][i];
				
				// Special case
				if(p==0)
				{
					A[i][p] = aip;
					continue;
				}
				//
				
				Sdll = BigDecimal.ZERO;
				for (int k = 0; k <= p-1; k++)
				{
					if(i == k)
						lik = BigDecimal.ONE;
					else
						lik = A[k][i];
					lpk = A[k][p];
					dk = D[k];
					Sdll = Sdll.add(dk.multiply(lik.multiply(lpk))); /* formula */
				}
				
				A[i][p] = aip.subtract(Sdll).divide(dp, precision.getExponent(), RoundingMode.HALF_UP); /* formula */
			}
		}
		
	}
	
	public void determinant()
	{
		
	}
	
	public String printA()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				if(i == j)
					sb.append(String.format("%"+padding+"."+precision+"f", A[i][j]));
				else if (i < j)
					sb.append(String.format("%"+padding+"."+precision+"f", A[i][j]));
				else
					sb.append(String.format("%"+padding+"."+precision+"f", A[j][i]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String printL()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				if(i == j)
					sb.append(String.format("%"+padding+"."+precision+"f", 1.0));
				else if (i < j)
					sb.append(String.format("%"+padding+"."+precision+"f", A[j][i]));
				else
					sb.append(String.format("%"+padding+"."+precision+"f", A[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String printD()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				if(i == j)
					sb.append(String.format("%"+padding+"."+precision+"f", D[i]));
				else
					sb.append(String.format("%"+padding+"s", " "));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return super.toString();
	}
}
