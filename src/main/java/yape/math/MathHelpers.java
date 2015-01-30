package yape.math;

public class MathHelpers {
	/**
	 * Find the GCD of two integers.
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b)
	{
		if(a < 0)
			a *= -1;
		if(b < 0)
			b *= -1;
		
		if(a > b)
			return _gcd(a, b);
		else
			return _gcd(b, a);
	}
	
	/**
	 * Find the GCD of two non-negative integers where a >= b.
	 * @param a
	 * @param b
	 * @return
	 */
	private static int _gcd(int a, int b)
	{
		if(b == 0)
			return a;
		return _gcd(b, a%b);
	}


	/**
	 * Get the factorial of n.
	 * @param n
	 * @return
	 */
	public static long factorial(long n)
	{
		long f = 1;
		while(n > 1)
		{
			f *= n;
			n--;
		}
		
		return f;
	}
	
	/**
	 * Get sum(i=0..n) (r^i)
	 * @param r
	 * @param n
	 * @return The sum.
	 */
	public static long geomSum(long r, int n)
	{
		return (long) ((Math.pow(r, n+1) - 1) / (r - 1));
	}
}
