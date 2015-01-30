package yape.yape.java;

/**
 * Find the number of combinations of n and r such that
 * (n choose r) > 1 million, and
 * n <= 100.
 */
public class Euler53 {
	
	/**
	 * So, here's the deal:
	 * 
	 * It is fairly easy to see that (n+1 choose r) > (n choose r). Thus, if we find
	 * a pair (n0,r) that work, we can immediately include all values n>=n0 as being
	 * solutions for this particular value of r, and continue to the next r.
	 * 
	 * n must be >= r, so we can talk about n as r+c, for some c >= 0.
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		long total = 0;
		for(long r=2; r<101; r++) {
			long c = 2;
			while(c+r < 101 && chooseNR(r, c) < 1000000)
				c++;
			
			if(c+r < 101)
				total += (101 - (c+r));
		}
		
		System.out.println("total is " + total + "\ntime = " + (System.currentTimeMillis() - startTime) * 0.001);
	}

	/**
	 * Calculate (n choose r), where n = r+c. As an optimization, we assume 0<r<n. Note
	 * that this won't work for very large binomial coefficients, but since the algorithm
	 * used here doesn't require us to go much further than 1 million, it's sufficient.
	 */
	public static long chooseNR(long r, long c) {
		long choose = r+1;
		for(long x=2; x<=c; x++)
		{
			choose *= (r+x);
			choose /= x; // x must be a factor, since we've covered all the possibilities
		}
		
		return choose;
	}
}
