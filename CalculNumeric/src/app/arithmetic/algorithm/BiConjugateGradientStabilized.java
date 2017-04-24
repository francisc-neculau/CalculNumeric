package app.arithmetic.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

import app.arithmetic.model.EpsilonPrecision;
import app.arithmetic.model.matrix.Matrix;
import app.arithmetic.model.matrix.NormType;
import app.arithmetic.model.matrix.type.square.SquareMatrix;
import app.arithmetic.model.matrix.type.vector.ColumnMatrix;

public class BiConjugateGradientStabilized
{
	private Matrix x;
	
	public BiConjugateGradientStabilized()
	{
		
	}

	
	public boolean solve(SquareMatrix A, ColumnMatrix B)
	{
		BigDecimal rho, rhoPrevious, beta, alpha, omega, omegaPrevious;
		/*Column*/Matrix r0, r, rPrevious, p, pPrevious, v, vPrevious, s, t, xPrevious;
		int iterationCounter = 0;
		int n = A.getDimension();
		
		//convergenceNumber = EpsilonPrecision.getInstance().getEpsilon().multiply(B.norm(NormType.NATURAL));
		
		// initialization
		int precision = EpsilonPrecision.getInstance().getExponent();
		RoundingMode roundingMode = RoundingMode.HALF_UP;
		
		rho   = BigDecimal.ONE;
		omega = BigDecimal.ONE;
		alpha = BigDecimal.ONE;
		
		BigDecimal [] zeros = new BigDecimal[n];
		for (int i = 0; i < n; i++)
			zeros[i] = BigDecimal.ZERO;
		v = new ColumnMatrix(n, zeros);
		p = new ColumnMatrix(n, zeros);
		
		r  = B;
		r0 = r;
		
		x = new ColumnMatrix(n, zeros);
		
		boolean converging = false;
		while(!converging)
		{
			iterationCounter++;
			
			rPrevious = r;
			pPrevious = p;
			vPrevious = v;
			xPrevious = x;

			rhoPrevious   = rho;
			omegaPrevious = omega;
			
			// ********************* Step 1
			rho = r0.transposeMultiplyToNumber(rPrevious);

			// ********************* Step 2
			beta = (rho.divide(rhoPrevious, precision, roundingMode)).multiply(alpha.divide(omegaPrevious, precision, roundingMode));
			
			// ********************* Step 3
			p = pPrevious.subtract(vPrevious.multiply(omegaPrevious)).multiply(beta).add(rPrevious);
			
			// ********************* Step 4
			v = A.multiply(p);
			
			// ********************* Step 5
			alpha = rho.divide(r0.transposeMultiplyToNumber(v), precision, roundingMode);
			
			// ********************* Step 6 - 7
			// Quit Test
			
			// ********************* Step 8
			s = rPrevious.subtract(v.multiply(alpha));
			
			// ********************* Step 9
			t = A.multiply(s);
			
			// ********************* Step 10
			omega = t.transposeMultiplyToNumber(s).divide(t.transposeMultiplyToNumber(t), precision, roundingMode);
			
			// ********************* Step 11
			x = xPrevious.add(p.multiply(alpha)).add(s.multiply(omega));
			
			// ********************* Step 12
			// Quit Test
			
			// ********************* Step 13
			r = s.subtract(t.multiply(omega));
			
			if(iterationCounter > 2*n)
				break;
			if(x.subtract(xPrevious).norm(NormType.UNIFORM).compareTo(EpsilonPrecision.getInstance().getEpsilon()) == -1)
				converging = true;
		}
		
		return converging;
	}
	
	public Matrix getX()
	{
		return this.x;
	}
}
