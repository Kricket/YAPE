package yape.yape.java;

import yape.math.primes.*;

/**
 * For quadratics of the form n^2 + an + b, where |a| < 1000 and |b| < 1000,
 * find a*b for the quadratic that produces the maximum number of primes for
 * consecutive values of n, starting with n=0.
 */
public class Euler27 {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		long max = 0;
		long A=0, B=0;
		PrimeLister.ensurePrimesUpTo(10000000);

		for(long b : PrimeLister.getPrimeList())
		{
			if(b > 1000)
				break;

			for(long a=999; a>-1000; a-=2)
			{
				// If there are integral roots, then there are at most 2 primes possible.
				// This is because the polynomial can be decomposed as (n-x1)(n-x2), for
				// which there are at most 2 values of n allowing this expression to be prime.
				if(polyHasIntegralRoots(a, b))
					continue;

				long newMax = makesMorePrimes(a, b, max);
				if(newMax > max)
				{
					max = newMax;
					A = a;
					B = b;
				}
			}
		}
		
		System.out.println("Polynomial n^2 + " + A + "n + " + B + " generates " + max + " primes.");
		System.out.println("Answer = " + A*B);
		System.out.println("runtime: " + (System.currentTimeMillis() - startTime) / 1000.0);
	}
	
	/**
	 * Determine if the polynomial n^2 + an + b generates more than p primes. If so,
	 * return the number of primes that it generates.
	 * @param a
	 * @param b
	 * @param p
	 * @return Either the number of primes generated, if >p, or 0 if <p.
	 */
	public static long makesMorePrimes(long a, long b, long p)
	{
		for(long n = p; n>0; n--)
		{
			long pn = Math.abs(evalPoly(a, b, n));
			if(pn < 2)
				return 0;
			if(PrimeLister.hasDivisor(pn))
				return 0;
		}
		
		while(true)
		{
			long pn = Math.abs(evalPoly(a, b, ++p));
			if(pn < 2)
				break;
			if(PrimeLister.hasDivisor(pn))
				break;
		}
		
		return p-1;
	}
	
	/**
	 * Determine if the roots of the polynomial are integers.
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean polyHasIntegralRoots(long a, long b)
	{
		long disc = a*a - 4*b;
		long sqrt = (long) Math.sqrt(disc);
		if(sqrt*sqrt != disc)
			return false; // discriminant is not a perfect square
		return ((a + sqrt) % 2) == 0;
	}

	/**
	 * Evaluate the polynomial n^2 + an + b
	 * @param a
	 * @param b
	 * @param n
	 * @return The value.
	 */
	public static long evalPoly(long a, long b, long n)
	{
		return n * (n + a) + b;
	}
}
