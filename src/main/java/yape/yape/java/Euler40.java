package yape.yape.java;

/**
 * For the decimal expansion 0.1234567891011121314...
 * Find the product of digits 1, 10, 100, 1000, 10000, 100000, 1000000
 * (= 1 * 1 * ...)
 */
public class Euler40 {
	
	public static final int MAX_POW_N = 7;
	/**
	 * For 10^n, powNStartsAt[n-1] = the digit where we start using
	 * n-digit numbers.
	 */
	private static long[] powNStartsAt = new long[MAX_POW_N];
	
	
	public static void main(String[] args) {
		setupPows();

		long d = 1;
		int total = 1;
		while(d <= 1000000)
		{
			total *= getNthDigit(d) - '0';
			d *= 10;
		}
		System.out.println("total = " + total);
	}
	
	
	/**
	 * Get the digit in the nth place after the decimal point (1-based).
	 * @param n
	 * @return
	 */
	private static char getNthDigit(long n) {
		int d = 0;
		while(n >= powNStartsAt[d])
		{
			d++;
			if(d == MAX_POW_N)
				return 0;
		}

		// d_n has d digits
		
		n = n - powNStartsAt[d-1];
		// n is now relative to the first index with d digits
		
		int dig = (int) n%d;
		n = n / d;
		// n is now the xth number away from 10^(d-1)
		
		n += Math.pow(10, d-1);
		String s = Long.toString(n);
		return s.charAt(dig);
	}

	
	/**
	 * Setup the table of indexes for each power of 10.
	 */
	private static void setupPows()
	{
		long pow10 = 1;
		powNStartsAt[0] = 1;
		for(int i=1; i<MAX_POW_N; i++)
		{
			powNStartsAt[i] = powNStartsAt[i-1] + (pow10 * 9 * i);
			pow10 *= 10;
		}
	}
}
