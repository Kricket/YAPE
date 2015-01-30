package yape.yape.java;

/**
 * Find the last ten digits of the sum
 * 1^1 + 2^2 + 3^3 + ... + 1000^1000
 */
public class Euler48
{
	
	public static void main(String[] args)
	{
		long total = 1;
		long mod = 10000000000L;
		
		for(long n=2; n<=1000; n++)
		{
			long pow = 1;
			for(long j=0; j<n; j++)
				pow = (pow*n) % mod;
			total += pow;
			total = total % mod;
		}
		
		System.out.println("Last digits are: " + total);
	}

}
