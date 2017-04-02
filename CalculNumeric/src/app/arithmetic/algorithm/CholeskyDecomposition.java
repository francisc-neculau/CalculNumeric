package app.arithmetic.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

import app.arithmetic.model.EpsilonPrecision;
import app.arithmetic.model.matrix.type.MutableMatrix;
import app.arithmetic.model.matrix.type.square.DiagonalMatrix;
import app.arithmetic.model.matrix.type.square.MirrorMatrix;
import app.arithmetic.model.matrix.type.square.triangular.StrictLowerTriangularMatrix;

public class CholeskyDecomposition
{
	
	/**
	 *  This algorithm uses mutable, memory efficient 
	 * matrices. As input we will provide an MirrorMatrix 
	 * and it will return a vector of matrices, one 
	 * {@link StrictLowerTriangularMatrix} and the other
	 * {@link DiagonalMatrix}
	 * 
	 * 		A = L*D*Lt
	 * 	where Lt is the transpose of L
	 * 
	 * @param A
	 * @return
	 */
	public MutableMatrix[] decompose(MirrorMatrix A)
	{
		MutableMatrix[] result = new MutableMatrix[2];
		MutableMatrix D = new DiagonalMatrix(A.getDimension());
		MutableMatrix L = new StrictLowerTriangularMatrix(A.getDimension(), BigDecimal.ONE);
		
		BigDecimal dp, dk, aip, app, lip, lpk, lik, Sdl, Sdll;
		
		for (int p = 0; p < A.getDimension(); p++)
		{
			app = A.getEij(p, p);
			if(p == 0) // ~Special case!~
				D.setEii(p, app);
			
			Sdl = BigDecimal.ZERO;
			for (int k = 0; k <= p-1; k++)
			{
				lpk = L.getEij(p, k);
				dk  = D.getEii(k);
				Sdl = Sdl.add(dk.multiply(lpk.multiply(lpk))); // ~Formula!~
			}
			dp = app.subtract(Sdl); // ~Formula!~
			D.setEii(p, dp);
			
			for (int i = p + 1; i < A.getDimension(); i++)
			{
				aip = A.getEij(i, p);
				
				// Special case
				if(p==0)
				{
					L.setEij(i, p, aip.divide(dp, EpsilonPrecision.getInstance().getExponent(), RoundingMode.HALF_UP));
					continue;
				}
				//
				
				Sdll = BigDecimal.ZERO;
				for (int k = 0; k <= p-1; k++)
				{
					if(i == k)
						lik = BigDecimal.ONE;
					else
						lik = L.getEij(i, k);
					lpk  = L.getEij(p, k);
					dk   = D.getEii(k);
					Sdll = Sdll.add(dk.multiply(lik.multiply(lpk))); // ~Formula!~
				}
				// FIXME: Atentie la eroare de impartire aici !
				lip = aip.subtract(Sdll).divide(dp, EpsilonPrecision.getInstance().getExponent(), RoundingMode.HALF_UP);
				L.setEij(i, p, lip); // ~Formula!~
			}
		}

		result[0] = L;
		result[1] = D;
		return result;
	}
	/*
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
				Sdl = Sdl.add(dk.multiply(lpk.pow(2))); // formula
			}
			dp = app.subtract(Sdl); // formula
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
					Sdll = Sdll.add(dk.multiply(lik.multiply(lpk))); // formula
				}
				A[i][p] = aip.subtract(Sdll).divide(dp, precision.getExponent(), RoundingMode.HALF_UP); // formula
			}
		}
		
	}
	 */
}
